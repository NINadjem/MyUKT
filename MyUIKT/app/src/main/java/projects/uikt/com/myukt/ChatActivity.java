package projects.uikt.com.myukt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;
    TextView sendButton;
    EditText writeMsgEdtx;
    Context context = ChatActivity.this;
    RecyclerView.Adapter adapter;
    LinearLayout linearLayout;
    SharedPreferences preferences;
    ArrayList<RecyclerAdapter.msgs> list;
    Bitmap my_avatar, my_partner_avatar;
    boolean imageConverted=false,list_ready=false;
    static getAllMsgs allMsgs;
     String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        preferences = getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
        recyclerView = findViewById(R.id.chat_recycler_view);
        progressBar = findViewById(R.id.progress_bar_chat);
        sendButton = findViewById(R.id.send_button);
        writeMsgEdtx = findViewById(R.id.msg_to_send);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        //id="26";
        allMsgs=new getAllMsgs();
        allMsgs.execute(id);
        String p=intent.getStringExtra("avatar");
        final SharedPreferences preferences=getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
        byte[] decodedString = Base64.decode(preferences.getString("avatar",""), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        my_avatar=decodedByte;
        decodedString = Base64.decode(p, Base64.DEFAULT);
        my_partner_avatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!writeMsgEdtx.getText().toString().isEmpty()) {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    list.add(new RecyclerAdapter.msgs(writeMsgEdtx.getText().toString(), formattedDate, "",
                            true));
                    adapter = new RecyclerAdapter(list, my_avatar, my_partner_avatar);
                    if(linearLayout.getVisibility()==View.VISIBLE)
                        linearLayout.setVisibility(View.GONE);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                    MyClasses.MSG msg=new MyClasses.MSG("",String.valueOf(preferences.getLong("id", 1)),
                            writeMsgEdtx.getText().toString(),"");
                    msg.setAdd_date(formattedDate);
                    writeMsgEdtx.setText("");
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                            createUrl(msg.getMsg(), String.valueOf(preferences.getLong("id", 1)), id, formattedDate), null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if(response.getInt("params")!=0){
                                            if(response.getInt("add_msg")==1){
                                                if(response.getInt("add_journal")==1){
                                                    if(response.getInt("add_sent")==1){
                                                        MyDbHelper dbHelper=new MyDbHelper(ChatActivity.this);
                                                        SQLiteDatabase database=dbHelper.getWritableDatabase();
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.MSG msg1=gson.fromJson(response.getJSONObject("msg").toString(),
                                                                MyClasses.MSG.class);
                                                        MyClasses.MsgSentTo sent=gson.fromJson(response.getJSONObject("msg_send_to").
                                                                        toString(), MyClasses.MsgSentTo.class);
                                                        MyClasses.Journal journal=gson.fromJson(response.getJSONObject("journal").
                                                                toString(), MyClasses.Journal.class);
                                                        dbHelper.insertAmsg(database,msg1);
                                                        dbHelper.insertAmsgSendTo(database,sent);
                                                        dbHelper.addTojournal(database,journal);
                                                        Snackbar.make(recyclerView, getString(R.string.msg_sent) ,
                                                                Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                                    }
                                                }else{

                                                }
                                            }else{
                                               //msg not sent
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Snackbar.make(recyclerView,getString(R.string.error_happend_retry) ,Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    });
                    mySinglton.getmInstance(ChatActivity.this).addToRequestQue(jsonObjectRequest);
                }
            }
        });
        linearLayout=findViewById(R.id.empty_list_txv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(intent.getStringExtra("name"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        list = new ArrayList<>();
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight,
                                       int oldBottom) {
                if (bottom < oldBottom) {
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                        }
                    });
                }
            }
        });
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                createSeen(id,String.valueOf(preferences.getLong("id", 1))), null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                MyDbHelper dbHelper=new MyDbHelper(ChatActivity.this);
                SQLiteDatabase database=dbHelper.getWritableDatabase();
                dbHelper.seeMsgs(database,id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mySinglton.getmInstance(ChatActivity.this).addToRequestQue(jsonObjectRequest);

    }

    private String createSeen(String id_user,String id) {
        String result=null;
        Uri uri=Uri.parse(MyClasses.INSERT_A_SEEN).buildUpon()
                .appendQueryParameter("id",id).appendQueryParameter("id_sender",id_user)
                .build();
        result=uri.toString();
        return  result;

    }

    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.mViewHolder> {
        ArrayList<RecyclerAdapter.msgs> list;
        final Bitmap user_avatar, his_partner_avatar;

        public RecyclerAdapter(ArrayList<RecyclerAdapter.msgs> list, Bitmap user_avatar, Bitmap his_partner_avatar) {
            this.list = list;
            this.his_partner_avatar = his_partner_avatar;
            this.user_avatar = user_avatar;
        }

        @Override
        public RecyclerAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chat_item, parent, false);
            RecyclerAdapter.mViewHolder mViewHolder = new RecyclerAdapter.mViewHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.mViewHolder holder, int position) {
            if (!list.get(position).isIwroteThisMsg()) {
                holder.partner_avatar.setImageBitmap(his_partner_avatar);
                holder.partner_msg.setText(list.get(position).getMsg());
                holder.partner_send_date.setText(list.get(position).getSend_date());
                holder.my_partner_layout.setVisibility(View.VISIBLE);
                holder.my_layout.setVisibility(View.GONE);
            } else {
                holder.my_avatar.setImageBitmap(user_avatar);
                holder.my_msg.setText(list.get(position).getMsg());
                holder.my_send_date.setText(list.get(position).getSend_date());
                if (!list.get(position).getSeen_date().equals(""))
                    holder.seen_date.append(" " + list.get(position).getSeen_date());
                else holder.seen_date.setVisibility(View.GONE);
                holder.my_layout.setVisibility(View.VISIBLE);
                holder.my_partner_layout.setVisibility(View.GONE);
            }

        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public static class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView partner_avatar, my_avatar;
            TextView partner_msg, my_msg, partner_send_date, my_send_date, seen_date;
            LinearLayout my_layout, my_partner_layout;

            public mViewHolder(View itemView) {
                super(itemView);
                my_partner_layout = itemView.findViewById(R.id.layout_partner_msg);
                my_layout = itemView.findViewById(R.id.layout_my_msg);
                partner_avatar = itemView.findViewById(R.id.partner_avatar);
                my_avatar = itemView.findViewById(R.id.my_avatar);
                partner_msg = itemView.findViewById(R.id.partner_msg_txvw);
                my_msg = itemView.findViewById(R.id.my_msg_txvw);
                partner_send_date = itemView.findViewById(R.id.partner_send_time_txvw);
                my_send_date = itemView.findViewById(R.id.my_send_time_txvw);
                seen_date = itemView.findViewById(R.id.seen_time_txvw);
            }
        }

        public static class msgs {
            String msg, send_date, seen_date;
            boolean IwroteThisMsg;

            public msgs(String msg, String send_date, String seen_date, boolean iwroteThisMsg) {
                this.msg = msg;
                this.send_date = send_date;
                this.seen_date = seen_date;
                IwroteThisMsg = iwroteThisMsg;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getSend_date() {
                return send_date;
            }

            public void setSend_date(String send_date) {
                this.send_date = send_date;
            }

            public String getSeen_date() {
                return seen_date;
            }

            public void setSeen_date(String seen_date) {
                this.seen_date = seen_date;
            }

            public boolean isIwroteThisMsg() {
                return IwroteThisMsg;
            }

            public void setIwroteThisMsg(boolean iwroteThisMsg) {
                IwroteThisMsg = iwroteThisMsg;
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }
     class getAllMsgs extends AsyncTask<String,Void,ArrayList<RecyclerAdapter.msgs>>{

        @Override
        protected ArrayList<RecyclerAdapter.msgs> doInBackground(String... integers) {
            MyDbHelper dbHelper=new MyDbHelper(ChatActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
            String id= integers[0];
            return dbHelper.getConversation(database,id,String.valueOf(preferences.getLong("id", 1)));

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<RecyclerAdapter.msgs> msgs) {
            super.onPostExecute(msgs);
            progressBar.setVisibility(View.GONE);
            list=msgs;
            list_ready=true;
            if(msgs.size()==0&&imageConverted){
                linearLayout.setVisibility(View.VISIBLE);
            }else{
                RecyclerAdapter adapter=new RecyclerAdapter(list,my_avatar,my_partner_avatar);
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }

        }
    }

    String createUrl(String msg,String id,String id_receiver,String add_date){
    String result;
    Uri uri=Uri.parse(MyClasses.INSERT_A_MSG_URL).buildUpon().appendQueryParameter("msg",msg)
            .appendQueryParameter("id",id).appendQueryParameter("id_receiver",id_receiver)
            .appendQueryParameter("date",add_date).build();
    result=uri.toString();
    return  result;
}
    }



package projects.uikt.com.myukt;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context = MainActivity.this;
    android.support.v7.widget.SearchView searchView;
    RecyclerView.Adapter adapter;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<UserItem> list;
    ArrayList<String> emails=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        recyclerView = findViewById(R.id.absences_recycler_view);
        progressBar = findViewById(R.id.progress_bar_absences);
        final ArrayList<UserItem> users=new ArrayList<>();

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, MyClasses.GET_ALL_USERS_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getInt("result")>=1){
                                JSONArray jsonArray=response.getJSONArray("user");
                                String json;
                                for(int i=0;i<jsonArray.length();i++){
                                    int userType=jsonArray.getJSONObject(i).getInt("user_type");
                                    json=jsonArray.getJSONObject(i).toString();
                                    String function="";
                                    String userName=jsonArray.getJSONObject(i).getString("last_name")+" "+
                                            jsonArray.getJSONObject(i).getString("first_name");
                                    switch (userType){
                                        case 0:{
                                            function=getString(R.string.admin_label);
                                            break;
                                        }
                                        case 1:{
                                            if(jsonArray.getJSONObject(i).getString("sexe").equals("f")||
                                                    jsonArray.getJSONObject(i).getString("sexe").equals("F"))
                                                function=getString(R.string.student_female);
                                            else function=getString(R.string.student_male);
                                            break;
                                        }
                                        case 2:{
                                            if(jsonArray.getJSONObject(i).getString("sexe").equals("f")||
                                                    jsonArray.getJSONObject(i).getString("sexe").equals("F"))
                                                function=getString(R.string.teacher_female);
                                            else function=getString(R.string.teacher_male);
                                            break;
                                        }
                                        case 4:{
                                            if(jsonArray.getJSONObject(i).getString("sexe").equals("f")||
                                                    jsonArray.getJSONObject(i).getString("sexe").equals("F"))
                                                function=getString(R.string.student_female);
                                            else function=getString(R.string.student_male);
                                            break;
                                        }
                                        case 5:{
                                            if(jsonArray.getJSONObject(i).getString("sexe").equals("f")||
                                                    jsonArray.getJSONObject(i).getString("sexe").equals("F"))
                                                function=getString(R.string.teacher_female);
                                            else function=getString(R.string.teacher_male);
                                            break;
                                        }
                                    }

                                    emails.add(jsonArray.getJSONObject(i).getString("email"));
                                    UserItem userItem=new UserItem("0",
                                            userName,function,null,json,userType);
                                    users.add(userItem);

                                }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        list=users;
                        ImageRequest imageRequest;
                        for (String a:emails){

                            final String email = a;
                            imageRequest = new ImageRequest(MyClasses.AVATARS_URL + a + MyClasses.AVATARS_FORMAT, new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    list.get(emails.indexOf(email)).setAvatar(response);
                                    if (emails.indexOf(email) == emails.size() - 1) {
                                        progressBar.setVisibility(View.GONE);
                                        recyclerView.setVisibility(View.VISIBLE);
                                        layoutManager=new LinearLayoutManager(MainActivity.this);
                                        recyclerView.setLayoutManager(layoutManager);
                                        recyclerView.setHasFixedSize(false);
                                        adapter=new UserAdapter(list,MainActivity.this);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }
                            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    Log.d("GetUsersAvatars:","oneRROR");
                                }
                            });
                            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(imageRequest);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(progressBar, R.string.error_happend_retry,Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        mySinglton.getmInstance(this).addToRequestQue(jsonObjectRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chercher_un_utilisateur,menu);
        final MenuItem search_item=menu.findItem(R.id.searchMenuItem);
        searchView= (android.support.v7.widget.SearchView) search_item.getActionView();
        final EditText editText= searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editText.setHint("Tapez nom ou prénom");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String query=editText.getText().toString().trim();
                ArrayList<UserItem> itemArrayList=filter(list,query);
                layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                adapter=new UserAdapter(itemArrayList,MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });
      /*  searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified())
                    searchView.setIconified(true);
                search_item.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
        return super.onCreateOptionsMenu(menu);
    }
    private ArrayList<UserItem> filter(ArrayList<UserItem> items, String query){
        ArrayList<UserItem> result=new ArrayList<>();
        String lower=query.toLowerCase(),upper=query.toUpperCase();
        String first;
        if(query.length()>=1){
        first=String.valueOf(query.charAt(0)).toUpperCase()
                +query.substring(1);}else{
            first="";
        }
        if(items.size()!=0)
        for(UserItem item:items){
            if(item.getUserName().contains(query)||item.getUserName().contains(lower)||item.getUserName().contains(upper)
                    ||item.getUserName().contains(first)){
                result.add(item);

            }
        }
        return result;
    }

    class UserItem{
       String id,userName,function,json;
       int userType;
       Bitmap avatar;

        public UserItem(String id, String userName, String function, Bitmap avatar,String json ,int userType) {
            this.id = id;
            this.userName = userName;
            this.function = function;
            this.avatar = avatar;
            this.json=json;
            this.userType=userType;
        }

        public String BitmapConverted (){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            avatar.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            return encodedImage;
        }
        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public Bitmap getAvatar() {
            return avatar;
        }

        public void setAvatar(Bitmap avatar) {
            this.avatar = avatar;
        }
    }
    public static class UserAdapter extends RecyclerView.Adapter<UserAdapter.mCommentsHolder>{
        ArrayList<UserItem> userItems;
        Context context;
        public UserAdapter(ArrayList<UserItem> userItems,Context context1) {
            this.userItems = userItems;
            context=context1;
        }

        @Override
        public mCommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
            mCommentsHolder mViewHolder =new mCommentsHolder(view,context,userItems);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(final mCommentsHolder holder, int position) {
            holder.avatar.setImageBitmap(userItems.get(position).getAvatar());
            holder.userName.setText(userItems.get(position).getUserName());
            holder.function.setText(userItems.get(position).getFunction());
        }

        @Override
        public int getItemCount() {
            if(userItems!=null)
                return userItems.size();
            else return 0;
        }

        public static class mCommentsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            //LinearLayout container;
            TextView userName,function;
            Context context;
            ArrayList<UserItem> userItems;
            public mCommentsHolder(View itemView, Context context,ArrayList<UserItem> userItems) {
                super(itemView);
                this.userItems=userItems;
                this.context=context;
                itemView.setOnClickListener(this);
                this.avatar = itemView.findViewById(R.id.user_item_avatar);
                this.userName = itemView.findViewById(R.id.user_item_name);
                this.function = itemView.findViewById(R.id.user_item_fun);
                //this.container=itemView.findViewById(R.id.container_user_item);

            }

            @Override
            public void onClick(View v) {


                    int position=getAdapterPosition();
                    UserItem userItem=userItems.get(position);
                    Intent intent;
                    switch (userItem.getUserType()) {
                        case 0:{ intent = new Intent(context, AdminProfilActivity.class);break;}
                        case 1:{ intent = new Intent(context, ProfilActivity.class);break;}
                        case 4:{ intent = new Intent(context, ProfilActivity.class);break;}
                        default:{intent = new Intent(context, TeacherProfilActivity.class);
                        intent.putExtra("user_type",userItem.getUserType());
                        break;}
                    }
                    intent.putExtra("json",userItem.getJson());
                    this.context.startActivity(intent);

            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(   MainActivity .this,HomeActivity.class));
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }
}

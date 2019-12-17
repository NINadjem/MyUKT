package projects.uikt.com.myukt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
Spinner AddTypeSpinner,freqActivitySpinner,receiverSpinner,newsTypeSpinner,oldSessionSpinner,newSessionSpinner,
        /*newsVisibilitySpinner,newsVoteSpinner,*/consultationSessionSpinner,examTypeSpinner,ensSpinner,moduleSpinner,
        grpSpinner,meetingPresenceSpinner,exam_grp_spinner;
ArrayList<MyClasses.SpinnerItem> addTypeList,freqTypeList,newsTypeList,oldSessionsList,newsSessionsList,
                                     /*newsVisibilityList,newsVoteList,*/examTypeList,sessionList,ensList,moduleList,grpList;
    MyClasses.SpinnerAdapter mAdapter;
    ArrayList<MyClasses.ProfilSpinnerItem> receiverList;
    EditText /*nameActivity,placeActivity,noteActivity,beginDateActivity,endDateActivity,timeActivity,*/msgEdtx,
            /*reminderNameEdtx,reminderDesEdtx,reminderTimeEdtx,*/docNameEdtx,docReasonEdtx,titleNewsEdtx,
            contentNewsEdtx,consultationDateEdtx,examDateEdtx,holydayDateEdtx,meetingDateEdtx;
    //TextView endDateActivityTvw;
    GridLayout /*activityAdd,*/newsAdd,msgAdd,docrReqAdd/*,reminderAdd*/;
    LinearLayout changeOfSessionLayout,consultationLayout,examLayout,holyDayLayout,marksDesLayout,newsVisibilityVote,meetingLayout/*,procedureLayout,
                      strikeLayout*/;
    SharedPreferences preferences;
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        preferences=getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        receiverSpinner=findViewById(R.id.spinner_receiver_add_activity);
        initList();
        /*MyDbHelper myDbHelper=new MyDbHelper(AddActivity.this);
        myDbHelper.getReadableDatabase();*/
        newsVisibilityVote=findViewById(R.id.news_visibility_vote);
        exam_grp_spinner=findViewById(R.id.spinner_exam_grp_spinner_add_activity);
        meetingPresenceSpinner=findViewById(R.id.spinner_presence_add_activity);
        holyDayLayout=findViewById(R.id.layout_holyday_items_add_activity);
        marksDesLayout=findViewById(R.id.layout_marksD_items_add_activity);
        meetingLayout=findViewById(R.id.layout_meeting_items_add_activity);
        meetingDateEdtx=findViewById(R.id.meeting_date_edx);
        ensSpinner=findViewById(R.id.spinner_teacher_marks_d_marksD_add_activity);
        moduleSpinner=findViewById(R.id.spinner_module_marksD_add_activity);
        grpSpinner=findViewById(R.id.spinner_grp_marksD_add_activity);
        examDateEdtx=findViewById(R.id.exam_date_edx);
        holydayDateEdtx=findViewById(R.id.holyday_date_edx);
        examLayout=findViewById(R.id.layout_exam_items_add_activity);
        examTypeSpinner=findViewById(R.id.spinner_exam_type_spinner_add_activity);
        consultationSessionSpinner=findViewById(R.id.spinner_consultation_session_add_activity);
        consultationDateEdtx=findViewById(R.id.consultation_date_edx);
        consultationLayout=findViewById(R.id.layout_consultation_items_add_activity);
        changeOfSessionLayout=findViewById(R.id.layout_change_of_session_items_add_activity);
        /*newsVisibilitySpinner=findViewById(R.id.spinner_news_visibility_add_activity);
        newsVoteSpinner=findViewById(R.id.spinner_news_vote_add_activity);*/
        oldSessionSpinner=findViewById(R.id.spinner_old_session_add_activity);
        newSessionSpinner=findViewById(R.id.spinner_new_session_add_activity);
        contentNewsEdtx=findViewById(R.id.news_content_edx);
        titleNewsEdtx=findViewById(R.id.news_title_edx);
        newsTypeSpinner=findViewById(R.id.spinner_news_type_add_activity);
        docNameEdtx= findViewById(R.id.doc_name_edx);
        docReasonEdtx= findViewById(R.id.doc_reason_edx);
        /*reminderDesEdtx= findViewById(R.id.reminder_name_edx);
        reminderNameEdtx= findViewById(R.id.reminder_des_edx);
        reminderTimeEdtx= findViewById(R.id.reminder_time_edx);
        nameActivity=findViewById(R.id.activity_name_edx);
        placeActivity=findViewById(R.id.activity_place_edx);
        noteActivity=findViewById(R.id.activity_note_edx);
        beginDateActivity=findViewById(R.id.activity_begin_date_edx);
        endDateActivity=findViewById(R.id.activity_end_date_edx);
        timeActivity=findViewById(R.id.activity_time_edx);
        endDateActivityTvw=findViewById(R.id.activity_end_date_txv);
        activityAdd=findViewById(R.id.add_an_activity_layout);*/
        AddTypeSpinner = findViewById(R.id.spinner_add_type_add_activity);
        newsAdd=findViewById(R.id.add_a_news_layout);
        msgAdd=findViewById(R.id.add_a_msg_layout);
        docrReqAdd=findViewById(R.id.add_a_doc_req_layout);
        //reminderAdd=findViewById(R.id.add_a_reminder_layout);

        msgEdtx=findViewById(R.id.msg_edx);
        mAdapter = new MyClasses.SpinnerAdapter(this, addTypeList);
        AddTypeSpinner.setAdapter(mAdapter);
        AddTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              /*  MyClasses.SpinnerItem clickedItem = (MyClasses.SpinnerItem) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getItemName();*/
                switch (position){
                    case 0:{newsAdd.setVisibility(View.VISIBLE);
                    //activityAdd.setVisibility(View.GONE);
                    msgAdd.setVisibility(View.GONE);
                    docrReqAdd.setVisibility(View.GONE);
                    //reminderAdd.setVisibility(View.GONE);
                    break;}
                    case 1:{
                        newsAdd.setVisibility(View.GONE);
                        //activityAdd.setVisibility(View.GONE);
                        msgAdd.setVisibility(View.VISIBLE);
                        docrReqAdd.setVisibility(View.GONE);
                        //reminderAdd.setVisibility(View.GONE);
                        break;}
                    case 2:{newsAdd.setVisibility(View.GONE);
                        //activityAdd.setVisibility(View.GONE);
                        msgAdd.setVisibility(View.GONE);
                        docrReqAdd.setVisibility(View.VISIBLE);
                        //reminderAdd.setVisibility(View.GONE);
                        break;}
                   /* case 4:{newsAdd.setVisibility(View.GONE);
                        //activityAdd.setVisibility(View.GONE);
                        msgAdd.setVisibility(View.GONE);
                        docrReqAdd.setVisibility(View.GONE);
                        //reminderAdd.setVisibility(View.VISIBLE);
                        break;}
                    default:{newsAdd.setVisibility(View.GONE);
                        //activityAdd.setVisibility(View.VISIBLE);
                        msgAdd.setVisibility(View.GONE);
                        docrReqAdd.setVisibility(View.GONE);
                        //reminderAdd.setVisibility(View.GONE);
                        break;}*/
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


       /* freqActivitySpinner.setAdapter(new MyClasses.SpinnerAdapter(this,freqTypeList));
       freqActivitySpinner=findViewById(R.id.freq_spinner_add_activity);
        freqActivitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              /*  MyClasses.SpinnerItem clickedItem = (MyClasses.SpinnerItem) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getItemName();
                if(position!=0){
                    endDateActivityTvw.setVisibility(View.VISIBLE);
                    endDateActivity.setVisibility(View.VISIBLE);
                }else{
                    endDateActivityTvw.setVisibility(View.GONE);
                    endDateActivity.setVisibility(View.GONE);


                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        newsTypeSpinner.setAdapter(new MyClasses.SpinnerAdapter(this,newsTypeList));
        newsTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              /*  MyClasses.SpinnerItem clickedItem = (MyClasses.SpinnerItem) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getItemName();*/
              switch (position){
                  case 0:{
                      changeOfSessionLayout.setVisibility(View.GONE);
                      consultationLayout.setVisibility(View.GONE);
                      examLayout.setVisibility(View.GONE);
                      holyDayLayout.setVisibility(View.GONE);
                      marksDesLayout.setVisibility(View.GONE);
                      meetingLayout.setVisibility(View.GONE);
                      newsVisibilityVote.setVisibility(View.VISIBLE);
                      break;}
                  case 1:{
                      newsVisibilityVote.setVisibility(View.GONE);
                      changeOfSessionLayout.setVisibility(View.GONE);
                      consultationLayout.setVisibility(View.GONE);
                      examLayout.setVisibility(View.GONE);
                      holyDayLayout.setVisibility(View.GONE);
                      marksDesLayout.setVisibility(View.VISIBLE);
                      meetingLayout.setVisibility(View.GONE);

                      break;
                  }
                  case 2:{
                      changeOfSessionLayout.setVisibility(View.GONE);
                      consultationLayout.setVisibility(View.GONE);
                      examLayout.setVisibility(View.VISIBLE);
                      holyDayLayout.setVisibility(View.GONE);
                      marksDesLayout.setVisibility(View.GONE);
                      meetingLayout.setVisibility(View.GONE);
                      newsVisibilityVote.setVisibility(View.GONE);
                      break;}
                  case 3:{
                      changeOfSessionLayout.setVisibility(View.GONE);
                      consultationLayout.setVisibility(View.VISIBLE);
                      examLayout.setVisibility(View.GONE);
                      holyDayLayout.setVisibility(View.GONE);
                      marksDesLayout.setVisibility(View.GONE);
                      meetingLayout.setVisibility(View.GONE);

                      break;}
                  case 4:{
                      changeOfSessionLayout.setVisibility(View.GONE);
                      consultationLayout.setVisibility(View.GONE);
                      examLayout.setVisibility(View.GONE);
                      holyDayLayout.setVisibility(View.GONE);
                      marksDesLayout.setVisibility(View.GONE);
                      meetingLayout.setVisibility(View.VISIBLE);

                      break;}
                  case 5:{
                      changeOfSessionLayout.setVisibility(View.GONE);
                      consultationLayout.setVisibility(View.GONE);
                      examLayout.setVisibility(View.GONE);
                      holyDayLayout.setVisibility(View.VISIBLE);
                      marksDesLayout.setVisibility(View.GONE);
                      meetingLayout.setVisibility(View.GONE);

                      break;}
                  case 6:{
                      changeOfSessionLayout.setVisibility(View.VISIBLE);
                      consultationLayout.setVisibility(View.GONE);
                      examLayout.setVisibility(View.GONE);
                      holyDayLayout.setVisibility(View.GONE);
                      marksDesLayout.setVisibility(View.GONE);
                      meetingLayout.setVisibility(View.GONE);
                      /*procedureLayout.setVisibility(View.GONE);
                      strikeLayout.setVisibility(View.GONE);*/
                      break;}
              }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent intent=getIntent();
        type=intent.getIntExtra("add_type",0);
        switch (type){
            case 2: AddTypeSpinner.setSelection(1);break;
            case 3: AddTypeSpinner.setSelection(2);break;
            case 4:AddTypeSpinner.setSelection(0);
                newsTypeSpinner.setSelection(newsTypeSpinner.getLastVisiblePosition());break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String id= String.valueOf(preferences.getLong("id",0));
        switch (AddTypeSpinner.getSelectedItemPosition()){
            case 0:{
                //nvl

                break;
            }
            case 1:{
                //msg
                String msg=msgEdtx.getText().toString();
                if(!msg.isEmpty()){
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    Uri uri=Uri.parse(MyClasses.WriteAmsg).buildUpon().appendQueryParameter("id_user",id)
                            .appendQueryParameter("msg",msg).appendQueryParameter("date",formattedDate)
                            .appendQueryParameter("id_receiver",receiverList.get(receiverSpinner.getSelectedItemPosition())
                            .getId()).build();
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                            uri.toString(), null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if(response.getInt("done")==1) {
                                            GsonBuilder gsonBuilder = new GsonBuilder();
                                            Gson gson = gsonBuilder.create();
                                            MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
                                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                                            MyClasses.MSG msg1=gson.fromJson(response.getJSONObject("msg").toString(),
                                                    MyClasses.MSG.class);
                                            dbHelper.insertAmsg(db,msg1);
                                            MyClasses.Journal journal=gson.fromJson(response.getJSONObject("journal").toString(),
                                                    MyClasses.Journal.class);
                                           dbHelper.addTojournal(db,journal);
                                           MyClasses.MsgSentTo msgSentTo=gson.fromJson(response.getJSONObject("msg_send_to").toString(),
                                                   MyClasses.MsgSentTo.class);
                                           dbHelper.insertAmsgSendTo(db,msgSentTo);
                                        dbHelper.close();
                                        Snackbar.make(msgEdtx, R.string.msg_sent,
                                                Snackbar.LENGTH_LONG).
                                                setAction("Action", null).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(docReasonEdtx, R.string.error_happend_retry, Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    }
                });
                mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);

                }else{
                    Snackbar.make(msgEdtx, "Vous devez ecrire au moin un mot !"
                            ,
                            Snackbar.LENGTH_LONG).
                            setAction("Action", null).show();
                }
                break;
            }
            case 2:{
                //doc
                if(!docNameEdtx.getText().toString().isEmpty()){
                    if(!docReasonEdtx.getText().toString().isEmpty()){
                        String doc=docNameEdtx.getText().toString();
                        String reason=docReasonEdtx.getText().toString();

                        Uri uri=Uri.parse(MyClasses.addDocUrl).buildUpon().appendQueryParameter("id",id)
                                .appendQueryParameter("doc",doc)
                                .appendQueryParameter("reason",reason)
                                .build();
                        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                                uri.toString(), null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            if(response.getInt("done")==1){
                                                GsonBuilder gsonBuilder = new GsonBuilder();
                                                Gson gson = gsonBuilder.create();
                                                MyDbHelper dbHelper=new MyDbHelper(getApplicationContext());
                                                SQLiteDatabase db=dbHelper.getWritableDatabase();
                                                MyClasses.DocumentRequest documentRequest=gson.fromJson(
                                                        response.getJSONObject("doc").toString(),
                                                        MyClasses.DocumentRequest.class
                                                );
                                                MyClasses.Journal journal=gson.fromJson(
                                                        response.getJSONObject("journal").toString(),
                                                        MyClasses.Journal.class
                                                );
                                                dbHelper.addAdoc(db,documentRequest);
                                                dbHelper.addTojournal(db,journal);
                                                dbHelper.close();
                                                Snackbar.make(docReasonEdtx, "Votre demande est ajouté avec succées",
                                                        Snackbar.LENGTH_LONG).
                                                        setAction("Action", null).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Snackbar.make(docReasonEdtx, R.string.error_happend_retry, Snackbar.LENGTH_LONG).
                                        setAction("Action", null).show();
                            }
                        });
                        mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);

                    }else{
                        Snackbar.make(docReasonEdtx, "Vous devez introduire la raison d'extraction du document",
                                Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    }
                }else{
                    Snackbar.make(docReasonEdtx, "Vous devez d'abord nommer le document",
                            Snackbar.LENGTH_LONG).
                            setAction("Action", null).show();
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initList() {
        addTypeList = new ArrayList<>();
       // addTypeList.add(new MyClasses.SpinnerItem(getString(R.string.add_an_activity), R.drawable.icons8schedule32));
        addTypeList.add(new MyClasses.SpinnerItem(getString(R.string.add_a_new), R.drawable.icons8news32));
        addTypeList.add(new MyClasses.SpinnerItem(getString(R.string.write_a_new_msg), R.drawable.icons8gmail32));
        addTypeList.add(new MyClasses.SpinnerItem(getString(R.string.write_a_new_req), R.drawable.icons8documents32));
        //addTypeList.add(new MyClasses.SpinnerItem(getString(R.string.add_a_reminder), R.drawable.icons8alarmclock32));
        /*freqTypeList= new ArrayList<>();
        freqTypeList.add(new MyClasses.SpinnerItem(getString(R.string.just_once), R.drawable.icons8rpterunefois32));
        freqTypeList.add(new MyClasses.SpinnerItem(getString(R.string.daily), R.drawable.icons8rpter32));
        freqTypeList.add(new MyClasses.SpinnerItem(getString(R.string.weekly), R.drawable.icons8calendrier726));
        freqTypeList.add(new MyClasses.SpinnerItem(getString(R.string.monthly), R.drawable.icons8vuedumois26));*/

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST
                , getUserUrl(String.valueOf(preferences.getLong("id",0)))
                , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ( response.getInt("result") > 0) {
                                JSONArray array=response.getJSONArray("users");
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                final ArrayList<MyClasses.ProfilSpinnerItem> items=new ArrayList<>();
                                MyClasses.ProfilSpinnerItem item;
                                for(int i=0;i<array.length();i++){
                                  item=gson.fromJson(array.getJSONObject(i).toString(),MyClasses.ProfilSpinnerItem.class)  ;
                                  items.add(item);
                                  final int position=i;
                                  String email=array.getJSONObject(i).getString("email");
                                    ImageRequest imageRequest = new ImageRequest(MyClasses.AVATARS_URL + email +
                                            MyClasses.AVATARS_FORMAT, new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap response) {
                                            items.get(position).setImage(response);
                                        }
                                    }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                                            new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            items.get(position).setImage(BitmapFactory.decodeResource(AddActivity.this.getResources(),
                                                    R.drawable.logo128x128));
                                            error.printStackTrace();
                                        }
                                    });
                                    mySinglton.getmInstance(getApplicationContext()).addToRequestQue(imageRequest);
                                }

                                receiverList=items;
                                receiverSpinner.setAdapter(new MyClasses.SpinnerProfilAdapter(AddActivity.this,receiverList));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
        newsTypeList=new ArrayList<>();
        newsTypeList.add(new MyClasses.SpinnerItem(getString(R.string.news), R.drawable.icons8nouvelles26gray));
        if(preferences.getInt("user_type",0)==2&&preferences.getInt("user_type",0)==5) {
            newsTypeList.add(new MyClasses.SpinnerItem(getString(R.string.marks_des), R.drawable.icons8examen24));
            newsTypeList.add(new MyClasses.SpinnerItem(getString(R.string.exam), R.drawable.icons8testpartiellementrus24));
            //newsTypeList.add(new MyClasses.SpinnerItem(getString(R.string.strike), R.drawable.icons8strike26));
            newsTypeList.add(new MyClasses.SpinnerItem(getString(R.string.consultation), R.drawable.icons8voirlaproprit26));
            //newsTypeList.add(new MyClasses.SpinnerItem(getString(R.string.meeting), R.drawable.icons8usergroups26));
            if(preferences.getInt("user_type",0)==5)
            newsTypeList.add(new MyClasses.SpinnerItem(getString(R.string.holyday), R.drawable.icons8baindesoleil24));
            newsTypeList.add(new MyClasses.SpinnerItem(getString(R.string.change_of_session), R.drawable.icons8remplacer24gris));
            //newsTypeList.add(new MyClasses.SpinnerItem(getString(R.string.pedag_procedu), R.drawable.icons8resume24));
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }
    String getUserUrl(String id){
        Uri uri=Uri.parse(MyClasses.GETUSERS).buildUpon().appendQueryParameter("id",id).build();
        return  uri.toString();
    }
}

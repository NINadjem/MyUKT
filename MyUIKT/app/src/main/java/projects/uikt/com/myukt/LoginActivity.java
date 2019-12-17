package projects.uikt.com.myukt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {
    private EditText myEmailEDT,myPasswordEDT;
    private Button mySignInBTN;
    private TextView myForgotPasswordTXV;
    private ProgressBar myProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*MyClasses.create_a_notification(3,2,"malika : salem imen !","malika a envoyer un message",
                this,"ghjkl");*/
        SharedPreferences preferences=getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
        if(!preferences.getBoolean("database_created",false)){
            MyDbHelper helper=new MyDbHelper(LoginActivity.this);
            SQLiteDatabase database=helper.getReadableDatabase();
            helper.close();
            Intent intent=new Intent(LoginActivity.this, MyClasses.FillDatabaseWithStableInfo.class);
            startService(intent);
        }
        if(preferences.getBoolean("is_already_connected",false))
            {
            startActivity(new Intent(LoginActivity.this,WelcomeActivity.class));
        }else{
            setViews();}


    }
    void setViews(){
        myEmailEDT=findViewById(R.id.emailEditTextLogIn);
        myPasswordEDT=findViewById(R.id.passwordEditTextLogIn);
        mySignInBTN=findViewById(R.id.signInButtonLogIn);
        myForgotPasswordTXV=findViewById(R.id.forgotPasswordTextViewLogIn);
        myProgressBar=findViewById(R.id.progressBarLogIn);
        mySignInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myEmailEDT.getText().length()!=0){
                    if(myEmailEDT.getText().toString().contains("@")&&myEmailEDT.getText().toString().contains(".")){
                        if(myPasswordEDT.getText().length()!=0){
                            // everything is fine
                            mySignInBTN.setVisibility(View.GONE);
                            myPasswordEDT.setEnabled(false);
                            myEmailEDT.setEnabled(false);
                            myProgressBar.setVisibility(View.VISIBLE);
                            myForgotPasswordTXV.setVisibility(View.GONE);
                            //We connect to the server
                             String email=myEmailEDT.getText().toString().trim();
                             JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                                    createUrl(email), null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if(response.getInt( MyClasses.PARAMS)==1) {
                                            if(response.getInt( MyClasses.RESULT)>0) {
                                                if(response.getInt( "admin")==0){
                                                GsonBuilder gsonBuilder = new GsonBuilder();
                                                Gson gson = gsonBuilder.create();
                                                String password=myPasswordEDT.getText().toString().trim();
                                                switch (response.getJSONObject(MyClasses.User.TABLE_NAME).getInt(MyClasses.User.USER_TYPE))
                                                {
                                                    case 1:{//student
                                                        MyClasses.Student user=gson.fromJson(response.getJSONObject(MyClasses.User.TABLE_NAME).toString(),MyClasses.Student.class);
                                                        if(user.getPassword().equals(password)){
                                                        saveInSharred(user,null,null,null,0,0,0,1,
                                                                Integer.parseInt(response.getJSONObject(MyClasses.User.TABLE_NAME).getString("id_groupe")));
                                                        Intent myIntent=new Intent(LoginActivity.this,WelcomeActivity.class);
                                                         startActivity(myIntent);
                                                        }else{
                                                            myProgressBar.setVisibility(View.GONE);
                                                            mySignInBTN.setVisibility(View.VISIBLE);
                                                            myPasswordEDT.setEnabled(true);
                                                            myEmailEDT.setEnabled(true);
                                                            Snackbar.make(mySignInBTN, R.string.error_password, Snackbar.LENGTH_LONG).
                                                                    setAction("Action", null).show();
                                                        }
                                                        break;}
                                                    case 2:{//treacher
                                                        MyClasses.Teacher user=gson.fromJson(response.getJSONObject(MyClasses.User.TABLE_NAME).toString(),MyClasses.Teacher.class);
                                                        if(user.getPassword().equals(password)){
                                                            saveInSharred(null,user,null,null,
                                                                    Integer.parseInt(response.getJSONObject(MyClasses.User.TABLE_NAME).getString("id_specialty"))
                                                                    ,0,0,2,
                                                                    0);
                                                            Intent myIntent=new Intent(LoginActivity.this,WelcomeActivity.class);
                                                        startActivity(myIntent);
                                                    }else{
                                                        myProgressBar.setVisibility(View.GONE);
                                                        mySignInBTN.setVisibility(View.VISIBLE);
                                                        myPasswordEDT.setEnabled(true);
                                                        myEmailEDT.setEnabled(true);
                                                        Snackbar.make(mySignInBTN, R.string.error_password, Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                        break;}
                                                    case 4:{//delegue
                                                        MyClasses.Delegue user=gson.fromJson(response.getJSONObject(MyClasses.User.TABLE_NAME).toString(),MyClasses.Delegue.class);
                                                        if(user.getPassword().equals(password)){
                                                            saveInSharred(null,null,user,null,
                                                                    0,Integer.parseInt(response.getJSONObject(MyClasses.User.TABLE_NAME).getString("id_promo"))
                                                                    ,0,4,
                                                                    Integer.parseInt(response.getJSONObject(MyClasses.User.TABLE_NAME).getString("id_groupe")));
                                                            Intent myIntent=new Intent(LoginActivity.this,WelcomeActivity.class);
                                                            startActivity(myIntent);}
                                                            else{
                                                            myProgressBar.setVisibility(View.GONE);
                                                            mySignInBTN.setVisibility(View.VISIBLE);
                                                            myPasswordEDT.setEnabled(true);
                                                            myEmailEDT.setEnabled(true);
                                                            Snackbar.make(mySignInBTN, R.string.error_password, Snackbar.LENGTH_LONG).
                                                                    setAction("Action", null).show();
                                                        }
                                                        break;}
                                                    case 5:{//responsable
                                                        MyClasses.ResponsibleTeacher user=gson.fromJson(response.getJSONObject(MyClasses.User.TABLE_NAME).toString(),MyClasses.ResponsibleTeacher.class);
                                                        if(user.getPassword().equals(password)){
                                                            saveInSharred(null,null,null,user,
                                                                    Integer.parseInt(response.getJSONObject(MyClasses.User.TABLE_NAME).getString("id_specialty"))
                                                                    ,0,Integer.parseInt(response.getJSONObject(MyClasses.User.TABLE_NAME).getString("id_responsbility")),
                                                                    5, 0);
                                                        Intent myIntent=new Intent(LoginActivity.this,WelcomeActivity.class);
                                                        startActivity(myIntent); }
                                                        else{
                                                            myProgressBar.setVisibility(View.GONE);
                                                            mySignInBTN.setVisibility(View.VISIBLE);
                                                            myPasswordEDT.setEnabled(true);
                                                            myEmailEDT.setEnabled(true);
                                                            Snackbar.make(mySignInBTN, R.string.error_password, Snackbar.LENGTH_LONG).
                                                                    setAction("Action", null).show();
                                                        }
                                                        break;}

                                                }
                                                }else{
                                                    myProgressBar.setVisibility(View.GONE);
                                                    mySignInBTN.setVisibility(View.VISIBLE);
                                                    myPasswordEDT.setEnabled(true);
                                                    myEmailEDT.setEnabled(true);
                                                    Snackbar.make(mySignInBTN, R.string.admins_can_t_connect, Snackbar.LENGTH_LONG).
                                                            setAction("Action", null).show();
                                                }
                                            }else{
                                                myProgressBar.setVisibility(View.GONE);
                                                mySignInBTN.setVisibility(View.VISIBLE);
                                                myPasswordEDT.setEnabled(true);
                                                myEmailEDT.setEnabled(true);
                                                Snackbar.make(mySignInBTN, R.string.this_user_doesn_t_exist, Snackbar.LENGTH_LONG).
                                                        setAction("Action", null).show();
                                            }
                                        }else{
                                            myProgressBar.setVisibility(View.GONE);
                                            mySignInBTN.setVisibility(View.VISIBLE);
                                            myPasswordEDT.setEnabled(true);
                                            myEmailEDT.setEnabled(true);
                                            Log.d("Login:mySignInBTN",
                                                    "Un paramettre est manquant dans votre code 66");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    myProgressBar.setVisibility(View.GONE);
                                    mySignInBTN.setVisibility(View.VISIBLE);
                                    myPasswordEDT.setEnabled(true);
                                    myEmailEDT.setEnabled(true);
                                    error.printStackTrace();
                                    Snackbar.make(mySignInBTN, R.string.error_happend_retry, Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(LoginActivity.this).addToRequestQue(jsonObjectRequest);
                            //we go to the home activity
                        }else {
                            //password not set
                             Snackbar.make(v, R.string.you_should_enter_password, Snackbar.LENGTH_LONG).
                                    setAction("Action", null).show();
                        }
                    }else{
                        //user typed email without any . or @
                        Snackbar.make(v, R.string.you_should_enter_valid_email, Snackbar.LENGTH_LONG).
                                setAction("Action", null).show();
                    }

                }else{
                    //user didn't type his email
                    Snackbar.make(v, R.string.you_should_enter_email, Snackbar.LENGTH_LONG).
                            setAction("Action", null).show();
                }
            }
        });
        myForgotPasswordTXV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, R.string.forget_password_rasponse, Snackbar.LENGTH_LONG).
                        setAction("Action", null).show();

            }
        });

    }
    String createUrl(String email){
        String result=null;
        Uri uri=Uri.parse(MyClasses.LOGIN_URL).buildUpon().appendQueryParameter(MyClasses.User.EMAIL,email).build();
        result=uri.toString();
        return  result;
    }
    void saveInSharred(MyClasses.Student student,MyClasses.Teacher teacher,MyClasses.Delegue delegue,MyClasses.ResponsibleTeacher responsibleTeacher,int id_specialty,int id_promo
    ,int id_responsibility,int user_type,int id_groupe){
        SharedPreferences.Editor editor=getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE).edit();
        MyClasses.User user=null;
        MyDbHelper dbHelper=new MyDbHelper(LoginActivity.this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Intent intent=new Intent(LoginActivity.this, MyClasses.FillDatabaseTimeTableTable.class);
        String first_name,last_name,email,password,sexe;
        long id;
        if(student!=null){
            //user is student
            user=student;
            editor.putInt("id_specialty",id_groupe);
            dbHelper.insertAStudent(db,student);
            //intent.putExtra("id",id_groupe);

        }else{
            if(teacher!=null){
                //teacher
                user=teacher;
                editor.putInt("id_specialty",id_specialty);
                dbHelper.insertATeacher(db,teacher);
                //intent.putExtra("id",teacher.getId());
            }else{
                if(delegue!=null){
                    //delegue
                    user=delegue;
                    editor.putInt("id_specialty",id_groupe);
                    editor.putInt("id_promo",id_promo);
                    dbHelper.insertAStudent(db,delegue);
                    //intent.putExtra("id",id_groupe);
                }else{
                    //responsible
                    user=responsibleTeacher;
                    editor.putInt("id_specialty",id_specialty);
                    editor.putInt("id_responsibility",id_responsibility);
                    dbHelper.insertAresponsibleTeacher(db,responsibleTeacher);
                    //intent.putExtra("id",responsibleTeacher.getId());
                }
            }
        }
        id=Long.parseLong(user.getId());
        first_name=user.getFirst_name();
        last_name=user.getLast_name();
        email=user.getEmail();
        sexe=user.getSexe();
        password=user.getPassword();
        editor.putLong("id",id);
        editor.putString("first_name",first_name);
        editor.putString("last_name",last_name);
        editor.putString("email",email);
        editor.putInt("user_type",user_type);
        editor.putString("password",password);
        editor.putString("sexe",sexe);
        editor.putBoolean("is_already_connected",true);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        editor.putString("update",formattedDate);
        /*intent.putExtra("user_type",user_type);
        intent.putExtra("id_user",String.valueOf(id));*/
        editor.commit();
        startService(intent);
        intent=new Intent(LoginActivity.this,MyClasses.FillDatabaseWithNews.class);
        startService(intent);
        intent=new Intent(LoginActivity.this,MyClasses.GetAllDocs.class);
        startService(intent);
        intent=new Intent(LoginActivity.this,MyClasses.GetAllMarks.class);
        startService(intent);
        intent=new Intent(LoginActivity.this,MyClasses.GetAllMessages.class);
        startService(intent);
        intent=new Intent(LoginActivity.this,MyClasses.getAllVotes.class);
        startService(intent);
        /*intent=new Intent(getApplicationContext(),MyClasses.MySyncService.class);
        startService(intent);*/
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

}

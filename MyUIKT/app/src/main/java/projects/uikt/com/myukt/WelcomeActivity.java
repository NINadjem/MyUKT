package projects.uikt.com.myukt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WelcomeActivity extends AppCompatActivity {

    Button goBTN;
    Bitmap bitmap=null;
    TextView nameTXV,emailTXV,functionTXV;
    de.hdodenhof.circleimageview.CircleImageView avatarImgView;
    ImageView changeAvatar;
    Intent myIntent;
    ProgressBar progressBar;
    LinearLayout linearLayout;
    SharedPreferences preferences;
    public static final int IMAGE_REQUEST=29;
    void selectImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        avatarImgView=findViewById(R.id.avatar_img_view_welcome);
        goBTN=findViewById(R.id.go_btn_welcome);
        linearLayout=findViewById(R.id.content_welcom_layout);
        progressBar=findViewById(R.id.progress_bar_welcome);
        nameTXV=findViewById(R.id.name_text_view_welcome);
        emailTXV=findViewById(R.id.email_text_view_welcome);
        functionTXV=findViewById(R.id.function_text_view_welcome);
        changeAvatar=findViewById(R.id.change_avatar_button);
        changeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        goBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
         preferences=getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
        if(preferences.getString("grp_header","").equals("")){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, createUrl(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getInt("params") == 1) {
                                    if (response.getInt("result") == 1) {
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("grp_header", response.getString("specialty"));
                                        editor.commit();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onErrorResponse json","error");
                }
            }

            );
            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
        }
        if(preferences.getString("avatar","").equals("")) {
            ImageRequest imageRequest = new ImageRequest(MyClasses.AVATARS_URL + preferences.getString("email", "") + MyClasses.AVATARS_FORMAT, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    avatarImgView.setImageBitmap(response);
                    linearLayout.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    saveInDataBase(response);

                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                /*Snackbar.make(goBTN, R.string.error_happend_retry, Snackbar.LENGTH_LONG).
                        setAction("Action", null).show();*/
                    avatarImgView.setImageBitmap(BitmapFactory.decodeResource(WelcomeActivity.this.getResources(),
                            R.drawable.logo128x128));
                    saveInDataBase(BitmapFactory.decodeResource(WelcomeActivity.this.getResources(),
                            R.drawable.logo128x128));
                    linearLayout.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    error.printStackTrace();
                }
            });

        mySinglton.getmInstance(getApplicationContext()).addToRequestQue(imageRequest);

        }else{
            byte[] decodedString = Base64.decode(preferences.getString("avatar",""), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            avatarImgView.setImageBitmap(decodedByte);
            linearLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

        nameTXV.setText(preferences.getString("last_name","")+" "+preferences.getString("first_name",""));
        emailTXV.setText(preferences.getString("email",""));
        switch (preferences.getInt("user_type",0)){
            case 1:{if (preferences.getString("sexe","").equals("f")||preferences.getString("sexe","").equals("F"))
                functionTXV.setText(getString(R.string.student_female));
            else functionTXV.setText(getString(R.string.student_male));
                break;}
            case 2:{if(preferences.getString("sexe","").equals("f")||preferences.getString("sexe","").equals("F"))
                functionTXV.setText(getString(R.string.teacher_female));
            else functionTXV.setText(getString(R.string.teacher_male));break;}
            case 4:{if(preferences.getString("sexe","").equals("f")||preferences.getString("sexe","").equals("F"))
                functionTXV.setText(getString(R.string.student_female));
            else functionTXV.setText(getString(R.string.student_male));break;}
            case 5:{if(preferences.getString("sexe","").equals("f")||preferences.getString("sexe","").equals("F"))
                functionTXV.setText(getString(R.string.teacher_female));
            else functionTXV.setText(getString(R.string.teacher_male));break;}
        }

    }
   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                AlertDialog.Builder builder=new AlertDialog.Builder(WelcomeActivity.this);
                View view=getLayoutInflater().inflate(R.layout.new_avatar_showing_dialogue,null);
                de.hdodenhof.circleimageview.CircleImageView avatar=view.findViewById(R.id.avatar_img_view);
                Button save,cancel,change;
                save=view.findViewById(R.id.update_avatar);
                cancel=view.findViewById(R.id.undo);
                change=view.findViewById(R.id.change_avatar_button);
                builder.setView(view);
                final AlertDialog alertDialog=builder.create();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarImgView.setImageBitmap(bitmap);
                        alertDialog.cancel();
                    }
                });
                change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectImage();
                        alertDialog.cancel();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                avatar.setImageBitmap(bitmap);
                alertDialog.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    String createImageUrl(String email){
        String result=null;
        //Uri uri=Uri.parse().buildUpon().build();
        //result=uri.toString();
        return  result;
    }*/
    void saveInDataBase(Bitmap response){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        response.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        SharedPreferences preferences=getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
        String id= String.valueOf(preferences.getLong("id",0));
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("avatar",encodedImage);
        editor.commit();
        MyDbHelper dbHelper=new MyDbHelper(WelcomeActivity.this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        dbHelper.changeUserImage(db,id,encodedImage);


    }
    String createUrl(){
        String result=null;
        Uri uri=Uri.parse(MyClasses.GET_SPECIALTY_URL).buildUpon().appendQueryParameter(MyClasses.User.USER_TYPE,
                String.valueOf(preferences.getInt("user_type",0)))
                .appendQueryParameter(MyClasses.User.USER_ID,String.valueOf(preferences.getInt("id_specialty",0)))
                .build();
        result=uri.toString();
        Log.d("url",result);
        return  result;
    }
    @Override
    public void onBackPressed() {

    }
}

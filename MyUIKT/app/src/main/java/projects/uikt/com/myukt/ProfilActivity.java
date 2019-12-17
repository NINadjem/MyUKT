package projects.uikt.com.myukt;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class ProfilActivity extends AppCompatActivity {
    ImageView sexImageView;
    de.hdodenhof.circleimageview.CircleImageView avatarImageView;
    TextView sexTextView, studentTypeTextView, specialtyTextView, levelTextView, grpTextView, homeTextView,
            birthDateTextView, emailTextView/*, add_dateTextView*/,fullName;
    MyClasses.Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Intent intent = getIntent();
                avatarImageView = findViewById(R.id.avatar_img_view_Profil);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                student=gson.fromJson(intent.getStringExtra("json"),MyClasses.Student.class);
                ImageRequest imageRequest = new ImageRequest(MyClasses.AVATARS_URL + student.getEmail() +MyClasses.AVATARS_FORMAT,
                        new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        avatarImageView.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                /*Snackbar.make(goBTN, R.string.error_happend_retry, Snackbar.LENGTH_LONG).
                        setAction("Action", null).show();*/
                        avatarImageView.setImageBitmap(BitmapFactory.decodeResource(ProfilActivity.this.getResources(),
                                R.drawable.logo128x128));
                        error.printStackTrace();
                    }
                });
                mySinglton.getmInstance(getApplicationContext()).addToRequestQue(imageRequest);
               /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);*/
                sexImageView = findViewById(R.id.sexImageViewProfil);
                sexTextView = findViewById(R.id.sexTextViewProfil);
                fullName=findViewById(R.id.full_name_profil);
                fullName.setText(student.getLast_name()+" "+student.getFirst_name());
                studentTypeTextView = findViewById(R.id.studentLabelTextViewProfil);
                if(!student.getSexe().equals("f")&&!student.getSexe().equals("F")){
                  sexImageView.setImageBitmap(BitmapFactory.decodeResource(ProfilActivity.this.getResources(),
                          R.drawable.icons8male64));
                  sexTextView.setText(getString(R.string.male));
                  studentTypeTextView.setText(getString(R.string.student_male));
                }
                //add_dateTextView = findViewById(R.id.add_date_TextViewProfil);
                specialtyTextView = findViewById(R.id.spacialtyLabelTextViewProfil);
                specialtyTextView.setText(student.getAdd_date());
                levelTextView = findViewById(R.id.levelTextViewProfil);
                levelTextView.setText(student.getBib());
                grpTextView = findViewById(R.id.groupTextViewProfil);
                grpTextView.setText(student.getMat());
                homeTextView = findViewById(R.id.homeTextViewProfil);
                homeTextView.setText(student.getHome_town());
                birthDateTextView = findViewById(R.id.birth_dateTextViewProfil);
                birthDateTextView.setText(student.getBirth_date());
                emailTextView = findViewById(R.id.emailTextViewProfil);
                emailTextView.setText(student.getEmail());
                //add_dateTextView = findViewById(R.id.add_date_TextViewProfil);
                //add_dateTextView.setVisibility(View.GONE);}
            }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/
}
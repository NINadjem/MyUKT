package projects.uikt.com.myukt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class AdminProfilActivity extends AppCompatActivity {
    ImageView sexImageView;
    de.hdodenhof.circleimageview.CircleImageView avatarImageView;
    TextView studentTypeTextView,specialtyTextView,homeTextView,
            birthDateTextView,emailTextView,add_dateTextView,sexTextView,fullName;
    MyClasses.Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profil);
        Intent intent = getIntent();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        admin=gson.fromJson(intent.getStringExtra("json"),MyClasses.Admin.class);
        avatarImageView=findViewById(R.id.avatar_img_view_Profil);
        ImageRequest imageRequest = new ImageRequest(MyClasses.AVATARS_URL + admin.getEmail() +MyClasses.AVATARS_FORMAT,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        avatarImageView.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                avatarImageView.setImageBitmap(BitmapFactory.decodeResource(AdminProfilActivity.this.getResources(),
                        R.drawable.logo128x128));
                error.printStackTrace();
            }
        });
        mySinglton.getmInstance(getApplicationContext()).addToRequestQue(imageRequest);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/

        sexImageView=findViewById(R.id.sexImageViewProfil);
        studentTypeTextView=findViewById(R.id.studentLabelTextViewProfil);
        specialtyTextView=findViewById(R.id.spacialtyLabelTextViewProfil);
        homeTextView=findViewById(R.id.homeTextViewProfil);
        birthDateTextView=findViewById(R.id.birth_dateTextViewProfil);
        emailTextView=findViewById(R.id.emailTextViewProfil);
        add_dateTextView=findViewById(R.id.add_date_TextViewProfil);
        sexTextView = findViewById(R.id.sexTextViewProfil);
        if(!admin.getSexe().equals("f")&&!admin.getSexe().equals("F")){
            sexImageView.setImageBitmap(BitmapFactory.decodeResource(AdminProfilActivity.this.getResources(),
                    R.drawable.icons8male64));
            sexTextView.setText(getString(R.string.male));
        }
        fullName=findViewById(R.id.full_name_profil);
        fullName.setText(admin.getLast_name()+" "+admin.getFirst_name());
        specialtyTextView.setText(admin.getId_work());
        homeTextView.setText(admin.getHome_town());
        birthDateTextView.setText(admin.getBirth_date());
        emailTextView.setText(admin.getEmail());
    }
  /*  @Override
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

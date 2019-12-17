package projects.uikt.com.myukt;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TeacherProfilActivity extends AppCompatActivity {
    ImageView sexImageView;
    de.hdodenhof.circleimageview.CircleImageView avatarImageView;
    TextView sexTextView,studentTypeTextView,specialtyTextView,/*levelTextView,*/responsibilityTextVie,homeTextView,
            birthDateTextView,emailTextView,/*add_dateTextView,*/fullName;
    LinearLayout resp_layout;
    boolean is_responsible=false;
    MyClasses.Teacher teacher;MyClasses.ResponsibleTeacher responsibleTeacher=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profil);
        Intent intent=getIntent();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        teacher=gson.fromJson(intent.getStringExtra("json"),MyClasses.Teacher.class);
        avatarImageView=findViewById(R.id.avatar_img_view_Profil);
        ImageRequest imageRequest = new ImageRequest(MyClasses.AVATARS_URL + teacher.getEmail() +MyClasses.AVATARS_FORMAT,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        avatarImageView.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                avatarImageView.setImageBitmap(BitmapFactory.decodeResource(TeacherProfilActivity.this.getResources(),
                        R.drawable.logo128x128));
                error.printStackTrace();
            }
        });
        mySinglton.getmInstance(getApplicationContext()).addToRequestQue(imageRequest);
        /*android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/
        sexImageView=findViewById(R.id.sexImageViewProfil);
        sexTextView=findViewById(R.id.sexTextViewProfil);
        studentTypeTextView=findViewById(R.id.studentLabelTextViewProfil);
        specialtyTextView=findViewById(R.id.spacialtyLabelTextViewProfil);
        //levelTextView=findViewById(R.id.levelTextViewProfil);
        homeTextView=findViewById(R.id.homeTextViewProfil);
        birthDateTextView=findViewById(R.id.birth_dateTextViewProfil);
        emailTextView=findViewById(R.id.emailTextViewProfil);
        //add_dateTextView=findViewById(R.id.add_date_TextViewProfil);
        resp_layout=findViewById(R.id.responsbility_layout);
        fullName=findViewById(R.id.full_name_profil);
        fullName.setText(teacher.getLast_name()+" "+teacher.getFirst_name());
        if(!teacher.getSexe().equals("f")&&!teacher.getSexe().equals("F")){
            sexImageView.setImageBitmap(BitmapFactory.decodeResource(TeacherProfilActivity.this.getResources(),
                    R.drawable.icons8male64));
            sexTextView.setText(getString(R.string.male));
            studentTypeTextView.setText(getString(R.string.teacher_male));
        }
        specialtyTextView.setText(teacher.getId_specialty());
        birthDateTextView.setText(teacher.getBirth_date());
        emailTextView.setText(teacher.getEmail());
        homeTextView.setText(teacher.getHome_town());
        if(intent.getIntExtra("user_type",0)!=2){
            responsibleTeacher=gson.fromJson(intent.getStringExtra("json"),
                    MyClasses.ResponsibleTeacher.class);
            teacher=null;
            responsibilityTextVie=findViewById(R.id.responsibilityTextViewProfil);
            responsibilityTextVie.setText(responsibleTeacher.getId_responsibility());
            resp_layout.setVisibility(View.VISIBLE);
            responsibilityTextVie.setVisibility(View.VISIBLE);
            is_responsible=true;
        }

    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.saveMenuItem){
        MyDbHelper dbHelper=new MyDbHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(is_responsible){
            dbHelper.insertAresponsibleTeacher(db,responsibleTeacher);
        }else{
            dbHelper.insertATeacher(db,teacher);
        }
        Snackbar.make(avatarImageView,
                getString(R.string.sauvgarde_avec_succ ),Snackbar.LENGTH_LONG).setAction("Action", null).show();}
                else{

        }
        return super.onOptionsItemSelected(item);
    }*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profil_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

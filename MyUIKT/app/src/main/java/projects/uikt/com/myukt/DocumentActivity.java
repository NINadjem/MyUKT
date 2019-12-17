package projects.uikt.com.myukt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class DocumentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    MyClasses.ViewPagerAdapter viewPagerAdapter;
    TextView email_header,neme_header,grp_header;
    de.hdodenhof.circleimageview.CircleImageView avatarHeader;
    SharedPreferences preferences;
    ArrayList<MyClasses.DocAdapter.mClass> docs=new ArrayList<>();
    ArrayList<MyClasses.DocDoneAdapter.mClass> docsDone=new ArrayList<>();
    int tables=0;
    ProgressBar progressBar;
    getAllDocs allDocs;
    getAllDocsDone allDocsDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar=findViewById(R.id.progress_bar_table);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        preferences=getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
        if(preferences.getInt("user_type",0)==5||preferences.getInt("user_type",0)==2){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.teacher_drawer );
        }
        allDocsDone=new getAllDocsDone();
        allDocsDone.execute();
        allDocs=new getAllDocs();
        allDocs.execute();

        tabLayout = findViewById(R.id.tab_layout_docs);
        viewPager = findViewById(R.id.view_pager_docs);
        View view=navigationView.getHeaderView(0);
        avatarHeader=view.findViewById(R.id.avatar_header);
        email_header=view.findViewById(R.id.email_header);
        neme_header=view.findViewById(R.id.username_header);
        grp_header=view.findViewById(R.id.groupe_or_specialty_header);

        email_header.setText(preferences.getString("email",""));
        neme_header.setText(preferences.getString("last_name","")+" "+preferences.getString("first_name",""));
        grp_header.setText(preferences.getString("grp_header",""));
        byte[] decodedString = Base64.decode(preferences.getString("avatar",""), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        avatarHeader.setImageBitmap(decodedByte);



    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_new_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addNewMenuNewsItem:
                Intent intent=new Intent(DocumentActivity.this, AddActivity.class);
                intent.putExtra("add_type",3);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.time_table_item) {
            startActivity(new Intent(DocumentActivity.this,TimeTableActivity.class));
        } else if (id == R.id.news_item) {
            startActivity(new Intent(DocumentActivity.this,NewsActivity.class));
        } else if (id == R.id.msg_item) {
            startActivity(new Intent(DocumentActivity.this,MessegesActivity.class));
        } else if (id == R.id.docs_item) {
            startActivity(new Intent(DocumentActivity.this,DocumentActivity.class));
        } else if (id == R.id.stat_item) {
            startActivity(new Intent(DocumentActivity.this,AbsenceActivity.class));
        } else if (id == R.id.reminders_item) {
            startActivity(new Intent(DocumentActivity.this,MarksActivity.class));

        } else if (id == R.id.settings_item) {
            startActivity(new Intent(DocumentActivity.this, SettingsActivity.class));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(DocumentActivity.this);
            View view = getLayoutInflater().inflate(R.layout.log_out_dialogue, null);
            TextView logOut, cancel;
            logOut = view.findViewById(R.id.log_out);
            cancel = view.findViewById(R.id.undo);
            builder.setView(view);
            final AlertDialog alertDialog = builder.create();
            logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean("is_already_connected",false);
                    editor.putLong("id",0);
                    editor.putString("first_name","");
                    editor.putString("last_name","");
                    editor.putString("email","");
                    editor.putInt("user_type",0);
                    editor.putString("password","");
                    editor.putString("sexe","");
                    editor.putString("avatar","");
                    editor.putString("grp_header", "");
                    editor.commit();
                    startActivity(new Intent(DocumentActivity.this, LoginActivity.class));
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                }
            });
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class getAllDocs extends AsyncTask<Void,Void,ArrayList<MyClasses.DocAdapter.mClass>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.DocAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            tables++;
            docs=mClasses;
            if(tables==2){
                progressBar.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.DocDoneFragment(docsDone),getString(R.string.ready_doc));
                //viewPagerAdapter.addFragment(new MyClasses.DocOnFragment(),getString(R.string.doc_on));
                viewPagerAdapter.addFragment(new MyClasses.DocFragment(docs),getString(R.string.doc_req) );
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8ouvrirlecv24);
                //tabLayout.getTabAt(1).setIcon(R.drawable.icons8soumettrelecv24);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8soumettrelecv24);
            }

        }

        @Override
        protected ArrayList<MyClasses.DocAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(DocumentActivity.this);
           SQLiteDatabase database=dbHelper.getReadableDatabase();
            return dbHelper.getAllFromDocTable(database);
        }
    }
    class getAllDocsDone extends AsyncTask<Void,Void,ArrayList<MyClasses.DocDoneAdapter.mClass>>{
        @Override
        protected void onPostExecute(ArrayList<MyClasses.DocDoneAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            tables++;
            docsDone=mClasses;
            if(tables==2){
                progressBar.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.DocDoneFragment(docsDone),getString(R.string.ready_doc));
                //viewPagerAdapter.addFragment(new MyClasses.DocOnFragment(),getString(R.string.doc_on));
                viewPagerAdapter.addFragment(new MyClasses.DocFragment(docs),getString(R.string.doc_req) );
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8ouvrirlecv24);
                //tabLayout.getTabAt(1).setIcon(R.drawable.icons8soumettrelecv24);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8soumettrelecv24);
            }
        }

        @Override
        protected ArrayList<MyClasses.DocDoneAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(DocumentActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
            return dbHelper.getAllFromDocsDoneTable(database);
        }
    }
}

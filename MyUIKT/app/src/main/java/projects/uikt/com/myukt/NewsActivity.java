package projects.uikt.com.myukt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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

public class NewsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    MyClasses.ViewPagerAdapter viewPagerAdapter;
    TextView email_header,neme_header,grp_header;
    SharedPreferences preferences;
    ProgressBar progressBar;
    ArrayList<MyClasses.RecyclerNewsAdapter.mClass> newsList=new ArrayList<>();
    ArrayList<MyClasses.RecyclerChangeAdapter.mClass> changeList=new ArrayList<>();
    ArrayList<MyClasses.RecyclerConsultationDAdapter.mClass> consultationList=new ArrayList<>();
    ArrayList<MyClasses.RecyclerExamAdapter.mClass> examList=new ArrayList<>();
    ArrayList<MyClasses.RecyclerMarksDAdapter.mClass> marksDList=new ArrayList<>();
    ArrayList<MyClasses.RecyclerMeetingAdapter.mClass> meetingList=new ArrayList<>();
    ArrayList<MyClasses.RecyclerProcedureAdapter.mClass> procedureList=new ArrayList<>();
    ArrayList<MyClasses.RecyclerStrikeAdapter.mClass> strikeList=new ArrayList<>();
    ArrayList<MyClasses.RecyclerHolydayAdapter.mClass> holydayList=new ArrayList<>();
    int numResult=1;
    de.hdodenhof.circleimageview.CircleImageView avatarHeader;
    GetNews getNews;GetConsultation  getConsultation;
    GetMarksD getMarksD;GetMeetings getMeetings;
    GetExams getExams;GetHolydays getHolydays;
    GetStrike getStrike;GetChangeOfSession getChangeOfSession;
    GetProcedure getProcedure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        progressBar=findViewById(R.id.progress_bar_table);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        preferences=getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tabLayout = findViewById(R.id.tab_layout_news);
        viewPager = findViewById(R.id.view_pager_news);
        if(preferences.getInt("user_type",0)==5||preferences.getInt("user_type",0)==2){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.teacher_drawer );
        }
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
        getNews=new GetNews();
        getNews.execute();
        getMarksD=new GetMarksD();
        getMarksD.execute();








    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(NewsActivity.this,HomeActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNewMenuNewsItem:
                Intent intent=new Intent(NewsActivity.this, AddActivity.class);
                intent.putExtra("add_type",0);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_new_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.time_table_item) {
            startActivity(new Intent(NewsActivity.this,TimeTableActivity.class));
        } else if (id == R.id.news_item) {
            startActivity(new Intent(NewsActivity.this,NewsActivity.class));
        } else if (id == R.id.msg_item) {
            startActivity(new Intent(NewsActivity.this,MessegesActivity.class));
        } else if (id == R.id.docs_item) {
            startActivity(new Intent(NewsActivity.this,DocumentActivity.class));
        } else if (id == R.id.stat_item) {
            startActivity(new Intent(NewsActivity.this,AbsenceActivity.class));
        } else if (id == R.id.reminders_item) {
            startActivity(new Intent(NewsActivity.this,MarksActivity.class));

        } else if (id == R.id.settings_item) {
            startActivity(new Intent(NewsActivity.this, SettingsActivity.class));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(NewsActivity.this);
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
                    startActivity(new Intent(NewsActivity.this, LoginActivity.class));
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
    class GetNews extends AsyncTask<Void,Void,ArrayList<MyClasses.RecyclerNewsAdapter.mClass>>{

        @Override
        protected ArrayList<MyClasses.RecyclerNewsAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(NewsActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
            return dbHelper.getNews(database);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.RecyclerNewsAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            numResult++;
            newsList=mClasses;
            getExams=new GetExams();
            getExams.execute();
            if(numResult==9){
                progressBar.setVisibility(View.GONE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.NewsFragment(newsList), getString(R.string.news));
                viewPagerAdapter.addFragment(new MyClasses.MarksDisplayedFragment(marksDList), getString(R.string.marks_des));
                viewPagerAdapter.addFragment(new MyClasses.ExamFragment(examList), getString(R.string.exam));
                viewPagerAdapter.addFragment(new MyClasses.StrikeFragment(strikeList), getString(R.string.strike));
                viewPagerAdapter.addFragment(new MyClasses.ConsultationFragment(consultationList), getString(R.string.consultation));
                viewPagerAdapter.addFragment(new MyClasses.MeetingFragment(meetingList), getString(R.string.meeting));
                viewPagerAdapter.addFragment(new MyClasses.HolydayFragment(holydayList), getString(R.string.holyday));
                viewPagerAdapter.addFragment(new MyClasses.ChangeFragment(changeList), getString(R.string.change_of_session));
                viewPagerAdapter.addFragment(new MyClasses.ProcedureFragment(procedureList), getString(R.string.pedag_procedu));
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8nouvelles26gray);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8examen24);
                tabLayout.getTabAt(2).setIcon(R.drawable.icons8testpartiellementrus24);
                tabLayout.getTabAt(3).setIcon(R.drawable.icons8strike26);
                tabLayout.getTabAt(4).setIcon(R.drawable.icons8voirlaproprit26);
                tabLayout.getTabAt(5).setIcon(R.drawable.icons8usergroups26);
                tabLayout.getTabAt(6).setIcon(R.drawable.icons8baindesoleil24);
                tabLayout.getTabAt(7).setIcon(R.drawable.icons8remplacer24gris);
                tabLayout.getTabAt(8).setIcon(R.drawable.icons8resume24);
                viewPager.setVisibility(View.VISIBLE);
            }
        }
    }


    public class GetMarksD extends AsyncTask<Void,Void,ArrayList<MyClasses.RecyclerMarksDAdapter.mClass>>{

        @Override
        protected ArrayList<MyClasses.RecyclerMarksDAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(NewsActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
            //dbHelper.GetAsession(database,"1");
            return dbHelper.getAllMarksD(database);
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.RecyclerMarksDAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            numResult++;
            marksDList=mClasses;
            getStrike=new GetStrike();
            getStrike.execute();
            if(numResult==9){
                progressBar.setVisibility(View.GONE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.NewsFragment(newsList), getString(R.string.news));
                viewPagerAdapter.addFragment(new MyClasses.MarksDisplayedFragment(marksDList), getString(R.string.marks_des));
                viewPagerAdapter.addFragment(new MyClasses.ExamFragment(examList), getString(R.string.exam));
                viewPagerAdapter.addFragment(new MyClasses.StrikeFragment(strikeList), getString(R.string.strike));
                viewPagerAdapter.addFragment(new MyClasses.ConsultationFragment(consultationList), getString(R.string.consultation));
                viewPagerAdapter.addFragment(new MyClasses.MeetingFragment(meetingList), getString(R.string.meeting));
                viewPagerAdapter.addFragment(new MyClasses.HolydayFragment(holydayList), getString(R.string.holyday));
                viewPagerAdapter.addFragment(new MyClasses.ChangeFragment(changeList), getString(R.string.change_of_session));
                viewPagerAdapter.addFragment(new MyClasses.ProcedureFragment(procedureList), getString(R.string.pedag_procedu));
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8nouvelles26gray);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8examen24);
                tabLayout.getTabAt(2).setIcon(R.drawable.icons8testpartiellementrus24);
                tabLayout.getTabAt(3).setIcon(R.drawable.icons8strike26);
                tabLayout.getTabAt(4).setIcon(R.drawable.icons8voirlaproprit26);
                tabLayout.getTabAt(5).setIcon(R.drawable.icons8usergroups26);
                tabLayout.getTabAt(6).setIcon(R.drawable.icons8baindesoleil24);
                tabLayout.getTabAt(7).setIcon(R.drawable.icons8remplacer24gris);
                tabLayout.getTabAt(8).setIcon(R.drawable.icons8resume24);
                viewPager.setVisibility(View.VISIBLE);
            }
        }
        }


    public class GetExams extends AsyncTask<Void,Void,ArrayList<MyClasses.RecyclerExamAdapter.mClass>>{

        @Override
        protected ArrayList<MyClasses.RecyclerExamAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(NewsActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
           /* dbHelper.addAvote(database,new  MyClasses.Voted("2","10","31","0"
                    ,"cette prologation va nous perdre du tempd",""));
            dbHelper.addTojournal(database,new MyClasses.Journal("1","1","2","38","25/01/2018 11:30"));*/
            return dbHelper.getAllexams(database);
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.RecyclerExamAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            numResult++;
            examList=mClasses;
            getConsultation=new GetConsultation();
            getConsultation.execute();
            if(numResult==9){
                progressBar.setVisibility(View.GONE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.NewsFragment(newsList), getString(R.string.news));
                viewPagerAdapter.addFragment(new MyClasses.MarksDisplayedFragment(marksDList), getString(R.string.marks_des));
                viewPagerAdapter.addFragment(new MyClasses.ExamFragment(examList), getString(R.string.exam));
                viewPagerAdapter.addFragment(new MyClasses.StrikeFragment(strikeList), getString(R.string.strike));
                viewPagerAdapter.addFragment(new MyClasses.ConsultationFragment(consultationList), getString(R.string.consultation));
                viewPagerAdapter.addFragment(new MyClasses.MeetingFragment(meetingList), getString(R.string.meeting));
                viewPagerAdapter.addFragment(new MyClasses.HolydayFragment(holydayList), getString(R.string.holyday));
                viewPagerAdapter.addFragment(new MyClasses.ChangeFragment(changeList), getString(R.string.change_of_session));
                viewPagerAdapter.addFragment(new MyClasses.ProcedureFragment(procedureList), getString(R.string.pedag_procedu));
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8nouvelles26gray);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8examen24);
                tabLayout.getTabAt(2).setIcon(R.drawable.icons8testpartiellementrus24);
                tabLayout.getTabAt(3).setIcon(R.drawable.icons8strike26);
                tabLayout.getTabAt(4).setIcon(R.drawable.icons8voirlaproprit26);
                tabLayout.getTabAt(5).setIcon(R.drawable.icons8usergroups26);
                tabLayout.getTabAt(6).setIcon(R.drawable.icons8baindesoleil24);
                tabLayout.getTabAt(7).setIcon(R.drawable.icons8remplacer24gris);
                tabLayout.getTabAt(8).setIcon(R.drawable.icons8resume24);
                viewPager.setVisibility(View.VISIBLE);
            }

        }
    }


    public class GetStrike extends AsyncTask<Void,Void,ArrayList<MyClasses.RecyclerStrikeAdapter.mClass>>{

        @Override
        protected ArrayList<MyClasses.RecyclerStrikeAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(NewsActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
            return dbHelper.getAllStrike(database);
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.RecyclerStrikeAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            numResult++;
            strikeList=mClasses;
            getMeetings=new GetMeetings();
            getMeetings.execute();
            if(numResult==9){
                progressBar.setVisibility(View.GONE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.NewsFragment(newsList), getString(R.string.news));
                viewPagerAdapter.addFragment(new MyClasses.MarksDisplayedFragment(marksDList), getString(R.string.marks_des));
                viewPagerAdapter.addFragment(new MyClasses.ExamFragment(examList), getString(R.string.exam));
                viewPagerAdapter.addFragment(new MyClasses.StrikeFragment(strikeList), getString(R.string.strike));
                viewPagerAdapter.addFragment(new MyClasses.ConsultationFragment(consultationList), getString(R.string.consultation));
                viewPagerAdapter.addFragment(new MyClasses.MeetingFragment(meetingList), getString(R.string.meeting));
                viewPagerAdapter.addFragment(new MyClasses.HolydayFragment(holydayList), getString(R.string.holyday));
                viewPagerAdapter.addFragment(new MyClasses.ChangeFragment(changeList), getString(R.string.change_of_session));
                viewPagerAdapter.addFragment(new MyClasses.ProcedureFragment(procedureList), getString(R.string.pedag_procedu));
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8nouvelles26gray);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8examen24);
                tabLayout.getTabAt(2).setIcon(R.drawable.icons8testpartiellementrus24);
                tabLayout.getTabAt(3).setIcon(R.drawable.icons8strike26);
                tabLayout.getTabAt(4).setIcon(R.drawable.icons8voirlaproprit26);
                tabLayout.getTabAt(5).setIcon(R.drawable.icons8usergroups26);
                tabLayout.getTabAt(6).setIcon(R.drawable.icons8baindesoleil24);
                tabLayout.getTabAt(7).setIcon(R.drawable.icons8remplacer24gris);
                tabLayout.getTabAt(8).setIcon(R.drawable.icons8resume24);
                viewPager.setVisibility(View.VISIBLE);
            }

        }
    }


    public class GetConsultation extends AsyncTask<Void,Void,ArrayList<MyClasses.RecyclerConsultationDAdapter.mClass>>{

        @Override
        protected ArrayList<MyClasses.RecyclerConsultationDAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(NewsActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
            return dbHelper.getAllConsultation(database);
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.RecyclerConsultationDAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            consultationList=mClasses;
            getHolydays=new GetHolydays();
            getHolydays.execute();
            if(numResult==9){
                progressBar.setVisibility(View.GONE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.NewsFragment(newsList), getString(R.string.news));
                viewPagerAdapter.addFragment(new MyClasses.MarksDisplayedFragment(marksDList), getString(R.string.marks_des));
                viewPagerAdapter.addFragment(new MyClasses.ExamFragment(examList), getString(R.string.exam));
                viewPagerAdapter.addFragment(new MyClasses.StrikeFragment(strikeList), getString(R.string.strike));
                viewPagerAdapter.addFragment(new MyClasses.ConsultationFragment(consultationList), getString(R.string.consultation));
                viewPagerAdapter.addFragment(new MyClasses.MeetingFragment(meetingList), getString(R.string.meeting));
                viewPagerAdapter.addFragment(new MyClasses.HolydayFragment(holydayList), getString(R.string.holyday));
                viewPagerAdapter.addFragment(new MyClasses.ChangeFragment(changeList), getString(R.string.change_of_session));
                viewPagerAdapter.addFragment(new MyClasses.ProcedureFragment(procedureList), getString(R.string.pedag_procedu));
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8nouvelles26gray);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8examen24);
                tabLayout.getTabAt(2).setIcon(R.drawable.icons8testpartiellementrus24);
                tabLayout.getTabAt(3).setIcon(R.drawable.icons8strike26);
                tabLayout.getTabAt(4).setIcon(R.drawable.icons8voirlaproprit26);
                tabLayout.getTabAt(5).setIcon(R.drawable.icons8usergroups26);
                tabLayout.getTabAt(6).setIcon(R.drawable.icons8baindesoleil24);
                tabLayout.getTabAt(7).setIcon(R.drawable.icons8remplacer24gris);
                tabLayout.getTabAt(8).setIcon(R.drawable.icons8resume24);
                viewPager.setVisibility(View.VISIBLE);
            }

        }
    }

    public class GetMeetings extends AsyncTask<Void,Void,ArrayList<MyClasses.RecyclerMeetingAdapter.mClass>>{

        @Override
        protected ArrayList<MyClasses.RecyclerMeetingAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(NewsActivity.this);
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            return dbHelper.getAllMeetings(db);
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.RecyclerMeetingAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            numResult++;
            meetingList=mClasses;
            if(numResult==9){
                progressBar.setVisibility(View.GONE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.NewsFragment(newsList), getString(R.string.news));
                viewPagerAdapter.addFragment(new MyClasses.MarksDisplayedFragment(marksDList), getString(R.string.marks_des));
                viewPagerAdapter.addFragment(new MyClasses.ExamFragment(examList), getString(R.string.exam));
                viewPagerAdapter.addFragment(new MyClasses.StrikeFragment(strikeList), getString(R.string.strike));
                viewPagerAdapter.addFragment(new MyClasses.ConsultationFragment(consultationList), getString(R.string.consultation));
                viewPagerAdapter.addFragment(new MyClasses.MeetingFragment(meetingList), getString(R.string.meeting));
                viewPagerAdapter.addFragment(new MyClasses.HolydayFragment(holydayList), getString(R.string.holyday));
                viewPagerAdapter.addFragment(new MyClasses.ChangeFragment(changeList), getString(R.string.change_of_session));
                viewPagerAdapter.addFragment(new MyClasses.ProcedureFragment(procedureList), getString(R.string.pedag_procedu));
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8nouvelles26gray);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8examen24);
                tabLayout.getTabAt(2).setIcon(R.drawable.icons8testpartiellementrus24);
                tabLayout.getTabAt(3).setIcon(R.drawable.icons8strike26);
                tabLayout.getTabAt(4).setIcon(R.drawable.icons8voirlaproprit26);
                tabLayout.getTabAt(5).setIcon(R.drawable.icons8usergroups26);
                tabLayout.getTabAt(6).setIcon(R.drawable.icons8baindesoleil24);
                tabLayout.getTabAt(7).setIcon(R.drawable.icons8remplacer24gris);
                tabLayout.getTabAt(8).setIcon(R.drawable.icons8resume24);
                viewPager.setVisibility(View.VISIBLE);
            }

        }
    }

    public class GetHolydays extends AsyncTask<Void,Void,ArrayList<MyClasses.RecyclerHolydayAdapter.mClass>>{

        @Override
        protected ArrayList<MyClasses.RecyclerHolydayAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(NewsActivity.this);
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            return dbHelper.getAllHolydays(db);
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.RecyclerHolydayAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            numResult++;
            holydayList=mClasses;
            getChangeOfSession=new GetChangeOfSession();
            getChangeOfSession.execute();
            if(numResult==9){
                progressBar.setVisibility(View.GONE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.NewsFragment(newsList), getString(R.string.news));
                viewPagerAdapter.addFragment(new MyClasses.MarksDisplayedFragment(marksDList), getString(R.string.marks_des));
                viewPagerAdapter.addFragment(new MyClasses.ExamFragment(examList), getString(R.string.exam));
                viewPagerAdapter.addFragment(new MyClasses.StrikeFragment(strikeList), getString(R.string.strike));
                viewPagerAdapter.addFragment(new MyClasses.ConsultationFragment(consultationList), getString(R.string.consultation));
                viewPagerAdapter.addFragment(new MyClasses.MeetingFragment(meetingList), getString(R.string.meeting));
                viewPagerAdapter.addFragment(new MyClasses.HolydayFragment(holydayList), getString(R.string.holyday));
                viewPagerAdapter.addFragment(new MyClasses.ChangeFragment(changeList), getString(R.string.change_of_session));
                viewPagerAdapter.addFragment(new MyClasses.ProcedureFragment(procedureList), getString(R.string.pedag_procedu));
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8nouvelles26gray);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8examen24);
                tabLayout.getTabAt(2).setIcon(R.drawable.icons8testpartiellementrus24);
                tabLayout.getTabAt(3).setIcon(R.drawable.icons8strike26);
                tabLayout.getTabAt(4).setIcon(R.drawable.icons8voirlaproprit26);
                tabLayout.getTabAt(5).setIcon(R.drawable.icons8usergroups26);
                tabLayout.getTabAt(6).setIcon(R.drawable.icons8baindesoleil24);
                tabLayout.getTabAt(7).setIcon(R.drawable.icons8remplacer24gris);
                tabLayout.getTabAt(8).setIcon(R.drawable.icons8resume24);
                viewPager.setVisibility(View.VISIBLE);
            }

        }
    }

    public class GetChangeOfSession extends AsyncTask<Void,Void,ArrayList<MyClasses.RecyclerChangeAdapter.mClass>>{

        @Override
        protected ArrayList<MyClasses.RecyclerChangeAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(NewsActivity.this);
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            return dbHelper.getAllChanges(db);
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.RecyclerChangeAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            numResult++;
            changeList=mClasses;
            getProcedure=new GetProcedure();
            getProcedure.execute();
            if(numResult==9){
                progressBar.setVisibility(View.GONE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.NewsFragment(newsList), getString(R.string.news));
                viewPagerAdapter.addFragment(new MyClasses.MarksDisplayedFragment(marksDList), getString(R.string.marks_des));
                viewPagerAdapter.addFragment(new MyClasses.ExamFragment(examList), getString(R.string.exam));
                viewPagerAdapter.addFragment(new MyClasses.StrikeFragment(strikeList), getString(R.string.strike));
                viewPagerAdapter.addFragment(new MyClasses.ConsultationFragment(consultationList), getString(R.string.consultation));
                viewPagerAdapter.addFragment(new MyClasses.MeetingFragment(meetingList), getString(R.string.meeting));
                viewPagerAdapter.addFragment(new MyClasses.HolydayFragment(holydayList), getString(R.string.holyday));
                viewPagerAdapter.addFragment(new MyClasses.ChangeFragment(changeList), getString(R.string.change_of_session));
                viewPagerAdapter.addFragment(new MyClasses.ProcedureFragment(procedureList), getString(R.string.pedag_procedu));
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8nouvelles26gray);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8examen24);
                tabLayout.getTabAt(2).setIcon(R.drawable.icons8testpartiellementrus24);
                tabLayout.getTabAt(3).setIcon(R.drawable.icons8strike26);
                tabLayout.getTabAt(4).setIcon(R.drawable.icons8voirlaproprit26);
                tabLayout.getTabAt(5).setIcon(R.drawable.icons8usergroups26);
                tabLayout.getTabAt(6).setIcon(R.drawable.icons8baindesoleil24);
                tabLayout.getTabAt(7).setIcon(R.drawable.icons8remplacer24gris);
                tabLayout.getTabAt(8).setIcon(R.drawable.icons8resume24);
                viewPager.setVisibility(View.VISIBLE);
            }

        }
    }
    public class GetProcedure extends AsyncTask<Void,Void,ArrayList<MyClasses.RecyclerProcedureAdapter.mClass>>{

        @Override
        protected ArrayList<MyClasses.RecyclerProcedureAdapter.mClass> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(NewsActivity.this);
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            return dbHelper.getAllProcedures(db);
        }

        @Override
        protected void onPostExecute(ArrayList<MyClasses.RecyclerProcedureAdapter.mClass> mClasses) {
            super.onPostExecute(mClasses);
            numResult++;
            procedureList=mClasses;
            if(numResult==9){
                progressBar.setVisibility(View.GONE);
                viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(new MyClasses.NewsFragment(newsList), getString(R.string.news));
                viewPagerAdapter.addFragment(new MyClasses.MarksDisplayedFragment(marksDList), getString(R.string.marks_des));
                viewPagerAdapter.addFragment(new MyClasses.ExamFragment(examList), getString(R.string.exam));
                viewPagerAdapter.addFragment(new MyClasses.StrikeFragment(strikeList), getString(R.string.strike));
                viewPagerAdapter.addFragment(new MyClasses.ConsultationFragment(consultationList), getString(R.string.consultation));
                viewPagerAdapter.addFragment(new MyClasses.MeetingFragment(meetingList), getString(R.string.meeting));
                viewPagerAdapter.addFragment(new MyClasses.HolydayFragment(holydayList), getString(R.string.holyday));
                viewPagerAdapter.addFragment(new MyClasses.ChangeFragment(changeList), getString(R.string.change_of_session));
                viewPagerAdapter.addFragment(new MyClasses.ProcedureFragment(procedureList), getString(R.string.pedag_procedu));
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setIcon(R.drawable.icons8nouvelles26gray);
                tabLayout.getTabAt(1).setIcon(R.drawable.icons8examen24);
                tabLayout.getTabAt(2).setIcon(R.drawable.icons8testpartiellementrus24);
                tabLayout.getTabAt(3).setIcon(R.drawable.icons8strike26);
                tabLayout.getTabAt(4).setIcon(R.drawable.icons8voirlaproprit26);
                tabLayout.getTabAt(5).setIcon(R.drawable.icons8usergroups26);
                tabLayout.getTabAt(6).setIcon(R.drawable.icons8baindesoleil24);
                tabLayout.getTabAt(7).setIcon(R.drawable.icons8remplacer24gris);
                tabLayout.getTabAt(8).setIcon(R.drawable.icons8resume24);
                viewPager.setVisibility(View.VISIBLE);
            }

        }
    }
    }





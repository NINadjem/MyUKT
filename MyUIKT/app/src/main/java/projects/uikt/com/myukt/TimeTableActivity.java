package projects.uikt.com.myukt;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static android.view.View.GONE;

public class TimeTableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    MyClasses.ViewPagerAdapter viewPagerAdapter;
    TextView email_header, neme_header, grp_header;
    de.hdodenhof.circleimageview.CircleImageView avatarHeader;
    SharedPreferences preferences;
    TFragment sun,mon,tue,wed,thur;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        preferences = getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
        if (preferences.getInt("user_type", 0) == 5 || preferences.getInt("user_type", 0) == 2) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.teacher_drawer);
        }

        View view = navigationView.getHeaderView(0);
        avatarHeader = view.findViewById(R.id.avatar_header);
        email_header = view.findViewById(R.id.email_header);
        neme_header = view.findViewById(R.id.username_header);
        grp_header = view.findViewById(R.id.groupe_or_specialty_header);
        preferences = getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
        email_header.setText(preferences.getString("email", ""));
        neme_header.setText(preferences.getString("last_name", "") + " " + preferences.getString("first_name", ""));
        grp_header.setText(preferences.getString("grp_header", ""));
        byte[] decodedString = Base64.decode(preferences.getString("avatar", ""), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        avatarHeader.setImageBitmap(decodedByte);
        tabLayout = findViewById(R.id.tab_layout_news);
        viewPager = findViewById(R.id.view_pager_news);
        progressBar=findViewById(R.id.progress_bar_table);
        /*viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
        thur=new TFragment(6,timeTables);
        wed=new TFragment(5,timeTables);
        tue=new TFragment(4,timeTables);
        mon=new TFragment(3,timeTables);
        sun=new TFragment(2,timeTables);
        viewPagerAdapter.addFragment(sun, getString(R.string.sun));
        viewPagerAdapter.addFragment(mon, getString(R.string.mon));
        viewPagerAdapter.addFragment(tue, getString(R.string.tue));
        viewPagerAdapter.addFragment(wed, getString(R.string.wed));
        viewPagerAdapter.addFragment(thur, getString(R.string.thu));
        //viewPagerAdapter.addFragment(new TFragment(6), getString(R.string.fri));
        //viewPagerAdapter.addFragment(new TFragment(7), getString(R.string.sat));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icons8dimanche26);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8lundi26);
        tabLayout.getTabAt(2).setIcon(R.drawable.icons8mardi26);
        tabLayout.getTabAt(3).setIcon(R.drawable.icons8mercredi26);
        tabLayout.getTabAt(4).setIcon(R.drawable.icons8jeudi26);
        //tabLayout.getTabAt(5).setIcon(R.drawable.icons8vendredi26);
        //tabLayout.getTabAt(6).setIcon(R.drawable.icons8samedi26);
        */
        TimeTableLoader loader=new TimeTableLoader(this);
        loader.execute();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(TimeTableActivity.this, HomeActivity.class));

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNewMenuNewsItem:
                Intent intent=new Intent(TimeTableActivity.this, AddActivity.class);
                intent.putExtra("add_type",4);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (preferences.getInt("user_type", 0) == 5 || preferences.getInt("user_type", 0) == 3) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.add_new_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("ValidFragment")
    public static class TFragment extends Fragment {
        int day;
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout empty;
        View view;
        ArrayList<TimeTableAdapter.mClass> list=null;

        public TFragment(int day,ArrayList<ArrayList<TimeTableAdapter.mClass>> arrayLists) {
            this.day = day;
            if(arrayLists!=null)
            {this.list=arrayLists.get(day);}
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar= view.findViewById(R.id.progress_bar_news);
            progressBar.setVisibility(GONE);
            empty=view.findViewById(R.id.empty_list_txv);
            if(list!=null){

                if (list.size() != 0) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(false);
                    TimeTableAdapter NewsAdapter = new TimeTableAdapter(list);
                    recyclerView.setAdapter(NewsAdapter);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    empty.setVisibility(View.VISIBLE);
                }
            }else{
                Snackbar.make(empty, "pas d'emploi", Snackbar.LENGTH_LONG).
                        setAction("Action", null).show();
            }
            return view;
        }
    }

    public static class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.mViewHolder> {
        ArrayList<mClass> comments;

        public TimeTableAdapter(ArrayList<mClass> comments) {
            this.comments = comments;
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_time_table_item, parent, false);
            mViewHolder mViewHolder = new mViewHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(mViewHolder holder, int position) {
            //Time timeEnd= Time.valueOf(comments.get(position).getTime()).Time.valueOf("1:30").getTime();
            holder.time.setText(comments.get(position).getTime());
            if (comments.get(position).getType() == 1) {
                holder.work.setVisibility(View.VISIBLE);
                //holder.personal.setVisibility(View.GONE);
                holder.session_type.setText(comments.get(position).getSession_type());
                holder.module.setText(comments.get(position).getModule());
                holder.classroom.setText(comments.get(position).getClassroom());
                if (comments.get(position).getTeacher_id() == 0) {
                    holder.teacher.setVisibility(GONE);
                    holder.grp.setVisibility(View.VISIBLE);
                    holder.grp.setText(comments.get(position).getGrp());
                } else {
                    holder.teacher.setVisibility(View.VISIBLE);
                    holder.grp.setVisibility(GONE);
                    holder.teacher.setText(comments.get(position).getTeacher());
                    holder.teacher.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            } else {/*
                holder.personal.setVisibility(View.VISIBLE);
                holder.work.setVisibility(View.GONE);
                holder.activity_name.setText(comments.get(position).getActivity_name());
                holder.activity_place.setText(comments.get(position).activity_place);
                holder.note.setText(comments.get(position).getNote());*/

            }

        }

        @Override
        public int getItemCount() {
            if (comments != null)
                return comments.size();
            else return 0;
        }

        public static class mClass {
            String time, session_type, module, classroom, grp, teacher/*,activity_name,activity_place,note*/;
            int type;
            long teacher_id;

            /* public String getActivity_place() {
                 return activity_place;
             }

             public void setActivity_place(String activity_place) {
                 this.activity_place = activity_place;
             }

             public String getNote() {
                 return note;
             }

             public void setNote(String note) {
                 this.note = note;
             }
     */
            public long getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(long teacher_id) {
                this.teacher_id = teacher_id;
            }

            public mClass(String time, String session_type, String module, String classroom, String grp, String teacher,
                    /* String activity_name, String activity_place, String note,int type,*/  long teacher_id) {
                this.time = time;
                this.session_type = session_type;
                this.module = module;
                this.classroom = classroom;
                this.grp = grp;
                this.teacher = teacher;
            /*this.activity_name = activity_name;
            this.activity_place = activity_place;
            this.note = note;
            this.type = type;*/
                type = 1;
                this.teacher_id = teacher_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getSession_type() {
                return session_type;
            }

            public void setSession_type(String session_type) {
                this.session_type = session_type;
            }

            public String getModule() {
                return module;
            }

            public void setModule(String module) {
                this.module = module;
            }

            public String getClassroom() {
                return classroom;
            }

            public void setClassroom(String classroom) {
                this.classroom = classroom;
            }

            public String getGrp() {
                return grp;
            }

            public void setGrp(String grp) {
                this.grp = grp;
            }

            public String getTeacher() {
                return teacher;
            }

            public void setTeacher(String teacher) {
                this.teacher = teacher;
            }

       /* public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }*/

        }

        public static class mViewHolder extends RecyclerView.ViewHolder {
            TextView time, session_type, module, classroom, grp, teacher/*,activity_name,activity_place,note*/;
            LinearLayout /*personal,*/work;

            public mViewHolder(View itemView) {
                super(itemView);
                time = itemView.findViewById(R.id.layout_time_table_item_activity_time);
                session_type = itemView.findViewById(R.id.layout_time_table_item_session_type);
                module = itemView.findViewById(R.id.layout_time_table_item_session_module);
                classroom = itemView.findViewById(R.id.layout_time_table_item_session_place);
                grp = itemView.findViewById(R.id.layout_time_table_item_session_grp);
                teacher = itemView.findViewById(R.id.layout_time_table_item_session_teacher);
            /*activity_name= itemView.findViewById(R.id.layout_time_table_item_activity_name);
            activity_place= itemView.findViewById(R.id.layout_time_table_item_activity_place);
            note= itemView.findViewById(R.id.layout_time_table_item_activity_note);
            personal= itemView.findViewById(R.id.layout_time_table_item_personal_activities);*/
                work = itemView.findViewById(R.id.layout_time_table_item_work_activities);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.time_table_item) {
            startActivity(new Intent(TimeTableActivity.this, TimeTableActivity.class));
        } else if (id == R.id.news_item) {
            startActivity(new Intent(TimeTableActivity.this, NewsActivity.class));
        } else if (id == R.id.msg_item) {
            startActivity(new Intent(TimeTableActivity.this, MessegesActivity.class));
        } else if (id == R.id.docs_item) {
            startActivity(new Intent(TimeTableActivity.this, DocumentActivity.class));
        } else if (id == R.id.stat_item) {
            startActivity(new Intent(TimeTableActivity.this, AbsenceActivity.class));
        } else if (id == R.id.reminders_item) {
            startActivity(new Intent(TimeTableActivity.this, MarksActivity.class));

        } else if (id == R.id.settings_item) {
            startActivity(new Intent(TimeTableActivity.this, SettingsActivity.class));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(TimeTableActivity.this);
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
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("is_already_connected", false);
                    editor.putLong("id", 0);
                    editor.putString("first_name", "");
                    editor.putString("last_name", "");
                    editor.putString("email", "");
                    editor.putInt("user_type", 0);
                    editor.putString("password", "");
                    editor.putString("sexe", "");
                    editor.putString("avatar", "");
                    editor.putString("grp_header", "");

                    editor.commit();
                    startActivity(new Intent(TimeTableActivity.this, LoginActivity.class));
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

    public class TimeTableLoader extends AsyncTask<Void,Void,ArrayList<ArrayList<TimeTableAdapter.mClass>>> {
        Context context;
        public TimeTableLoader(Context context) {
            this.context=context;
        }

        @Override
        protected ArrayList<ArrayList<TimeTableAdapter.mClass>> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(context);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
            ArrayList<ArrayList<TimeTableAdapter.mClass>> list=dbHelper.getTimeTable(database,preferences.getInt("user_type",0));
            dbHelper.close();
            return list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<TimeTableAdapter.mClass>> timeTables) {
            super.onPostExecute(timeTables);
            viewPagerAdapter = new MyClasses.ViewPagerAdapter(getSupportFragmentManager());
            thur=new TFragment(4,timeTables);
            wed=new TFragment(3,timeTables);
            tue=new TFragment(2,timeTables);
            mon=new TFragment(1,timeTables);
            sun=new TFragment(0,timeTables);
            viewPagerAdapter.addFragment(sun, getString(R.string.sun));
            viewPagerAdapter.addFragment(mon, getString(R.string.mon));
            viewPagerAdapter.addFragment(tue, getString(R.string.tue));
            viewPagerAdapter.addFragment(wed, getString(R.string.wed));
            viewPagerAdapter.addFragment(thur, getString(R.string.thu));
            //viewPagerAdapter.addFragment(new TFragment(6), getString(R.string.fri));
            //viewPagerAdapter.addFragment(new TFragment(7), getString(R.string.sat));
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.getTabAt(0).setIcon(R.drawable.icons8dimanche26);
            tabLayout.getTabAt(1).setIcon(R.drawable.icons8lundi26);
            tabLayout.getTabAt(2).setIcon(R.drawable.icons8mardi26);
            tabLayout.getTabAt(3).setIcon(R.drawable.icons8mercredi26);
            tabLayout.getTabAt(4).setIcon(R.drawable.icons8jeudi26);
            //tabLayout.getTabAt(5).setIcon(R.drawable.icons8vendredi26);
            //tabLayout.getTabAt(6).setIcon(R.drawable.icons8samedi26);
            progressBar.setVisibility(GONE);
            viewPager.setVisibility(View.VISIBLE);
        }
    }

}




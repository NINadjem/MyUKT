package projects.uikt.com.myukt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MarksActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;
    Context context=MarksActivity.this;
    RecyclerView.Adapter adapter;
    LinearLayout emptyLayout;
    ArrayList<RecyclerAdapter.mark> list;
    TextView email_header,neme_header,grp_header;
    SharedPreferences preferences;
    GetAllMarks allMarks;
    de.hdodenhof.circleimageview.CircleImageView avatarHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        recyclerView=findViewById(R.id.marks_recycler_view);
        progressBar=findViewById(R.id.progress_bar_marks);
        emptyLayout=findViewById(R.id.empty_list_txv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        allMarks=new GetAllMarks();
        allMarks.execute();
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

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        list=new ArrayList<>();
        /*list.add(new RecyclerAdapter.mark("Se","1","0","1"));
        list.add(new RecyclerAdapter.mark("Si","1","03","1"));
        list.add(new RecyclerAdapter.mark("compilation","1","02","1"));
        list.add(new RecyclerAdapter.mark("seurité","1","01","11"));
        list.add(new RecyclerAdapter.mark("algom","10","1","12"));
        adapter=new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);*/
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(   MarksActivity.this,HomeActivity.class));
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.time_table_item) {
            startActivity(new Intent(MarksActivity.this,TimeTableActivity.class));
        } else if (id == R.id.news_item) {
            startActivity(new Intent(MarksActivity.this,NewsActivity.class));
        } else if (id == R.id.msg_item) {
            startActivity(new Intent(MarksActivity.this,MessegesActivity.class));
        } else if (id == R.id.docs_item) {
            startActivity(new Intent(MarksActivity.this,DocumentActivity.class));
        } else if (id == R.id.stat_item) {
            startActivity(new Intent(MarksActivity.this,AbsenceActivity.class));
        } else if (id == R.id.reminders_item) {
            //startActivity(new Intent(MarksActivity.this,MarksActivity.class));

        } else if (id == R.id.settings_item) {
            startActivity(new Intent(MarksActivity.this, SettingsActivity.class));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MarksActivity.this);
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
                    startActivity(new Intent(MarksActivity.this, LoginActivity.class));
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
        return true;}
    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.mViewHolder> {
        ArrayList<RecyclerAdapter.mark> list=new ArrayList<>();
        public RecyclerAdapter(ArrayList<RecyclerAdapter.mark> list) {
            this.list = list;
        }

        @Override
        public RecyclerAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_marks_item,parent,false);
            RecyclerAdapter.mViewHolder mViewHolder =new RecyclerAdapter.mViewHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.mViewHolder holder, int position) {
            if(list.get(position).getTp().equals("-1.0"))list.get(position).setTp("/");
            if(list.get(position).getTd().equals("-1.0"))list.get(position).setTd("/");
            if(list.get(position).getExam().equals("-1.0"))list.get(position).setExam("/");
            holder.tp.setText(list.get(position).getTp());
            holder.cours.setText(list.get(position).getExam());
            holder.td.setText(list.get(position).getTd());
            holder.module.setText(list.get(position).getModule());

        }


        @Override
        public int getItemCount() {
            if(list!=null)
                return list.size();
            else return 0;
        }

        public static class mViewHolder extends RecyclerView.ViewHolder {
            TextView module,tp,td,cours;
            public mViewHolder(View itemView) {
                super(itemView);
                module=itemView.findViewById(R.id.module_marks_item);
                tp=itemView.findViewById(R.id.tp_marks_item);
                td=itemView.findViewById(R.id.td_marks_item);
                cours=itemView.findViewById(R.id.exam_marks_item);
            }
        }
        public static class mark{
            String module,tp,td,exam;

            public mark(String module, String tp, String td, String exam) {
                this.module = module;
                this.tp = tp;
                this.td = td;
                this.exam = exam;
            }

            public String getModule() {
                return module;
            }

            public void setModule(String module) {
                this.module = module;
            }

            public String getTp() {
                return tp;
            }

            public void setTp(String tp) {
                this.tp = tp;
            }

            public String getTd() {
                return td;
            }

            public void setTd(String td) {
                this.td = td;
            }

            public String getExam() {
                return exam;
            }

            public void setExam(String cours) {
                this.exam = cours;
            }
        }
    }
    class GetAllMarks extends AsyncTask<Void,Void,ArrayList<RecyclerAdapter.mark>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<RecyclerAdapter.mark> absences) {
            super.onPostExecute(absences);
            progressBar.setVisibility(View.GONE);
            list=absences;
            if(absences.size()!=0){
                emptyLayout.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                layoutManager=new LinearLayoutManager(MarksActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                adapter=new RecyclerAdapter(list);
                recyclerView.setAdapter(adapter);}
            else{
                emptyLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

        }

        @Override
        protected ArrayList<RecyclerAdapter.mark> doInBackground(Void... voids) {

            MyDbHelper dbHelper=new MyDbHelper(MarksActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
            return dbHelper.getAllMarks(database);
        }
    }
}


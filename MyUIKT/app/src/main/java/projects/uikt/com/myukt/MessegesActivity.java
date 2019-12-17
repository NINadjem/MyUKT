package projects.uikt.com.myukt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MessegesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;
    Context context=MessegesActivity.this;
    RecyclerView.Adapter adapter;
    ArrayList<RecyclerAdapter.msgs> list;
    TextView email_header,neme_header,grp_header;
    de.hdodenhof.circleimageview.CircleImageView avatarHeader;
    SharedPreferences preferences;
    LinearLayout emptyLayout;
    GetAllMesseges allMesseges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messeges);
        recyclerView=findViewById(R.id.msgs_recycler_view);
        progressBar=findViewById(R.id.progress_bar_msgs);
        emptyLayout=findViewById(R.id.empty_list_txv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        allMesseges=new GetAllMesseges();
        allMesseges.execute();
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

        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        list=new ArrayList<>();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(   MessegesActivity.this,HomeActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNewMenuNewsItem:
                Intent intent=new Intent(MessegesActivity.this, AddActivity.class);
                intent.putExtra("add_type",2);
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
            startActivity(new Intent(MessegesActivity.this,TimeTableActivity.class));
        } else if (id == R.id.news_item) {
            startActivity(new Intent(MessegesActivity.this,NewsActivity.class));
        } else if (id == R.id.msg_item) {
            startActivity(new Intent(MessegesActivity.this,MessegesActivity.class));
        } else if (id == R.id.docs_item) {
            startActivity(new Intent(MessegesActivity.this,DocumentActivity.class));
        } else if (id == R.id.stat_item) {
            startActivity(new Intent(MessegesActivity.this,AbsenceActivity.class));
        } else if (id == R.id.reminders_item) {
            startActivity(new Intent(MessegesActivity.this,MarksActivity.class));

        } else if (id == R.id.settings_item) {
            startActivity(new Intent(MessegesActivity.this, SettingsActivity.class));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MessegesActivity.this);
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
                    startActivity(new Intent(MessegesActivity.this, LoginActivity.class));
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

    class GetAllMesseges extends AsyncTask<Void,Void,ArrayList<RecyclerAdapter.msgs>>{

        @Override
        protected ArrayList<RecyclerAdapter.msgs> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(MessegesActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
            return dbHelper.getAllMesseges(database,String.valueOf(preferences.getLong("id", 1)));
        }

        @Override
        protected void onPostExecute(ArrayList<RecyclerAdapter.msgs> msgs) {
            super.onPostExecute(msgs);
            progressBar.setVisibility(View.GONE);
            list=msgs;
            SharedPreferences preferences=context.getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
            byte[] decodedString = Base64.decode(preferences.getString("avatar",""), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            if(msgs.size()!=0){
                emptyLayout.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                layoutManager=new LinearLayoutManager(MessegesActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                adapter=new RecyclerAdapter(list,MessegesActivity.this);
                recyclerView.setAdapter(adapter);}
            else{
                emptyLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }
    }
}

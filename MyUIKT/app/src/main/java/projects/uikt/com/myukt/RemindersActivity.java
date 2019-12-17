package projects.uikt.com.myukt;

import android.content.Context;
import android.content.Intent;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class RemindersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
RecyclerView recyclerView;
ProgressBar progressBar;
RecyclerView.LayoutManager layoutManager;
Context context=RemindersActivity.this;
RecyclerView.Adapter adapter;
ArrayList<RecyclerAdapter.reminder> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        recyclerView=findViewById(R.id.reminders_recycler_view);
        progressBar=findViewById(R.id.progress_bar_reminders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        list=new ArrayList<>();
        list.add(new RecyclerAdapter.reminder("Aller au Bain","je doit aller au bain le jeudi inchannlah avant allar a saida"
        ,"03/01/2014",1));
        list.add(new RecyclerAdapter.reminder("All Bain","je doit aller au bain le jeudi inchannlah avant allar a saida"
                ,"03/01/2014",1));
        list.add(new RecyclerAdapter.reminder("Al Bain","je doit aller au bain le jeudi inchannlah avant allar a saida"
                ,"03/01/2014",1));
        list.add(new RecyclerAdapter.reminder("au Bain","je doit aller au bain le jeudi inchannlah avant allar a saida"
                ,"03/01/2014",1));
        list.add(new RecyclerAdapter.reminder("Aller au Bain","je doit aller au bain le jeudi inchannlah avant allar a saida"
                ,"03/01/2014",1));
        list.add(new RecyclerAdapter.reminder("Bain","je doit aller au bain le jeudi inchannlah avant allar a saida"
                ,"03/01/2014",1));
        list.add(new RecyclerAdapter.reminder("Aller","je doit aller au bain le jeudi inchannlah avant allar a saida"
                ,"03/01/2014",1));
        list.add(new RecyclerAdapter.reminder("Aller au Bain","je doit aller au bain le jeudi inchannlah avant allar a saida"
                ,"03/01/2014",1));
        adapter=new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNewMenuNewsItem:
                startActivity(new Intent(RemindersActivity.this,AddActivity.class));
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
            startActivity(new Intent(RemindersActivity.this,TimeTableActivity.class));
        } else if (id == R.id.news_item) {
            startActivity(new Intent(RemindersActivity.this,NewsActivity.class));
        } else if (id == R.id.msg_item) {
            startActivity(new Intent(RemindersActivity.this,MessegesActivity.class));
        } else if (id == R.id.docs_item) {
            startActivity(new Intent(RemindersActivity.this,DocumentActivity.class));
        } else if (id == R.id.stat_item) {
            startActivity(new Intent(RemindersActivity.this,HomeActivity.class));
        } else if (id == R.id.reminders_item) {
            startActivity(new Intent(RemindersActivity.this,RemindersActivity.class));

        } else if (id == R.id.settings_item) {
            startActivity(new Intent(RemindersActivity.this, SettingsActivity.class));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(RemindersActivity.this);
            View view = getLayoutInflater().inflate(R.layout.log_out_dialogue, null);
            Button logOut, cancel;
            logOut = view.findViewById(R.id.log_out);
            cancel = view.findViewById(R.id.undo);
            builder.setView(view);
            final AlertDialog alertDialog = builder.create();
            logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                    startActivity(new Intent(RemindersActivity.this, LoginActivity.class));
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
    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.mViewHolder> {
        ArrayList<reminder> list=new ArrayList<>();
        public RecyclerAdapter(ArrayList<reminder> list) {
            this.list = list;
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_reminder_item,parent,false);
            mViewHolder mViewHolder =new mViewHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(mViewHolder holder, int position) {
            holder.title.setText(list.get(position).getTitle());
            holder.date.setText(list.get(position).getDate());
            holder.des.setText(list.get(position).getDes());
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


        @Override
        public int getItemCount() {
            if(list!=null)
            return list.size();
            else return 0;
        }

        public static class mViewHolder extends RecyclerView.ViewHolder {
            TextView title,des,date;
            ImageView edit;
            public mViewHolder(View itemView) {
                super(itemView);
                edit=itemView.findViewById(R.id.layout_reminder_item_edit);
                title=itemView.findViewById(R.id.layout_reminder_item_name);
                des=itemView.findViewById(R.id.layout_reminder_item_note);
                date=itemView.findViewById(R.id.layout_reminder_item_date);
            }
        }
        public static class reminder{
            String title,des,date;
            long id;

            public reminder(String title, String des, String date, long id) {
                this.title = title;
                this.des = des;
                this.date = date;
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }
        }
    }

}

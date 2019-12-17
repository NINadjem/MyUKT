package projects.uikt.com.myukt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AbsenceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView email_header,neme_header,grp_header;
    de.hdodenhof.circleimageview.CircleImageView avatarHeader;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;
    Context context=AbsenceActivity.this;
    RecyclerView.Adapter adapter;
    ArrayList<RecyclerAdapter.absence> list;
    SharedPreferences preferences;
    LinearLayout emptyLayout;
    GetAllAbsence allAbsence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);
        recyclerView=findViewById(R.id.absences_recycler_view);
        progressBar=findViewById(R.id.progress_bar_absences);
        emptyLayout=findViewById(R.id.empty_list_txv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view=navigationView.getHeaderView(0);
        avatarHeader=view.findViewById(R.id.avatar_header);
        email_header=view.findViewById(R.id.email_header);
        neme_header=view.findViewById(R.id.username_header);
        grp_header=view.findViewById(R.id.groupe_or_specialty_header);
        preferences=getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
        if (preferences.getInt("user_type", 0) == 5 || preferences.getInt("user_type", 0) == 2) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.teacher_drawer);
        }
        email_header.setText(preferences.getString("email",""));
        neme_header.setText(preferences.getString("last_name","")+" "+preferences.getString("first_name",""));
        grp_header.setText(preferences.getString("grp_header",""));
        byte[] decodedString = Base64.decode(preferences.getString("avatar",""), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        avatarHeader.setImageBitmap(decodedByte);
        allAbsence=new GetAllAbsence();
        allAbsence.execute();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(AbsenceActivity.this,HomeActivity.class));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.absence_menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.time_table_item) {
            startActivity(new Intent(AbsenceActivity.this,TimeTableActivity.class));
        } else if (id == R.id.news_item) {
            startActivity(new Intent(AbsenceActivity.this,NewsActivity.class));
        } else if (id == R.id.msg_item) {
            startActivity(new Intent(AbsenceActivity.this,MessegesActivity.class));
        } else if (id == R.id.docs_item) {
            startActivity(new Intent(AbsenceActivity.this,DocumentActivity.class));
        } else if (id == R.id.stat_item) {
            //startActivity(new Intent(AbsenceActivity.this,HomeActivity.class));
        } else if (id == R.id.reminders_item) {
            startActivity(new Intent(AbsenceActivity.this,MarksActivity.class));

        } else if (id == R.id.settings_item) {
            startActivity(new Intent(AbsenceActivity.this, SettingsActivity.class));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(AbsenceActivity.this);
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
                    startActivity(new Intent(AbsenceActivity.this, LoginActivity.class));
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
        ArrayList<RecyclerAdapter.absence> list=new ArrayList<>();
        public RecyclerAdapter(ArrayList<RecyclerAdapter.absence> list) {
            this.list = list;
        }

        @Override
        public RecyclerAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_absence_item,parent,false);
            RecyclerAdapter.mViewHolder mViewHolder =new RecyclerAdapter.mViewHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.mViewHolder holder, int position) {
            holder.cours.setText(list.get(position).getCours());
            holder.tp.setText(list.get(position).getTp());
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
                module=itemView.findViewById(R.id.module_absence_item);
                tp=itemView.findViewById(R.id.tp_absence_item);
                td=itemView.findViewById(R.id.td_absence_item);
                cours=itemView.findViewById(R.id.cours_absence_item);
                }
        }
        public static class absence{
            String module,tp,td,cours;

            public absence(String module, String tp, String td, String cours) {
                this.module = module;
                this.tp = tp;
                this.td = td;
                this.cours = cours;
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

            public String getCours() {
                return cours;
            }

            public void setCours(String cours) {
                this.cours = cours;
            }
        }
    }

    public class GpsTracker implements LocationListener {

        Context context;

        public GpsTracker(Context context) {
            super();
            this.context = context;
        }

        public Location getLocation() {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.e("fist", "error");
                return null;
            }
            try {
                LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
                boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (isGPSEnabled) {
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600, 10, this);
                    Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    lm.removeUpdates(this);
                    return loc;
                } else {
                    Log.e("sec", "errpr");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onLocationChanged(Location location) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.searchMenuItem) {
            startActivity(new Intent(AbsenceActivity .this, MainActivity.class));
        } else {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.e("fist", "error");
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                Location location = new GpsTracker(this).getLocation();
                if (location == null) {
                    Snackbar.make(avatarHeader,getString(R.string.error_happend_retry) ,Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    Snackbar.make(avatarHeader,getString(R.string.sharing_location) ,Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                            createUrl(location.getAltitude(),location.getLongitude(),location.getLatitude())
                            , null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if(response.getInt("params")!=0 ){
                                    int id_new=response.getInt("id_new");
                                    if(response.getInt("add_journal")!=0&&id_new!=0){
                                        Snackbar.make(avatarHeader, R.string.ur_location_is_shared,Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Snackbar.make(avatarHeader,getString(R.string.error_happend_retry) ,Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    });
                    mySinglton.getmInstance(this).addToRequestQue(jsonObjectRequest);
                }
            }



        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Location location = new GpsTracker(this).getLocation();
                    if (location == null) {
                        Toast.makeText(this, "vide", Toast.LENGTH_LONG);
                    } else {
                        Toast.makeText(this, "location" + location.getLatitude()
                                + " * " + location.getLongitude() + " * " + location.getAltitude(), Toast.LENGTH_LONG);
                    }

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public String createUrl(double alt,double lon,double lat){
        SharedPreferences preferences=getApplicationContext().getSharedPreferences(MyClasses.SHARED_PREFRENCES,MODE_PRIVATE);
        long id=preferences.getLong("id",0);
        Uri uri;
        uri=Uri.parse(MyClasses.SHARE_LOCATION_URL).buildUpon().
                    appendQueryParameter("id_user",String.valueOf(id)).appendQueryParameter("alt",String.valueOf(alt)).
        appendQueryParameter("lat",String.valueOf(lat)).appendQueryParameter("long",String.valueOf(lon))
                    .build();
        return  uri.toString();
    }

    class GetAllAbsence extends AsyncTask<Void,Void,ArrayList<RecyclerAdapter.absence>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<RecyclerAdapter.absence> absences) {
            super.onPostExecute(absences);
            progressBar.setVisibility(View.GONE);
            list=absences;
            if(absences.size()!=0){
                emptyLayout.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                layoutManager=new LinearLayoutManager(AbsenceActivity.this);
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
        protected ArrayList<RecyclerAdapter.absence> doInBackground(Void... voids) {
            MyDbHelper dbHelper=new MyDbHelper(AbsenceActivity.this);
            SQLiteDatabase database=dbHelper.getReadableDatabase();
           dbHelper.addAnAbsence(database,new MyClasses.Absence("34","5"));
            dbHelper.addAnAbsence(database,new MyClasses.Absence("34","1"));
            dbHelper.addAnAbsence(database,new MyClasses.Absence("31","5"));
            dbHelper.addAnAbsence(database,new MyClasses.Absence("33","5"));
            dbHelper.addAnAbsence(database,new MyClasses.Absence("33","1"));
            dbHelper.addAnAbsence(database,new MyClasses.Absence("33","2"));
            dbHelper.addAnAbsence(database,new MyClasses.Absence("33","5"));
            dbHelper.addAnAbsence(database,new MyClasses.Absence("33","5"));
            dbHelper.addAnAbsence(database,new MyClasses.Absence("34","5"));
            dbHelper.addAnAbsence(database,new MyClasses.Absence("31","5"));
            return dbHelper.getAllAbsences(database);
        }
    }
}

package projects.uikt.com.myukt;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.Equalizer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView email_header, neme_header, grp_header, hiTXV, your_marks_txv;
    de.hdodenhof.circleimageview.CircleImageView avatarHeader, avatarImgView;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*Intent i=new Intent(getApplicationContext(),MyClasses.MySyncService.class);
        startService(i);*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AddActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        preferences = getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (preferences.getInt("user_type", 0) == 5 || preferences.getInt("user_type", 0) == 2) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.teacher_drawer);
        }

        View view = navigationView.getHeaderView(0);
        avatarHeader = view.findViewById(R.id.avatar_header);
        email_header = view.findViewById(R.id.email_header);
        neme_header = view.findViewById(R.id.username_header);
        grp_header = view.findViewById(R.id.groupe_or_specialty_header);

        email_header.setText(preferences.getString("email", ""));
        neme_header.setText(preferences.getString("last_name", "") + " " + preferences.getString("first_name", ""));
        grp_header.setText(preferences.getString("grp_header", ""));
        byte[] decodedString = Base64.decode(preferences.getString("avatar", ""), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        avatarHeader.setImageBitmap(decodedByte);

        avatarImgView = findViewById(R.id.avatar_home);
        hiTXV = findViewById(R.id.hi_txv_home);
        your_marks_txv = findViewById(R.id.vos_notes_txv);
        avatarImgView.setImageBitmap(decodedByte);
        hiTXV.setText("Salut " + preferences.getString("first_name", ""));
        if (preferences.getInt("user_type", 0) == 5 || preferences.getInt("user_type", 0) == 2) {
            your_marks_txv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.searchMenuItem) {
            startActivity(new Intent(HomeActivity .this, MainActivity.class));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.time_table_item) {
            startActivity(new Intent(HomeActivity.this, TimeTableActivity.class));
        } else if (id == R.id.news_item) {
            startActivity(new Intent(HomeActivity.this, NewsActivity.class));
        } else if (id == R.id.msg_item) {
            startActivity(new Intent(HomeActivity.this, MessegesActivity.class));
        } else if (id == R.id.docs_item) {
            startActivity(new Intent(HomeActivity.this, DocumentActivity.class));
        } else if (id == R.id.stat_item) {
            startActivity(new Intent(HomeActivity.this, AbsenceActivity.class));
        } else if (id == R.id.reminders_item) {
            startActivity(new Intent(HomeActivity.this, MarksActivity.class));
        } else if (id == R.id.settings_item) {
            startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            View view = getLayoutInflater().inflate(R.layout.log_out_dialogue, null);
            TextView logOut, cancel;
            logOut = view.findViewById(R.id.log_out);
            cancel = view.findViewById(R.id.undo);
            builder.setView(view);
            final AlertDialog alertDialog = builder.create();
            logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                    alertDialog.cancel();
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
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

    public class GpsTracker implements LocationListener {

        Context context;

        public GpsTracker(Context context) {
            super();
            this.context = context;
        }

        public Location getLocation() {
            if (ContextCompat.checkSelfPermission(context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.e("fist", "error");
                return null;
            }
            try {
                LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
                boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (isGPSEnabled) {
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 10, this);
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
                        Toast.makeText(this, "location" + location.getLatitude() + " * " + location.getLongitude() + " * " + location.getAltitude(), Toast.LENGTH_LONG);
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
}


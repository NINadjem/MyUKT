package projects.uikt.com.myukt;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.view.View.GONE;
import static java.lang.Thread.sleep;

/**
 * Created by Imen on 11/03/2018.
 */
          /* $news_type=array(0=>"news",1=>"change_of_session",2=>"consultation",3=>"exam",4=>"holiday",5=>"marks_displayed",
            6=>"meeting",0=>"news",8=>"pedagogical-procedure",9=>"strike");*/
public class MyClasses {

    public static final String PATH = "http://192.168.1.6/UKTserver/", AVATARS_URL = PATH + "avatars/", AVATARS_FORMAT = ".jpg",
            GET_SPECIALTY_URL = PATH + "getSpecialty.php", GET_STABLE_DATA_URL = PATH + "getAllStableData.php",
            GET_TEACHER_TIME_TABLE_URL = PATH + "getTeacherTimeTable.php", GET_GROUPE_TIME_TABLE_URL = PATH + "getGroupeTimeTable.php",
            INSERT_A_MSG_URL = PATH + "writeAmsg.php", INSERT_A_SEEN = PATH + "seeMsg.php",
            GET_NEWS_URL = PATH + "getNews.php", SHARE_LOCATION_URL = PATH + "shareLocation.php", GET_ALL_USERS_URL = PATH + "getAllUsers.php",
            LOGIN_URL = PATH + "loginMobile.php", PARAMS = "params", RESULT = "result", SHARED_PREFRENCES = "projects.uikt.com.myukt.info",
            GET_ALL_DOCS_URL = PATH + "getAllDocs.php", GET_ALL_MARKS_URL = PATH + "getMarks.php", VOTE_URL = PATH + "vote.php",
            GET_ALL_MSG_URL = PATH + "getMsgs.php", GET_VOTES = PATH + "getAllVotes.php",SYNC=PATH+"Sync.php",
    GETUSERS=PATH+"getU.php",addDocUrl=PATH+"addADoc.php",WriteAmsg=PATH+"writeAmsg.php";

    public static class SpinnerItem {
        private String name;
        private int image;

        public SpinnerItem(String Name, int Image) {
            name = Name;
            image = Image;
        }

        public String getItemName() {
            return name;
        }

        public int getItemImage() {
            return image;
        }

    }
    public static class ProfilSpinnerItem {
        private String name;
        private Bitmap image;
        private String id;
        public ProfilSpinnerItem(String Name,Bitmap Image,String id) {
            name = Name;
            image = Image;
            this.id=id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getItemName() {
            return name;
        }

        public Bitmap getItemImage() {
            return image;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImage(Bitmap image) {
            this.image = image;
        }
    }
    public static class SpinnerAdapter extends ArrayAdapter<SpinnerItem> {

        public SpinnerAdapter(Context context, ArrayList<SpinnerItem> countryList) {
            super(context, 0, countryList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        private View initView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.add_type_item, parent, false
                );
            }

            ImageView imageViewImage = convertView.findViewById(R.id.image_spinner_row);
            TextView textViewName = convertView.findViewById(R.id.name_spinner_row);

            SpinnerItem currentItem = getItem(position);

            if (currentItem != null) {
                imageViewImage.setImageResource(currentItem.getItemImage());
                textViewName.setText(currentItem.getItemName());
            }

            return convertView;
        }
    }

    public static class SpinnerProfilAdapter extends ArrayAdapter<ProfilSpinnerItem> {

        public SpinnerProfilAdapter(Context context, ArrayList<ProfilSpinnerItem> countryList) {
            super(context, 0, countryList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        private View initView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.choose_a_profil, parent, false
                );
            }

            ImageView imageViewImage = convertView.findViewById(R.id.image_spinner_row);
            TextView textViewName = convertView.findViewById(R.id.name_spinner_row);

            ProfilSpinnerItem currentItem = getItem(position);

            if (currentItem != null) {
                imageViewImage.setImageBitmap(currentItem.getItemImage());
                textViewName.setText(currentItem.getItemName());
            }

            return convertView;
        }
    }


    @SuppressLint("ValidFragment")
    public static class DocDoneFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.DocDoneAdapter.mClass> list;

        public DocDoneFragment(ArrayList<MyClasses.DocDoneAdapter.mClass> list) {
            this.list = list;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            progressBar.setVisibility(GONE);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_ready_req);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerlefichierfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.DocDoneAdapter adapter = new DocDoneAdapter(list);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            return view;
        }

    }

    public static class DocDoneAdapter extends RecyclerView.Adapter<DocDoneAdapter.mCommentsHolder> {
        ArrayList<mClass> comments;

        public DocDoneAdapter(ArrayList<mClass> comments) {
            this.comments = comments;
        }

        @Override
        public mCommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_request_done_item, parent, false);
            mCommentsHolder mViewHolder = new mCommentsHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(mCommentsHolder holder, int position) {
            holder.doc.setText(comments.get(position).getDoc());
            holder.reason.setText(comments.get(position).getReason());
            holder.date.setText(comments.get(position).getDate());
            holder.est.setText(comments.get(position).getEst());
            holder.admin.setText(comments.get(position).getAdminName());
            holder.admin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

        @Override
        public int getItemCount() {
            if (comments != null)
                return comments.size();
            else return 0;
        }

        public static class mClass {
            String doc, reason, est, adminName, date;
            long id_admin;

            public mClass(String doc, String reason, String est, String adminName, String date, long id_admin) {
                this.doc = doc;
                this.reason = reason;
                this.est = est;
                this.adminName = adminName;
                this.date = date;
                this.id_admin = id_admin;
            }

            public String getDoc() {
                return doc;
            }

            public void setDoc(String doc) {
                this.doc = doc;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getEst() {
                return est;
            }

            public void setEst(String est) {
                this.est = est;
            }

            public String getAdminName() {
                return adminName;
            }

            public void setAdminName(String adminName) {
                this.adminName = adminName;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public long getId_admin() {
                return id_admin;
            }

            public void setId_admin(long id_admin) {
                this.id_admin = id_admin;
            }
        }

        public static class mCommentsHolder extends RecyclerView.ViewHolder {
            TextView doc, date, est, reason, admin;

            public mCommentsHolder(View itemView) {
                super(itemView);
                date = itemView.findViewById(R.id.layout_news_vote_item_date);
                this.doc = itemView.findViewById(R.id.layout_doc_request_done_item_doc);
                this.date = itemView.findViewById(R.id.layout_doc_request_done_item_date);
                this.reason = itemView.findViewById(R.id.layout_doc_request_done_item_reason);
                this.est = itemView.findViewById(R.id.layout_doc_request_done_item_esth);
                this.admin = itemView.findViewById(R.id.layout_doc_request_done_item_admin);
            }
        }
    }

    @SuppressLint("ValidFragment")
    public static class DocFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<DocAdapter.mClass> list;

        public DocFragment(ArrayList<DocAdapter.mClass> list) {
            this.list = list;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            progressBar.setVisibility(GONE);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_req_sent);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerlefichierfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.DocAdapter adapter = new DocAdapter(list);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            return view;
        }
    }

    public static class DocAdapter extends RecyclerView.Adapter<DocAdapter.mCommentsHolder> {
        ArrayList<mClass> comments;

        public DocAdapter(ArrayList<mClass> comments) {
            this.comments = comments;
        }

        @Override
        public mCommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_doc_request_item, parent, false);
            mCommentsHolder mViewHolder = new mCommentsHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(mCommentsHolder holder, int position) {
            holder.date.setText(comments.get(position).getDate());
            holder.doc.setText(comments.get(position).getDoc());
            holder.reason.setText(comments.get(position).getReason());
        }

        @Override
        public int getItemCount() {
            if (comments != null)
                return comments.size();
            else return 0;
        }

        public static class mClass {
            String doc, reason, date;

            public mClass(String doc, String reason, String date) {
                this.doc = doc;
                this.reason = reason;
                this.date = date;
            }

            public String getDoc() {
                return doc;
            }

            public void setDoc(String doc) {
                this.doc = doc;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }


            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

        }

        public static class mCommentsHolder extends RecyclerView.ViewHolder {
            TextView doc, date, reason;

            public mCommentsHolder(View itemView) {
                super(itemView);
                date = itemView.findViewById(R.id.layout_doc_request_item_date);
                this.doc = itemView.findViewById(R.id.layout_doc_request_item_doc);
                this.reason = itemView.findViewById(R.id.layout_doc_request_item_reason);
            }
        }
    }

    public static class Comments {
        Bitmap userAvatar;
        String name, reason, date;
        Boolean agree;
        int id_user;

        public Comments(int id_user, Bitmap userAvatar, String name, String reason, Boolean agree, String date) {
            this.userAvatar = userAvatar;
            this.name = name;
            this.reason = reason;
            this.agree = agree;
            this.date = date;
            this.id_user = id_user;
        }

        public int getId_user() {
            return id_user;
        }

        public void setId_user(int id_user) {
            this.id_user = id_user;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Bitmap getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(Bitmap userAvatar) {
            this.userAvatar = userAvatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Boolean getAgree() {
            return agree;
        }

        public void setAgree(Boolean agree) {
            this.agree = agree;
        }
    }

    public static class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.mCommentsHolder> {
        ArrayList<Comments> comments;
        Context context;

        public CommentsAdapter(ArrayList<Comments> comments, Context context1) {
            this.comments = comments;
            context = context1;
        }

        @Override
        public mCommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_vote_item, parent, false);
            mCommentsHolder mViewHolder = new mCommentsHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(mCommentsHolder holder, int position) {
            holder.avatar.setImageBitmap(comments.get(position).getUserAvatar());
            holder.userName.setText(comments.get(position).getName());
            holder.reason.setText(comments.get(position).getReason());
            holder.date.setText(comments.get(position).getDate());

            if (!comments.get(position).getAgree()) {
                holder.agree.setVisibility(View.GONE);
                holder.dontAgree.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            if (comments != null)
                return comments.size();
            else return 0;
        }

        public static class mCommentsHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView userName, agree, dontAgree, reason, date;

            public mCommentsHolder(View itemView) {
                super(itemView);
                date = itemView.findViewById(R.id.layout_news_vote_item_date);
                this.avatar = itemView.findViewById(R.id.layout_news_vote_item_poster_avatar);
                this.userName = itemView.findViewById(R.id.layout_news_vote_item_poster_name);
                this.reason = itemView.findViewById(R.id.layout_news_vote_item_poster_reason);
                this.dontAgree = itemView.findViewById(R.id.layout_news_vote_item_poster_dont_agree);
                this.agree = itemView.findViewById(R.id.layout_news_vote_item_poster_agree);
            }
        }
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> titleArrayList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentArrayList.add(fragment);
            titleArrayList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleArrayList.get(position);
        }

    }

    @SuppressLint("ValidFragment")
    public static class NewsFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.RecyclerNewsAdapter.mClass> list;

        public NewsFragment(ArrayList<MyClasses.RecyclerNewsAdapter.mClass> list) {
            // Required empty public constructor
            this.list = list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_news);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerles_publicitsfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.RecyclerNewsAdapter adapter = new RecyclerNewsAdapter(list, getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(GONE);
            return view;
        }

    }

    public static class RecyclerNewsAdapter extends RecyclerView.Adapter<RecyclerNewsAdapter.mViewHolder> {
        ArrayList<mClass> list = new ArrayList<>();
        Context context;
        Dialog dialog;
        String id_user;

        public RecyclerNewsAdapter(ArrayList<mClass> list, Context context) {
            this.list = list;
            this.context = context;
            SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, Context.MODE_PRIVATE);
            id_user = String.valueOf(preferences.getLong("id", 0));
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, parent, false);
            final mViewHolder holder = new mViewHolder(view);

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_avez_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "1";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_n_avez_pas_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "0";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.commentRecyclerView.getVisibility() != View.VISIBLE) {
                        holder.commentRecyclerView.setVisibility(View.VISIBLE);
                    } else holder.commentRecyclerView.setVisibility(View.GONE);
                }
            });
            return holder;
        }

        String createUrl(String id_user, String id_post, String agree, String reason) {
            Uri uri = Uri.parse(MyClasses.VOTE_URL).buildUpon().appendQueryParameter("id_user", id_user)
                    .appendQueryParameter("id_post", id_post).appendQueryParameter("agree", agree)
                    .appendQueryParameter("reason", reason).build();
            return uri.toString();
        }

        @Override
        public void onBindViewHolder(final mViewHolder holder, int position) {
            holder.avatar.setImageBitmap(list.get(position).getUserAvatar());
            holder.name.setText(list.get(position).getUserName());
            holder.specialty.setText(list.get(position).getUserSpecialty());
            holder.pubdate.setText(list.get(position).getPubDate());
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getContent());
            holder.like.setText(String.valueOf(list.get(position).getNumLike()));
            holder.dislike.setText(String.valueOf(list.get(position).getNumDislike()));
            holder.comment.setText(String.valueOf(list.get(position).getNumComment()));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            holder.commentRecyclerView.setLayoutManager(layoutManager);
            holder.commentRecyclerView.setHasFixedSize(false);
            CommentsAdapter commentsAdapter;
            commentsAdapter = new CommentsAdapter(list.get(position).getComments(), context);
            holder.commentRecyclerView.setAdapter(commentsAdapter);

        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView name, specialty, pubdate, title, content, New, old, like, dislike, comment;
            RecyclerView commentRecyclerView;

            public mViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.layout_news_item_poster_avatar);
                name = itemView.findViewById(R.id.layout_news_item_poster_name);
                specialty = itemView.findViewById(R.id.layout_news_item_specialty_poster);
                pubdate = itemView.findViewById(R.id.layout_procedure_item_date);
                title = itemView.findViewById(R.id.layout_news_item_title);
                content = itemView.findViewById(R.id.layout_news_item_content);
                like = itemView.findViewById(R.id.layout_news_item_like_number);
                dislike = itemView.findViewById(R.id.layout_news_item_dislike_number);
                comment = itemView.findViewById(R.id.layout_news_item_comment_number);
                commentRecyclerView = itemView.findViewById(R.id.layout_news_item_comments_recycler_view);
            }

        }

        public static class mClass {
            Bitmap userAvatar;
            String userName, userSpecialty, pubDate, title, content;
            int numLike, numDislike, numComment;
            int id_news, id_poster;
            ArrayList<Comments> comments;

            public ArrayList<Comments> getComments() {
                return comments;
            }

            public void setComments(ArrayList<Comments> comments) {
                this.comments = comments;
            }

            public mClass(int id_news, int id_poster, Bitmap userAvatar, String userName, String userSpecialty, String pubDate, String title, String content,
                          int numLike, int numDislike, int numComment, ArrayList<Comments> c) {
                this.userAvatar = userAvatar;
                this.id_news = id_news;
                this.id_poster = id_poster;
                this.userName = userName;
                this.userSpecialty = userSpecialty;
                this.pubDate = pubDate;
                this.title = title;
                this.content = content;
                this.numLike = numLike;
                this.numDislike = numDislike;
                this.numComment = numComment;
                comments = c;
            }

            public int getId_news() {
                return id_news;
            }

            public void setId_news(int id_news) {
                this.id_news = id_news;
            }

            public Bitmap getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(Bitmap userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSpecialty() {
                return userSpecialty;
            }

            public void setUserSpecialty(String userSpecialty) {
                this.userSpecialty = userSpecialty;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getNumLike() {
                return numLike;
            }

            public void setNumLike(int numLike) {
                this.numLike = numLike;
            }

            public int getNumDislike() {
                return numDislike;
            }

            public void setNumDislike(int numDislike) {
                this.numDislike = numDislike;
            }

            public int getNumComment() {
                return numComment;
            }

            public void setNumComment(int numComment) {
                this.numComment = numComment;
            }
        }
    }

    @SuppressLint("ValidFragment")
    public static class ChangeFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.RecyclerChangeAdapter.mClass> list;

        public ChangeFragment(ArrayList<MyClasses.RecyclerChangeAdapter.mClass> list) {
            this.list = list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_news);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerles_publicitsfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.RecyclerChangeAdapter adapter = new RecyclerChangeAdapter(list, getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(GONE);
            return view;
        }
    }

    public static class RecyclerChangeAdapter extends RecyclerView.Adapter<RecyclerChangeAdapter.mViewHolder> {
        ArrayList<mClass> list = new ArrayList<>();
        Context context;
        String id_user;

        public RecyclerChangeAdapter(ArrayList<mClass> list, Context context) {
            this.list = list;
            this.context = context;
            SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, Context.MODE_PRIVATE);
            id_user = String.valueOf(preferences.getLong("id", 0));
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_change_of_session_item, parent, false);
            final mViewHolder holder = new mViewHolder(view);

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_avez_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_new()), agree = "1";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_n_avez_pas_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_new()), agree = "0";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.commentRecyclerView.getVisibility() != View.VISIBLE) {
                        holder.commentRecyclerView.setVisibility(View.VISIBLE);
                    } else holder.commentRecyclerView.setVisibility(View.GONE);
                }
            });
            return holder;
        }

        String createUrl(String id_user, String id_post, String agree, String reason) {
            Uri uri = Uri.parse(MyClasses.VOTE_URL).buildUpon().appendQueryParameter("id_user", id_user)
                    .appendQueryParameter("id_post", id_post).appendQueryParameter("agree", agree)
                    .appendQueryParameter("reason", reason).build();
            return uri.toString();
        }

        @Override
        public void onBindViewHolder(final mViewHolder holder, int position) {

            holder.avatar.setImageBitmap(list.get(position).getUserAvatar());
            holder.name.setText(list.get(position).getUserName());
            holder.specialty.setText(list.get(position).getUserSpecialty());
            holder.pubdate.setText(list.get(position).getPubDate());
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getContent());
            holder.like.setText(String.valueOf(list.get(position).getNumLike()));
            holder.dislike.setText(String.valueOf(list.get(position).getNumDislike()));
            holder.comment.setText(String.valueOf(list.get(position).getNumComment()));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            holder.commentRecyclerView.setLayoutManager(layoutManager);
            holder.commentRecyclerView.setHasFixedSize(false);
            CommentsAdapter commentsAdapter;
            commentsAdapter = new CommentsAdapter(list.get(position).getComments(), context);
            holder.commentRecyclerView.setAdapter(commentsAdapter);
        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView name, specialty, pubdate, title, content, New, old, like, dislike, comment;
            RecyclerView commentRecyclerView;

            public mViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.layout_change_item_poster_avatar);
                name = itemView.findViewById(R.id.layout_change_item_poster_name);
                specialty = itemView.findViewById(R.id.layout_change_item_specialty_poster);
                pubdate = itemView.findViewById(R.id.layout_change_item_date);
                title = itemView.findViewById(R.id.layout_change_item_title);
                content = itemView.findViewById(R.id.layout_change_item_content);
                like = itemView.findViewById(R.id.layout_change_item_like_number);
                old = itemView.findViewById(R.id.layout_change_item_old);
                New = itemView.findViewById(R.id.layout_change_item_old);
                dislike = itemView.findViewById(R.id.layout_change_item_dislike_number);
                comment = itemView.findViewById(R.id.layout_change_item_comment_number);
                commentRecyclerView = itemView.findViewById(R.id.layout_change_item_comments_recycler_view);
            }
        }

        public static class mClass {
            Bitmap userAvatar;
            String userName, userSpecialty, pubDate, title, content;
            int numLike, numDislike, numComment, id_new, id_poster;
            long idOld, idNew;

            public long getIdOld() {
                return idOld;
            }

            public void setIdOld(long idOld) {
                this.idOld = idOld;
            }

            public long getIdNew() {
                return idNew;
            }

            public void setIdNew(long idNew) {
                this.idNew = idNew;
            }

            ArrayList<Comments> comments;

            public ArrayList<Comments> getComments() {
                return comments;
            }

            public void setComments(ArrayList<Comments> comments) {
                this.comments = comments;
            }

            public mClass(int id_news, int id_poster, Bitmap userAvatar, String userName, String userSpecialty, String pubDate, String title, String content,
                          int numLike, int numDislike, int numComment, ArrayList<Comments> c, long idOld, long idNew) {
                this.idOld = idOld;
                this.idNew = idNew;
                this.userAvatar = userAvatar;
                this.userName = userName;
                this.userSpecialty = userSpecialty;
                this.pubDate = pubDate;
                this.title = title;
                this.content = content;
                this.numLike = numLike;
                this.id_new = id_news;
                this.id_poster = id_poster;
                this.numDislike = numDislike;
                this.numComment = numComment;
                comments = c;
            }

            public Bitmap getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(Bitmap userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSpecialty() {
                return userSpecialty;
            }

            public void setUserSpecialty(String userSpecialty) {
                this.userSpecialty = userSpecialty;
            }

            public int getId_new() {
                return id_new;
            }

            public void setId_new(int id_new) {
                this.id_new = id_new;
            }

            public int getId_poster() {
                return id_poster;
            }

            public void setId_poster(int id_poster) {
                this.id_poster = id_poster;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getNumLike() {
                return numLike;
            }

            public void setNumLike(int numLike) {
                this.numLike = numLike;
            }

            public int getNumDislike() {
                return numDislike;
            }

            public void setNumDislike(int numDislike) {
                this.numDislike = numDislike;
            }

            public int getNumComment() {
                return numComment;
            }

            public void setNumComment(int numComment) {
                this.numComment = numComment;
            }
        }
    }

    @SuppressLint("ValidFragment")
    public static class MarksDisplayedFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.RecyclerMarksDAdapter.mClass> list;

        public MarksDisplayedFragment(ArrayList<MyClasses.RecyclerMarksDAdapter.mClass> list) {
            // Required empty public constructor
            this.list = list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_news);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerles_publicitsfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.RecyclerMarksDAdapter adapter = new RecyclerMarksDAdapter(list, getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }

            progressBar.setVisibility(GONE);

            return view;
        }

    }

    public static class RecyclerMarksDAdapter extends RecyclerView.Adapter<RecyclerMarksDAdapter.mViewHolder> {
        ArrayList<mClass> list = new ArrayList<>();
        Context context;
        String id_user;

        public RecyclerMarksDAdapter(ArrayList<mClass> list, Context context) {
            this.list = list;
            this.context = context;
            SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, Context.MODE_PRIVATE);
            id_user = String.valueOf(preferences.getLong("id", 0));
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_marks_displayed_item, parent, false);
            final mViewHolder holder = new mViewHolder(view);

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);
                    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_avez_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "1";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_n_avez_pas_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "0";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.commentRecyclerView.getVisibility() != View.VISIBLE) {
                        holder.commentRecyclerView.setVisibility(View.VISIBLE);
                    } else holder.commentRecyclerView.setVisibility(View.GONE);
                }
            });
            return holder;
        }

        String createUrl(String id_user, String id_post, String agree, String reason) {
            Uri uri = Uri.parse(MyClasses.VOTE_URL).buildUpon().appendQueryParameter("id_user", id_user)
                    .appendQueryParameter("id_post", id_post).appendQueryParameter("agree", agree)
                    .appendQueryParameter("reason", reason).build();
            return uri.toString();
        }

        @Override
        public void onBindViewHolder(final mViewHolder holder, int position) {

            holder.avatar.setImageBitmap(list.get(position).getUserAvatar());
            holder.name.setText(list.get(position).getUserName());
            holder.specialty.setText(list.get(position).getUserSpecialty());
            holder.pubdate.setText(list.get(position).getPubDate());
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getContent());
            holder.like.setText(String.valueOf(list.get(position).getNumLike()));
            holder.dislike.setText(String.valueOf(list.get(position).getNumDislike()));
            holder.teacher.setText(list.get(position).getTeacher());
            holder.module.setText(list.get(position).getModule());
            holder.examType.setText(list.get(position).getExamType());
            holder.groupe.setText(list.get(position).getGrp());
            holder.comment.setText(String.valueOf(list.get(position).getNumComment()));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            holder.commentRecyclerView.setLayoutManager(layoutManager);
            holder.commentRecyclerView.setHasFixedSize(false);
            CommentsAdapter commentsAdapter;
            commentsAdapter = new CommentsAdapter(list.get(position).getComments(), context);
            holder.commentRecyclerView.setAdapter(commentsAdapter);
        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView name, specialty, pubdate, title, content, module, examType, groupe, teacher, like, dislike, comment;
            RecyclerView commentRecyclerView;

            public mViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.layout_displayed_item_poster_avatar);
                name = itemView.findViewById(R.id.layout_displayed_item_poster_name);
                specialty = itemView.findViewById(R.id.layout_displayed_item_specialty_poster);
                pubdate = itemView.findViewById(R.id.layout_displayed_item_date);
                title = itemView.findViewById(R.id.layout_displayed_item_title);
                content = itemView.findViewById(R.id.layout_displayed_item_content);
                like = itemView.findViewById(R.id.layout_displayed_item_like_number);
                teacher = itemView.findViewById(R.id.layout_displayed_item_ens);
                groupe = itemView.findViewById(R.id.layout_displayed_item_grp);
                module = itemView.findViewById(R.id.layout_displayed_item_module);
                examType = itemView.findViewById(R.id.layout_displayed_item_exam_type);
                dislike = itemView.findViewById(R.id.layout_displayed_item_dislike_number);
                comment = itemView.findViewById(R.id.layout_displayed_item_comment_number);
                commentRecyclerView = itemView.findViewById(R.id.layout_displayed_item_comments_recycler_view);
            }
        }

        public static class mClass {
            Bitmap userAvatar;
            String userName, userSpecialty, pubDate, title, content;
            int numLike, numDislike, numComment, id_news;
            String teacher, module, grp, examType;
            long teacher_id;
            ArrayList<Comments> comments;

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

            public String getModule() {
                return module;
            }

            public void setModule(String module) {
                this.module = module;
            }

            public String getExamType() {
                return examType;
            }

            public void setExamType(String examType) {
                this.examType = examType;
            }

            public long getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(long teacher_id) {
                this.teacher_id = teacher_id;
            }

            public ArrayList<Comments> getComments() {
                return comments;
            }

            public void setComments(ArrayList<Comments> comments) {
                this.comments = comments;
            }

            public mClass(int id_new, Bitmap userAvatar, String userName, String userSpecialty, String pubDate, String title, String content,
                          int numLike, int numDislike, int numComment, ArrayList<Comments> c, String teacher, long teacher_id
                    , String module, String grp, String examType) {
                this.teacher_id = teacher_id;
                this.teacher = teacher;
                this.module = module;
                this.grp = grp;
                this.examType = examType;
                this.userAvatar = userAvatar;
                this.userName = userName;
                this.userSpecialty = userSpecialty;
                this.pubDate = pubDate;
                this.title = title;
                this.content = content;
                this.numLike = numLike;
                this.numDislike = numDislike;
                this.numComment = numComment;
                this.id_news = id_new;
                comments = c;
            }

            public int getId_news() {
                return id_news;
            }

            public void setId_news(int id_news) {
                this.id_news = id_news;
            }

            public Bitmap getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(Bitmap userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSpecialty() {
                return userSpecialty;
            }

            public void setUserSpecialty(String userSpecialty) {
                this.userSpecialty = userSpecialty;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getNumLike() {
                return numLike;
            }

            public void setNumLike(int numLike) {
                this.numLike = numLike;
            }

            public int getNumDislike() {
                return numDislike;
            }

            public void setNumDislike(int numDislike) {
                this.numDislike = numDislike;
            }

            public int getNumComment() {
                return numComment;
            }

            public void setNumComment(int numComment) {
                this.numComment = numComment;
            }
        }
    }

    public static class RecyclerConsultationDAdapter extends RecyclerView.Adapter<RecyclerConsultationDAdapter.mViewHolder> {
        ArrayList<mClass> list = new ArrayList<>();
        Context context;
        String id_user;

        public RecyclerConsultationDAdapter(ArrayList<mClass> list, Context context) {
            this.list = list;
            this.context = context;
            SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, Context.MODE_PRIVATE);
            id_user = String.valueOf(preferences.getLong("id", 0));
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_consultation_item, parent, false);
            final mViewHolder holder = new mViewHolder(view);

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);
                    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_avez_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_new()), agree = "1";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_n_avez_pas_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_new()), agree = "0";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.commentRecyclerView.getVisibility() != View.VISIBLE) {
                        holder.commentRecyclerView.setVisibility(View.VISIBLE);
                    } else holder.commentRecyclerView.setVisibility(View.GONE);
                }
            });
            return holder;
        }

        String createUrl(String id_user, String id_post, String agree, String reason) {
            Uri uri = Uri.parse(MyClasses.VOTE_URL).buildUpon().appendQueryParameter("id_user", id_user)
                    .appendQueryParameter("id_post", id_post).appendQueryParameter("agree", agree)
                    .appendQueryParameter("reason", reason).build();
            return uri.toString();
        }

        @Override
        public void onBindViewHolder(final mViewHolder holder, int position) {

            holder.avatar.setImageBitmap(list.get(position).getUserAvatar());
            holder.name.setText(list.get(position).getUserName());
            holder.specialty.setText(list.get(position).getUserSpecialty());
            holder.pubdate.setText(list.get(position).getPubDate());
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getContent());
            holder.like.setText(String.valueOf(list.get(position).getNumLike()));
            holder.dislike.setText(String.valueOf(list.get(position).getNumDislike()));
            holder.date.setText(list.get(position).getDate());
            holder.comment.setText(String.valueOf(list.get(position).getNumComment()));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            holder.commentRecyclerView.setLayoutManager(layoutManager);
            holder.commentRecyclerView.setHasFixedSize(false);
            CommentsAdapter commentsAdapter;
            commentsAdapter = new CommentsAdapter(list.get(position).getComments(), context);
            holder.commentRecyclerView.setAdapter(commentsAdapter);

        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView name, specialty, pubdate, title, content, session, date, like, dislike, comment;
            RecyclerView commentRecyclerView;

            public mViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.layout_consultation_item_poster_avatar);
                name = itemView.findViewById(R.id.layout_consultation_item_poster_name);
                specialty = itemView.findViewById(R.id.layout_consultation_item_specialty_poster);
                pubdate = itemView.findViewById(R.id.layout_consultation_item_date);
                title = itemView.findViewById(R.id.layout_consultation_item_title);
                content = itemView.findViewById(R.id.layout_consultation_item_content);
                like = itemView.findViewById(R.id.layout_consultation_item_like_number);
                session = itemView.findViewById(R.id.layout_consultation_item_session);
                date = itemView.findViewById(R.id.layout_consultation_item_c_date);
                dislike = itemView.findViewById(R.id.layout_consultation_item_dislike_number);
                comment = itemView.findViewById(R.id.layout_consultation_item_comment_number);
                commentRecyclerView = itemView.findViewById(R.id.layout_consultation_item_comments_recycler_view);
            }
        }

        public static class mClass {
            Bitmap userAvatar;
            String userName, userSpecialty, pubDate, title, content;
            int numLike, numDislike, numComment, id_new, id_poster;
            String date;
            long session_id;
            ArrayList<Comments> comments;

            public int getId_new() {
                return id_new;
            }

            public void setId_new(int id_new) {
                this.id_new = id_new;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public long getSession_id() {
                return session_id;
            }

            public void setSession_id(long session_id) {
                this.session_id = session_id;
            }

            public ArrayList<Comments> getComments() {
                return comments;
            }

            public void setComments(ArrayList<Comments> comments) {
                this.comments = comments;
            }

            public mClass(int id_news, int id_poster, Bitmap userAvatar, String userName, String userSpecialty, String pubDate, String title, String content,
                          int numLike, int numDislike, int numComment, ArrayList<Comments> c, String teacher, long session_id
                    , String date) {
                this.date = date;
                this.id_new = id_news;
                this.id_poster = id_poster;
                this.session_id = session_id;
                this.userAvatar = userAvatar;
                this.userName = userName;
                this.userSpecialty = userSpecialty;
                this.pubDate = pubDate;
                this.title = title;
                this.content = content;
                this.numLike = numLike;
                this.numDislike = numDislike;
                this.numComment = numComment;
                comments = c;
            }

            public int getId_poster() {
                return id_poster;
            }

            public void setId_poster(int id_poster) {
                this.id_poster = id_poster;
            }

            public Bitmap getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(Bitmap userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSpecialty() {
                return userSpecialty;
            }

            public void setUserSpecialty(String userSpecialty) {
                this.userSpecialty = userSpecialty;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getNumLike() {
                return numLike;
            }

            public void setNumLike(int numLike) {
                this.numLike = numLike;
            }

            public int getNumDislike() {
                return numDislike;
            }

            public void setNumDislike(int numDislike) {
                this.numDislike = numDislike;
            }

            public int getNumComment() {
                return numComment;
            }

            public void setNumComment(int numComment) {
                this.numComment = numComment;
            }
        }
    }

    @SuppressLint("ValidFragment")
    public static class ConsultationFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.RecyclerConsultationDAdapter.mClass> list;

        public ConsultationFragment(ArrayList<MyClasses.RecyclerConsultationDAdapter.mClass> list) {
            // Required empty public constructor
            this.list = list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_news);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerles_publicitsfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.RecyclerConsultationDAdapter adapter = new RecyclerConsultationDAdapter(list, getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(GONE);

            return view;
        }

    }

    @SuppressLint("ValidFragment")
    public static class ExamFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.RecyclerExamAdapter.mClass> list;

        public ExamFragment(ArrayList<MyClasses.RecyclerExamAdapter.mClass> list) {
            // Required empty public constructor
            this.list = list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_news);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerles_publicitsfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.RecyclerExamAdapter adapter = new RecyclerExamAdapter(list, getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(GONE);
            return view;
        }

    }

    public static class RecyclerExamAdapter extends RecyclerView.Adapter<RecyclerExamAdapter.mViewHolder> {
        ArrayList<mClass> list;
        Context context;
        String id_user;

        public RecyclerExamAdapter(ArrayList<mClass> list, Context context) {
            this.list = list;
            this.context = context;
            SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, Context.MODE_PRIVATE);
            id_user = String.valueOf(preferences.getLong("id", 0));
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_exam_item, parent, false);
            final mViewHolder holder = new mViewHolder(view);

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);
                    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_avez_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "1";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_n_avez_pas_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "0";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.commentRecyclerView.getVisibility() != View.VISIBLE) {
                        holder.commentRecyclerView.setVisibility(View.VISIBLE);
                    } else holder.commentRecyclerView.setVisibility(View.GONE);
                }
            });
            return holder;
        }

        String createUrl(String id_user, String id_post, String agree, String reason) {
            Uri uri = Uri.parse(MyClasses.VOTE_URL).buildUpon().appendQueryParameter("id_user", id_user)
                    .appendQueryParameter("id_post", id_post).appendQueryParameter("agree", agree)
                    .appendQueryParameter("reason", reason).build();
            return uri.toString();
        }


        @Override
        public void onBindViewHolder(final mViewHolder holder, int position) {

            holder.avatar.setImageBitmap(list.get(position).getUserAvatar());
            holder.name.setText(list.get(position).getUserName());
            holder.specialty.setText(list.get(position).getUserSpecialty());
            holder.pubdate.setText(list.get(position).getPubDate());
            holder.comment.setText(String.valueOf(list.get(position).getNumComment()));
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getContent());
            holder.like.setText(String.valueOf(list.get(position).getNumLike()));
            holder.dislike.setText(String.valueOf(list.get(position).getNumDislike()));
            holder.examType.setText(list.get(position).getExam_type());
            holder.date.setText(list.get(position).getDate());
            holder.module.setText(list.get(position).getModule());
            holder.groupe.setText(list.get(position).getGrp());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            holder.commentRecyclerView.setLayoutManager(layoutManager);
            holder.commentRecyclerView.setHasFixedSize(false);
            CommentsAdapter commentsAdapter;
            commentsAdapter = new CommentsAdapter(list.get(position).getComments(), context);
            holder.commentRecyclerView.setAdapter(commentsAdapter);
        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView name, specialty, pubdate, title, content, examType, date, like, dislike, comment, groupe, module;
            RecyclerView commentRecyclerView;

            public mViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.layout_exam_item_poster_avatar);
                name = itemView.findViewById(R.id.layout_exam_item_poster_name);
                specialty = itemView.findViewById(R.id.layout_exam_item_specialty_poster);
                pubdate = itemView.findViewById(R.id.layout_exam_item_date);
                title = itemView.findViewById(R.id.layout_exam_item_title);
                content = itemView.findViewById(R.id.layout_exam_item_content);
                like = itemView.findViewById(R.id.layout_exam_item_like_number);
                examType = itemView.findViewById(R.id.layout_exam_item_exam_type);
                date = itemView.findViewById(R.id.layout_exam_item_delais);
                groupe = itemView.findViewById(R.id.layout_displayed_item_grp);
                module = itemView.findViewById(R.id.layout_displayed_item_module);
                dislike = itemView.findViewById(R.id.layout_exam_item_dislike_number);
                comment = itemView.findViewById(R.id.layout_exam_item_comment_number);
                commentRecyclerView = itemView.findViewById(R.id.layout_exam_item_comments_recycler_view);
            }
        }

        public static class mClass {
            Bitmap userAvatar;
            String userName, userSpecialty, pubDate, title, content;
            int numLike, numDislike, numComment, id_news, id_poster;
            String date, exam_type, grp, module;
            ArrayList<Comments> comments;

            public String getGrp() {
                return grp;
            }

            public void setGrp(String grp) {
                this.grp = grp;
            }

            public String getModule() {
                return module;
            }

            public void setModule(String module) {
                this.module = module;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getExam_type() {
                return exam_type;
            }

            public void setExam_type(String exam_type) {
                this.exam_type = exam_type;
            }

            public ArrayList<Comments> getComments() {
                return comments;
            }

            public void setComments(ArrayList<Comments> comments) {
                this.comments = comments;
            }

            public mClass(int id_news, int id_poster, Bitmap userAvatar, String userName, String userSpecialty, String pubDate,
                          String title, String content,
                          int numLike, int numDislike, int numComment, ArrayList<Comments> c, String exam_type, String date
                    , String grp, String module) {
                this.date = date;
                this.id_news = id_news;
                this.id_poster = id_poster;
                this.exam_type = exam_type;
                this.userAvatar = userAvatar;
                this.userName = userName;
                this.userSpecialty = userSpecialty;
                this.pubDate = pubDate;
                this.title = title;
                this.content = content;
                this.numLike = numLike;
                this.numDislike = numDislike;
                this.numComment = numComment;
                this.grp = grp;
                this.module = module;
                comments = c;
            }

            public int getId_news() {
                return id_news;
            }

            public void setId_news(int id_news) {
                this.id_news = id_news;
            }

            public int getId_poster() {
                return id_poster;
            }

            public void setId_poster(int id_poster) {
                this.id_poster = id_poster;
            }

            public Bitmap getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(Bitmap userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSpecialty() {
                return userSpecialty;
            }

            public void setUserSpecialty(String userSpecialty) {
                this.userSpecialty = userSpecialty;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getNumLike() {
                return numLike;
            }

            public void setNumLike(int numLike) {
                this.numLike = numLike;
            }

            public int getNumDislike() {
                return numDislike;
            }

            public void setNumDislike(int numDislike) {
                this.numDislike = numDislike;
            }

            public int getNumComment() {
                return numComment;
            }

            public void setNumComment(int numComment) {
                this.numComment = numComment;
            }
        }
    }

    @SuppressLint("ValidFragment")
    public static class StrikeFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.RecyclerStrikeAdapter.mClass> list;

        public StrikeFragment(ArrayList<MyClasses.RecyclerStrikeAdapter.mClass> list) {
            // Required empty public constructor
            this.list = list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_news);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerles_publicitsfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.RecyclerStrikeAdapter adapter = new RecyclerStrikeAdapter(list, getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(GONE);
            return view;

        }

    }

    public static class RecyclerStrikeAdapter extends RecyclerView.Adapter<RecyclerStrikeAdapter.mViewHolder> {
        ArrayList<mClass> list = new ArrayList<>();
        Context context;
        String id_user;

        public RecyclerStrikeAdapter(ArrayList<mClass> list, Context context) {
            this.list = list;
            this.context = context;
            SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, Context.MODE_PRIVATE);
            id_user = String.valueOf(preferences.getLong("id", 0));
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_strike_item, parent, false);
            final mViewHolder holder = new mViewHolder(view);

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_avez_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "1";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_n_avez_pas_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "0";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.commentRecyclerView.getVisibility() != View.VISIBLE) {
                        holder.commentRecyclerView.setVisibility(View.VISIBLE);
                    } else holder.commentRecyclerView.setVisibility(View.GONE);
                }
            });
            return holder;
        }

        String createUrl(String id_user, String id_post, String agree, String reason) {
            Uri uri = Uri.parse(MyClasses.VOTE_URL).buildUpon().appendQueryParameter("id_user", id_user)
                    .appendQueryParameter("id_post", id_post).appendQueryParameter("agree", agree)
                    .appendQueryParameter("reason", reason).build();
            return uri.toString();
        }


        @Override
        public void onBindViewHolder(final mViewHolder holder, int position) {

            holder.avatar.setImageBitmap(list.get(position).getUserAvatar());
            holder.name.setText(list.get(position).getUserName());
            holder.specialty.setText(list.get(position).getUserSpecialty());
            holder.pubdate.setText(list.get(position).getPubDate());
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getContent());
            holder.like.setText(String.valueOf(list.get(position).getNumLike()));
            holder.dislike.setText(String.valueOf(list.get(position).getNumDislike()));
            holder.reasons.setText(list.get(position).getReasons());
            holder.date.setText(list.get(position).getDate());
            holder.comment.setText(String.valueOf(list.get(position).getNumComment()));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            holder.commentRecyclerView.setLayoutManager(layoutManager);
            holder.commentRecyclerView.setHasFixedSize(false);
            CommentsAdapter commentsAdapter;
            commentsAdapter = new CommentsAdapter(list.get(position).getComments(), context);
            holder.commentRecyclerView.setAdapter(commentsAdapter);
        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView name, specialty, pubdate, title, content, reasons, date, like, dislike, comment;
            RecyclerView commentRecyclerView;

            public mViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.layout_strike_item_poster_avatar);
                name = itemView.findViewById(R.id.layout_strike_item_poster_name);
                specialty = itemView.findViewById(R.id.layout_strike_item_specialty_poster);
                pubdate = itemView.findViewById(R.id.layout_strike_item_date);
                title = itemView.findViewById(R.id.layout_strike_item_title);
                content = itemView.findViewById(R.id.layout_strike_item_content);
                like = itemView.findViewById(R.id.layout_strike_item_like_number);
                reasons = itemView.findViewById(R.id.layout_strike_item_cause);
                date = itemView.findViewById(R.id.layout_strike_item_delais);
                dislike = itemView.findViewById(R.id.layout_strike_item_dislike_number);
                comment = itemView.findViewById(R.id.layout_strike_item_comment_number);
                commentRecyclerView = itemView.findViewById(R.id.layout_strike_item_comments_recycler_view);
            }
        }

        public static class mClass {
            Bitmap userAvatar;
            String userName, userSpecialty, pubDate, title, content;
            int numLike, numDislike, numComment, id_news, id_user;
            String date, reasons;
            ArrayList<Comments> comments;

            public int getId_news() {
                return id_news;
            }

            public void setId_news(int id_news) {
                this.id_news = id_news;
            }

            public int getId_user() {
                return id_user;
            }

            public void setId_user(int id_user) {
                this.id_user = id_user;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getReasons() {
                return reasons;
            }

            public void setReasons(String reasons) {
                this.reasons = reasons;
            }

            public ArrayList<Comments> getComments() {
                return comments;
            }

            public void setComments(ArrayList<Comments> comments) {
                this.comments = comments;
            }

            public mClass(int id_news, int id_user, Bitmap userAvatar, String userName, String userSpecialty, String pubDate,
                          String title, String content,
                          int numLike, int numDislike, int numComment, ArrayList<Comments> c, String reasons, String date) {
                this.date = date;
                this.id_news = id_news;
                this.id_user = id_user;
                this.reasons = reasons;
                this.userAvatar = userAvatar;
                this.userName = userName;
                this.userSpecialty = userSpecialty;
                this.pubDate = pubDate;
                this.title = title;
                this.content = content;
                this.numLike = numLike;
                this.numDislike = numDislike;
                this.numComment = numComment;
                comments = c;
            }

            public Bitmap getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(Bitmap userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSpecialty() {
                return userSpecialty;
            }

            public void setUserSpecialty(String userSpecialty) {
                this.userSpecialty = userSpecialty;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getNumLike() {
                return numLike;
            }

            public void setNumLike(int numLike) {
                this.numLike = numLike;
            }

            public int getNumDislike() {
                return numDislike;
            }

            public void setNumDislike(int numDislike) {
                this.numDislike = numDislike;
            }

            public int getNumComment() {
                return numComment;
            }

            public void setNumComment(int numComment) {
                this.numComment = numComment;
            }
        }
    }

    @SuppressLint("ValidFragment")
    public static class MeetingFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.RecyclerMeetingAdapter.mClass> list;

        public MeetingFragment(ArrayList<MyClasses.RecyclerMeetingAdapter.mClass> list) {
            // Required empty public constructor
            this.list = list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_news);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerles_publicitsfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.RecyclerMeetingAdapter adapter = new RecyclerMeetingAdapter(list, getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(GONE);
            return view;
        }

    }

    public static class RecyclerMeetingAdapter extends RecyclerView.Adapter<RecyclerMeetingAdapter.mViewHolder> {
        ArrayList<mClass> list = new ArrayList<>();
        Context context;
        String id_user;

        public RecyclerMeetingAdapter(ArrayList<mClass> list, Context context) {
            this.list = list;
            this.context = context;
            SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, Context.MODE_PRIVATE);
            id_user = String.valueOf(preferences.getLong("id", 0));
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_meeting_item, parent, false);
            final mViewHolder holder = new mViewHolder(view);

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);
                    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_avez_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_new()), agree = "1";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_n_avez_pas_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_new()), agree = "0";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.commentRecyclerView.getVisibility() != View.VISIBLE) {
                        holder.commentRecyclerView.setVisibility(View.VISIBLE);
                    } else holder.commentRecyclerView.setVisibility(View.GONE);
                }
            });
            return holder;
        }

        String createUrl(String id_user, String id_post, String agree, String reason) {
            Uri uri = Uri.parse(MyClasses.VOTE_URL).buildUpon().appendQueryParameter("id_user", id_user)
                    .appendQueryParameter("id_post", id_post).appendQueryParameter("agree", agree)
                    .appendQueryParameter("reason", reason).build();
            return uri.toString();
        }

        @Override
        public void onBindViewHolder(final mViewHolder holder, int position) {
            holder.avatar.setImageBitmap(list.get(position).getUserAvatar());
            holder.name.setText(list.get(position).getUserName());
            holder.specialty.setText(list.get(position).getUserSpecialty());
            holder.pubdate.setText(list.get(position).getPubDate());
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getContent());
            holder.like.setText(String.valueOf(list.get(position).getNumLike()));
            holder.dislike.setText(String.valueOf(list.get(position).getNumDislike()));
            holder.date.setText(list.get(position).getDate());
            holder.classroom.setText(list.get(position).getClassroom());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            holder.commentRecyclerView.setLayoutManager(layoutManager);
            holder.commentRecyclerView.setHasFixedSize(false);
            holder.comment.setText(String.valueOf(list.get(position).getNumComment()));
            CommentsAdapter commentsAdapter;
            commentsAdapter = new CommentsAdapter(list.get(position).getComments(), context);
            holder.commentRecyclerView.setAdapter(commentsAdapter);
        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView name, specialty, pubdate, title, content, date, like, dislike, comment, classroom;
            RecyclerView commentRecyclerView;

            public mViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.layout_meeting_item_poster_avatar);
                name = itemView.findViewById(R.id.layout_meeting_item_poster_name);
                specialty = itemView.findViewById(R.id.layout_meeting_item_specialty_poster);
                pubdate = itemView.findViewById(R.id.layout_meeting_item_date);
                title = itemView.findViewById(R.id.layout_meeting_item_title);
                content = itemView.findViewById(R.id.layout_meeting_item_content);
                like = itemView.findViewById(R.id.layout_meeting_item_like_number);
                date = itemView.findViewById(R.id.layout_meeting_item_delais);
                dislike = itemView.findViewById(R.id.layout_meeting_item_dislike_number);
                comment = itemView.findViewById(R.id.layout_meeting_item_comment_number);
                classroom = itemView.findViewById(R.id.layout_meeting_item_classroom);
                commentRecyclerView = itemView.findViewById(R.id.layout_meeting_item_comments_recycler_view);

            }
        }

        public static class mClass {
            Bitmap userAvatar;
            String userName, userSpecialty, pubDate, title, content, classroom;
            int numLike, numDislike, numComment, id_new, id_poster;
            String date;
            ArrayList<Comments> comments;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public ArrayList<Comments> getComments() {
                return comments;
            }

            public void setComments(ArrayList<Comments> comments) {
                this.comments = comments;
            }

            public mClass(int id_new, int id_poster, Bitmap userAvatar, String userName, String userSpecialty, String pubDate, String title, String content,
                          int numLike, int numDislike, int numComment, ArrayList<Comments> c, String date, String classroom) {
                this.date = date;
                this.userAvatar = userAvatar;
                this.userName = userName;
                this.userSpecialty = userSpecialty;
                this.pubDate = pubDate;
                this.title = title;
                this.content = content;
                this.numLike = numLike;
                this.numDislike = numDislike;
                this.numComment = numComment;
                this.id_new = id_new;
                this.id_poster = id_poster;
                comments = c;
                this.classroom = classroom;
            }

            public Bitmap getUserAvatar() {
                return userAvatar;
            }

            public int getId_new() {
                return id_new;
            }

            public void setId_new(int id_new) {
                this.id_new = id_new;
            }

            public int getId_poster() {
                return id_poster;
            }

            public void setId_poster(int id_poster) {
                this.id_poster = id_poster;
            }

            public String getClassroom() {
                return classroom;
            }

            public void setClassroom(String classroom) {
                this.classroom = classroom;
            }

            public void setUserAvatar(Bitmap userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSpecialty() {
                return userSpecialty;
            }

            public void setUserSpecialty(String userSpecialty) {
                this.userSpecialty = userSpecialty;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getNumLike() {
                return numLike;
            }

            public void setNumLike(int numLike) {
                this.numLike = numLike;
            }

            public int getNumDislike() {
                return numDislike;
            }

            public void setNumDislike(int numDislike) {
                this.numDislike = numDislike;
            }

            public int getNumComment() {
                return numComment;
            }

            public void setNumComment(int numComment) {
                this.numComment = numComment;
            }
        }
    }

    @SuppressLint("ValidFragment")
    public static class HolydayFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.RecyclerHolydayAdapter.mClass> list;

        public HolydayFragment(ArrayList<MyClasses.RecyclerHolydayAdapter.mClass> list) {
            // Required empty public constructor
            this.list = list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_news);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerles_publicitsfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.RecyclerHolydayAdapter adapter = new RecyclerHolydayAdapter(list, getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(GONE);
            return view;
        }

    }

    public static class RecyclerHolydayAdapter extends RecyclerView.Adapter<RecyclerHolydayAdapter.mViewHolder> {
        ArrayList<mClass> list;
        Context context;
        String id_user;

        public RecyclerHolydayAdapter(ArrayList<mClass> list, Context context) {
            this.list = list;
            this.context = context;
            SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, Context.MODE_PRIVATE);
            id_user = String.valueOf(preferences.getLong("id", 0));
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_holyday_item, parent, false);
            final mViewHolder holder = new mViewHolder(view);

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);
                    //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_avez_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "1";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_n_avez_pas_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "0";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.commentRecyclerView.getVisibility() != View.VISIBLE) {
                        holder.commentRecyclerView.setVisibility(View.VISIBLE);
                    } else holder.commentRecyclerView.setVisibility(View.GONE);
                }
            });
            return holder;
        }

        String createUrl(String id_user, String id_post, String agree, String reason) {
            Uri uri = Uri.parse(MyClasses.VOTE_URL).buildUpon().appendQueryParameter("id_user", id_user)
                    .appendQueryParameter("id_post", id_post).appendQueryParameter("agree", agree)
                    .appendQueryParameter("reason", reason).build();
            return uri.toString();
        }

        @Override
        public void onBindViewHolder(final mViewHolder holder, int position) {

            holder.avatar.setImageBitmap(list.get(position).getUserAvatar());
            holder.name.setText(list.get(position).getUserName());
            holder.specialty.setText(list.get(position).getUserSpecialty());
            holder.pubdate.setText(list.get(position).getPubDate());
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getContent());
            holder.like.setText(String.valueOf(list.get(position).getNumLike()));
            holder.dislike.setText(String.valueOf(list.get(position).getNumDislike()));
            holder.date.setText(list.get(position).getDate());
            holder.comment.setText(String.valueOf(list.get(position).getNumComment()));
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            holder.commentRecyclerView.setLayoutManager(layoutManager);
            holder.commentRecyclerView.setHasFixedSize(false);
            CommentsAdapter commentsAdapter;
            commentsAdapter = new CommentsAdapter(list.get(position).getComments(), context);
            holder.commentRecyclerView.setAdapter(commentsAdapter);
        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView name, specialty, pubdate, title, content, date, like, dislike, comment;
            RecyclerView commentRecyclerView;

            public mViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.layout_holyday_item_poster_avatar);
                name = itemView.findViewById(R.id.layout_holyday_item_poster_name);
                specialty = itemView.findViewById(R.id.layout_holyday_item_specialty_poster);
                pubdate = itemView.findViewById(R.id.layout_holyday_item_date);
                title = itemView.findViewById(R.id.layout_holyday_item_title);
                content = itemView.findViewById(R.id.layout_holyday_item_content);
                like = itemView.findViewById(R.id.layout_holyday_item_like_number);
                date = itemView.findViewById(R.id.layout_holyday_item_delais);
                dislike = itemView.findViewById(R.id.layout_holyday_item_dislike_number);
                comment = itemView.findViewById(R.id.layout_holyday_item_comment_number);
                commentRecyclerView = itemView.findViewById(R.id.layout_holyday_item_comments_recycler_view);
            }
        }

        public static class mClass {
            Bitmap userAvatar;
            String userName, userSpecialty, pubDate, title, content;
            int numLike, numDislike, numComment, id_news, id_poster;
            String date;
            ArrayList<Comments> comments;

            public int getId_news() {
                return id_news;
            }

            public void setId_news(int id_news) {
                this.id_news = id_news;
            }

            public int getId_poster() {
                return id_poster;
            }

            public void setId_poster(int id_poster) {
                this.id_poster = id_poster;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public ArrayList<Comments> getComments() {
                return comments;
            }

            public void setComments(ArrayList<Comments> comments) {
                this.comments = comments;
            }

            public mClass(int id_news, int id_poster,
                          Bitmap userAvatar, String userName, String userSpecialty, String pubDate, String title, String content,
                          int numLike, int numDislike, int numComment, ArrayList<Comments> c, String date) {
                this.date = date;
                this.userAvatar = userAvatar;
                this.userName = userName;
                this.userSpecialty = userSpecialty;
                this.pubDate = pubDate;
                this.title = title;
                this.content = content;
                this.numLike = numLike;
                this.numDislike = numDislike;
                this.numComment = numComment;
                this.id_news = id_news;
                this.id_poster = id_poster;
                comments = c;
            }

            public Bitmap getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(Bitmap userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSpecialty() {
                return userSpecialty;
            }

            public void setUserSpecialty(String userSpecialty) {
                this.userSpecialty = userSpecialty;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getNumLike() {
                return numLike;
            }

            public void setNumLike(int numLike) {
                this.numLike = numLike;
            }

            public int getNumDislike() {
                return numDislike;
            }

            public void setNumDislike(int numDislike) {
                this.numDislike = numDislike;
            }

            public int getNumComment() {
                return numComment;
            }

            public void setNumComment(int numComment) {
                this.numComment = numComment;
            }
        }
    }


    @SuppressLint("ValidFragment")
    public static class ProcedureFragment extends Fragment {
        RecyclerView recyclerView;
        ProgressBar progressBar;
        LinearLayout emtpy;
        TextView emtpylabel;
        ImageView emtyImage;
        ArrayList<MyClasses.RecyclerProcedureAdapter.mClass> list;

        public ProcedureFragment(ArrayList<MyClasses.RecyclerProcedureAdapter.mClass> list) {
            // Required empty public constructor
            this.list = list;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_news, container, false);
            recyclerView = view.findViewById(R.id.news_recycler_view);
            progressBar = view.findViewById(R.id.progress_bar_news);
            emtpy = view.findViewById(R.id.empty_list_txv);
            emtpylabel = view.findViewById(R.id.empty_list_txv_text);
            emtyImage = view.findViewById(R.id.empty_list_pic);
            if (list.size() == 0) {
                emtpylabel.setText(R.string.no_news);
                emtyImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                        R.drawable.icons8supprimerles_publicitsfilled50));
                emtpy.setVisibility(View.VISIBLE);
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(false);
                MyClasses.RecyclerProcedureAdapter adapter = new RecyclerProcedureAdapter(list, getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(GONE);
            return view;
        }

    }

    public static class RecyclerProcedureAdapter extends RecyclerView.Adapter<RecyclerProcedureAdapter.mViewHolder> {
        ArrayList<mClass> list = new ArrayList<>();
        Context context;
        String id_user;

        public RecyclerProcedureAdapter(ArrayList<mClass> list, Context context) {
            this.list = list;
            this.context = context;
            SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, Context.MODE_PRIVATE);
            id_user = String.valueOf(preferences.getLong("id", 0));
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_procedure_item, parent, false);
            final mViewHolder holder = new mViewHolder(view);

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);
                    //ialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_avez_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "1";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.vote_layout);

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    final TextView why_label = dialog.findViewById(R.id.why_vote),
                            go_vote = dialog.findViewById(R.id.go_vote);
                    final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar_);
                    final EditText text = dialog.findViewById(R.id.justification_edtx);
                    final CheckBox checkBox = dialog.findViewById(R.id.vote_dont_justify_checkbox);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                text.setText("");
                                text.setEnabled(false);
                            } else {
                                text.setEnabled(true);
                            }
                        }
                    });
                    why_label.setText(context.getString(R.string.pourquoi_vous_n_avez_pas_aimer_la_pulblication_de) + " " +
                            list.get(holder.getAdapterPosition()).getUserName() + " ?");
                    go_vote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            go_vote.setVisibility(View.GONE);
                            String reason, id_post = String.valueOf(list.get(holder.getAdapterPosition()).getId_news()), agree = "0";
                            if (checkBox.isChecked()) {
                                reason = "";
                            } else {
                                reason = text.getText().toString().trim();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    createUrl(id_user, id_post, agree, reason), null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                if (response.getInt("params") == 1) {
                                                    if (response.getInt("done") == 0) {
                                                        Snackbar.make(text, R.string.vote_exists_already,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    } else {
                                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                                        Gson gson = gsonBuilder.create();
                                                        MyClasses.Voted voted = gson.fromJson(
                                                                response.getJSONObject("vote").toString(),
                                                                MyClasses.Voted.class);
                                                        MyClasses.Journal journal = gson.fromJson(
                                                                response.getJSONObject("journal").toString(),
                                                                MyClasses.Journal.class
                                                        );
                                                        MyDbHelper dbHelper = new MyDbHelper(context);
                                                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                                                        dbHelper.addTojournal(db, journal);
                                                        dbHelper.addAvote(db, voted);
                                                        progressBar.setVisibility(View.GONE);
                                                        Snackbar.make(text, R.string.vote_envoye,
                                                                Snackbar.LENGTH_LONG).
                                                                setAction("Action", null).show();
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(GONE);
                                    text.setEnabled(true);
                                    go_vote.setVisibility(View.VISIBLE);
                                    Snackbar.make(text, R.string.error_happend_retry,
                                            Snackbar.LENGTH_LONG).
                                            setAction("Action", null).show();
                                }
                            });
                            mySinglton.getmInstance(context).addToRequestQue(jsonObjectRequest);


                        }
                    });
                    dialog.show();
                }
            });
            holder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.commentRecyclerView.getVisibility() != View.VISIBLE) {
                        holder.commentRecyclerView.setVisibility(View.VISIBLE);
                    } else holder.commentRecyclerView.setVisibility(View.GONE);
                }
            });
            return holder;
        }

        String createUrl(String id_user, String id_post, String agree, String reason) {
            Uri uri = Uri.parse(MyClasses.VOTE_URL).buildUpon().appendQueryParameter("id_user", id_user)
                    .appendQueryParameter("id_post", id_post).appendQueryParameter("agree", agree)
                    .appendQueryParameter("reason", reason).build();
            return uri.toString();
        }

        @Override
        public void onBindViewHolder(final mViewHolder holder, int position) {

            holder.avatar.setImageBitmap(list.get(position).getUserAvatar());
            holder.name.setText(list.get(position).getUserName());
            holder.specialty.setText(list.get(position).getUserSpecialty());
            holder.pubdate.setText(list.get(position).getPubDate());
            holder.title.setText(list.get(position).getTitle());
            holder.comment.setText(String.valueOf(list.get(position).getNumComment()));
            holder.content.setText(list.get(position).getContent());
            holder.like.setText(String.valueOf(list.get(position).getNumLike()));
            holder.dislike.setText(String.valueOf(list.get(position).getNumDislike()));
            holder.date.setText(list.get(position).getDate());
            holder.docs.setText(list.get(position).getDocs());
            holder.esth.setText(list.get(position).getEst());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            holder.commentRecyclerView.setLayoutManager(layoutManager);
            holder.commentRecyclerView.setHasFixedSize(false);
            CommentsAdapter commentsAdapter;
            commentsAdapter = new CommentsAdapter(list.get(position).getComments(), context);
            holder.commentRecyclerView.setAdapter(commentsAdapter);
        }


        @Override
        public int getItemCount() {
            if (list != null)
                return list.size();
            else return 0;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            de.hdodenhof.circleimageview.CircleImageView avatar;
            TextView name, specialty, pubdate, title, content, date, docs, esth, like, dislike, comment;
            RecyclerView commentRecyclerView;

            public mViewHolder(View itemView) {
                super(itemView);
                avatar = itemView.findViewById(R.id.layout_procedure_item_poster_avatar);
                name = itemView.findViewById(R.id.layout_procedure_item_poster_name);
                specialty = itemView.findViewById(R.id.layout_procedure_item_specialty_poster);
                pubdate = itemView.findViewById(R.id.layout_procedure_item_date);
                title = itemView.findViewById(R.id.layout_procedure_item_title);
                content = itemView.findViewById(R.id.layout_procedure_item_content);
                like = itemView.findViewById(R.id.layout_procedure_item_like_number);
                esth = itemView.findViewById(R.id.layout_procedure_item_esth);
                docs = itemView.findViewById(R.id.layout_procedure_item_docs);
                date = itemView.findViewById(R.id.layout_procedure_item_delais);
                dislike = itemView.findViewById(R.id.layout_procedure_item_dislike_number);
                comment = itemView.findViewById(R.id.layout_procedure_item_comment_number);
                commentRecyclerView = itemView.findViewById(R.id.layout_procedure_item_comments_recycler_view);
            }
        }

        public static class mClass {
            Bitmap userAvatar;
            String userName, userSpecialty, pubDate, title, content;
            int numLike, numDislike, numComment, id_news, id_poster;
            String est, date, docs;
            ArrayList<Comments> comments;

            public String getEst() {
                return est;
            }

            public void setEst(String est) {
                this.est = est;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDocs() {
                return docs;
            }

            public void setDocs(String docs) {
                this.docs = docs;
            }

            public ArrayList<Comments> getComments() {
                return comments;
            }

            public void setComments(ArrayList<Comments> comments) {
                this.comments = comments;
            }

            public mClass(int id_news, int id_pster, Bitmap userAvatar, String userName, String userSpecialty,
                          String pubDate, String title, String content,
                          int numLike, int numDislike, int numComment, ArrayList<Comments> c,
                          String date, String est, String docs) {
                this.docs = docs;
                this.date = date;
                this.est = est;
                this.userAvatar = userAvatar;
                this.userName = userName;
                this.userSpecialty = userSpecialty;
                this.pubDate = pubDate;
                this.title = title;
                this.content = content;
                this.numLike = numLike;
                this.numDislike = numDislike;
                this.id_poster = id_pster;
                this.id_news = id_news;
                this.numComment = numComment;
                comments = c;
            }

            public int getId_news() {
                return id_news;
            }

            public void setId_news(int id_news) {
                this.id_news = id_news;
            }

            public int getId_poster() {
                return id_poster;
            }

            public void setId_poster(int id_poster) {
                this.id_poster = id_poster;
            }

            public Bitmap getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(Bitmap userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserSpecialty() {
                return userSpecialty;
            }

            public void setUserSpecialty(String userSpecialty) {
                this.userSpecialty = userSpecialty;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getNumLike() {
                return numLike;
            }

            public void setNumLike(int numLike) {
                this.numLike = numLike;
            }

            public int getNumDislike() {
                return numDislike;
            }

            public void setNumDislike(int numDislike) {
                this.numDislike = numDislike;
            }

            public int getNumComment() {
                return numComment;
            }

            public void setNumComment(int numComment) {
                this.numComment = numComment;
            }
        }
    }


    public static class Admin extends User {
        private String id_work;
        private String add_date;
        public static final String TABLE_NAME = "aministrator";
        public static final String ID_WORK = "id_work";
        public static final String ADD_DATE = "add_date";

        public Admin(String id, String email, String userType, String last_name, String first_name, String sexe, String birth_date, String password, String home_town, String avatar, String id_work, String add_date) {
            super(id, email, userType, last_name, first_name, sexe, birth_date, password, home_town, avatar);
            this.id_work = id_work;
            this.add_date = add_date;
        }

        public String getId_work() {
            return id_work;
        }

        public void setId_work(String id_work) {
            this.id_work = id_work;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class ChangeOfSession extends New {

        private String id_old_session;
        private String id_new_session;
        public static final String TABLE_NAME = "change_of_session";
        public static final String ChangeOfSession_ID = "id";
        public static final String _ID = "_id";
        public static final String OLD = "id_old_session";
        public static final String NEW = "id_new_session";

        public ChangeOfSession(String id, String id_user, String title, String content, String pub_date,
                               String id_old_session, String id_new_session) {
            super(id, id_user, title, content, pub_date);
            this.id_old_session = id_old_session;
            this.id_new_session = id_new_session;
            this.setNews_Type("1");
        }

        public String getId_old_session() {
            return id_old_session;
        }

        public void setId_old_session(String id_old_session) {
            this.id_old_session = id_old_session;
        }

        public String getId_new_session() {
            return id_new_session;
        }

        public void setId_new_session(String id_new_session) {
            this.id_new_session = id_new_session;
        }
    }

    public static class Classroom {
        private String id;
        private String name;
        private String add_date;
        public static final String TABLE_NAME = "classroom";
        public static final String CLASSROOM_ID = "id";
        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String ADD_DATE = "add_date";

        public Classroom(String id, String name, String add_date) {
            this.id = id;
            this.name = name;
            this.add_date = add_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class Consultation extends New {
        private String id_sesson;
        private String date;
        public static final String TABLE_NAME = "consultation";
        public static final String Consultation_ID = "id";
        public static final String _ID = "_id";
        public static final String DATE = "date";
        public static final String ID_SESSION = "id_session";

        public Consultation(String id, String id_user, String title, String content, String pub_date,
                            String id_sesson, String date) {
            super(id, id_user, title, content, pub_date);
            this.date = date;
            this.id_sesson = id_sesson;
            this.setNews_Type("2");
        }

        public String getId_sesson() {
            return id_sesson;
        }

        public void setId_sesson(String id_sesson) {
            this.id_sesson = id_sesson;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class Day {
        private String id;
        private String name;
        public static final String TABLE_NAME = "days";
        public static final String DAY_ID = "id";
        public static final String _ID = "_id";
        public static final String NAME = "name";

        public Day(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Delegue extends Student {
        private String id_level;
        public static final String TABLE_NAME = "delegue";
        public static final String LEVEL_ID = "id_level";
        public static final String ADD_DATE = "add_date";
        public static final String DELEGUE_ID = "id_delege";
        public static final String ID = "_id";

        public Delegue(String id, String email, String userType, String last_name, String first_name, String sexe, String birth_date, String password, String home_town, String avatar, String mat, String bib, String add_date, String id_level) {
            super(id, email, userType, last_name, first_name, sexe, birth_date, password, home_town, avatar, mat, bib, add_date);
            this.id_level = id_level;

        }

        public String getId_level() {
            return id_level;
        }

        public void setId_level(String id_level) {
            this.id_level = id_level;
        }

        @Override
        public String getAdd_date() {
            return add_date;
        }

        @Override
        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class DelegueRow {
        String id, id_level, id_delege;

        public DelegueRow(String id, String id_level, String id_delege) {
            this.id = id;
            this.id_level = id_level;
            this.id_delege = id_delege;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_level() {
            return id_level;
        }

        public void setId_level(String id_level) {
            this.id_level = id_level;
        }

        public String getId_delege() {
            return id_delege;
        }

        public void setId_delege(String id_delege) {
            this.id_delege = id_delege;
        }
    }

    public static class DocumentRequest {
        private String id;
        private String id_user;
        private String doc;
        private String reason;
        private String add_date;
        public static final String TABLE_NAME = "document_request";
        public static final String DOC_REQ_ID = "id";
        public static final String _ID = "_id";
        public static final String USER_ID = "id_user";
        public static final String DOC = "doc";
        public static final String REASON = "reason";
        public static final String ADD_DATE = "add_date";

        public DocumentRequest(String id, String id_user, String doc, String reason, String add_date) {
            this.id = id;
            this.id_user = id_user;
            this.doc = doc;
            this.reason = reason;
            this.add_date = add_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getDoc() {
            return doc;
        }

        public void setDoc(String doc) {
            this.doc = doc;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class Encadreur {
        private String id;
        private String id_encadreur;
        private String id_student;
        private String add_date;
        public static final String ADD_DATE = "add_date";
        public static final String TABLE_NAME = "encadreur";
        public static final String ENCADRANT_ID = "id";
        public static final String _ID = "_id";
        public static final String ENCADREUR_ID = "id_encadreur";
        public static final String STUDENT_ID = "id_student";

        public Encadreur(String id, String id_encadreur, String id_student, String add_date) {
            this.id = id;
            this.id_encadreur = id_encadreur;
            this.id_student = id_student;
            this.add_date = add_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_encadreur() {
            return id_encadreur;
        }

        public void setId_encadreur(String id_encadreur) {
            this.id_encadreur = id_encadreur;
        }

        public String getId_student() {
            return id_student;
        }

        public void setId_student(String id_student) {
            this.id_student = id_student;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class EST {
        private String id;
        private String name;
        private String add_date;
        public static final String TABLE_NAME = "establishment";
        public static final String EST_ID = "id";
        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String ADD_DATE = "add_date";

        public EST(String id, String name, String add_date) {
            this.id = id;
            this.name = name;
            this.add_date = add_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class Exam extends New {
        private String id_exam_type, id_groupe, id_module, id_classroom;
        private String begin;

        public static final String ID = "_id", CLASSROOM = "id_classroom";
        public static final String TYPE = "id_exam_type";
        public static final String BEGIN_DATE = "date";
        public static final String TABLE_NAME = "exam", GROUPE = "id_groupe", MODULE = "id_module";


        public Exam(String id, String id_user, String title, String id_groupe, String id_module, String content, String pub_date, String id_exam_type, String begin, String id_classroom) {
            super(id, id_user, title, content, pub_date);
            this.id_exam_type = id_exam_type;
            this.begin = begin;
            this.id_groupe = id_groupe;
            this.id_module = id_module;
            this.id_classroom = id_classroom;
            this.setNews_Type("3");
        }

        public String getId_exam_type() {
            return id_exam_type;
        }

        public void setId_exam_type(String id_exam_type) {
            this.id_exam_type = id_exam_type;
        }

        public String getBegin_date() {
            return begin;
        }

        public void setBegin_date(String begin_date) {
            this.begin = begin_date;
        }

        public String getId_groupe() {
            return id_groupe;
        }

        public void setId_groupe(String id_groupe) {
            this.id_groupe = id_groupe;
        }

        public String getId_module() {
            return id_module;
        }

        public void setId_module(String id_module) {
            this.id_module = id_module;
        }

        public String getId_classroom() {
            return id_classroom;
        }

        public void setId_classroom(String id_classroom) {
            this.id_classroom = id_classroom;
        }

        public String getBegin() {
            return begin;
        }

        public void setBegin(String begin) {
            this.begin = begin;
        }
    }

    public static class ExamType {
        private String id;
        private String type;
        public static final String TYPE = "type";
        public static final String EXAM_TYPE_ID = "id";
        public static final String _ID = "_id";
        public static final String TABLE_NAME = "exam_type";

        public ExamType(String id, String type) {
            this.id = id;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Group {
        private String id;
        private String id_specialty;
        private String id_level;
        private String name;
        private String add_date;
        public static final String GRP_ID = "id";
        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String ADD_DATE = "add_date";
        public static final String TABLE_NAME = "groupe";
        public static final String ID_SP = "id_specialty";
        public static final String ID_LEVEL = "id_level";

        public Group(String id, String id_specialty, String id_level, String name, String add_date) {
            this.id = id;
            this.id_specialty = id_specialty;
            this.id_level = id_level;
            this.name = name;
            this.add_date = add_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_specialty() {
            return id_specialty;
        }

        public void setId_specialty(String id_specialty) {
            this.id_specialty = id_specialty;
        }

        public String getId_level() {
            return id_level;
        }

        public void setId_level(String id_level) {
            this.id_level = id_level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class Journal {
        private String id_user;
        private String op;
        private String id;
        private String tab;
        private String date;
        public static final String ID = "id";
        public static final String USER_ID = "id_poster";
        public static final String OP = "op";
        public static final String TAB = "tab", TABLE_NAME = "journal";
        public static final String DATE = "date";
        public static final String DEL = "3", UPDATE = "2", INSERT = "1";

        public Journal(String id_user, String op, String id, String tab, String date) {
            this.id_user = id_user;
            this.op = op;
            this.id = id;
            this.tab = tab;
            this.date = date;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class Location {
        String id, id_user, latitude, longitude, altitude, time;
        public static final String ID = "_id", ID_USER = "id_user", ALT = "latitude", LONG = "longitude", TIME = "time",
                TABLE_NAME = "location", LAT = "latitude";

        public Location(String id, String id_user, String latitude, String logitude, String time) {
            this.id = id;
            this.id_user = id_user;
            this.latitude = latitude;
            this.longitude = logitude;
            this.time = time;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getAltitude() {
            return altitude;
        }

        public void setAltitude(String altitude) {
            this.altitude = altitude;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLogitude() {
            return longitude;
        }

        public void setLogitude(String logitude) {
            this.longitude = logitude;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class Absence {
        String id_module, id_session_type;
        public static final String TABLE_NAME = "absence",
                MODULE = "id_module", SESSION = "id_session_type";

        public Absence(String id_module, String id_session_type) {
            this.id_module = id_module;
            this.id_session_type = id_session_type;
        }

        public String getId_module() {
            return id_module;
        }

        public void setId_module(String id_module) {
            this.id_module = id_module;
        }

        public String getId_session_type() {
            return id_session_type;
        }

        public void setId_session_type(String id_session_type) {
            this.id_session_type = id_session_type;
        }
    }

    public static class Marks {
        String id, id_student, id_exam, note;
        public static final String ID = "id", ID_S = "id_student", ID_EXAM = "id_exam", NOTE = "note", TABLE_NAME = "marks";

        public Marks(String id, String id_student, String id_exam, String note) {
            this.id_student = id_student;
            this.id_exam = id_exam;
            this.note = note;
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_student() {
            return id_student;
        }

        public void setId_student(String id_student) {
            this.id_student = id_student;
        }

        public String getId_exam() {
            return id_exam;
        }

        public void setId_exam(String id_exam) {
            this.id_exam = id_exam;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }

    public static class groupeMember {
        private String id_groupe;
        private String id_Student;
        private String add_date;

        public static final String ADD_DATE = "add_date", ID = "_id";
        public static final String GRP_ID = "id_group";
        public static final String TABLE_NAME = "groupe_member";
        public static final String STUDENT_ID = "id_Student";

        public groupeMember(String id_groupe, String id_Student, String add_date) {
            this.id_groupe = id_groupe;
            this.id_Student = id_Student;
            this.add_date = add_date;
        }

        public String getId_groupe() {
            return id_groupe;
        }

        public void setId_groupe(String id_groupe) {
            this.id_groupe = id_groupe;
        }

        public String getId_Student() {
            return id_Student;
        }

        public void setId_Student(String id_Student) {
            this.id_Student = id_Student;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class Holiday extends New {
        private String begin_date;
        private String end_date;
        public static final String BEGIN_DATE = "begin_date";
        public static final String END_DATE = "end_date";
        public static final String TABLE_NAME = "holiday";

        public Holiday(String id, String id_user, String title, String content, String pub_date, String begin_date, String end_date) {
            super(id, id_user, title, content, pub_date);
            this.begin_date = begin_date;
            this.setNews_Type("4");
            this.end_date = end_date;
        }

        public String getBegin_date() {
            return begin_date;
        }

        public void setBegin_date(String begin_date) {
            this.begin_date = begin_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }
    }

    public static class Level {
        private String id, abrg, add_date;
        public static final String TABLE_NAME = "level";
        public static final String LEVEL_ID = "id";
        public static final String ID = "_id";
        public static final String ABRG = "abrg";
        public static final String ADD_DATE = "add_date";


        public Level(String id, String abrg, String add_date) {
            this.id = id;
            this.abrg = abrg;
            this.add_date = add_date;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAbrg() {
            return abrg;
        }

        public void setAbrg(String abrg) {
            this.abrg = abrg;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class MarksDisplayed extends New {
        private String id_exam;
        private String id_teacher;
        // private String id_group;
        public static final String TABLE_NAME = "marks_displayed";
        public static final String TEACHER_ID = "id_teacher";
        public static final String _ID = "_id";
        public static final String EXAM_ID = "id_exam", MODULE = "id_module";

        public static final String GRP_ID = "id_group";

        public MarksDisplayed(String id, String id_user, String title, String content, String pub_date, String id_exam,
                              String id_teacher) {
            super(id, id_user, title, content, pub_date);
            this.id_exam = id_exam;
            this.id_teacher = id_teacher;
            //this.id_group = id_group;
            this.setNews_Type("5");
        }

        public String getId_exam() {
            return id_exam;
        }

        public void setId_exam(String id_exam) {
            this.id_exam = id_exam;
        }

        public String getId_teacher() {
            return id_teacher;
        }

        public void setId_teacher(String id_teacher) {
            this.id_teacher = id_teacher;
        }

     /*   public String getId_group() {
            return id_group;
        }

        public void setId_group(String id_group) {
            this.id_group = id_group;
        }*/
    }

    public static class Meeting extends New {
        private String id_classroom, date;
        public static final String TABLE_NAME = "meeting";
        public static final String CLASSROOM_ID = "id_classroom";
        public static final String DATE = "date";

        public Meeting(String id, String id_user, String title, String content, String pub_date, String id_classroom, String date) {
            super(id, id_user, title, content, pub_date);
            this.id_classroom = id_classroom;
            this.date = date;
            this.setNews_Type("6");
        }

        public String getId_classroom() {
            return id_classroom;
        }

        public void setId_classroom(String id_classroom) {
            this.id_classroom = id_classroom;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class MeetingPresency {
        private String id_user, id_meeting;
        public static final String TABLE_NAME = "meeting_presence";
        public static final String ID_USER = "id_user", ID = "_id";
        public static final String ID_MEETING = "id_meeting";

        public MeetingPresency(String id_user, String id_meeting) {
            this.id_user = id_user;
            this.id_meeting = id_meeting;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getId_meeting() {
            return id_meeting;
        }

        public void setId_meeting(String id_meeting) {
            this.id_meeting = id_meeting;
        }
    }

    public static class Module {
        private String id, name, abrg, add_date;
        public static final String NAME = "name";
        public static final String TABLE_NAME = "module";
        public static final String MODULE_ID = "id";
        public static final String ID = "_id";
        public static final String ABRG = "abrg";
        public static final String ADD_DATE = "add_date";

        public Module(String id, String name, String abrg, String add_date) {
            this.id = id;
            this.name = name;
            this.abrg = abrg;
            this.add_date = add_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAbrg() {
            return abrg;
        }

        public void setAbrg(String abrg) {
            this.abrg = abrg;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class MSG {
        private String id, id_user, msg, add_date;
        public static final String TABLE_NAME = "message";
        public static final String _ID = "_id";
        public static final String MSG_ID = "id";
        public static final String MSG = "msg";
        public static final String USER_ID = "id_user";
        public static final String ADD_DATE = "add_date";

        public MSG(String id, String id_user, String msg, String add_date) {
            this.id = id;
            this.id_user = id_user;
            this.msg = msg;
            this.add_date = add_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class MsgSentTo {
        private String id, id_msg, id_user, seen;
        public static final String TABLE_NAME = "messege_sent_to", ID = "_id";
        public static final String MSG_ID = "id_msg";
        public static final String USER_ID = "id_user";
        public static final String SEEN = "seen";

        public MsgSentTo(String id, String id_msg, String id_user, String seen) {
            this.id_msg = id_msg;
            this.id_user = id_user;
            this.seen = seen;
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setSeen(String seen) {
            this.seen = seen;
        }

        public String getSeen() {
            return seen;
        }

        public String getId_msg() {
            return id_msg;
        }

        public void setId_msg(String id_msg) {
            this.id_msg = id_msg;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }
    }

    public static class New {
        private String id;
        private String id_user, news_type;
        private String title;
        private String content;
        private String pub_date;
        public static final String NEWS_ID = "id";
        public static final String ID_USER = "id_user";
        public static final String ID = "_id", TYPE = "news_type";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String PUBLESHED = "pub_date";
        public static final String TABLE_NAME = "news";

        public String getNews_Type() {
            return news_type;
        }

        public void setNews_Type(String news_Type) {
            this.news_type = news_Type;
        }

        public New(String id, String id_user, String title, String content, String pub_date) {
            this.id = id;
            this.id_user = id_user;
            this.title = title;
            this.content = content;
            news_type = "0";
            this.pub_date = pub_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPub_date() {
            return pub_date;
        }

        public void setPub_date(String pub_date) {
            this.pub_date = pub_date;
        }
    }

    public static class NewsVisibility {
        private String id, id_user, id_news, vote_possible;
        public static final String TABLE_NAME = "news_visibilitu";
        public static final String USER_ID = "id_user", ID = "_id";
        public static final String NEWS_ID = "id_news";
        public static final String Vote = "vote_possible";


        public NewsVisibility(String id, String id_user, String id_news, String vote_possible) {
            this.id_user = id_user;
            this.id_news = id_news;
            this.vote_possible = vote_possible;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getId_news() {
            return id_news;
        }

        public void setId_news(String id_news) {
            this.id_news = id_news;
        }

        public String getVote_possible() {
            return vote_possible;
        }

        public void setVote_possible(String vote_possible) {
            this.vote_possible = vote_possible;
        }
    }

    public static class PedagogicalProcedure extends New {
        private String begin_date, end_date, id_place, docs;
        public static final String TABLE_NAME = "pedagogical_procedure";
        public static final String BEGIN_DATE = "begin_date";
        public static final String END_DATE = "end_date";
        public static final String PLACE_ID = "id_place";
        public static final String DOCS = "docs";

        public PedagogicalProcedure(String id, String id_user, String title, String content, String pub_date, String begin_date,
                                    String end_date, String id_place, String docs) {
            super(id, id_user, title, content, pub_date);
            this.begin_date = begin_date;
            this.end_date = end_date;
            this.id_place = id_place;
            this.docs = docs;
            this.setNews_Type("8");
        }

        public String getBegin_date() {
            return begin_date;
        }

        public void setBegin_date(String begin_date) {
            this.begin_date = begin_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getId_place() {
            return id_place;
        }

        public void setId_place(String id_place) {
            this.id_place = id_place;
        }

        public String getDocs() {
            return docs;
        }

        public void setDocs(String docs) {
            this.docs = docs;
        }
    }

    public static class RequestDone {
        private String id, id_establishement, add_date, id_admin;
        public static final String TABLE_NAME = "request_done";
        public static final String REQ_DONE_ID = "id";
        public static final String EST_ID = "id_establishement";
        public static final String ID = "_id", ID_ADMIN = "id_admin";
        public static final String ADD_DATE = "add_date";

        public RequestDone(String id, String id_establishement, String id_admin) {
            this.id = id;
            this.id_establishement = id_establishement;
            this.id_admin = id_admin;
        }

        public String getId_admin() {
            return id_admin;
        }

        public void setId_admin(String id_admin) {
            this.id_admin = id_admin;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_establishement() {
            return id_establishement;
        }

        public void setId_establishement(String id_establishement) {
            this.id_establishement = id_establishement;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class Responsibility {
        private String id;
        private String name;
        public static final String TABLE_NAME = "responsibility";
        public static final String RESP_ID = "id";
        public static final String _ID = "_id";
        public static final String NAME = "name";

        public Responsibility(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ResponsibleTeacher extends Teacher {
        private String id_responsbility;
        public static final String ADD_DATE = "add_date";
        public static final String SPECIALTY = "id_responsibility";
        public static final String TABLE_NAME = "responsble_teacher";

        public ResponsibleTeacher(String id, String email, String userType, String last_name, String first_name, String sexe, String birth_date, String password, String home_town, String avatar, String id_specialty, String add_date, String id_responsibility) {
            super(id, email, userType, last_name, first_name, sexe, birth_date, password, home_town, avatar, id_specialty, add_date);
            this.id_responsbility = id_responsibility;
        }

        public String getId_responsibility() {
            return id_responsbility;
        }

        public void setId_responsibility(String id_responsibility) {
            this.id_responsbility = id_responsibility;
        }
    }

    public static class Session {
        private String id;
        private String id_session_type;
        private String enabled;
        private String id_day;
        private String time;
        private String id_classroom, id_module;
        private String id_teacher;
        private String id_groupe;
        private String semester_1;

        private String add_date;
        public static final String TABLE_NAME = "session";
        public static final String SESSION_ID = "id", ID_MODULE = "id_module";
        public static final String _ID = "_id";
        public static final String ID_SESSION_TYPE = "id_session_type";
        public static final String ENABLED = "enabled";
        public static final String ID_DAY = "id_day";
        public static final String TIME = "time";
        public static final String ID_CLASSROOM = "id_classroom";
        public static final String ID_TEACHER = "id_teacher";
        public static final String ID_GRP = "id_groupe";
        public static final String IS_SEMESTER_1 = "semester_1";
        public static final String ADD_DATE = "add_date";

        public Session(String id, String id_session_type, String enabled, String id_day, String time, String id_classroom, String id_module, String id_teacher, String id_groupe, String semester_1) {
            this.id = id;
            this.id_session_type = id_session_type;
            this.enabled = enabled;
            this.id_day = id_day;
            this.time = time;
            this.id_classroom = id_classroom;
            this.id_module = id_module;
            this.id_teacher = id_teacher;
            this.id_groupe = id_groupe;
            this.semester_1 = semester_1;
        }

        public String getId_module() {
            return id_module;
        }

        public void setId_module(String id_module) {
            this.id_module = id_module;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_session_type() {
            return id_session_type;
        }

        public void setId_session_type(String id_session_type) {
            this.id_session_type = id_session_type;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getId_day() {
            return id_day;
        }

        public void setId_day(String id_day) {
            this.id_day = id_day;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getId_classroom() {
            return id_classroom;
        }

        public void setId_classroom(String id_classroom) {
            this.id_classroom = id_classroom;
        }

        public String getId_teacher() {
            return id_teacher;
        }

        public void setId_teacher(String id_teacher) {
            this.id_teacher = id_teacher;
        }

        public String getId_groupe() {
            return id_groupe;
        }

        public void setId_groupe(String id_groupe) {
            this.id_groupe = id_groupe;
        }

        public String getSemester_1() {
            return semester_1;
        }

        public void setSemester_1(String semester_1) {
            this.semester_1 = semester_1;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class SessionType {
        private String id;
        private String name;
        public static final String TABLE_NAME = "session_type";
        public static final String SESSION_TYPE_ID = "id";
        public static final String _ID = "_id";
        public static final String NAME = "name";

        public SessionType(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Specialty {
        private String id;
        private String name;
        protected String add_date;
        public static final String ADD_DATE = "add_date";
        public static final String TABLE_NAME = "spacialty";
        public static final String S_ID = "id";
        public static final String _ID = "_id";
        public static final String NAME = "name";

        public Specialty(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Strike extends New {
        private String begin_date, end_date, cause;
        public static final String BEGIN_DATE = "begin_date";
        public static final String END_DATE = "end_date";
        public static final String TABLE_NAME = "strike";
        public static final String CAUSE = "cause";

        public Strike(String id, String id_user, String title, String content, String pub_date, String begin_date,
                      String end_date, String cause) {
            super(id, id_user, title, content, pub_date);
            this.begin_date = begin_date;
            this.end_date = end_date;
            this.cause = cause;
            this.setNews_Type("9");
        }

        public String getBegin_date() {
            return begin_date;
        }

        public void setBegin_date(String begin_date) {
            this.begin_date = begin_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }
    }

    public static class Student extends User {
        protected String mat;
        protected String bib;
        protected String add_date;
        public static final String lIB_CARD_NUM = "bib";
        public static final String MAT_NUM = "mat";
        public static final String ADD_DATE = "add_date";
        public static final String TABLE_NAME = "student";

        public Student(String id, String email, String userType, String last_name, String first_name, String sexe,
                       String birth_date, String password, String home_town, String avatar, String mat, String bib, String add_date) {
            super(id, email, userType, last_name, first_name, sexe, birth_date, password, home_town, avatar);
            this.mat = mat;
            this.bib = bib;
            this.add_date = add_date;
        }

        public String getMat() {
            return mat;
        }

        public void setMat(String mat) {
            this.mat = mat;
        }

        public String getBib() {
            return bib;
        }

        public void setBib(String bib) {
            this.bib = bib;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public static class Teacher extends User {
        protected String id_specialty;
        protected String add_date;
        public static final String ID_SPACIALTY = "id_specialty";
        public static final String ADD_DATE = "add_date";
        public static final String TABLE_NAME = "teacher";

        public Teacher(String id, String email, String userType, String last_name, String first_name, String sexe,
                       String birth_date, String password, String home_town, String avatar, String id_specialty, String add_date) {
            super(id, email, userType, last_name, first_name, sexe, birth_date, password, home_town, avatar);
            this.id_specialty = id_specialty;
            this.add_date = add_date;
        }

        public String getId_specialty() {
            return id_specialty;
        }

        public void setId_specialty(String id_specialty) {
            this.id_specialty = id_specialty;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public class Town {
        private String id;
        private String town_name;
        public static final String TABLE_NAME = "town";
        public static final String TOWN_ID = "id";
        public static final String NAME = "town_name";
        public static final String _ID = "_id";

        public Town(String id, String town_name) {
            this.id = id;
            this.town_name = town_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTown_name() {
            return town_name;
        }

        public void setTown_name(String town_name) {
            this.town_name = town_name;
        }


    }

    public static class User {
        protected String id;
        protected String email;
        protected String user_type;
        protected String last_name;
        protected String first_name;
        protected String sexe;
        protected String birth_date;
        protected String password;
        protected String home_town;
        protected String avatar;
        public static final String USER_ID = "id";
        public static final String ID = "_id";
        public static final String EMAIL = "email";
        public static final String LAST_NAME = "last_name";
        public static final String FIRST_NAME = "first_name";
        public static final String SEXE = "sexe";
        public static final String USER_TYPE = "user_type";
        public static final String BIRTH_DATE = "birth_date";
        public static final String PASSWORD = "password";
        public static final String HOME = "home_town";
        public static final String PIC = "avatar";
        public static final String TABLE_NAME = "user";

        public User(String id, String email, String userType, String last_name, String first_name, String sexe, String birth_date,
                    String password, String home_town, String avatar) {
            this.id = id;
            this.email = email;
            this.user_type = userType;
            this.last_name = last_name;
            this.first_name = first_name;
            this.sexe = sexe;
            this.birth_date = birth_date;
            this.password = password;
            this.home_town = home_town;
            this.avatar = avatar;
        }

        public String getUserType() {
            return user_type;
        }

        public void setUserType(String userType) {
            this.user_type = userType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getSexe() {
            return sexe;
        }

        public void setSexe(String sexe) {
            this.sexe = sexe;
        }

        public String getBirth_date() {
            return birth_date;
        }

        public void setBirth_date(String birth_date) {
            this.birth_date = birth_date;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHome_town() {
            return home_town;
        }

        public void setHome_town(String home_town) {
            this.home_town = home_town;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class Voted {
        private String id, id_post, id_user, agree, reason, add_date;
        public static final String ID_USER = "id_user", ID = "_id";
        public static final String AGREE = "agree";
        public static final String POST = "id_post";
        public static final String TABLE_NAME = "voted";
        public static final String REASON = "reason";
        public static final String ADD_DATE = "add_date";

        public Voted(String id, String id_post, String id_user, String agree, String reason, String add_date) {
            this.id_post = id_post;
            this.id = id;
            this.id_user = id_user;
            this.agree = agree;
            this.reason = reason;
            this.add_date = add_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_post() {
            return id_post;
        }

        public void setId_post(String id_post) {
            this.id_post = id_post;
        }

        public String getId_user() {
            return id_user;
        }

        public void setId_user(String id_user) {
            this.id_user = id_user;
        }

        public String getAgree() {
            return agree;
        }

        public void setAgree(String agree) {
            this.agree = agree;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getAdd_date() {
            return add_date;
        }

        public void setAdd_date(String add_date) {
            this.add_date = add_date;
        }
    }

    public class Work {
        private String id_work;
        private String name;
        public static final String TABLE_NAME = "work";
        public static final String NAME = "work";
        public static final String _ID = "_id";
        public static final String WORK_ID = "id";

        public Work(String id, String name) {
            this.id_work = id;
            this.name = name;
        }

        public String getId() {
            return id_work;
        }

        public void setId(String id) {
            this.id_work = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void create_a_notification(int type, int id, String body, String triger, Context context, String chanel) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, chanel);
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.logo64x64);
        notification.setLargeIcon(
                BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.logo128x128));
        notification.setWhen(System.currentTimeMillis());
        notification.setTicker(triger);
        String title;
        notification.setContentText(body);
        Intent intent;
        switch (type) {
            case 1: {
                intent = new Intent(context, TimeTableActivity.class);
                title = "Changement d'emploi du temps";
                break;
            }
            case 2: {
                intent = new Intent(context, NewsActivity.class);
                title = "Une nouvelle est partagé";
                break;
            }
            case 6: {
                intent = new Intent(context, AbsenceActivity.class);
                title = "Une nouvelle absence";
                break;
            }
            case 3: {
                intent = new Intent(context, MessegesActivity.class);

                title = "Un nouveau message";
                break;
            }
            case 4: {
                intent = new Intent(context, MarksActivity.class);

                title = "Une note a été affiché";
                break;
            }
            case 5: {
                intent = new Intent(context, DocumentRequest.class);
                title = "Un document Prét";
                break;
            }
            default: {
                intent = new Intent(context, HomeActivity.class);
                title = "MyUKT";
                break;
            }
        }
        notification.setContentTitle(title);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, notification.build());


    }

    public static class FillDatabaseWithStableInfo extends IntentService {
        public FillDatabaseWithStableInfo() {
            super("add_stable_data_to_service");
            Log.d("StableInfo:", "constructor");
        }


        @SuppressLint("LongLogTag")
        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, createUrl(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getInt("result") != 0) {
                                    GsonBuilder gsonBuilder = new GsonBuilder();
                                    Gson gson = gsonBuilder.create();
                                    MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
                                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                                    JSONArray jsonArray = response.getJSONArray("exam_type");
                                    MyClasses.ExamType examType;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        examType = gson.fromJson(jsonArray.get(i).toString(), MyClasses.ExamType.class);
                                        dbHelper.addToExamTypeTable(database, examType);
                                    }
                                    jsonArray = response.getJSONArray("classroom");
                                    MyClasses.Classroom classroom;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        classroom = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Classroom.class);
                                        dbHelper.addToClassroomTable(database, classroom);
                                    }
                                    jsonArray = response.getJSONArray("days");
                                    MyClasses.Day day;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        day = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Day.class);
                                        dbHelper.addToDaysTable(database, day);
                                    }
                                    jsonArray = response.getJSONArray("level");
                                    MyClasses.Level level;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        level = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Level.class);
                                        dbHelper.addToLevelTable(database, level);
                                    }
                                    jsonArray = response.getJSONArray("session_type");
                                    MyClasses.SessionType session_type;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        session_type = gson.fromJson(jsonArray.get(i).toString(), MyClasses.SessionType.class);
                                        dbHelper.addToSessionTypeTable(database, session_type);
                                    }
                                    jsonArray = response.getJSONArray("specialty");
                                    MyClasses.Specialty specialty;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        specialty = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Specialty.class);
                                        dbHelper.addToSpecialtyTable(database, specialty);
                                    }
                                    jsonArray = response.getJSONArray("town");
                                    MyClasses.Town town;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        town = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Town.class);
                                        dbHelper.addToTownTable(database, town);
                                    }
                                    jsonArray = response.getJSONArray("work");
                                    MyClasses.Work work;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        work = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Work.class);
                                        dbHelper.addToWorkTable(database, work);
                                    }
                                    jsonArray = response.getJSONArray("esth");
                                    MyClasses.EST est;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        est = gson.fromJson(jsonArray.get(i).toString(), MyClasses.EST.class);
                                        dbHelper.addToEstTable(database, est);
                                    }
                                    jsonArray = response.getJSONArray("responsibility");
                                    MyClasses.Responsibility responsibility;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        responsibility = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Responsibility.class);
                                        dbHelper.addToResponsibilityTable(database, responsibility);

                                    }
                                    jsonArray = response.getJSONArray("module");
                                    MyClasses.Module module;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        module = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Module.class);
                                        dbHelper.addToModuleTable(database, module);
                                    }
                                    dbHelper.close();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("StableInfo:", "onHandleIntent:onResponse");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("StableInfo:onErrorResponse:", "onHandleIntent");
                    error.printStackTrace();
                }
            }
            );
            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
            Log.d("StableInfo:", "onHandleIntent");
            onDestroy();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("StableInfo:", "onDestroy");
        }

        private String createUrl() {
            String result = null;
            Uri uri = Uri.parse(MyClasses.GET_STABLE_DATA_URL).buildUpon().build();
            result = uri.toString();
            return result;
        }
    }

    public static class FillDatabaseTimeTableTable extends IntentService {
        public FillDatabaseTimeTableTable() {
            super("FillDatabaseTimeTableTable");
            Log.d("TimeTableInfo:", "constructor");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            final SharedPreferences preferences = getApplicationContext().getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
            //int user_type=intent.getIntExtra("user_type",0);
            final int a = preferences.getInt("user_type", 0);
            final ArrayList<String> emails = new ArrayList<>();
            final ArrayList<String> ids = new ArrayList<>();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    createUrl(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getInt("params") == 1) {
                                    GsonBuilder gsonBuilder = new GsonBuilder();
                                    Gson gson = gsonBuilder.create();
                                    MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
                                    SQLiteDatabase database = dbHelper.getWritableDatabase();

                                    JSONArray jsonArray;

                                    jsonArray = response.getJSONArray("table");
                                    MyClasses.Session session;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        session = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Session.class);
                                        dbHelper.addToTimeTable(database, session);
                                    }
                                    if (a == 5 || a == 2) {
                                        jsonArray = response.getJSONArray("groupe");
                                        MyClasses.Group group;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            group = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Group.class);
                                            dbHelper.addToGroupeTable(database, group);
                                        }
                                    } else {

                                        jsonArray = response.getJSONArray("groupe");
                                        MyClasses.Group group;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            group = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Group.class);
                                            dbHelper.addToGroupeTable(database, group);
                                        }
                                        jsonArray = response.getJSONArray("user");
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            String email = "";
                                            String id = "0";
                                            switch (jsonArray.getJSONObject(i).getInt("user_type")) {
                                            /*session = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Session.class);
                                            dbHelper.addToTimeTable(database, session);*/

                                                case 2: {
                                                    MyClasses.Teacher teacher = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Teacher.class);
                                                    teacher.setUserType(String.valueOf(2));
                                                    email = teacher.getEmail();
                                                    emails.add(email);
                                                    id = teacher.getId();
                                                    ids.add(id);
                                                    dbHelper.insertATeacher(database, teacher);
                                                    break;
                                                }
                                                case 5: {
                                                    MyClasses.ResponsibleTeacher responsibleTeacher = gson.fromJson(jsonArray.get(i).toString(),
                                                            MyClasses.ResponsibleTeacher.class);
                                                    responsibleTeacher.setUserType(String.valueOf(5));
                                                    email = responsibleTeacher.getEmail();
                                                    emails.add(email);
                                                    id = responsibleTeacher.getId();
                                                    ids.add(id);
                                                    dbHelper.insertAresponsibleTeacher(database, responsibleTeacher);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    //dbHelper.close();
                                    Log.d("TimeTableInfo:", "onResponse");
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("has_load_time_table", true);
                                    editor.commit();
                                    Intent mintent = new Intent(getApplicationContext(), GetUsersAvatars.class);
                                    mintent.putStringArrayListExtra("emails", emails);
                                    mintent.putStringArrayListExtra("ids", ids);
                                    startService(mintent);
                                    onDestroy();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TimeTableInfo:onError:", "onHandleIntent");
                    error.printStackTrace();
                }
            });
            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
            Log.d("TimeTableInfo:", "onHandleIntent");
        }

        public String createUrl() {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
            int userType = preferences.getInt("user_type", 0);
            Log.d("usertype in create url", String.valueOf(userType));
            String result = null;
            String param;
            Uri uri;
            if (userType == 2 || userType == 5) {
                param = "id_teacher";
                long id = preferences.getLong("id", 0);
                uri = Uri.parse(MyClasses.GET_TEACHER_TIME_TABLE_URL).buildUpon().
                        appendQueryParameter(param, String.valueOf(id)).appendQueryParameter("id", String.valueOf(id)).build();
            } else {
                param = "id_groupe";
                int id = preferences.getInt("id_specialty", 0);
                long id_user = preferences.getLong("id", 0);
                uri = Uri.parse(MyClasses.GET_GROUPE_TIME_TABLE_URL).buildUpon().
                        appendQueryParameter(param, String.valueOf(id)).appendQueryParameter("id", String.valueOf(id_user))
                        .build();
            }
            result = uri.toString();
            return result;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("TimeTableInfo:", "onDestroy");
        }
    }

    public static class GetUsersAvatars extends IntentService {

        public GetUsersAvatars() {
            super("images");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            Log.d("GetUsersAvatars:", "onHANDLE");
            final ArrayList<String> emails = intent.getStringArrayListExtra("emails"),
                    ids = intent.getStringArrayListExtra("ids");
            ImageRequest imageRequest;
            final MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
            final SQLiteDatabase database = dbHelper.getWritableDatabase();
            for (String a : emails) {

                final String email = a;
                imageRequest = new ImageRequest(MyClasses.AVATARS_URL + a + MyClasses.AVATARS_FORMAT, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        response.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
                        byte[] b = baos.toByteArray();
                        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        dbHelper.changeUserImage(database, ids.get(emails.indexOf(email)), encodedImage);
                        if (emails.indexOf(email) == emails.size() - 1) {
                            //dbHelper.close();
                            onDestroy();
                            Log.d("GetUsersAvatars:", "onRESPONSE");
                        }
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("GetUsersAvatars:", "oneRROR");
                    }
                });
                mySinglton.getmInstance(getApplicationContext()).addToRequestQue(imageRequest);
            }

        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("GetUsersAvatarsService:", "onDestroy");
        }


    }

    public static class FillDatabaseWithNews extends IntentService {

        public FillDatabaseWithNews() {
            super("FillDatabaseWithNews");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
            String id = String.valueOf(preferences.getLong("id", 0));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, createUrl(id), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                final MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
                                final SQLiteDatabase database = dbHelper.getWritableDatabase();
                                if (response.getInt("params") != 0) {
                                    if (response.getInt("result") > 0) {
                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                        Gson gson = gsonBuilder.create();
                                        final ArrayList<String> emails = new ArrayList<>();
                                        final ArrayList<String> ids = new ArrayList<>();
                                        /*JSONArray jsonArray=response.getJSONArray("news_visibilitu");
                                        MyClasses.NewsVisibility visibility;
                                        for(int i=0;i<jsonArray.length();i++){
                                            visibility=gson.fromJson(jsonArray.get(i).toString(),MyClasses.NewsVisibility.class);
                                            dbHelper.addAnewsVisibility(database,visibility);
                                        }*/
                                        JSONArray jsonArray = response.getJSONArray("change_of_session");
                                        MyClasses.ChangeOfSession changeOfSession;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            changeOfSession = gson.fromJson(jsonArray.get(i).toString(), MyClasses.ChangeOfSession.class);
                                            dbHelper.addAchangeOfSession(database, changeOfSession);
                                        }
                                        jsonArray = response.getJSONArray("consultation");
                                        MyClasses.Consultation consultation;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            consultation = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Consultation.class);
                                            dbHelper.addAconsultation(database, consultation);
                                        }
                                        jsonArray = response.getJSONArray("exam");
                                        MyClasses.Exam exam;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            exam = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Exam.class);
                                            dbHelper.addAexam(database, exam);
                                        }
                                        jsonArray = response.getJSONArray("holiday");
                                        MyClasses.Holiday holiday;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            holiday = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Holiday.class);
                                            dbHelper.addAholiday(database, holiday);
                                        }
                                        jsonArray = response.getJSONArray("marks_displayed");
                                        MyClasses.MarksDisplayed marksDisplayed;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            marksDisplayed = gson.fromJson(jsonArray.get(i).toString(), MyClasses.MarksDisplayed.class);
                                            dbHelper.addAmarksDisplayed(database, marksDisplayed);
                                        }
                                        jsonArray = response.getJSONArray("meeting");
                                        MyClasses.Meeting meeting;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            meeting = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Meeting.class);
                                            dbHelper.addAmeeting(database, meeting);
                                        }
                                        jsonArray = response.getJSONArray("news");
                                        MyClasses.New aNew;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            aNew = gson.fromJson(jsonArray.get(i).toString(), MyClasses.New.class);
                                            dbHelper.addAnew(database, aNew);
                                        }
                                        jsonArray = response.getJSONArray("pedagogical_procedure");
                                        MyClasses.PedagogicalProcedure procedure;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            procedure = gson.fromJson(jsonArray.get(i).toString(), MyClasses.PedagogicalProcedure.class);
                                            dbHelper.addAprocedure(database, procedure);
                                        }
                                        jsonArray = response.getJSONArray("strike");
                                        MyClasses.Strike strike;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            strike = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Strike.class);
                                            dbHelper.addAstrike(database, strike);
                                        }
                                        jsonArray = response.getJSONArray("voted");
                                        MyClasses.Voted voted;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            voted = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Voted.class);
                                            dbHelper.addAvote(database, voted);
                                        }
                                        jsonArray = response.getJSONArray("journal");
                                        MyClasses.Journal journal;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            journal = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Journal.class);
                                            dbHelper.addTojournal(database, journal);
                                        }
                                        jsonArray = response.getJSONArray("user");
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            String email = "";
                                            String id = "0";
                                            int a = Integer.parseInt(jsonArray.getJSONObject(i).getString("user_type"));
                                            switch (Integer.parseInt(jsonArray.getJSONObject(i).getString("user_type"))) {
                                            /*session = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Session.class);
                                            dbHelper.addToTimeTable(database, session);*/

                                                case 2: {
                                                    MyClasses.Teacher teacher = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Teacher.class);
                                                    teacher.setUserType("2");
                                                    email = teacher.getEmail();
                                                    emails.add(email);
                                                    id = teacher.getId();
                                                    ids.add(id);
                                                    dbHelper.insertATeacher(database, teacher);
                                                    break;
                                                }
                                                case 5: {
                                                    MyClasses.ResponsibleTeacher responsibleTeacher = gson.fromJson(jsonArray.get(i).toString(),
                                                            MyClasses.ResponsibleTeacher.class);
                                                    responsibleTeacher.setUserType("5");
                                                    email = responsibleTeacher.getEmail();
                                                    emails.add(email);
                                                    id = responsibleTeacher.getId();
                                                    ids.add(id);
                                                    dbHelper.insertAresponsibleTeacher(database, responsibleTeacher);
                                                    break;
                                                }
                                                case 1: {//student
                                                    MyClasses.Student user = gson.fromJson(jsonArray.get(i).toString()
                                                            , MyClasses.Student.class);
                                                    user.setUserType("1");
                                                    email = user.getEmail();
                                                    emails.add(email);
                                                    id = user.getId();
                                                    ids.add(id);
                                                    break;
                                                }
                                                case 0: {//student
                                                    MyClasses.Admin user = gson.fromJson(jsonArray.get(i).toString()
                                                            , MyClasses.Admin.class);
                                                    user.setUserType("0");
                                                    email = user.getEmail();
                                                    emails.add(email);
                                                    id = user.getId();
                                                    ids.add(id);
                                                    dbHelper.insertAdmin(database, user);
                                                    break;
                                                }
                                                case 4: {
                                                    MyClasses.Delegue user = gson.fromJson(jsonArray.get(i).toString()
                                                            , MyClasses.Delegue.class);
                                                    user.setUserType("4");
                                                    email = user.getEmail();
                                                    emails.add(email);
                                                    id = user.getId();
                                                    ids.add(id);
                                                    dbHelper.insertAStudent(database,user);
                                                    break;
                                                }

                                            }
                                        }
                                        Intent mintent = new Intent(getApplicationContext(), GetUsersAvatars.class);
                                        mintent.putStringArrayListExtra("emails", emails);
                                        mintent.putStringArrayListExtra("ids", ids);
                                        startService(mintent);
                                        /*ImageRequest imageRequest;
                                        for (String a : emails) {

                                            final String email = a;
                                            imageRequest = new ImageRequest(MyClasses.AVATARS_URL + a + MyClasses.AVATARS_FORMAT, new Response.Listener<Bitmap>() {
                                                @Override
                                                public void onResponse(Bitmap response) {
                                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                    response.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
                                                    byte[] b = baos.toByteArray();
                                                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                                                    dbHelper.changeUserImage(database, ids.get(emails.indexOf(email)), encodedImage);
                                                    if (emails.indexOf(email) == emails.size() - 1) {
                                                        dbHelper.close();
                                                        onDestroy();
                                                        Log.d("GetUsersAvatars:","onRESPONSE");
                                                    }
                                                }
                                            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    error.printStackTrace();
                                                    Log.d("GetUsersAvatars:","oneRROR");
                                                }
                                            });
                                            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(imageRequest);
                                        }*/

                                    }
                                }
                                Log.d("FillDatabaseWithNews:", "onResponse");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("FillDatabaseWithNews:", "onErrorResponse");
                }
            }
            );
            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
            Log.d("FillDatabaseWithNews:", "onHandleIntent");

        }

        private String createUrl(String id) {
            String answer = null;
            Uri uri = Uri.parse(MyClasses.GET_NEWS_URL).buildUpon().
                    appendQueryParameter("id_user", id).build();
            answer = uri.toString();
            return answer;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("onHandleIntent:", "onDestroy");
        }
    }

    public static class GetAllDocs extends IntentService {

        public GetAllDocs() {
            super("GetAllDocs");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
            String id = String.valueOf(preferences.getLong("id", 0));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    create(id), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getInt("params") == 1) {
                            if (response.getInt("result") > 0) {
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                ArrayList<String> admins = new ArrayList<>();
                                ArrayList<String> ids = new ArrayList<>();
                                final MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
                                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                                JSONArray jsonArray = response.getJSONArray("journal");
                                MyClasses.Journal journal;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    journal = gson.fromJson(jsonArray.getJSONObject(i).toString(), MyClasses.Journal.class);
                                    dbHelper.addTojournal(db, journal);
                                }
                                jsonArray = response.getJSONArray("requests");
                                MyClasses.DocumentRequest documentRequest;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    documentRequest = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                            MyClasses.DocumentRequest.class);
                                    dbHelper.addAdoc(db, documentRequest);
                                }
                                jsonArray = response.getJSONArray("requests_done");
                                MyClasses.RequestDone requestDone;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    requestDone = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                            MyClasses.RequestDone.class);
                                    dbHelper.addAdocDone(db, requestDone);
                                }
                                jsonArray = response.getJSONArray("users");
                                MyClasses.Admin admin;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    admin = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                            MyClasses.Admin.class);
                                    ids.add(admin.getId());
                                    admins.add(admin.getEmail());
                                    dbHelper.insertAdmin(db, admin);

                                }

                                Intent mintent = new Intent(getApplicationContext(), GetUsersAvatars.class);
                                mintent.putStringArrayListExtra("emails", admins);
                                mintent.putStringArrayListExtra("ids", ids);
                                startService(mintent);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            );
            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("GetAllDocs:", "onDestroy");
        }

        String create(String id) {
            String result = null;
            Uri uri = Uri.parse(GET_ALL_DOCS_URL).buildUpon().appendQueryParameter("id_user", id).build();
            result = uri.toString();
            return result;
        }
    }

    public static class GetAllMarks extends IntentService {

        public GetAllMarks() {
            super("GetAllMarks");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
            String id = String.valueOf(preferences.getLong("id", 0));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, create(id), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getInt("params") == 1) {
                                    if (response.getInt("result") > 0) {
                                        final MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
                                        final SQLiteDatabase db = dbHelper.getWritableDatabase();
                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                        Gson gson = gsonBuilder.create();
                                        JSONArray jsonArray = response.getJSONArray("marks");
                                        MyClasses.Marks marks;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            marks = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.Marks.class);
                                            dbHelper.addAmark(db, marks);
                                        }
                                        onDestroy();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("GetAllMarks:", "onDestroy");
        }

        String create(String id) {
            String result = null;
            Uri uri = Uri.parse(GET_ALL_MARKS_URL).buildUpon().appendQueryParameter("id_user", id).build();
            result = uri.toString();
            return result;
        }
    }

    public static class GetAllMessages extends IntentService {

        public GetAllMessages() {
            super("GetAllMessages");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
            String id = String.valueOf(preferences.getLong("id", 0));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, create(id), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getInt("params") == 1) {
                                    if (response.getInt("result") > 0) {
                                        final MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
                                        final SQLiteDatabase db = dbHelper.getWritableDatabase();
                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                        final ArrayList<String> emails = new ArrayList<>();
                                        final ArrayList<String> ids = new ArrayList<>();
                                        Gson gson = gsonBuilder.create();
                                        JSONArray jsonArray = response.getJSONArray("msgs");
                                        MyClasses.MSG msg;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            msg = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.MSG.class);
                                            dbHelper.insertAmsg(db, msg);
                                        }
                                        jsonArray = response.getJSONArray("msgs_send_to");
                                        MyClasses.MsgSentTo msgSentTo;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            msgSentTo = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.MsgSentTo.class);
                                            dbHelper.insertAmsgSendTo(db, msgSentTo);
                                        }
                                        jsonArray = response.getJSONArray("journal");
                                        MyClasses.Journal journal;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            journal = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.Journal.class);
                                            dbHelper.addTojournal(db, journal);
                                        }
                                        jsonArray = response.getJSONArray("user");
                                        MyClasses.User user;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            user = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.User.class);
                                            emails.add(user.getEmail());
                                            ids.add(user.getId());
                                            dbHelper.insertAuser(db, user);
                                        }
                                        Intent mintent = new Intent(getApplicationContext(), GetUsersAvatars.class);
                                        mintent.putStringArrayListExtra("emails", emails);
                                        mintent.putStringArrayListExtra("ids", ids);
                                        startService(mintent);

                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("GetAllMessages:", "onDestroy");
        }

        String create(String id) {
            String result = null;
            Uri uri = Uri.parse(GET_ALL_MSG_URL).buildUpon().appendQueryParameter("id_user", id).build();
            result = uri.toString();
            return result;
        }
    }

    public static class getAllVotes extends IntentService {

        public getAllVotes() {
            super("getAllVotes");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {

            SharedPreferences preferences = getApplicationContext().getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
            String id = String.valueOf(preferences.getLong("id", 0));
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    create(id), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getInt("params") == 1) {
                            if (response.getInt("result") > 0) {
                                GsonBuilder gsonBuilder = new GsonBuilder();
                                Gson gson = gsonBuilder.create();
                                ArrayList<String> admins = new ArrayList<>();
                                ArrayList<String> ids = new ArrayList<>();
                                final MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
                                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                                JSONArray jsonArray = response.getJSONArray("journal");
                                MyClasses.Journal journal;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    journal = gson.fromJson(jsonArray.getJSONObject(i).toString(), MyClasses.Journal.class);
                                    dbHelper.addTojournal(db, journal);
                                }
                                jsonArray = response.getJSONArray("voted");
                                MyClasses.Voted voted;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    voted = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                            MyClasses.Voted.class);
                                    dbHelper.addAvote(db, voted);
                                }
                                jsonArray = response.getJSONArray("users");
                                MyClasses.User admin;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    admin = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                            MyClasses.User.class);
                                    ids.add(admin.getId());
                                    admins.add(admin.getEmail());
                                    dbHelper.insertAuser(db, admin);

                                }

                                Intent mintent = new Intent(getApplicationContext(), GetUsersAvatars.class);
                                mintent.putStringArrayListExtra("emails", admins);
                                mintent.putStringArrayListExtra("ids", ids);
                                startService(mintent);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            );
            mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("getAllVotes:", "onDestroy");
        }

        String create(String id) {
            String result = null;
            Uri uri = Uri.parse(GET_VOTES).buildUpon().appendQueryParameter("id_user", id).build();
            result = uri.toString();
            return result;
        }

    }
    public static  class  MySyncService extends IntentService{

        public MySyncService() {
            super("MySyncService");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            try {
                final SharedPreferences preferences = getApplicationContext().getSharedPreferences(MyClasses.SHARED_PREFRENCES,
                        MODE_PRIVATE);
                String id = String.valueOf(preferences.getLong("id", 0));
                String user_type=String.valueOf(preferences.getInt("user_type",0));
                String update=preferences.getString("update","0000-00-00");
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,
                        create(user_type, id, update), null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if(response.getInt("params")!=0){
                                    if(response.getInt("result")!=0) {
                                        final MyDbHelper dbHelper = new MyDbHelper(getApplicationContext());
                                        final SQLiteDatabase database = dbHelper.getWritableDatabase();
                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                        Gson gson = gsonBuilder.create();
                                        final ArrayList<String> emails = new ArrayList<>();
                                        final ArrayList<String> ids = new ArrayList<>();
                                        JSONArray jsonArray;
                                        JSONObject jsonObject=response.getJSONObject("in");
                                        jsonArray=jsonObject.getJSONArray("session");
                                        MyClasses.Session session ;
                                        int j=20;
                                        for(int i=0;i<jsonArray.length();i++){
                                            session=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.Session.class);
                                            dbHelper.addToTimeTable(database,session);
                                            create_a_notification(1,j++,"Une séeanse a été ajouté",
                                                    "l'ajout d'une séanse",getApplicationContext(),"abc");
                                        }
                                       /* jsonArray=jsonObject.getJSONArray("news");
                                        for(int i=0;i<jsonArray.length();i++){
                                            int news_type=Integer.parseInt(jsonArray.getJSONObject(i).getString("news_type"));
                                            /* $news_type=array(0=>"news",1=>"change_of_session",2=>"consultation",3=>"exam",
                                            4=>"holiday",5=>"marks_displayed",
                                             6=>"meeting",0=>"news",8=>"pedagogical-procedure",9=>"strike");*/
                                           /* switch (news_type){
                                                case 0:{
                                                    MyClasses.New aNew=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                            MyClasses.New.class);
                                                    dbHelper.addAnew(database,aNew);
                                                  break;
                                                }
                                                case 1:{
                                                    MyClasses.ChangeOfSession changeOfSession=gson.fromJson(jsonArray.
                                                                    getJSONObject(i).toString(),
                                                            MyClasses.ChangeOfSession.class);
                                                    dbHelper.addAchangeOfSession(database,changeOfSession);
                                                    break;
                                                }
                                                case 2:{
                                                    MyClasses.Consultation consultation=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                            MyClasses.Consultation.class);
                                                    dbHelper.addAconsultation(database,consultation);
                                                    break;
                                                }
                                                case 3:{
                                                    MyClasses.Exam aNew=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                            MyClasses.Exam.class);
                                                    dbHelper.addAexam(database,aNew);
                                                    break;
                                                }
                                                case 4:{
                                                    MyClasses.Holiday aNew=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                            MyClasses.Holiday.class);
                                                    dbHelper.addAholiday(database,aNew);
                                                    break;
                                                }
                                                case 5:{
                                                    MyClasses.MarksDisplayed aNew=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                            MyClasses.MarksDisplayed.class);
                                                    dbHelper.addAmarksDisplayed(database,aNew);

                                                    break;
                                                }
                                                case 6:{
                                                    MyClasses.Meeting aNew=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                            MyClasses.Meeting.class);
                                                    dbHelper.addAmeeting(database,aNew);

                                                    break;
                                                }
                                                case 8:{
                                                    MyClasses.PedagogicalProcedure aNew=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                            MyClasses.PedagogicalProcedure.class);
                                                    dbHelper.addAprocedure(database,aNew);

                                                    break;
                                                }
                                                case 9:{
                                                    MyClasses.Strike aNew=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                            MyClasses.Strike.class);
                                                    dbHelper.addAstrike(database,aNew);

                                                    break;
                                                }
                                            }
                                            create_a_notification(2,j++,"Une nouvelle a été ajouté",
                                                    "une nouvelle",getApplicationContext(),"abc");
                                        }*/
                                        jsonArray=jsonObject.getJSONArray("docs");
                                        MyClasses.RequestDone requestDone;
                                        for(int i=0;i<jsonArray.length();i++){
                                            requestDone=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.RequestDone.class);
                                            dbHelper.addAdocDone(database,requestDone);
                                            create_a_notification(5,j++,"Une demande de document a été prise en charge",
                                                    "l'ajout d'un document domandé",getApplicationContext(),"abc");
                                        }
                                        jsonArray=jsonObject.getJSONArray("abs");
                                        MyClasses.Absence abs;
                                        for(int i=0;i<jsonArray.length();i++){
                                            abs=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.Absence.class);
                                            dbHelper.addAnAbsence(database,abs);
                                            create_a_notification(6,j++,"Une absense a été ajouté",
                                                    "l'ajout d'une absense",getApplicationContext(),"abc");
                                        }
                                        jsonArray=jsonObject.getJSONArray("note");
                                        MyClasses.Marks marks;
                                        for(int i=0;i<jsonArray.length();i++){
                                            marks=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.Marks.class);
                                            dbHelper.addAmark(database,marks);
                                            create_a_notification(4,j++,"Une note a été ajouté",
                                                    "une nouvelle note",getApplicationContext(),"abc");
                                        }
                                        jsonArray=jsonObject.getJSONArray("msgs");
                                        MyClasses.MSG msg;
                                        for(int i=0;i<jsonArray.length();i++){
                                            msg=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.MSG.class);
                                            dbHelper.insertAmsg(database,msg);
                                            create_a_notification(3,j++," vous avez recu un nouveau message",
                                                    "Un nouveau message est reçu",getApplicationContext(),"abc");
                                        }
                                        jsonArray=jsonObject.getJSONArray("msgs_send_to");
                                        MyClasses.MsgSentTo m;
                                        for(int i=0;i<jsonArray.length();i++){
                                            m=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.MsgSentTo.class);
                                            dbHelper.insertAmsgSendTo(database,m);

                                        }

                                        jsonObject=response.getJSONObject("up");
                                        jsonArray=jsonObject.getJSONArray("session");
                                        for(int i=0;i<jsonArray.length();i++){
                                            session=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.Session.class);
                                            dbHelper.updateASession(database,session);
                                            create_a_notification(1,j++," une séanse à été modifier",
                                                    "Un changement d'emploi du temps",getApplicationContext(),"abc");

                                        }
                                        jsonArray=jsonObject.getJSONArray("msgs_send_to");
                                        MyClasses.MsgSentTo msgSentTo;
                                        for(int i=0;i<jsonArray.length();i++){
                                            msgSentTo=gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.MsgSentTo.class);
                                            dbHelper.updateAmsgSendTo(database,msgSentTo);
                                        }
                                        jsonArray = response.getJSONArray("voted");
                                        MyClasses.Voted voted;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            voted = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Voted.class);
                                            dbHelper.addAvote(database, voted);
                                        }
                                        jsonArray = response.getJSONArray("journal");
                                        MyClasses.Journal journal;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            journal = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Journal.class);
                                            dbHelper.addTojournal(database, journal);
                                        }
                                        jsonArray = response.getJSONArray("user");
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            String email = "";
                                            String id = "0";
                                            int a = Integer.parseInt(jsonArray.getJSONObject(i).getString("user_type"));
                                            switch (Integer.parseInt(jsonArray.getJSONObject(i).getString("user_type"))) {
                                            /*session = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Session.class);
                                            dbHelper.addToTimeTable(database, session);*/

                                                case 2: {
                                                    MyClasses.Teacher teacher = gson.fromJson(jsonArray.get(i).toString(), MyClasses.Teacher.class);
                                                    teacher.setUserType("2");
                                                    email = teacher.getEmail();
                                                    emails.add(email);
                                                    id = teacher.getId();
                                                    ids.add(id);
                                                    dbHelper.insertATeacher(database, teacher);
                                                    break;
                                                }
                                                case 5: {
                                                    MyClasses.ResponsibleTeacher responsibleTeacher = gson.fromJson(jsonArray.get(i).toString(),
                                                            MyClasses.ResponsibleTeacher.class);
                                                    responsibleTeacher.setUserType("5");
                                                    email = responsibleTeacher.getEmail();
                                                    emails.add(email);
                                                    id = responsibleTeacher.getId();
                                                    ids.add(id);
                                                    dbHelper.insertAresponsibleTeacher(database, responsibleTeacher);
                                                    break;
                                                }
                                                case 1: {//student
                                                    MyClasses.Student user = gson.fromJson(jsonArray.get(i).toString()
                                                            , MyClasses.Student.class);
                                                    user.setUserType("1");
                                                    email = user.getEmail();
                                                    emails.add(email);
                                                    id = user.getId();
                                                    ids.add(id);
                                                    break;
                                                }
                                                case 0: {//student
                                                    MyClasses.Admin user = gson.fromJson(jsonArray.get(i).toString()
                                                            , MyClasses.Admin.class);
                                                    user.setUserType("0");
                                                    email = user.getEmail();
                                                    emails.add(email);
                                                    id = user.getId();
                                                    ids.add(id);
                                                    dbHelper.insertAdmin(database, user);
                                                    break;
                                                }
                                                case 4: {
                                                    MyClasses.Delegue user = gson.fromJson(jsonArray.get(i).toString()
                                                            , MyClasses.Delegue.class);
                                                    user.setUserType("4");
                                                    email = user.getEmail();
                                                    emails.add(email);
                                                    id = user.getId();
                                                    ids.add(id);
                                                    dbHelper.insertAStudent(database,user);
                                                    break;
                                                }

                                            }
                                        }
                                        jsonArray = response.getJSONArray("users");
                                        MyClasses.User admin;
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            admin = gson.fromJson(jsonArray.getJSONObject(i).toString(),
                                                    MyClasses.User.class);
                                            ids.add(admin.getId());
                                            emails.add(admin.getEmail());
                                            dbHelper.insertAuser(database, admin);

                                        }
                                        Intent mintent = new Intent(getApplicationContext(), GetUsersAvatars.class);
                                        mintent.putStringArrayListExtra("emails", emails);
                                        mintent.putStringArrayListExtra("ids", ids);
                                        startService(mintent);
                                        Calendar c = Calendar.getInstance();
                                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        String newUpdate = df.format(c.getTime());
                                                //response.getString("update");
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("update", newUpdate);
                                        editor.commit();
                                    }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                mySinglton.getmInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
                sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        String create(String user_type,String id,String update){
            Uri uri=Uri.parse(MyClasses.SYNC).buildUpon()
                    .appendQueryParameter("id",id)
                    .appendQueryParameter("user_type",user_type)
                    .appendQueryParameter("update",update)
                    .build();
            return  uri.toString();
        }
        @Override
        public void onDestroy() {
            super.onDestroy();
            Intent intent=new Intent(getApplicationContext(),MyClasses.MySyncService.class);
            startService(intent);
        }
    }
    }

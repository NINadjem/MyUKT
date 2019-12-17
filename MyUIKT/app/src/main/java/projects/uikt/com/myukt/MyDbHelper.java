package projects.uikt.com.myukt;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "my_ukt";
    public static final int DB_VERSION = 1;
    Context context;

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        Log.d("MyDbHelper:", "constractor");
        Log.d("MyDbHelper:", CREATE_sESSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        int i = 0;
        db.execSQL(CREATE_SESSION_TYPE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:SESSION_TYPE");
        db.execSQL(CREATE_USER_TABLE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:user");
        db.execSQL(CREATE_STUDENT_TABLE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:student");
        db.execSQL(CREATE_TEACHER_TABLE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:teacher");
        db.execSQL(CREATE_ADMIN_TABLE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:Admin");
        db.execSQL(CREATE_DELEGUE_TABLE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:DELEGUE");
        db.execSQL(CREATE_RESPONSBLE_TABLE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:responsble teacher");
        db.execSQL(CREATE_CHAGNGE_SESSION);
        i++;
        Log.d("MyDbHelper:", "onCreate db:change of session");
        db.execSQL(CREATE_CLASSROOM_TABLE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:classroom");
        db.execSQL(CREATE_CONSULTATION);
        i++;
        Log.d("MyDbHelper:", "onCreate db:consultation");
        db.execSQL(CREATE_DAYS);
        i++;
        Log.d("MyDbHelper:", "onCreate db:days");
        db.execSQL(CREATE_DOC_REQ);
        i++;
        Log.d("MyDbHelper:", "onCreate db:doc req");
        db.execSQL(CREATE_ENCADREUR_TABLE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:encadreur");
        db.execSQL(CREATE_EXAM_TYPE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:EXAM_TYPE");
        db.execSQL(CREATE_LEVEL);
        i++;
        Log.d("MyDbHelper:", "onCreate db:LEVEL");
        db.execSQL(CREATE_SPECIALTY);
        i++;
        Log.d("MyDbHelper:", "onCreate db:specialty");
        db.execSQL(CREATE_TOWN);
        i++;
        Log.d("MyDbHelper:", "onCreate db:TOWN");
        db.execSQL(CREATE_WORK);
        i++;
        Log.d("MyDbHelper:", "onCreate db:WORK");
        db.execSQL(CREATE_EST);
        i++;
        Log.d("MyDbHelper:", "onCreate db:EST");
        db.execSQL(CREATE_EXAM);
        i++;
        Log.d("MyDbHelper:", "onCreate db:EXAM");
        db.execSQL(CREATE_GROUPE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:GRP");
        db.execSQL(CREATE_GRP_MEMBER);
        i++;
        Log.d("MyDbHelper:", "onCreate db:GRP member");
        db.execSQL(CREATE_HOLY_DAY);
        i++;
        Log.d("MyDbHelper:", "onCreate db:holyday");
        db.execSQL(CREATE_MARKS_DES);
        i++;
        Log.d("MyDbHelper:", "onCreate db:MARKS DES");
        db.execSQL(CREATE_MESSEGE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:MSG");
        db.execSQL(CREATE_MSG_SEND_TO);
        i++;
        Log.d("MyDbHelper:", "onCreate db:msg send to");
        db.execSQL(CREATE_MEETTING);
        i++;
        Log.d("MyDbHelper:", "onCreate db:MEETING");
        db.execSQL(CREATE_MEETING_P);
        i++;
        Log.d("MyDbHelper:", "onCreate db:MEETING_P");
        db.execSQL(CREATE_NEWS);
        i++;
        Log.d("MyDbHelper:", "onCreate db:NEWS");
        db.execSQL(CREATE_NEWS_visibility);
        i++;
        Log.d("MyDbHelper:", "onCreate db:NEWS VISIBILITY");
        db.execSQL(CREATE_PROCEDURE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:PROCEDURE");
        db.execSQL(CREATE_REQ_DONE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:REQ_DONE");
        db.execSQL(CREATE_sESSION);
        i++;
        Log.d("MyDbHelper:", "onCreate db:session");
        db.execSQL(CREATE_strike);
        i++;
        Log.d("MyDbHelper:", "onCreate db:STRIKE");
        db.execSQL(CREATE_VOTED);
        i++;
        Log.d("MyDbHelper:", "onCreate db:voted");
        db.execSQL(CREATE_JOURNAL);
        i++;
        Log.d("MyDbHelper:", "onCreate db:journal");
        db.execSQL(CREATE_ABSENCE);
        i++;
        Log.d("MyDbHelper:", "onCreate db:ABSENCE");
        db.execSQL(CREATE_MARKS);
        i++;
        Log.d("MyDbHelper:", "onCreate db:MARKS");
        db.execSQL(CREATE_Responsbility);
        i++;
        Log.d("MyDbHelper:", "onCreate db:Responsbility");
        db.execSQL(CREATE_Module);
        i++;
        Log.d("MyDbHelper:", "onCreate db:Module");
        Log.d("MyDbHelper:", "onCreate db:tables=" + String.valueOf(i));
        SharedPreferences preferences = context.getSharedPreferences(MyClasses.SHARED_PREFRENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("database_created", true);
        editor.commit();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertAmsgSendTo(SQLiteDatabase database, MyClasses.MsgSentTo msgSentTo) {
        ContentValues values = new ContentValues();
        values.put(MyClasses.MsgSentTo.ID, Integer.parseInt(msgSentTo.getId()));
        values.put(MyClasses.MsgSentTo.MSG_ID, Integer.parseInt(msgSentTo.getId_msg()));
        values.put(MyClasses.MsgSentTo.USER_ID, Integer.parseInt(msgSentTo.getId_user()));
        values.put(MyClasses.MsgSentTo.SEEN, Integer.parseInt(msgSentTo.getSeen()));
        database.insert(MyClasses.MsgSentTo.TABLE_NAME, null, values);
        Log.d("insert in database:", "Table:msgSentTo type row id" +
                msgSentTo.getId());

    }

    public void insertAmsg(SQLiteDatabase database, MyClasses.MSG msg) {
        ContentValues values = new ContentValues();
        values.put(MyClasses.MSG._ID, Integer.parseInt(msg.getId()));
        values.put(MyClasses.MSG.MSG, msg.getMsg());
        values.put(MyClasses.MsgSentTo.USER_ID, Integer.parseInt(msg.getId_user()));
        database.insert(MyClasses.MSG.TABLE_NAME, null, values);
        Log.d("insert in database:", "Table:msgSentTo type row id" +
                msg.getId());
    }

    public ArrayList<MyClasses.DocDoneAdapter.mClass> getAllFromDocsDoneTable(SQLiteDatabase db) {
        ArrayList<MyClasses.DocDoneAdapter.mClass> result = new ArrayList<MyClasses.DocDoneAdapter.mClass>();
        Log.d("getAllFromDocDoneTable:", " begin");
        String selection = " select * from " +/* MyClasses.DocumentRequest.TABLE_NAME + " , " + */MyClasses.RequestDone.TABLE_NAME +
                " , " + MyClasses.Journal.TABLE_NAME + " where ( " + MyClasses.RequestDone.ID + " = " + MyClasses.Journal.ID +
                /*" and " + MyClasses.RequestDone.TABLE_NAME + " . " + MyClasses.RequestDone.ID + " = " + MyClasses.DocumentRequest.TABLE_NAME +
                " . "+MyClasses.DocumentRequest._ID+*/" and " + MyClasses.Journal.TAB + " = 27 )" + " ORDER BY " + MyClasses.Journal.DATE +
                " DESC ";
        Cursor cursor = db.rawQuery(selection, null);
        while (cursor.moveToNext()) {
            Log.d("getAllFromDocDoneTable:", " while (cursor.moveToNext())");
            String id = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.RequestDone.ID)));
            String query = "select * from " + MyClasses.DocumentRequest.TABLE_NAME + " where " + MyClasses.DocumentRequest._ID + " = " + id;
            Cursor cursor1 = db.rawQuery(query, null);
            while (cursor1.moveToNext()) {
                Log.d("getAllFromDocDoneTable:", " while (cursor1.moveToNext())");
                String document, reason, date;
                document = cursor1.getString(cursor1.getColumnIndex(MyClasses.DocumentRequest.DOC));
                reason = cursor1.getString(cursor1.getColumnIndex(MyClasses.DocumentRequest.REASON));
                date = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
                int id_est = cursor.getInt(cursor.getColumnIndex(MyClasses.RequestDone.EST_ID));
                int id_admin = cursor.getInt(cursor.getColumnIndex(MyClasses.RequestDone.ID_ADMIN));
                Cursor c = db.query(MyClasses.EST.TABLE_NAME, new String[]{MyClasses.EST.NAME},
                        MyClasses.EST._ID + "= ? ", new String[]{String.valueOf(id_est)}, null, null, null);
                while (c.moveToNext()) {
                    Log.d("getAllFromDocDoneTable:", " while (c.moveToNext())");
                    String est = c.getString(c.getColumnIndex(MyClasses.EST.NAME));
                    MyClasses.User admin = getAuser(db, String.valueOf(id_admin));
                    MyClasses.DocDoneAdapter.mClass doc = new MyClasses.DocDoneAdapter.mClass(document,
                            reason, est, admin.getLast_name() + " " + admin.getFirst_name(), date, id_admin);
                    result.add(doc);
                    Log.d("getAllFromDocDoneTable:", " doc done added");
                }

            }

        }
        return result;
    }

    public void addAdoc(SQLiteDatabase db, MyClasses.DocumentRequest documentRequest) {
        ContentValues values = new ContentValues();
        values.put(MyClasses.DocumentRequest._ID, Integer.parseInt(documentRequest.getId()));
        values.put(MyClasses.DocumentRequest.USER_ID, documentRequest.getId_user());
        values.put(MyClasses.DocumentRequest.DOC, documentRequest.getDoc());
        values.put(MyClasses.DocumentRequest.REASON, documentRequest.getReason());
        db.insert(MyClasses.DocumentRequest.TABLE_NAME, null, values);
        Log.d("insert in database:", "Table:documentRequest row id" + documentRequest.getId());


    }


    public ArrayList<MyClasses.DocAdapter.mClass> getAllFromDocTable(SQLiteDatabase db) {
        ArrayList<MyClasses.DocAdapter.mClass> docs = new ArrayList<>();
        String selection = "select  *  from " + MyClasses.DocumentRequest.TABLE_NAME + " join " + MyClasses.Journal.TABLE_NAME + " on "
                + MyClasses.DocumentRequest._ID + " = " + MyClasses.Journal.ID + " where " + MyClasses.DocumentRequest._ID +
                " not in ( select " + MyClasses.RequestDone.ID + " from " + MyClasses.RequestDone.TABLE_NAME + " ) and " +
                MyClasses.Journal.TAB + " = 7 " +
                " ORDER BY " + MyClasses.Journal.DATE + " DESC ";
        Cursor cursor = db.rawQuery(selection, null);
        while (cursor.moveToNext()) {
            Log.d("getAllFromDocTable:", " while (cursor.moveToNext())");
            String document, reason, date;
            document = cursor.getString(cursor.getColumnIndex(MyClasses.DocumentRequest.DOC));
            reason = cursor.getString(cursor.getColumnIndex(MyClasses.DocumentRequest.REASON));
            date = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            MyClasses.DocAdapter.mClass doc = new MyClasses.DocAdapter.mClass(document, reason, date);
            docs.add(doc);
            Log.d("getAllFromDocTable:", " doc added");
        }
        return docs;
    }

    public void addAdocDone(SQLiteDatabase db, MyClasses.RequestDone requestDone) {
        ContentValues values = new ContentValues();
        values.put(MyClasses.RequestDone.ID, Integer.parseInt(requestDone.getId()));
        values.put(MyClasses.RequestDone.EST_ID, Integer.parseInt(requestDone.getId_establishement()));
        values.put(MyClasses.RequestDone.ID_ADMIN, Integer.parseInt(requestDone.getId_admin()));
        db.insert(MyClasses.RequestDone.TABLE_NAME, null, values);
        Log.d("insert in database:", "Table:requestDone row id" + requestDone.getId());

    }


    public static final String CREATE_sESSION = "create table " + MyClasses.Session.TABLE_NAME + " ( " + MyClasses.Session._ID + " INTEGER PRIMARY KEY, " +
            MyClasses.Session.ID_SESSION_TYPE + " INTEGER, " + MyClasses.Session.ENABLED + " INTEGER, " + MyClasses.Session.ID_DAY + " INTEGER, " +
            MyClasses.Session.TIME + " TEXT, " + MyClasses.Session.IS_SEMESTER_1 + " INTEGER, " + MyClasses.Session.ID_GRP + " INTEGER, " +
            MyClasses.Session.ID_TEACHER + " INTEGER, " + MyClasses.Session.ID_CLASSROOM + " INTEGER, " + MyClasses.Session.ID_MODULE + " INTEGER); ";

    public void addToTimeTable(SQLiteDatabase db, MyClasses.Session session) {
        String selection = MyClasses.Session._ID + "=?";
        String[] selectionArgs = {session.getId()};
        Cursor cursor = db.query(MyClasses.Session.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Session._ID, Integer.parseInt(session.getId()));
            values.put(MyClasses.Session.ID_SESSION_TYPE, Integer.parseInt(session.getId_session_type()));
            values.put(MyClasses.Session.ENABLED, Integer.parseInt(session.getEnabled()));
            values.put(MyClasses.Session.ID_DAY, Integer.parseInt(session.getId_day()));
            values.put(MyClasses.Session.TIME, session.getTime());
            values.put(MyClasses.Session.ID_MODULE, Integer.parseInt(session.getId_module()));
            values.put(MyClasses.Session.ID_CLASSROOM, Integer.parseInt(session.getId_classroom()));
            values.put(MyClasses.Session.ID_TEACHER, Integer.parseInt(session.getId_teacher()));
            values.put(MyClasses.Session.ID_GRP, Integer.parseInt(session.getId_groupe()));
            values.put(MyClasses.Session.IS_SEMESTER_1, Integer.parseInt(session.getSemester_1()));
            db.insert(MyClasses.Session.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:session row id" + session.getId());
        } else {
            Log.d("insert in database:", "Table:sessio, row id" + session.getId() + " already exists");
        }

    }

    public Cursor getAllFromTimeTableTable(SQLiteDatabase db) {
        String[] projection = {MyClasses.Session._ID, MyClasses.Session.ID_SESSION_TYPE, MyClasses.Session.ENABLED, MyClasses.Session.ID_DAY,
                MyClasses.Session.TIME, MyClasses.Session.ID_MODULE, MyClasses.Session.ID_CLASSROOM, MyClasses.Session.ID_TEACHER,
                MyClasses.Session.ID_GRP, MyClasses.Session.IS_SEMESTER_1};
        String selection = MyClasses.Session.ENABLED + "=?";
        String orderBy = MyClasses.Session.ID_DAY + " ASC ," + MyClasses.Session.TIME + " ASC";
        String[] selectionArgs = {"1"};
        Cursor cursor = db.query(MyClasses.Session.TABLE_NAME, projection,
                selection, selectionArgs, null, null, orderBy);
        return cursor;
    }

    public ArrayList<ArrayList<TimeTableActivity.TimeTableAdapter.mClass>> getTimeTable(SQLiteDatabase db, int userType) {
        ArrayList<ArrayList<TimeTableActivity.TimeTableAdapter.mClass>> list = new ArrayList<>();
        ArrayList<TimeTableActivity.TimeTableAdapter.mClass> sun, mon, tue, wed, thur;
        ArrayList<TimeTableActivity.TimeTableAdapter.mClass> sessions = new ArrayList<>();
        ;
        /*sun=new ArrayList();
        mon=new ArrayList();
        tue=new ArrayList();
        wed=new ArrayList();
        thur=new ArrayList();*/
        long id_teacher;
        boolean search_teacher_id, row_exists = false;
        int last_day = 0, last_id_level = 0;
        String last_session_type = "", last_time = "", last_classroom = "", last_module = "";

        if (userType == 1 || userType == 4) {
            search_teacher_id = true;
            id_teacher = 1;
        } else {
            search_teacher_id = false;
            id_teacher = 0;

        }
        list.add(new ArrayList<TimeTableActivity.TimeTableAdapter.mClass>());
        list.add(new ArrayList<TimeTableActivity.TimeTableAdapter.mClass>());
        list.add(new ArrayList<TimeTableActivity.TimeTableAdapter.mClass>());
        list.add(new ArrayList<TimeTableActivity.TimeTableAdapter.mClass>());
        list.add(new ArrayList<TimeTableActivity.TimeTableAdapter.mClass>());
        Cursor cursor = getAllFromTimeTableTable(db);
        while (cursor.moveToNext()) {
            String time = "", type = "", module = "", classroom = "", grp = "", teacher = "";
            row_exists = false;
            time = cursor.getString(cursor.getColumnIndex(MyClasses.Session.TIME));
            Cursor c = db.query(MyClasses.SessionType.TABLE_NAME, new String[]{MyClasses.SessionType.NAME},
                    MyClasses.SessionType._ID + "=?",
                    new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Session.ID_SESSION_TYPE)))}, null,
                    null, null);
            while (c.moveToNext()) {
                type = c.getString(c.getColumnIndex(MyClasses.SessionType.NAME));

            }
            c = db.query(MyClasses.Module.TABLE_NAME, new String[]{MyClasses.Module.ABRG}, MyClasses.Module.ID + "=?",
                    new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Session.ID_MODULE)))},
                    null, null, null);
            while (c.moveToNext()) {
                module = c.getString(c.getColumnIndex(MyClasses.Module.ABRG));

            }
            c = db.query(MyClasses.Classroom.TABLE_NAME, new String[]{MyClasses.Classroom.NAME},
                    MyClasses.Classroom._ID + "=?",
                    new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Session.ID_CLASSROOM)))},
                    null, null, null);
            while (c.moveToNext()) {
                classroom = c.getString(c.getColumnIndex(MyClasses.Classroom.NAME));

            }
            if (!search_teacher_id) {
                c = db.query(MyClasses.Group.TABLE_NAME, new String[]{MyClasses.Group.NAME, MyClasses.Group.ID_LEVEL},
                        MyClasses.Group._ID + "=?",
                        new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Session.ID_GRP)))},
                        null, null, null);
                while (c.moveToNext()) {
                    grp = c.getString(c.getColumnIndex(MyClasses.Group.NAME));
                    last_id_level = c.getInt(c.getColumnIndex(MyClasses.Group.ID_LEVEL));
                }
                if (last_day == cursor.getInt(cursor.getColumnIndex(MyClasses.Session.ID_DAY))
                        && type.equals(last_session_type) && time.equals(last_time) && classroom.equals(last_classroom)
                        && module.equals(last_module)) {
                    row_exists = true;

                }
            } else {
                c = db.query(MyClasses.User.TABLE_NAME, new String[]{MyClasses.User.LAST_NAME, MyClasses.User.FIRST_NAME},
                        MyClasses.User.ID + "=?",
                        new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Session.ID_TEACHER)))},
                        null, null, null);
                while (c.moveToNext()) {
                    teacher = c.getString(c.getColumnIndex(MyClasses.User.LAST_NAME)) + " " + c.getString(c.getColumnIndex(MyClasses.User.FIRST_NAME));
                }
                id_teacher = cursor.getInt(cursor.getColumnIndex(String.valueOf(MyClasses.Session.ID_TEACHER)));
            }
            if (!row_exists) {
                TimeTableActivity.TimeTableAdapter.mClass session = new TimeTableActivity.TimeTableAdapter.mClass
                        (time, type, module, classroom, grp, teacher, id_teacher);
                sessions.add(session);
                //list.get(0).add(session);list.get(4).add(session);list.get(3).add(session);list.get(2).add(session);list.get(1).add(session);
                switch (cursor.getInt(cursor.getColumnIndex(MyClasses.Session.ID_DAY))) {
                    case 2: {//sun.add(session);
                        list.get(0).add(session);
                        last_day = 2;
                        break;
                    }
                    case 3: {//mon.add(session);
                        list.get(1).add(session);
                        last_day = 3;
                        break;
                    }
                    case 4: {//tue.add(session);
                        list.get(2).add(session);
                        last_day = 4;
                        break;
                    }
                    case 5: {//wed.add(session);
                        list.get(3).add(session);
                        last_day = 5;
                        break;
                    }
                    case 6: {//thur.add(session);
                        list.get(4).add(session);
                        last_day = 6;
                        break;
                    }
                }
            } else {
                switch (last_day) {
                    case 2: {
                        String g = "";
                        Cursor a = db.query(MyClasses.Level.TABLE_NAME, new String[]{MyClasses.Level.ABRG},
                                MyClasses.Level.ID + "=?",
                                new String[]{String.valueOf(last_id_level)},
                                null, null, null);
                        while (a.moveToNext()) {
                            g = a.getString(a.getColumnIndex(MyClasses.Level.ABRG));
                        }

                        list.get(0).get(list.get(0).size() - 1).setGrp(g);
                        break;
                    }
                    case 3: {
                        String g = "";
                        Cursor a = db.query(MyClasses.Level.TABLE_NAME, new String[]{MyClasses.Level.ABRG},
                                MyClasses.Level.ID + "=?",
                                new String[]{String.valueOf(last_id_level)},
                                null, null, null);
                        while (a.moveToNext()) {
                            g = a.getString(a.getColumnIndex(MyClasses.Level.ABRG));
                        }

                        list.get(1).get(list.get(1).size() - 1).setGrp(g);
                        break;
                    }
                    case 4: {
                        String g = "";
                        Cursor a = db.query(MyClasses.Level.TABLE_NAME, new String[]{MyClasses.Level.ABRG},
                                MyClasses.Level.ID + "=?",
                                new String[]{String.valueOf(last_id_level)},
                                null, null, null);
                        while (a.moveToNext()) {
                            g = a.getString(a.getColumnIndex(MyClasses.Level.ABRG));
                        }

                        list.get(2).get(list.get(2).size() - 1).setGrp(g);
                        break;
                    }
                    case 5: {
                        String g = "";
                        Cursor a = db.query(MyClasses.Level.TABLE_NAME, new String[]{MyClasses.Level.ABRG},
                                MyClasses.Level.ID + "=?",
                                new String[]{String.valueOf(last_id_level)},
                                null, null, null);
                        while (a.moveToNext()) {
                            g = a.getString(a.getColumnIndex(MyClasses.Level.ABRG));
                        }
                        list.get(3).get(list.get(3).size() - 1).setGrp(g);
                        break;
                    }
                    case 6: {
                        String g = "";
                        Cursor a = db.query(MyClasses.Level.TABLE_NAME, new String[]{MyClasses.Level.ABRG},
                                MyClasses.Level.ID + "=?",
                                new String[]{String.valueOf(last_id_level)},
                                null, null, null);
                        while (a.moveToNext()) {
                            g = a.getString(a.getColumnIndex(MyClasses.Level.ABRG));
                        }

                        list.get(4).get(list.get(4).size() - 1).setGrp(g);
                        break;
                    }
                }

            }
            last_time = time;
            last_session_type = type;
            last_classroom = classroom;
            last_module = module;
        }

        /*list.add(sun);
        list.add(mon);
        list.add(tue);
        list.add(wed);
        list.add(thur);*/

        return list;
    }


    public ArrayList<MarksActivity.RecyclerAdapter.mark> getAllMarks(SQLiteDatabase database) {
        ArrayList<MarksActivity.RecyclerAdapter.mark> result = new ArrayList<MarksActivity.RecyclerAdapter.mark>();
        String query = "select * from " + MyClasses.Marks.TABLE_NAME + " , " + MyClasses.Exam.TABLE_NAME + " where (" +
                MyClasses.Marks.ID_EXAM + " = " + MyClasses.Exam.ID + " ) group by " + MyClasses.Exam.MODULE + " ;";
        Cursor cursor = database.rawQuery(query, null);
        int last_id_module = 0;
        double td = -1, tp = -1, exam = -1;
        int i = 0;
        String module = "";
        while (cursor.moveToNext()) {
            int current_module = cursor.getInt(cursor.getColumnIndex(MyClasses.Exam.MODULE));
            if (i == 0) last_id_module = current_module;
            if (current_module != last_id_module) {
                Cursor c = database.query(MyClasses.Module.TABLE_NAME, new String[]{MyClasses.Module.NAME},
                        MyClasses.Module.ID + "= ? ",
                        new String[]{String.valueOf(last_id_module)},
                        null, null, null);
                while (c.moveToNext()) {
                    module = c.getString(c.getColumnIndex(MyClasses.Module.NAME));
                    MarksActivity.RecyclerAdapter.mark absence =
                            new MarksActivity.RecyclerAdapter.mark(module
                                    , String.valueOf(tp), String.valueOf(td), String.valueOf(exam));
                    result.add(absence);
                    exam = -1;
                    td = -1;
                    tp = -1;
                }
            }
            int type = cursor.getInt(cursor.getColumnIndex(MyClasses.Exam.TYPE));
            double note = cursor.getDouble(cursor.getColumnIndex(MyClasses.Marks.NOTE));
            switch (type) {
                case 5:
                    td = note;
                    break;
                case 4:
                    tp = note;
                    break;
                case 2:
                    exam = note;
                    break;
            }
            i++;
            last_id_module = current_module;
            if (cursor.isLast()) {
                Cursor c = database.query(MyClasses.Module.TABLE_NAME, new String[]{MyClasses.Module.NAME},
                        MyClasses.Module.ID + "= ? ",
                        new String[]{String.valueOf(last_id_module)},
                        null, null, null);
                while (c.moveToNext()) {
                    module = c.getString(c.getColumnIndex(MyClasses.Module.NAME));
                    MarksActivity.RecyclerAdapter.mark absence =
                            new MarksActivity.RecyclerAdapter.mark(module
                                    , String.valueOf(tp), String.valueOf(td), String.valueOf(exam));
                    result.add(absence);
                }
            }
        }

        return result;

    }

    public void addAmark(SQLiteDatabase db, MyClasses.Marks marks) {
        ContentValues values = new ContentValues();
        values.put(MyClasses.Marks.ID, Integer.parseInt(marks.getId()));
        values.put(MyClasses.Marks.ID_S, Integer.parseInt(marks.getId_student()));
        values.put(MyClasses.Marks.ID_EXAM, Integer.parseInt(marks.getId_exam()));
        values.put(MyClasses.Marks.NOTE, Double.parseDouble(marks.getNote()));
        db.insert(MyClasses.Marks.TABLE_NAME, null, values);
        Log.d("insert in database:", "Table:absence row id" + marks.getId());
    }


    public void addAnAbsence(SQLiteDatabase db, MyClasses.Absence absence) {
        ContentValues values = new ContentValues();
        values.put(MyClasses.Absence.MODULE, absence.getId_module());
        values.put(MyClasses.Absence.SESSION, absence.getId_session_type());
        db.insert(MyClasses.Absence.TABLE_NAME, null, values);
        Log.d("insert in database:", "Table:absence row id" + absence.getId_module() + " " + absence.getId_session_type());

    }

    public ArrayList<AbsenceActivity.RecyclerAdapter.absence> getAllAbsences(SQLiteDatabase db) {
        ArrayList<AbsenceActivity.RecyclerAdapter.absence> result = new ArrayList<AbsenceActivity.RecyclerAdapter.absence>();
        Cursor cursor = db.query(MyClasses.Absence.TABLE_NAME, null,
                null, null, null, null, MyClasses.Absence.MODULE + " ASC");
        int id_module = 0, td = 0, tp = 0, cours = 0, i = 0;
        String module = "";
        while (cursor.moveToNext()) {
            int current_module = cursor.getInt(cursor.getColumnIndex(MyClasses.Absence.MODULE));
            if (i == 0) id_module = current_module;
            if (current_module != id_module) {
                Cursor c = db.query(MyClasses.Module.TABLE_NAME, new String[]{MyClasses.Module.NAME},
                        MyClasses.Module.ID + "= ? ",
                        new String[]{String.valueOf(id_module)},
                        null, null, null);
                while (c.moveToNext()) {
                    module = c.getString(c.getColumnIndex(MyClasses.Module.NAME));
                    AbsenceActivity.RecyclerAdapter.absence absence =
                            new AbsenceActivity.RecyclerAdapter.absence(module
                                    , String.valueOf(tp), String.valueOf(td), String.valueOf(cours));
                    result.add(absence);
                    cours = 0;
                    td = 0;
                    tp = 0;
                }

            }
            int type = cursor.getInt(cursor.getColumnIndex(MyClasses.Absence.SESSION));
            switch (type) {
                case 1:
                    td++;
                    break;
                case 2:
                    tp++;
                    break;
                case 5:
                    cours++;
                    break;
            }
            i++;
            id_module = current_module;
            if (cursor.isLast()) {
                Cursor c = db.query(MyClasses.Module.TABLE_NAME, new String[]{MyClasses.Module.NAME},
                        MyClasses.Module.ID + "= ? ",
                        new String[]{String.valueOf(id_module)},
                        null, null, null);
                while (c.moveToNext()) {
                    module = c.getString(c.getColumnIndex(MyClasses.Module.NAME));
                    AbsenceActivity.RecyclerAdapter.absence absence =
                            new AbsenceActivity.RecyclerAdapter.absence(module
                                    , String.valueOf(tp), String.valueOf(td), String.valueOf(cours));
                    result.add(absence);
                }
            }
        }
        return result;
    }


    public void addToDaysTable(SQLiteDatabase db, MyClasses.Day day) {
        String selection = MyClasses.Day._ID + "=?";
        String[] selectionArgs = {day.getId()};
        Cursor cursor = db.query(MyClasses.Day.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Day._ID, Integer.parseInt(day.getId()));
            values.put(MyClasses.Day.NAME, day.getName());
            db.insert(MyClasses.Day.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:day row id" + day.getId());
        } else {
            Log.d("insert in database:", "Table:DAYS row id" + day.getId() + " already exists");
        }
    }

    public static final String CREATE_DAYS = "create table " + MyClasses.Day.TABLE_NAME + " ( " + MyClasses.Day._ID + " INTEGER PRIMARY KEY, " +
            MyClasses.Day.NAME + " TEXT ) ;";


    public static final String CREATE_EXAM_TYPE = "create table " + MyClasses.ExamType.TABLE_NAME + " ( " + MyClasses.ExamType._ID + " INTEGER PRIMARY KEY, " +
            MyClasses.ExamType.TYPE + " TEXT ) ; ";

    public void addToExamTypeTable(SQLiteDatabase db, MyClasses.ExamType examType) {
        String selection = MyClasses.ExamType._ID + "=?";
        String[] selectionArgs = {examType.getId()};
        Cursor cursor = db.query(MyClasses.ExamType.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.ExamType._ID, Integer.parseInt(examType.getId()));
            values.put(MyClasses.ExamType.TYPE, examType.getType());
            db.insert(MyClasses.ExamType.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:exam_type row id" + examType.getId());
        } else {
            Log.d("insert in database:", "Table:examtype row id" + examType.getId() + " already exists");
        }
    }


    public static final String CREATE_CLASSROOM_TABLE = " create table " + MyClasses.Classroom.TABLE_NAME + " ( " + MyClasses.Classroom._ID +
            " INTEGER PRIMARY KEY, " +
            MyClasses.Classroom.NAME + " TEXT ) ; ";

    public void addToClassroomTable(SQLiteDatabase db, MyClasses.Classroom classroom) {
        String selection = MyClasses.Classroom._ID + "=?";
        String[] selectionArgs = {classroom.getId()};
        Cursor cursor = db.query(MyClasses.Classroom.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Classroom._ID, Integer.parseInt(classroom.getId()));
            values.put(MyClasses.Classroom.NAME, classroom.getName());
            db.insert(MyClasses.Classroom.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:classroom row id" + classroom.getId());
        } else {
            Log.d("insert in database:", "Table:classroom row id" + classroom.getId() + " already exists");
        }
    }


    public static final String CREATE_EST = "create table " + MyClasses.EST.TABLE_NAME + " ( " + MyClasses.EST._ID + " INTEGER PRIMARY KEY, " +
            MyClasses.EST.NAME + " TEXT ) ;";

    public void addToEstTable(SQLiteDatabase db, MyClasses.EST est) {
        String selection = MyClasses.EST._ID + "=?";
        String[] selectionArgs = {est.getId()};
        Cursor cursor = db.query(MyClasses.EST.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.EST._ID, Integer.parseInt(est.getId()));
            values.put(MyClasses.EST.NAME, est.getName());
            db.insert(MyClasses.EST.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:EST row id" + est.getId());
        } else {
            Log.d("insert in database:", "Table:est row id" + est.getId() + " already exists");
        }
    }


    public static final String CREATE_WORK = "create table " + MyClasses.Work.TABLE_NAME + " ( " + MyClasses.Work._ID + " INTEGER PRIMARY KEY, " +
            MyClasses.Work.NAME + " TEXT ) ;";

    public void addToWorkTable(SQLiteDatabase db, MyClasses.Work work) {
        String selection = MyClasses.Work._ID + "=?";
        String[] selectionArgs = {work.getId()};
        Cursor cursor = db.query(MyClasses.Work.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Work._ID, Integer.parseInt(work.getId()));
            values.put(MyClasses.Work.NAME, work.getName());
            db.insert(MyClasses.Work.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:work row id" + work.getId());
        } else {
            Log.d("insert in database:", "Table:work row id" + work.getId() + " already exists");
        }

    }

    public void addToResponsibilityTable(SQLiteDatabase db, MyClasses.Responsibility responsibility) {
        String selection = MyClasses.Responsibility._ID + "=?";
        String[] selectionArgs = {responsibility.getId()};
        Cursor cursor = db.query(MyClasses.Responsibility.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Responsibility._ID, Integer.parseInt(responsibility.getId()));
            values.put(MyClasses.Responsibility.NAME, responsibility.getName());
            db.insert(MyClasses.Responsibility.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:responsibility row id" + responsibility.getId());
        } else {
            Log.d("insert in database:", "Table:responsibility row id" + responsibility.getId() + " already exists");
        }

    }


    public static final String CREATE_TOWN = " create table " + MyClasses.Town.TABLE_NAME + " ( " + MyClasses.Town._ID + " INTEGER PRIMARY KEY, " +
            MyClasses.Town.NAME + " TEXT ) ;";

    public void addToTownTable(SQLiteDatabase db, MyClasses.Town town) {
        String selection = MyClasses.Town._ID + "=?";
        String[] selectionArgs = {town.getId()};
        Cursor cursor = db.query(MyClasses.Town.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Town._ID, Integer.parseInt(town.getId()));
            values.put(MyClasses.Town.NAME, town.getTown_name());
            db.insert(MyClasses.Town.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:town row id" + town.getId());
        } else {
            Log.d("insert in database:", "Table:town row id" + town.getId() + " already exists");
        }

    }


    public void addToSpecialtyTable(SQLiteDatabase db, MyClasses.Specialty specialty) {
        String selection = MyClasses.Specialty._ID + "=?";
        String[] selectionArgs = {specialty.getId()};
        Cursor cursor = db.query(MyClasses.Specialty.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Specialty._ID, Integer.parseInt(specialty.getId()));
            values.put(MyClasses.Specialty.NAME, specialty.getName());
            db.insert(MyClasses.Specialty.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:specialty row id" + specialty.getId());
        } else {
            Log.d("insert in database:", "Table:specialty row id" + specialty.getId() + " already exists");
        }

    }

    public static final String CREATE_SPECIALTY = " create table " + MyClasses.Specialty.TABLE_NAME + " ( " + MyClasses.Specialty._ID + " INTEGER PRIMARY KEY, " +
            MyClasses.Specialty.NAME + " TEXT);";


    public void addToSessionTypeTable(SQLiteDatabase db, MyClasses.SessionType sessionType) {
        String selection = MyClasses.SessionType._ID + "=?";
        String[] selectionArgs = {sessionType.getId()};
        Cursor cursor = db.query(MyClasses.SessionType.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.SessionType._ID, Integer.parseInt(sessionType.getId()));
            values.put(MyClasses.SessionType.NAME, sessionType.getName());
            db.insert(MyClasses.SessionType.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:session type row id" + sessionType.getId());
        } else {
            Log.d("insert in database:", "Table:Sessiontype row id" + sessionType.getId() + " already exists");
        }

    }

    public static final String CREATE_SESSION_TYPE = "create table " + MyClasses.SessionType.TABLE_NAME + "(" + MyClasses.SessionType._ID + " INTEGER PRIMARY KEY," +
            MyClasses.SessionType.NAME + " TEXT);";


    public void addToLevelTable(SQLiteDatabase db, MyClasses.Level level) {
        String selection = MyClasses.Level.ID + "=?";
        String[] selectionArgs = {level.getId()};
        Cursor cursor = db.query(MyClasses.Level.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Level.ID, Integer.parseInt(level.getId()));
            values.put(MyClasses.Level.ABRG, level.getAbrg());
            db.insert(MyClasses.Level.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:LEVEL row id" + level.getId());
        } else {
            Log.d("insert in database:", "Table:level row id" + level.getId() + " already exists");
        }
    }

    public static final String CREATE_LEVEL = "create table " + MyClasses.Level.TABLE_NAME + "(" + MyClasses.Level.ID + " INTEGER PRIMARY KEY," +
            MyClasses.Level.ABRG + " TEXT );";

    public void insertAuser(SQLiteDatabase db, MyClasses.User user) {
        String selection = MyClasses.User.ID + "=?";
        String[] selectionArgs = {user.getId()};
        Cursor cursor = db.query(MyClasses.User.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.User.ID, Integer.parseInt(user.getId()));
            values.put(MyClasses.User.USER_TYPE, user.getUserType());
            values.put(MyClasses.User.EMAIL, user.getEmail());
            values.put(MyClasses.User.LAST_NAME, user.getLast_name());
            values.put(MyClasses.User.FIRST_NAME, user.getFirst_name());
            values.put(MyClasses.User.SEXE, user.getSexe());
            values.put(MyClasses.User.BIRTH_DATE, user.getBirth_date());
            values.put(MyClasses.User.PASSWORD, user.getPassword());
            if (user.getAvatar() != null)
                values.put(MyClasses.User.PIC, Base64.decode(user.getAvatar(), Base64.DEFAULT));
            values.put(MyClasses.User.HOME, user.getHome_town());
            db.insert(MyClasses.User.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:User row id" + user.getId());
        } else {
            Log.d("insert in database:", "Table:user row id" + user.getId() + " already exists");
        }

    }

    public void insertAStudent(SQLiteDatabase db, MyClasses.Student student) {
        String selection = MyClasses.Student.ID + "=?";
        String[] selectionArgs = {student.getId()};
        Cursor cursor = db.query(MyClasses.Student.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            insertAuser(db, student);
            ContentValues values = new ContentValues();
            values.put(MyClasses.Student.ID, Integer.parseInt(student.getId()));
            values.put(MyClasses.Student.MAT_NUM, student.getMat());
            values.put(MyClasses.Student.lIB_CARD_NUM, student.getBib());
            db.insert(MyClasses.Student.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:Student row id" + student.getId());
        } else {
            Log.d("insert in database:", "Table:student row id" + student.getId() + " already exists");
        }

    }

    public void insertADelegue(SQLiteDatabase db, MyClasses.Delegue delegue, MyClasses.DelegueRow row) {
        String selection = MyClasses.Delegue.ID + "=?";
        String[] selectionArgs = {delegue.getId()};
        Cursor cursor = db.query(MyClasses.Delegue.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            insertAStudent(db, delegue);
            ContentValues values = new ContentValues();
            values.put(MyClasses.Delegue.ID, Integer.parseInt(row.getId()));
            values.put(MyClasses.Delegue.DELEGUE_ID, Integer.parseInt(delegue.getId()));
            values.put(MyClasses.Delegue.LEVEL_ID, Integer.parseInt(row.getId_level()));
            db.insert(MyClasses.Delegue.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:delegue row id" + row.getId());
        } else {
            Log.d("insert in database:", "Table:delegue row id" + delegue.getId() + " already exists");
        }
    }

    public void insertATeacher(SQLiteDatabase db, MyClasses.Teacher teacher) {
        String selection = MyClasses.Teacher.ID + "=?";
        String[] selectionArgs = {teacher.getId()};
        Cursor cursor = db.query(MyClasses.Teacher.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            insertAuser(db, teacher);
            ContentValues values = new ContentValues();
            values.put(MyClasses.Teacher.ID, Integer.parseInt(teacher.getId()));
            values.put(MyClasses.Teacher.ID_SPACIALTY, Integer.parseInt(teacher.getId_specialty()));
            db.insert(MyClasses.Teacher.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:teacher row id" + teacher.getId());
        } else {
            Log.d("insert in database:", "Table:Teacher row id" + teacher.getId() + " already exists");
        }

    }

    public void insertAresponsibleTeacher(SQLiteDatabase db, MyClasses.ResponsibleTeacher responsibleTeacher) {
        String selection = MyClasses.ResponsibleTeacher.ID + "=?";
        String[] selectionArgs = {responsibleTeacher.getId()};
        Cursor cursor = db.query(MyClasses.ResponsibleTeacher.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            insertATeacher(db, responsibleTeacher);
            ContentValues values = new ContentValues();
            values.put(MyClasses.ResponsibleTeacher.ID, Integer.parseInt(responsibleTeacher.getId()));
            values.put(MyClasses.ResponsibleTeacher.SPECIALTY, responsibleTeacher.getId_responsibility());
            db.insert(MyClasses.ResponsibleTeacher.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:responsibleTeacher row id" + responsibleTeacher.getId());
        } else {
            Log.d("insert in database:", "Table:responsibleTeacher row id" + responsibleTeacher.getId() + " already exists");
        }
    }

    public void insertAdmin(SQLiteDatabase db, MyClasses.Admin admin) {
        String selection = MyClasses.Admin.ID + "=?";
        String[] selectionArgs = {admin.getId()};
        Cursor cursor = db.query(MyClasses.Admin.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            insertAuser(db, admin);
            ContentValues values = new ContentValues();
            values.put(MyClasses.Admin.ID, Integer.parseInt(admin.getId()));
            values.put(MyClasses.Admin.ID_WORK, Integer.parseInt(admin.getId_work()));
            db.insert(MyClasses.Admin.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:admin row id" + admin.getId());
        } else {
            Log.d("insert in database:", "Tableadmin row id" + admin.getId() + " already exists");
        }
    }

    public void addToModuleTable(SQLiteDatabase db, MyClasses.Module module) {
        String selection = MyClasses.Module.ID + "=?";
        String[] selectionArgs = {module.getId()};
        Cursor cursor = db.query(MyClasses.Module.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Module.ID, Integer.parseInt(module.getId()));
            values.put(MyClasses.Module.NAME, module.getName());
            values.put(MyClasses.Module.ABRG, module.getAbrg());
            db.insert(MyClasses.Module.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:module row id" + module.getId());
        } else {
            Log.d("insert in database:", "Table:module row id" + module.getId() + " already exists");
        }
    }

    public void addToGroupeTable(SQLiteDatabase db, MyClasses.Group group) {
        String selection = MyClasses.Group._ID + "=?";
        String[] selectionArgs = {group.getId()};
        Cursor cursor = db.query(MyClasses.Group.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Group._ID, Integer.parseInt(group.getId()));
            values.put(MyClasses.Group.ID_SP, Integer.parseInt(group.getId_specialty()));
            values.put(MyClasses.Group.ID_LEVEL, Integer.parseInt(group.getId_level()));
            values.put(MyClasses.Group.NAME, group.getName());
            db.insert(MyClasses.Group.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:groupe row id" + group.getId());
        } else {
            Log.d("insert in database:", "Table:groupe row id" + group.getId() + " already exists");
        }

    }

    public void changeUserImage(SQLiteDatabase db, String id, String avatarEncoded) {
        String selection = MyClasses.User.ID + "=?";
        String[] selectionArgs = {id};
        Cursor cursor = db.query(MyClasses.User.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            if (avatarEncoded != null) {
                values.put(MyClasses.User.PIC, Base64.decode(avatarEncoded, Base64.DEFAULT));
            }
            db.update(MyClasses.User.TABLE_NAME, values, selection, selectionArgs);

            Log.d("update database:", "Table:User row id" + id);
        } else {
            Log.d("update database:", "Table:user row id" + id + " doesnt exists");
        }
    }
    //}

    public MyClasses.User getAuser(SQLiteDatabase db, String id_user) {
        MyClasses.User user = null;
        String selection = MyClasses.User.ID + "=?";
        String[] selectionArgs = {id_user};
        Cursor cursor = db.query(MyClasses.User.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (cursor.moveToNext()) {
            String id, userType, first_name, last_name, sexe, email, birth_date, home_town, password, avatar;
            id = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.User.ID)));
            userType = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.User.USER_TYPE)));
            first_name = cursor.getString(cursor.getColumnIndex(MyClasses.User.FIRST_NAME));
            last_name = cursor.getString(cursor.getColumnIndex(MyClasses.User.LAST_NAME));
            sexe = cursor.getString(cursor.getColumnIndex(MyClasses.User.SEXE));
            email = cursor.getString(cursor.getColumnIndex(MyClasses.User.EMAIL));
            birth_date = cursor.getString(cursor.getColumnIndex(MyClasses.User.BIRTH_DATE));
            home_town = cursor.getString(cursor.getColumnIndex(MyClasses.User.HOME));
            password = cursor.getString(cursor.getColumnIndex(MyClasses.User.PASSWORD));
            avatar = null;
            if (cursor.getBlob(cursor.getColumnIndex(MyClasses.User.PIC)) != null) {
                avatar = Base64.encodeToString(cursor.getBlob(cursor.getColumnIndex(MyClasses.User.PIC)), Base64.DEFAULT);
            }
            user = new MyClasses.User(id, email, userType, last_name, first_name, sexe, birth_date, password, home_town, avatar);
        }
        return user;
    }


    public MyClasses.Teacher getaTeacher(SQLiteDatabase db, String id_user, MyClasses.User user) {
        MyClasses.Teacher teacher = null;
        if (user == null) {
            user = getAuser(db, id_user);

           /* String selection = MyClasses.User.ID + "=?";
            String[] selectionArgs = {id_user};
            Cursor cursor = db.query(MyClasses.User.TABLE_NAME, null,
                    selection, selectionArgs, null, null, null);
            if (cursor.moveToNext()) {
                String id, userType, first_name, last_name, sexe, email, birth_date, home_town, password, avatar;
                id = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.User.ID)));
                userType = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.User.USER_TYPE)));
                first_name = cursor.getString(cursor.getColumnIndex(MyClasses.User.FIRST_NAME));
                last_name = cursor.getString(cursor.getColumnIndex(MyClasses.User.LAST_NAME));
                sexe = cursor.getString(cursor.getColumnIndex(MyClasses.User.SEXE));
                email = cursor.getString(cursor.getColumnIndex(MyClasses.User.EMAIL));
                birth_date = cursor.getString(cursor.getColumnIndex(MyClasses.User.BIRTH_DATE));
                home_town = cursor.getString(cursor.getColumnIndex(MyClasses.User.HOME));
                password = cursor.getString(cursor.getColumnIndex(MyClasses.User.PASSWORD));
                avatar = Base64.encodeToString(cursor.getBlob(cursor.getColumnIndex(MyClasses.User.PIC)), Base64.DEFAULT);
                //user=new MyClasses.User(id,email,userType,last_name,first_name,sexe,birth_date,password,home_town,avatar);*/
            String s = MyClasses.Teacher.ID + "=?";
            String[] Args = {id_user};
            Cursor c = db.query(MyClasses.Teacher.TABLE_NAME, null,
                    s, Args, null, null, null);
            if (c.moveToNext()) {
                String spe = c.getString(c.getColumnIndex(MyClasses.Teacher.ID_SPACIALTY));
                String se = MyClasses.Specialty._ID + "=?";
                String[] rg = {spe};
                Cursor cu = db.query(MyClasses.Specialty.TABLE_NAME, null,
                        se, rg, null, null, null);
                if (cu.moveToNext()) {
                    String specialty = cu.getString(cu.getColumnIndex(MyClasses.Specialty.NAME));
                    teacher = new MyClasses.Teacher(
                            user.getId(), user.getEmail(),
                            user.getUserType(), user.getLast_name(), user.getFirst_name(), user.getSexe(), user.getBirth_date()
                            , user.getPassword(), user.getHome_town(), user.getAvatar()
                            , specialty, "");
                }

            }
        } else {
            String s = MyClasses.Teacher.ID + "=?";
            String[] Args = {id_user};
            Cursor c = db.query(MyClasses.Teacher.TABLE_NAME, null,
                    s, Args, null, null, null);
            if (c.moveToNext()) {
                String spe = c.getString(c.getColumnIndex(MyClasses.Teacher.ID_SPACIALTY));
                String se = MyClasses.Specialty._ID + "=?";
                String[] rg = {spe};
                Cursor cu = db.query(MyClasses.Specialty.TABLE_NAME, null,
                        se, rg, null, null, null);
                if (cu.moveToNext()) {
                    String specialty = cu.getString(cu.getColumnIndex(MyClasses.Specialty.NAME));
                    teacher = new MyClasses.Teacher(user.getId(), user.getEmail(),
                            user.getUserType(), user.getLast_name(), user.getFirst_name(), user.getSexe(), user.getBirth_date()
                            , user.getPassword(), user.getHome_town(), user.getAvatar()
                            , specialty, "");
                }

            }
        }
        return teacher;
    }


    public MyClasses.ResponsibleTeacher getAresponsible(SQLiteDatabase db, String id_user, MyClasses.User user, MyClasses.Teacher teacher) {
        MyClasses.ResponsibleTeacher responsibleTeacher = null;
        /*teacher = null;
        user = null;*/
        if (teacher == null) {
            if (user == null) {
                user = getAuser(db, id_user);
                teacher = getaTeacher(db, id_user, user);

                /*String selection = MyClasses.User.ID + "=?";
                String[] selectionArgs = {id_user};
                Cursor cursor = db.query(MyClasses.User.TABLE_NAME, null,
                        selection, selectionArgs, null, null, null);
                if (cursor.moveToNext()) {
                    String id, userType, first_name, last_name, sexe, email, birth_date, home_town, password, avatar;
                    id = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.User.ID)));
                    userType = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.User.USER_TYPE)));
                    first_name = cursor.getString(cursor.getColumnIndex(MyClasses.User.FIRST_NAME));
                    last_name = cursor.getString(cursor.getColumnIndex(MyClasses.User.LAST_NAME));
                    sexe = cursor.getString(cursor.getColumnIndex(MyClasses.User.SEXE));
                    email = cursor.getString(cursor.getColumnIndex(MyClasses.User.EMAIL));
                    birth_date = cursor.getString(cursor.getColumnIndex(MyClasses.User.BIRTH_DATE));
                    home_town = cursor.getString(cursor.getColumnIndex(MyClasses.User.HOME));
                    password = cursor.getString(cursor.getColumnIndex(MyClasses.User.PASSWORD));
                    avatar = Base64.encodeToString(cursor.getBlob(cursor.getColumnIndex(MyClasses.User.PIC)), Base64.DEFAULT);
                    //user=new MyClasses.User(id,email,userType,last_name,first_name,sexe,birth_date,password,home_town,avatar);
                    String s = MyClasses.Teacher.ID + "=?";
                    String[] Args = {id_user};
                    Cursor c = db.query(MyClasses.Teacher.TABLE_NAME, null,
                            s, Args, null, null, null);
                    if (c.moveToNext()) {
                        String spe = String.valueOf(c.getInt(c.getColumnIndex(MyClasses.Teacher.ID_SPACIALTY)));
                        String se = MyClasses.Specialty._ID + "=?";
                        String[] rg = {spe};
                        Cursor cu = db.query(MyClasses.Specialty.TABLE_NAME, null,
                                se, rg, null, null, null);
                        if (cu.moveToNext()) {
                            String specialty = cu.getString(cu.getColumnIndex(MyClasses.Specialty.NAME));
                            //teacher=new MyClasses.Teacher(id,email,userType,last_name,first_name,sexe,birth_date,password,home_town,avatar,specialty,"");*/
                String select = "select * from " + MyClasses.ResponsibleTeacher.TABLE_NAME + " where " + MyClasses.ResponsibleTeacher.ID +
                        " = " + id_user;
                Cursor sor = db.rawQuery(select, null);
                if (sor.moveToNext()) {
                    String respon = String.valueOf(sor.getInt(sor.getColumnIndex(MyClasses.ResponsibleTeacher.SPECIALTY)));
                    String see = "select * from " + MyClasses.Responsibility.TABLE_NAME + " where " +
                            MyClasses.Responsibility._ID + " = " + respon;
                    Cursor or = db.rawQuery(see, null);
                    if (or.moveToNext()) {
                        String responsibility = or.getString(or.getColumnIndex(MyClasses.Responsibility.NAME));
                        responsibleTeacher = new MyClasses.ResponsibleTeacher(
                                teacher.getId(), teacher.getEmail(),
                                teacher.getUserType(), teacher.getLast_name(), teacher.getFirst_name(), teacher.getSexe(),
                                teacher.getBirth_date()
                                , teacher.getPassword(), teacher.getHome_town(), teacher.getAvatar()
                                , teacher.getId_specialty(), teacher.getAdd_date()
                                , responsibility);
                    }

                }
            } else {
                teacher = getaTeacher(db, id_user, user);
                String select = "select * from " + MyClasses.ResponsibleTeacher.TABLE_NAME + " where " + MyClasses.ResponsibleTeacher.ID +
                        " = " + id_user;
                Cursor sor = db.rawQuery(select, null);
                if (sor.moveToNext()) {
                    String respon = String.valueOf(sor.getInt(sor.getColumnIndex(MyClasses.ResponsibleTeacher.SPECIALTY)));
                    String see = "select * from " + MyClasses.Responsibility.TABLE_NAME + " where " +
                            MyClasses.Responsibility._ID + " = " + respon;
                    Cursor or = db.rawQuery(see, null);
                    if (or.moveToNext()) {
                        String responsibility = or.getString(or.getColumnIndex(MyClasses.Responsibility.NAME));
                        responsibleTeacher = new MyClasses.ResponsibleTeacher(
                                teacher.getId(), teacher.getEmail(),
                                teacher.getUserType(), teacher.getLast_name(), teacher.getFirst_name(), teacher.getSexe(),
                                teacher.getBirth_date()
                                , teacher.getPassword(), teacher.getHome_town(), teacher.getAvatar()
                                , teacher.getId_specialty(), teacher.getAdd_date()
                                , responsibility);
                    }

                }
            }
        } else {
            String select = "select * from " + MyClasses.ResponsibleTeacher.TABLE_NAME + " where " + MyClasses.ResponsibleTeacher.ID +
                    " = " + id_user;
            Cursor sor = db.rawQuery(select, null);
            if (sor.moveToNext()) {
                String respon = String.valueOf(sor.getInt(sor.getColumnIndex(MyClasses.ResponsibleTeacher.SPECIALTY)));
                String see = "select * from " + MyClasses.Responsibility.TABLE_NAME + " where " +
                        MyClasses.Responsibility._ID + " = " + respon;
                Cursor or = db.rawQuery(see, null);
                if (or.moveToNext()) {
                    String responsibility = or.getString(or.getColumnIndex(MyClasses.Responsibility.NAME));
                    responsibleTeacher = new MyClasses.ResponsibleTeacher(
                            teacher.getId(), teacher.getEmail(),
                            teacher.getUserType(), teacher.getLast_name(), teacher.getFirst_name(), teacher.getSexe(),
                            teacher.getBirth_date()
                            , teacher.getPassword(), teacher.getHome_town(), teacher.getAvatar()
                            , teacher.getId_specialty(), teacher.getAdd_date()
                            , responsibility);
                }

            }
        }
        return responsibleTeacher;
    }


    public MyClasses.Student getStudent(SQLiteDatabase db, String id_user, MyClasses.User user) {
        MyClasses.Student student = null;
        if (user == null) {
            user = getAuser(db, id_user);
            String[] selectionArgs = {id_user};
            String s = MyClasses.Student.ID + "=?";
            Cursor c = db.query(MyClasses.Student.TABLE_NAME, null,
                    s, selectionArgs, null, null, null);
            if (c.moveToNext()) {
                String bib = String.valueOf(c.getInt(c.getColumnIndex(MyClasses.Student.lIB_CARD_NUM)));
                String mat = String.valueOf(c.getInt(c.getColumnIndex(MyClasses.Student.MAT_NUM)));
                s = MyClasses.groupeMember.STUDENT_ID + "=?";
                Cursor cur = db.query(MyClasses.groupeMember.TABLE_NAME, null,
                        s, selectionArgs, null, null, null);
                if (cur.moveToNext()) {
                    String id_groupe = String.valueOf(cur.getInt(cur.getColumnIndex(MyClasses.groupeMember.GRP_ID)));
                    s = MyClasses.Group._ID + "=?";
                    selectionArgs = new String[]{id_groupe};
                    c = db.query(MyClasses.Group.TABLE_NAME, null,
                            s, selectionArgs, null, null, null);
                    String groupe = c.getString(c.getColumnIndex(MyClasses.Group.NAME));
                    String level = String.valueOf(c.getInt(c.getColumnIndex(MyClasses.Group.ID_LEVEL)));
                    s = MyClasses.Level.ID + "=?";
                    selectionArgs = new String[]{level};
                    c = db.query(MyClasses.Level.TABLE_NAME, null,
                            s, selectionArgs, null, null, null);
                    if (c.moveToNext()) {
                        level = c.getString(c.getColumnIndex(MyClasses.Level.ABRG));
                        if (user.getSexe().equals("f") || user.getSexe().equals("F")) {
                            s = context.getString(R.string.student_female) + " en " + level + " " + groupe;
                        } else {
                            s = context.getString(R.string.student_male) + " en " + level + " " + groupe;
                        }
                        student = new MyClasses.Student(
                                user.getId(), user.getEmail(),
                                user.getUserType(), user.getLast_name(), user.getFirst_name(), user.getSexe(), user.getBirth_date()
                                , user.getPassword(), user.getHome_town(), user.getAvatar()
                                ,
                                mat, bib, s);
                    }
                }
            }
        } else {
            String[] selectionArgs = {id_user};
            String s = MyClasses.Student.ID + "=?";
            Cursor c = db.query(MyClasses.Student.TABLE_NAME, null,
                    s, selectionArgs, null, null, null);
            if (c.moveToNext()) {
                String bib = String.valueOf(c.getInt(c.getColumnIndex(MyClasses.Student.lIB_CARD_NUM)));
                String mat = String.valueOf(c.getInt(c.getColumnIndex(MyClasses.Student.MAT_NUM)));
                s = MyClasses.groupeMember.STUDENT_ID + "=?";
                Cursor cur = db.query(MyClasses.groupeMember.TABLE_NAME, null,
                        s, selectionArgs, null, null, null);
                if (cur.moveToNext()) {
                    String id_groupe = String.valueOf(cur.getInt(cur.getColumnIndex(MyClasses.groupeMember.GRP_ID)));
                    s = MyClasses.Group._ID + "=?";
                    selectionArgs = new String[]{id_groupe};
                    c = db.query(MyClasses.Group.TABLE_NAME, null,
                            s, selectionArgs, null, null, null);
                    String groupe = c.getString(c.getColumnIndex(MyClasses.Group.NAME));
                    String level = String.valueOf(c.getInt(c.getColumnIndex(MyClasses.Group.ID_LEVEL)));
                    s = MyClasses.Level.ID + "=?";
                    selectionArgs = new String[]{level};
                    c = db.query(MyClasses.Level.TABLE_NAME, null,
                            s, selectionArgs, null, null, null);
                    if (c.moveToNext()) {
                        level = c.getString(c.getColumnIndex(MyClasses.Level.ABRG));
                        if (user.getSexe().equals("f") || user.getSexe().equals("F")) {
                            s = context.getString(R.string.student_female) + " en " + level + " " + groupe;
                        } else {
                            s = context.getString(R.string.student_male) + " en " + level + " " + groupe;
                        }
                        student = new MyClasses.Student(
                                user.getId(), user.getEmail(),
                                user.getUserType(), user.getLast_name(), user.getFirst_name(), user.getSexe(), user.getBirth_date()
                                , user.getPassword(), user.getHome_town(), user.getAvatar()
                                ,
                                mat, bib, s);
                    }
                }
            }
        }
        return student;
    }

    public MyClasses.Delegue getDelegue(SQLiteDatabase db, String id_user, MyClasses.User user, MyClasses.Student student) {
        MyClasses.Delegue delegue = null;
        if (student == null) {
            if (user == null) {
                user = getAuser(db, id_user);
                student = getStudent(db, id_user, user);
                String selection = MyClasses.User.ID + "=?";
                String[] selectionArgs = {id_user};
                Cursor cursor = db.query(MyClasses.Delegue.TABLE_NAME, null,
                        selection, selectionArgs, null, null, null);
                if (cursor.moveToNext()) {
                    String id_level = cursor.getString(cursor.getColumnIndex(MyClasses.Delegue.LEVEL_ID));
                    selection = MyClasses.Level.ID + "=?";
                    selectionArgs = new String[]{id_level};
                    Cursor c = db.query(MyClasses.Level.TABLE_NAME, null,
                            selection, selectionArgs, null, null, null);
                    if (c.moveToNext()) {
                        String level = c.getString(c.getColumnIndex(MyClasses.Level.ABRG));
                        delegue = new MyClasses.Delegue(student.getId(), student.getEmail(), student.getUserType(),
                                student.getLast_name(), student.getFirst_name(), student.getSexe(), student.getBirth_date(),
                                student.getPassword(), student.getHome_town(), student.getAvatar(), student.getMat()
                                , student.getBib(), student.getAdd_date(), level);
                    }
                }
            } else {
                student = getStudent(db, id_user, user);
                String selection = MyClasses.User.ID + "=?";
                String[] selectionArgs = {id_user};
                Cursor cursor = db.query(MyClasses.Delegue.TABLE_NAME, null,
                        selection, selectionArgs, null, null, null);
                if (cursor.moveToNext()) {
                    String id_level = cursor.getString(cursor.getColumnIndex(MyClasses.Delegue.LEVEL_ID));
                    selection = MyClasses.Level.ID + "=?";
                    selectionArgs = new String[]{id_level};
                    Cursor c = db.query(MyClasses.Level.TABLE_NAME, null,
                            selection, selectionArgs, null, null, null);
                    if (c.moveToNext()) {
                        String level = c.getString(c.getColumnIndex(MyClasses.Level.ABRG));
                        delegue = new MyClasses.Delegue(student.getId(), student.getEmail(), student.getUserType(),
                                student.getLast_name(), student.getFirst_name(), student.getSexe(), student.getBirth_date(),
                                student.getPassword(), student.getHome_town(), student.getAvatar(), student.getMat()
                                , student.getBib(), student.getAdd_date(), level);
                    }
                }
            }

        } else {
            String selection = MyClasses.User.ID + "=?";
            String[] selectionArgs = {id_user};
            Cursor cursor = db.query(MyClasses.Delegue.TABLE_NAME, null,
                    selection, selectionArgs, null, null, null);
            if (cursor.moveToNext()) {
                String id_level = cursor.getString(cursor.getColumnIndex(MyClasses.Delegue.LEVEL_ID));
                selection = MyClasses.Level.ID + "=?";
                selectionArgs = new String[]{id_level};
                Cursor c = db.query(MyClasses.Level.TABLE_NAME, null,
                        selection, selectionArgs, null, null, null);
                if (c.moveToNext()) {
                    String level = c.getString(c.getColumnIndex(MyClasses.Level.ABRG));
                    delegue = new MyClasses.Delegue(student.getId(), student.getEmail(), student.getUserType(),
                            student.getLast_name(), student.getFirst_name(), student.getSexe(), student.getBirth_date(),
                            student.getPassword(), student.getHome_town(), student.getAvatar(), student.getMat()
                            , student.getBib(), student.getAdd_date(), level);
                }
            }
        }

        return delegue;
    }


    public MyClasses.Admin getAnAdmin(SQLiteDatabase db, String id_user, MyClasses.User user) {
        MyClasses.Admin admin = null;
        if (user != null) {
            user = getAuser(db, id_user);
            String selection = MyClasses.Admin.ID + "=?";
            String[] selectionArgs = {id_user};
            Cursor cursor = db.query(MyClasses.Admin.TABLE_NAME, null,
                    selection, selectionArgs, null, null, null);
            if (cursor.moveToNext()) {
                String id_work = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Admin.ID_WORK)));
                String select = MyClasses.Work._ID + "=?";
                String[] selectA = {id_work};
                Cursor c = db.query(MyClasses.Work.TABLE_NAME, null,
                        select, selectA, null, null, null);
                if (c.moveToNext()) {
                    id_work = c.getString(c.getColumnIndex(MyClasses.Work.NAME));
                    admin = new MyClasses.Admin(id_user, user.getEmail(), user.getUserType(), user.getLast_name(), user.getFirst_name(), user.getSexe(), user.getBirth_date(),
                            user.getPassword(), user.getHome_town(), user.getAvatar(), id_work, "");
                }
            }
        } else {
            String selection = MyClasses.Admin.ID + "=?";
            String[] selectionArgs = {id_user};
            Cursor cursor = db.query(MyClasses.Admin.TABLE_NAME, null,
                    selection, selectionArgs, null, null, null);
            if (cursor.moveToNext()) {
                String id_work = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Admin.ID_WORK)));
                String select = MyClasses.Work._ID + "=?";
                String[] selectA = {id_work};
                Cursor c = db.query(MyClasses.Work.TABLE_NAME, null,
                        select, selectA, null, null, null);
                if (c.moveToNext()) {
                    id_work = c.getString(c.getColumnIndex(MyClasses.Work.NAME));
                    admin = new MyClasses.Admin(id_user, user.getEmail(), user.getUserType(), user.getLast_name(), user.getFirst_name(), user.getSexe(), user.getBirth_date(),
                            user.getPassword(), user.getHome_town(), user.getAvatar(), id_work, "");
                }
            }

        }
        return admin;
    }


    public void addAnew(SQLiteDatabase db, MyClasses.New aNew) {
        String selection = MyClasses.New.ID + "=?";
        String[] selectionArgs = {aNew.getId()};
        Cursor cursor = db.query(MyClasses.New.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.New.ID, Integer.parseInt(aNew.getId()));
            values.put(MyClasses.New.ID_USER, Integer.parseInt(aNew.getId_user()));
            values.put(MyClasses.New.TYPE, aNew.getNews_Type());
            values.put(MyClasses.New.TITLE, aNew.getTitle());
            values.put(MyClasses.New.CONTENT, aNew.getContent());
            db.insert(MyClasses.New.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:news row id" + aNew.getId());
        } else {
            Log.d("insert in database:", "Table:news row id" + aNew.getId() + " already exists");
        }
    }

    public void addAnewsVisibility(SQLiteDatabase db, MyClasses.NewsVisibility visibility) {

        String selection = MyClasses.NewsVisibility.ID + "=?";
        String[] selectionArgs = {visibility.getId()};
        Cursor cursor = db.query(MyClasses.NewsVisibility.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.NewsVisibility.ID, Integer.parseInt(visibility.getId()));
            values.put(MyClasses.NewsVisibility.USER_ID, Integer.parseInt(visibility.getId_user()));
            values.put(MyClasses.NewsVisibility.NEWS_ID, Integer.parseInt(visibility.getId_news()));
            values.put(MyClasses.NewsVisibility.Vote, Integer.parseInt(visibility.getVote_possible()));
            db.insert(MyClasses.NewsVisibility.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:NewsVisibility row id" + visibility.getId());
        } else {
            Log.d("insert in database:", "Table:NewsVisibility row id" + visibility.getId());
        }
    }

    public void addAchangeOfSession(SQLiteDatabase db, MyClasses.ChangeOfSession changeOfSession) {

        String selection = MyClasses.ChangeOfSession.ID + "=?";
        String[] selectionArgs = {changeOfSession.getId()};
        Cursor cursor = db.query(MyClasses.ChangeOfSession.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            addAnew(db, changeOfSession);
            ContentValues values = new ContentValues();
            values.put(MyClasses.ChangeOfSession.ID, Integer.parseInt(changeOfSession.getId()));
            values.put(MyClasses.ChangeOfSession.OLD, Integer.parseInt(changeOfSession.getId_old_session()));
            values.put(MyClasses.ChangeOfSession.NEW, Integer.parseInt(changeOfSession.getId_new_session()));
            db.insert(MyClasses.ChangeOfSession.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:changeOfSession row id" + changeOfSession.getId());
        } else {
            Log.d("insert in database:", "Table:changeOfSession row id" + changeOfSession.getId() + " already exists");
        }
    }


    public void addAconsultation(SQLiteDatabase db, MyClasses.Consultation consultation) {
        String selection = MyClasses.Consultation.ID + "=?";
        String[] selectionArgs = {consultation.getId()};
        Cursor cursor = db.query(MyClasses.Consultation.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            addAnew(db, consultation);
            ContentValues values = new ContentValues();
            values.put(MyClasses.Consultation.ID, Integer.parseInt(consultation.getId()));
            values.put(MyClasses.Consultation.ID_SESSION, Integer.parseInt(consultation.getId_sesson()));
            values.put(MyClasses.Consultation.DATE, consultation.getDate());
            db.insert(MyClasses.Consultation.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:Consultation row id" + consultation.getId());
        } else {
            Log.d("insert in database:", "Table:Consultation row id" + consultation.getId() + " already exists");
        }
    }


    public void addAexam(SQLiteDatabase db, MyClasses.Exam exam) {
        String selection = MyClasses.ChangeOfSession.ID + "=?";
        String[] selectionArgs = {exam.getId()};
        Cursor cursor = db.query(MyClasses.Exam.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            addAnew(db, exam);
            ContentValues values = new ContentValues();
            values.put(MyClasses.Exam.ID, Integer.parseInt(exam.getId()));
            values.put(MyClasses.Exam.TYPE, Integer.parseInt(exam.getId_exam_type()));
            values.put(MyClasses.Exam.GROUPE, Integer.parseInt(exam.getId_groupe()));
            values.put(MyClasses.Exam.CLASSROOM, Integer.parseInt(exam.getId_classroom()));
            values.put(MyClasses.Exam.MODULE, Integer.parseInt(exam.getId_module()));
            values.put(MyClasses.Exam.BEGIN_DATE, exam.getBegin());
            db.insert(MyClasses.Exam.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:Exam row id" + exam.getId());
        } else {
            Log.d("insert in database:", "Table:Exam row id" + exam.getId() + " already exists");
        }
    }


    public void addAholiday(SQLiteDatabase db, MyClasses.Holiday holiday) {
        String selection = MyClasses.Holiday.ID + "=?";
        String[] selectionArgs = {holiday.getId()};
        Cursor cursor = db.query(MyClasses.Holiday.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            addAnew(db, holiday);
            ContentValues values = new ContentValues();
            values.put(MyClasses.Holiday.ID, Integer.parseInt(holiday.getId()));
            values.put(MyClasses.Holiday.BEGIN_DATE, holiday.getBegin_date());
            values.put(MyClasses.Holiday.END_DATE, holiday.getEnd_date());
            db.insert(MyClasses.Holiday.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:Holiday row id" + holiday.getId());
        } else {
            Log.d("insert in database:", "Table:Holiday row id" + holiday.getId() + " already exists");
        }
    }


    public void addAmarksDisplayed(SQLiteDatabase db, MyClasses.MarksDisplayed marksDisplayed) {
        String selection = MyClasses.MarksDisplayed.ID + "=?";
        String[] selectionArgs = {
                marksDisplayed.getId()};
        Cursor cursor = db.query(MyClasses.MarksDisplayed.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            addAnew(db, marksDisplayed);
            ContentValues values = new ContentValues();
            values.put(MyClasses.MarksDisplayed.ID, Integer.parseInt(marksDisplayed.getId()));
            values.put(MyClasses.MarksDisplayed.TEACHER_ID, Integer.parseInt(marksDisplayed.getId_teacher()));
            values.put(MyClasses.MarksDisplayed.EXAM_ID, Integer.parseInt(marksDisplayed.getId_exam()));
            db.insert(MyClasses.MarksDisplayed.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:changeOfSession row id" + marksDisplayed.getId());
        } else {
            Log.d("insert in database:", "Table:changeOfSession row id" + marksDisplayed.getId() + " already exists");
        }
    }


    public void addAmeeting(SQLiteDatabase db, MyClasses.Meeting meeting) {
        String selection = MyClasses.Meeting.ID + "=?";
        String[] selectionArgs = {meeting.getId()};
        Cursor cursor = db.query(MyClasses.Meeting.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            addAnew(db, meeting);
            ContentValues values = new ContentValues();
            values.put(MyClasses.Meeting.ID, Integer.parseInt(meeting.getId()));
            values.put(MyClasses.Meeting.CLASSROOM_ID, Integer.parseInt(meeting.getId_classroom()));
            values.put(MyClasses.Meeting.DATE, meeting.getDate());
            db.insert(MyClasses.Meeting.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:Meeting row id" + meeting.getId());
        } else {
            Log.d("insert in database:", "Table:Meeting row id" + meeting.getId() + " already exists");
        }
    }


    public void addAprocedure(SQLiteDatabase db, MyClasses.PedagogicalProcedure procedure) {
        String selection = MyClasses.ChangeOfSession.ID + "=?";
        String[] selectionArgs = {procedure.getId()};
        Cursor cursor = db.query(MyClasses.PedagogicalProcedure.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            addAnew(db, procedure);
            ContentValues values = new ContentValues();
            values.put(MyClasses.PedagogicalProcedure.ID, Integer.parseInt(procedure.getId()));
            values.put(MyClasses.PedagogicalProcedure.PLACE_ID, Integer.parseInt(procedure.getId_place()));
            values.put(MyClasses.PedagogicalProcedure.BEGIN_DATE, procedure.getBegin_date());
            values.put(MyClasses.PedagogicalProcedure.END_DATE, procedure.getEnd_date());
            values.put(MyClasses.PedagogicalProcedure.DOCS, procedure.getDocs());
            db.insert(MyClasses.PedagogicalProcedure.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:changeOfSession row id" + procedure.getId());
        } else {
            Log.d("insert in database:", "Table:changeOfSession row id" + procedure.getId() + " already exists");
        }
    }

    public void addAstrike(SQLiteDatabase db, MyClasses.Strike strike) {
        String selection = MyClasses.Strike.ID + "=?";
        String[] selectionArgs = {strike.getId()};
        Cursor cursor = db.query(MyClasses.Strike.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            addAnew(db, strike);
            ContentValues values = new ContentValues();
            values.put(MyClasses.Strike.ID, Integer.parseInt(strike.getId()));
            values.put(MyClasses.Strike.BEGIN_DATE, strike.getBegin_date());
            values.put(MyClasses.Strike.END_DATE, strike.getEnd_date());
            values.put(MyClasses.Strike.CAUSE, strike.getCause());
            db.insert(MyClasses.Strike.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:Strike row id" + strike.getId());
        } else {
            Log.d("insert in database:", "Table:Strike row id" + strike.getId() + " already exists");
        }
    }

    public void addAvote(SQLiteDatabase db, MyClasses.Voted voted) {
        String selection = MyClasses.Voted.ID + "=?";
        String[] selectionArgs = {voted.getId()};
        Cursor cursor = db.query(MyClasses.Voted.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Voted.ID, Integer.parseInt(voted.getId()));
            values.put(MyClasses.Voted.ID_USER, Integer.parseInt(voted.getId_user()));
            values.put(MyClasses.Voted.POST, Integer.parseInt(voted.getId_post()));
            values.put(MyClasses.Voted.AGREE, Integer.parseInt(voted.getAgree()));
            values.put(MyClasses.Voted.REASON, voted.getReason());
            db.insert(MyClasses.Voted.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:Voted row id" + voted.getId());
        } else {
            Log.d("insert in database:", "Table:Voted row id" + voted.getId() + " already exists");
        }
    }

    public void addTojournal(SQLiteDatabase db, MyClasses.Journal journal) {
        String selection = MyClasses.Journal.OP + " = ? AND " + MyClasses.Journal.TAB + " =? AND " + MyClasses.Journal.ID
                + " = ?";
        String[] selectionArgs = {MyClasses.Journal.INSERT, journal.getTab(), journal.getId()};
        Cursor cursor = db.query(MyClasses.Journal.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Journal.ID, Integer.parseInt(journal.getId()));
            values.put(MyClasses.Journal.OP, Integer.parseInt(journal.getOp()));
            values.put(MyClasses.Journal.TAB, Integer.parseInt(journal.getTab()));
            values.put(MyClasses.Journal.USER_ID, Integer.parseInt(journal.getId_user()));
            values.put(MyClasses.Journal.DATE, journal.getDate());
            db.insert(MyClasses.Journal.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:JOURNAL row id" + journal.getId());
        } else {
            Log.d("insert in database:", "Table:JOURNAL row id" + journal.getId() + " already exists");
        }
    }

    public Cursor getAllFromNewsTable(SQLiteDatabase db, String type) {
        String selection = "select * from " + MyClasses.New.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME + " where ( "
                + MyClasses.New.ID + " = " + MyClasses.Journal.ID + " and " + MyClasses.New.TYPE + " = " + type + " and " +
                MyClasses.Journal.TAB + " = 23 ) " + " ORDER BY " + /*CAST( strftime( '%d/%m/%Y %H:%i' ,"+MyClasses.Journal.DATE+" ) AS INTEGER) */
                MyClasses.New.ID + " DESC ";
        Log.d("getAllFromNewsTable", selection);
        Cursor cursor = db.rawQuery(selection, null);
        return cursor;
    }
    private MyClasses.Exam getExam(SQLiteDatabase db, String id_exam) {
        MyClasses.Exam exam = null;
        String select = " select * from " + MyClasses.Exam.TABLE_NAME + " where " + MyClasses.Exam.ID + " = " + id_exam;
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToNext()) {
            String grp = "", module = "", exam_type = "", begin = "", classroom = "";
            begin = cursor.getString(cursor.getColumnIndex(MyClasses.Exam.BEGIN_DATE));
            String id_grp = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Exam.GROUPE)));
            select = "select * from " + MyClasses.Group.TABLE_NAME + " where " + MyClasses.Group._ID + " = " + id_grp;
            Cursor c = db.rawQuery(select, null);
            if (c.moveToNext()) grp = c.getString(c.getColumnIndex(MyClasses.Group.NAME));
            String id_module = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Exam.MODULE)));
            select = "select * from " + MyClasses.Module.TABLE_NAME + " where " + MyClasses.Module.ID + " = " + id_module;
            c = db.rawQuery(select, null);
            if (c.moveToNext()) module = c.getString(c.getColumnIndex(MyClasses.Module.ABRG));
            String id_exam_type = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Exam.TYPE)));
            select = "select * from " + MyClasses.ExamType.TABLE_NAME + " where " + MyClasses.ExamType._ID + " = " + id_exam_type;
            c = db.rawQuery(select, null);
            if (c.moveToNext()) exam_type = c.getString(c.getColumnIndex(MyClasses.ExamType.TYPE));
            String id_classroom = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.Exam.CLASSROOM)));
            select = "select * from " + MyClasses.Classroom.TABLE_NAME + " where " + MyClasses.Classroom._ID + " = " + id_classroom;
            c = db.rawQuery(select, null);
            if (c.moveToNext()) classroom = c.getString(c.getColumnIndex(MyClasses.Classroom.NAME));
            exam = new MyClasses.Exam(id_exam, null, null, grp, module, null,
                    null, exam_type, begin.substring(0,begin.length()-3), classroom);
        }
        return exam;
    }

    public ArrayList<ChatActivity.RecyclerAdapter.msgs> getConversation(SQLiteDatabase database, String id_partner, String mId) {
        ArrayList<ChatActivity.RecyclerAdapter.msgs> result = new ArrayList<ChatActivity.RecyclerAdapter.msgs>();
        String msg = "", send_date = "", seen_date = "";
        boolean iWrote = false;
        String query = "select * from " + MyClasses.MSG.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                " where ( ( " + MyClasses.MSG.USER_ID + " = " + mId + " or "
                + MyClasses.MSG.USER_ID + " = " + id_partner + " ) and " + MyClasses.Journal.TAB + " = 20" + " and " +
                MyClasses.MSG._ID + " = " + MyClasses.Journal.ID + " ) order by " + MyClasses.Journal.DATE + " ASC ";
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            msg = cursor.getString(cursor.getColumnIndex(MyClasses.MSG.MSG));
            send_date = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            String id_sender = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.MSG.USER_ID)));
            if (id_sender.equals(id_partner)) {
                iWrote = false;
                ChatActivity.RecyclerAdapter.msgs msgs = new ChatActivity.RecyclerAdapter.msgs(msg, send_date, seen_date,
                        iWrote);
                result.add(msgs);
            } else {
                iWrote = true;
                seen_date = "";
                String id_msg = String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.MSG._ID)));
                String check_query = " select * from " + MyClasses.MsgSentTo.TABLE_NAME + " where " + MyClasses.MsgSentTo.MSG_ID +
                        " = " + id_msg;
                Cursor k = database.rawQuery(check_query, null);
                if(k.moveToNext()) {
                    String p = String.valueOf(k.getInt(k.getColumnIndex(MyClasses.MsgSentTo.USER_ID)));
                    if (p.equals(id_partner)) {
                        String seen_query = " select " + MyClasses.Journal.DATE + " from " + MyClasses.MsgSentTo.TABLE_NAME + " , " +
                                MyClasses.Journal.TABLE_NAME +
                                " where ( " + MyClasses.MsgSentTo.ID + " = " + MyClasses.Journal.ID + " and " + MyClasses.MsgSentTo.MSG_ID +
                                " = " + id_msg + " and " + MyClasses.Journal.TAB + " = 21" + " and " + MyClasses.MsgSentTo.SEEN + " = 1 )";
                        Cursor c = database.rawQuery(seen_query, null);
                        if (c.moveToNext()) {
                            seen_date = c.getString(c.getColumnIndex(MyClasses.Journal.DATE));
                        }
                        ChatActivity.RecyclerAdapter.msgs msgs = new ChatActivity.RecyclerAdapter.msgs(msg, send_date, seen_date,
                                iWrote);
                        result.add(msgs);
                    }
                }

            }

        }
        return result;
    }
    public ArrayList<MessegesActivity.RecyclerAdapter.msgs> getAllMesseges(SQLiteDatabase db, String mId){
        ArrayList<MessegesActivity.RecyclerAdapter.msgs> msgs=new ArrayList<>();
        MessegesActivity.RecyclerAdapter.msgs m;
        String select=" select * from "+MyClasses.MSG.TABLE_NAME+" , "+MyClasses.Journal.TABLE_NAME+" where "+
                MyClasses.Journal.TAB+" = 20 and "+MyClasses.Journal.ID+" = "+MyClasses.MSG._ID+
                " order by "+MyClasses.MSG._ID+" DESC ";
        Cursor cursor=db.rawQuery(select,null) ;
        Bitmap avatar=null;boolean seen=false;
        String title="",des="",date="";
        String id_msg,id_user;
        ArrayList<String> ids = new ArrayList<>();
        while (cursor.moveToNext()){
            id_user= String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.MSG.USER_ID)));
            if(!id_user.equals(mId)){
                if(!ids.contains(id_user)) {
                    id_msg= String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.MSG._ID)));
                    MyClasses.User user=getAuser(db,id_user);
                    title=user.getLast_name()+" "+user.getFirst_name();
                    date =cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
                    String a = user.getAvatar();
                    byte[] d = Base64.decode(a, Base64.DEFAULT);
                    avatar = BitmapFactory.decodeByteArray(d, 0, d.length);
                    select="  select * from "+MyClasses.MsgSentTo.TABLE_NAME+" where "+MyClasses.MsgSentTo.MSG_ID+
                            " = "+id_msg;
                    Cursor c=db.rawQuery(select,null) ;
                    if(c.moveToNext()){
                        if(c.getInt(c.getColumnIndex(MyClasses.MsgSentTo.SEEN))==1){
                            seen=true;
                            des=cursor.getString(cursor.getColumnIndex(MyClasses.MSG.MSG));
                            m=new MessegesActivity.RecyclerAdapter.msgs(avatar,title,des,date,seen,Long.parseLong(id_user));
                            msgs.add(m);
                            ids.add(id_user);
                        }else{

                            seen=false;
                            des=cursor.getString(cursor.getColumnIndex(MyClasses.MSG.MSG));
                            m=new MessegesActivity.RecyclerAdapter.msgs(avatar,title,des,date,seen,Long.parseLong(id_user));
                            msgs.add(m);
                            ids.add(id_user);
                        }

                    }
                }
            }else{

                id_msg= String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.MSG._ID)));
                select="  select * from "+MyClasses.MsgSentTo.TABLE_NAME+" where "+MyClasses.MsgSentTo.MSG_ID+
                        " = "+id_msg;
                Cursor c=db.rawQuery(select,null) ;
                if(c.moveToNext()){
                    id_user= String.valueOf(c.getInt(c.getColumnIndex(MyClasses.MsgSentTo.USER_ID)));
                    if(!ids.contains(id_user)){
                        des=cursor.getString(cursor.getColumnIndex(MyClasses.MSG.MSG));
                        MyClasses.User user=getAuser(db,id_user);
                        title=user.getLast_name()+" "+user.getFirst_name();
                        date =cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
                        seen=true;
                        String a = user.getAvatar();
                        byte[] d = Base64.decode(a, Base64.DEFAULT);
                        avatar = BitmapFactory.decodeByteArray(d, 0, d.length);
                        m=new MessegesActivity.RecyclerAdapter.msgs(avatar,title,des,date,seen,Long.parseLong(id_user));
                        msgs.add(m);
                        ids.add(id_user);
                    }
                }

            }


        }

        return  msgs;
    }

    public  void seeMsgs(SQLiteDatabase db, String id){
        String selection = "select * from "+MyClasses.MSG.TABLE_NAME+
                " where "+ MyClasses.MSG.USER_ID+" = "+id;
        Cursor cursor = db.rawQuery(selection,null);
        while (cursor.moveToNext()) {
            String id_msg= String.valueOf(cursor.getInt(cursor.getColumnIndex(MyClasses.MSG._ID)));
            selection=" select * from "+ MyClasses.MsgSentTo.TABLE_NAME+" where "+MyClasses.MsgSentTo.MSG_ID+" = "+
                    id_msg;
            Cursor  c=db.rawQuery(selection,null);
            if(c.moveToNext()) {
                String id_= String.valueOf(c.getInt(c.getColumnIndex(MyClasses.MsgSentTo.ID)));
                ContentValues values = new ContentValues();
                values.put(MyClasses.MsgSentTo.SEEN, 1);
                selection = MyClasses.MsgSentTo.ID + " = ?";
                String[] selectionArgs = {id_};
                db.update(MyClasses.MsgSentTo.TABLE_NAME, values, selection, selectionArgs);
            }
        }

    }

    public ArrayList<MyClasses.RecyclerNewsAdapter.mClass> getNews(SQLiteDatabase db) {
        MyClasses.RecyclerNewsAdapter.mClass aNew;
        ArrayList<MyClasses.RecyclerNewsAdapter.mClass> news = new ArrayList<>();
        String username = null, userSpecialty = null, pubDate, title, content, avatar;
        Bitmap userAvatar = null;
        Cursor cursor = getAllFromNewsTable(db, "0");
        while (cursor.moveToNext()) {
            int like = 0, com = 0, dislike = 0;
            int id_news = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID)),
                    id_poster = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID_USER));
            MyClasses.User user = getAuser(db, String.valueOf(id_poster));
            if (user != null) {
                username = user.getLast_name() + " " + user.getFirst_name();
                avatar = user.getAvatar();
                  byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                userAvatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                switch (Integer.parseInt(user.getUserType())) {
                    case 0: {
                        MyClasses.Admin admin = getAnAdmin(db, user.getId(), user);
                        userSpecialty = admin.getId_work();
                        break;
                    }
                    case 1: {
                        MyClasses.Student student = getStudent(db, user.getId(), user);
                        userSpecialty = student.getAdd_date();
                        break;
                    }
                    case 2: {
                        if (user.getSexe().equals("f") || user.getSexe().equals("F"))
                            userSpecialty = context.getString(R.string.teacher_female);
                        else userSpecialty = context.getString(R.string.teacher_male);
                        break;
                    }
                    case 4: {
                        MyClasses.Delegue student = getDelegue(db, user.getId(), user, null);
                        userSpecialty = student.getId_level();
                        break;
                    }
                    case 5: {
                        MyClasses.ResponsibleTeacher t = getAresponsible(db, user.getId(), user, null);
                        userSpecialty = t.getId_responsibility();
                        break;
                    }
                }
            }
            pubDate = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            title = cursor.getString(cursor.getColumnIndex(MyClasses.New.TITLE));
            content = cursor.getString(cursor.getColumnIndex(MyClasses.New.CONTENT));
            String selection = "select * from " + MyClasses.Voted.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                    " where ( " + MyClasses.Voted.POST + " = " + String.valueOf(id_news) + " and " + MyClasses.Voted.ID + " = " +
                    MyClasses.Journal.ID + " and " + MyClasses.Journal.TAB + " = 38 ) order by " + MyClasses.Journal.DATE + " DESC";
            Cursor cLike = db.rawQuery(selection, null);
            ArrayList<MyClasses.Comments> comments = new ArrayList<>();
            while (cLike.moveToNext()) {
                int id_ = cLike.getInt(cursor.getColumnIndex(MyClasses.Voted.ID_USER));
                MyClasses.User u = getAuser(db, String.valueOf(id_));
                String a = u.getAvatar();
                byte[] d = Base64.decode(a, Base64.DEFAULT);
                Bitmap av = BitmapFactory.decodeByteArray(d, 0, d.length);
                String reason = cLike.getString(cLike.getColumnIndex(MyClasses.Voted.REASON));
                if (reason != null && !reason.equals("null") && !reason.equals("NULL") && !reason.equals("") && !reason.equals(" "))
                    com++;
                else reason = "";
                int agree = cLike.getInt(cLike.getColumnIndex(MyClasses.Voted.AGREE));
                boolean avis;
                if (agree == 0) {
                    avis = false;
                    dislike++;
                } else {
                    avis = true;
                    like++;
                }
                String date = cLike.getString(cLike.getColumnIndex(MyClasses.Journal.DATE));
                MyClasses.Comments comment = new MyClasses.Comments(id_, av, u.getLast_name() + " " + u.getFirst_name(),
                        reason, avis, date);
                comments.add(comment);
            }
            aNew = new MyClasses.RecyclerNewsAdapter.mClass(id_news, id_poster, userAvatar, username,
                    userSpecialty, pubDate, title, content, like, dislike, com, comments);
            news.add(aNew);
        }
        return news;
    }
    public ArrayList<MyClasses.RecyclerMarksDAdapter.mClass> getAllMarksD(SQLiteDatabase db) {
        ArrayList<MyClasses.RecyclerMarksDAdapter.mClass> reult = new ArrayList<>();
        MyClasses.RecyclerMarksDAdapter.mClass aNew;
        String username = null, userSpecialty = null, pubDate, title, content, avatar;
        Bitmap userAvatar = null;
        int id_teacher;
        String teacherName = "", module = "", grp = "", examType = "";
        Cursor cursor = getAllFromNewsTable(db, "5");
        while (cursor.moveToNext()) {
            int like = 0, com = 0, dislike = 0;
            int id_news = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID)),
                    id_poster = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID_USER));
            MyClasses.User user = getAuser(db, String.valueOf(id_poster));
            if (user != null) {
                username = user.getLast_name() + " " + user.getFirst_name();
                avatar = user.getAvatar();
                byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                userAvatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                switch (Integer.parseInt(user.getUserType())) {
                    case 0: {
                        MyClasses.Admin admin = getAnAdmin(db, user.getId(), user);
                        userSpecialty = admin.getId_work();
                        break;
                    }
                    case 1: {
                        MyClasses.Student student = getStudent(db, user.getId(), user);
                        userSpecialty = student.getAdd_date();
                        break;
                    }
                    case 2: {
                        if (user.getSexe().equals("f") || user.getSexe().equals("F"))
                            userSpecialty = context.getString(R.string.teacher_female);
                        else userSpecialty = context.getString(R.string.teacher_male);
                        break;
                    }
                    case 4: {
                        MyClasses.Delegue student = getDelegue(db, user.getId(), user, null);
                        userSpecialty = student.getId_level();
                        break;
                    }
                    case 5: {
                        MyClasses.ResponsibleTeacher t = getAresponsible(db, user.getId(), user, null);
                        userSpecialty = t.getId_responsibility();
                        break;
                    }
                }
            }
            pubDate = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            title = cursor.getString(cursor.getColumnIndex(MyClasses.New.TITLE));
            content = cursor.getString(cursor.getColumnIndex(MyClasses.New.CONTENT));
            String selection = "select * from " + MyClasses.Voted.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                    " where ( " + MyClasses.Voted.POST + " = " + String.valueOf(id_news) + " and " + MyClasses.Voted.ID + " = " +
                    MyClasses.Journal.ID + " and " + MyClasses.Journal.TAB + " = 38 ) order by " + MyClasses.Journal.DATE + " DESC";
            Cursor cLike = db.rawQuery(selection, null);
            ArrayList<MyClasses.Comments> comments = new ArrayList<>();
            while (cLike.moveToNext()) {
                int id_ = cLike.getInt(cursor.getColumnIndex(MyClasses.Voted.ID_USER));
                MyClasses.User u = getAuser(db, String.valueOf(id_));
                String a = u.getAvatar();
                byte[] d = Base64.decode(a, Base64.DEFAULT);
                Bitmap av = BitmapFactory.decodeByteArray(d, 0, d.length);
                String reason = cLike.getString(cLike.getColumnIndex(MyClasses.Voted.REASON));
                if (reason != null && !reason.equals("null") && !reason.equals("NULL") && !reason.equals("") && !reason.equals(" "))
                    com++;
                else reason = "";
                int agree = cLike.getInt(cLike.getColumnIndex(MyClasses.Voted.AGREE));
                boolean avis;
                if (agree == 0) {
                    avis = false;
                    dislike++;
                } else {
                    avis = true;
                    like++;
                }
                String date = cLike.getString(cLike.getColumnIndex(MyClasses.Journal.DATE));
                MyClasses.Comments comment = new MyClasses.Comments(id_, av, u.getLast_name() + " " + u.getFirst_name(),
                        reason, avis, date);
                comments.add(comment);

            }

            String marks_query = " select * from " + MyClasses.MarksDisplayed.TABLE_NAME + " where " + MyClasses.MarksDisplayed._ID +
                    " = " + String.valueOf(id_news);
            Cursor cMark = db.rawQuery(marks_query, null);
            if (cMark.moveToNext()) {
                id_teacher = cMark.getInt(cMark.getColumnIndex(MyClasses.MarksDisplayed.TEACHER_ID));
                String teacher_query = "select * from " + MyClasses.User.TABLE_NAME + " where " + MyClasses.User.ID +
                        " = " + String.valueOf(id_teacher);
                Cursor t = db.rawQuery(teacher_query, null);
                if (t.moveToNext()) {
                    teacherName = t.getString(t.getColumnIndex(MyClasses.User.LAST_NAME)) + " " + t.getString(t.getColumnIndex(MyClasses.User.FIRST_NAME));
                    MyClasses.Exam exam = getExam(db, String.valueOf(cMark.getInt(cMark.getColumnIndex(MyClasses.MarksDisplayed.EXAM_ID))));
                    aNew = new MyClasses.RecyclerMarksDAdapter.mClass(id_news, userAvatar, username,
                            userSpecialty, pubDate, title, content, like, dislike, com, comments, teacherName,
                            id_teacher, exam.getId_module(), exam.getId_groupe(), exam.getId_exam_type());
                    reult.add(aNew);
                }

            }

        }

        return reult;
    }
    public ArrayList<MyClasses.RecyclerExamAdapter.mClass> getAllexams(SQLiteDatabase db) {
        ArrayList<MyClasses.RecyclerExamAdapter.mClass> result = new ArrayList<>();
        MyClasses.RecyclerExamAdapter.mClass aNew;
        String username = null, userSpecialty = null, pubDate, title, content, avatar;
        Bitmap userAvatar = null;

        Cursor cursor = getAllFromNewsTable(db, "3");
        while (cursor.moveToNext()) {
            int like = 0, com = 0, dislike = 0;
            int id_news = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID)),
                    id_poster = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID_USER));
            MyClasses.User user = getAuser(db, String.valueOf(id_poster));
            if (user != null) {
                username = user.getLast_name() + " " + user.getFirst_name();
                avatar = user.getAvatar();
                byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                userAvatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                switch (Integer.parseInt(user.getUserType())) {
                    case 0: {
                        MyClasses.Admin admin = getAnAdmin(db, user.getId(), user);
                        userSpecialty = admin.getId_work();
                        break;
                    }
                    case 1: {
                        MyClasses.Student student = getStudent(db, user.getId(), user);
                        userSpecialty = student.getAdd_date();
                        break;
                    }
                    case 2: {
                        if (user.getSexe().equals("f") || user.getSexe().equals("F"))
                            userSpecialty = context.getString(R.string.teacher_female);
                        else userSpecialty = context.getString(R.string.teacher_male);
                        break;
                    }
                    case 4: {
                        MyClasses.Delegue student = getDelegue(db, user.getId(), user, null);
                        userSpecialty = student.getId_level();
                        break;
                    }
                    case 5: {
                        MyClasses.ResponsibleTeacher t = getAresponsible(db, user.getId(), user, null);
                        userSpecialty = t.getId_responsibility();
                        break;
                    }
                }
            }
            pubDate = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            title = cursor.getString(cursor.getColumnIndex(MyClasses.New.TITLE));
            content = cursor.getString(cursor.getColumnIndex(MyClasses.New.CONTENT));
            String selection = "select * from " + MyClasses.Voted.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                    " where ( " + MyClasses.Voted.POST + " = " + String.valueOf(id_news) + " and " + MyClasses.Voted.ID + " = " +
                    MyClasses.Journal.ID + " and " + MyClasses.Journal.TAB + " = 38 ) order by " + MyClasses.Journal.DATE + " DESC";
            Cursor cLike = db.rawQuery(selection, null);
            ArrayList<MyClasses.Comments> comments = new ArrayList<>();
            while (cLike.moveToNext()) {
                int id_ = cLike.getInt(cursor.getColumnIndex(MyClasses.Voted.ID_USER));
                MyClasses.User u = getAuser(db, String.valueOf(id_));
                String a = u.getAvatar();
                byte[] d = Base64.decode(a, Base64.DEFAULT);
                Bitmap av = BitmapFactory.decodeByteArray(d, 0, d.length);
                String reason = cLike.getString(cLike.getColumnIndex(MyClasses.Voted.REASON));
                if (reason != null && !reason.equals("null") && !reason.equals("NULL") && !reason.equals("") && !reason.equals(" "))
                    com++;
                else reason = "";
                int agree = cLike.getInt(cLike.getColumnIndex(MyClasses.Voted.AGREE));
                boolean avis;
                if (agree == 0) {
                    avis = false;
                    dislike++;
                } else {
                    avis = true;
                    like++;
                }
                String date = cLike.getString(cLike.getColumnIndex(MyClasses.Journal.DATE));
                MyClasses.Comments comment = new MyClasses.Comments(id_, av, u.getLast_name() + " " + u.getFirst_name(),
                        reason, avis, date);
                comments.add(comment);

            }
            MyClasses.Exam exam = getExam(db, String.valueOf(id_news));
            aNew = new MyClasses.RecyclerExamAdapter.mClass(id_news, id_poster, userAvatar, username,
                    userSpecialty, pubDate, title, content, like, dislike, com, comments, exam.getId_exam_type(),
                    exam.getBegin(), exam.getId_groupe(), exam.getId_module());

            result.add(aNew);

        }
        return result;
    }
    public ArrayList<MyClasses.RecyclerStrikeAdapter.mClass> getAllStrike(SQLiteDatabase db) {
        ArrayList<MyClasses.RecyclerStrikeAdapter.mClass> result=new ArrayList<>();
        MyClasses.RecyclerStrikeAdapter.mClass aNew;
        String username = null, userSpecialty = null, pubDate, title, content, avatar;
        Bitmap userAvatar = null;

        Cursor cursor = getAllFromNewsTable(db, "9");
        while (cursor.moveToNext()) {
            int like = 0, com = 0, dislike = 0;
            int id_news = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID)),
                    id_poster = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID_USER));
            MyClasses.User user = getAuser(db, String.valueOf(id_poster));
            if (user != null) {
                username = user.getLast_name() + " " + user.getFirst_name();
                avatar = user.getAvatar();
                byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                userAvatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                switch (Integer.parseInt(user.getUserType())) {
                    case 0: {
                        MyClasses.Admin admin = getAnAdmin(db, user.getId(), user);
                        userSpecialty = admin.getId_work();
                        break;
                    }
                    case 1: {
                        MyClasses.Student student = getStudent(db, user.getId(), user);
                        userSpecialty = student.getAdd_date();
                        break;
                    }
                    case 2: {
                        if (user.getSexe().equals("f") || user.getSexe().equals("F"))
                            userSpecialty = context.getString(R.string.teacher_female);
                        else userSpecialty = context.getString(R.string.teacher_male);
                        break;
                    }
                    case 4: {
                        MyClasses.Delegue student = getDelegue(db, user.getId(), user, null);
                        userSpecialty = student.getId_level();
                        break;
                    }
                    case 5: {
                        MyClasses.ResponsibleTeacher t = getAresponsible(db, user.getId(), user, null);
                        userSpecialty = t.getId_responsibility();
                        break;
                    }
                }
            }
            pubDate = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            title = cursor.getString(cursor.getColumnIndex(MyClasses.New.TITLE));
            content = cursor.getString(cursor.getColumnIndex(MyClasses.New.CONTENT));
            String selection = "select * from " + MyClasses.Voted.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                    " where ( " + MyClasses.Voted.POST + " = " + String.valueOf(id_news) + " and " + MyClasses.Voted.ID + " = " +
                    MyClasses.Journal.ID + " and " + MyClasses.Journal.TAB + " = 38 ) order by " + MyClasses.Journal.DATE + " DESC";
            Cursor cLike = db.rawQuery(selection, null);
            ArrayList<MyClasses.Comments> comments = new ArrayList<>();
            while (cLike.moveToNext()) {
                int id_ = cLike.getInt(cursor.getColumnIndex(MyClasses.Voted.ID_USER));
                MyClasses.User u = getAuser(db, String.valueOf(id_));
                String a = u.getAvatar();
                byte[] d = Base64.decode(a, Base64.DEFAULT);
                Bitmap av = BitmapFactory.decodeByteArray(d, 0, d.length);
                String reason = cLike.getString(cLike.getColumnIndex(MyClasses.Voted.REASON));
                if (reason != null && !reason.equals("null") && !reason.equals("NULL") && !reason.equals("") && !reason.equals(" "))
                    com++;
                else reason = "";
                int agree = cLike.getInt(cLike.getColumnIndex(MyClasses.Voted.AGREE));
                boolean avis;
                if (agree == 0) {
                    avis = false;
                    dislike++;
                } else {
                    avis = true;
                    like++;
                }
                String date = cLike.getString(cLike.getColumnIndex(MyClasses.Journal.DATE));
                MyClasses.Comments comment = new MyClasses.Comments(id_, av, u.getLast_name() + " " + u.getFirst_name(),
                        reason, avis, date);
                comments.add(comment);
            }
            String reason="",date="";
            String y="select * from "+MyClasses.Strike.TABLE_NAME+" where "+MyClasses.Strike.ID+" = "+String.valueOf(id_news);
            Cursor z=db.rawQuery(y,null);
            if(z.moveToNext()){
                reason=z.getString(z.getColumnIndex(MyClasses.Strike.CAUSE));
                date=" de "+z.getString(z.getColumnIndex(MyClasses.Strike.BEGIN_DATE))+
                        " jusqu'à "+z.getString(z.getColumnIndex(MyClasses.Strike.END_DATE));
            }
            aNew = new MyClasses.RecyclerStrikeAdapter.mClass(id_news, id_poster, userAvatar, username,
                    userSpecialty, pubDate, title, content, like, dislike, com, comments,reason,date);
            result.add(aNew);
        }

        return result;
    }
    public ArrayList<MyClasses.RecyclerConsultationDAdapter.mClass> getAllConsultation(SQLiteDatabase db) {
        ArrayList<MyClasses.RecyclerConsultationDAdapter.mClass> result=new ArrayList<>();
        MyClasses.RecyclerConsultationDAdapter.mClass aNew;
        String username = null, userSpecialty = null, pubDate, title, content, avatar;
        Bitmap userAvatar = null;

        Cursor cursor = getAllFromNewsTable(db, "2");
        while (cursor.moveToNext()) {
            int like = 0, com = 0, dislike = 0;
            int id_news = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID)),
                    id_poster = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID_USER));
            MyClasses.User user = getAuser(db, String.valueOf(id_poster));
            if (user != null) {
                username = user.getLast_name() + " " + user.getFirst_name();
                avatar = user.getAvatar();
                byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                userAvatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                switch (Integer.parseInt(user.getUserType())) {
                    case 0: {
                        MyClasses.Admin admin = getAnAdmin(db, user.getId(), user);
                        userSpecialty = admin.getId_work();
                        break;
                    }
                    case 1: {
                        MyClasses.Student student = getStudent(db, user.getId(), user);
                        userSpecialty = student.getAdd_date();
                        break;
                    }
                    case 2: {
                        if (user.getSexe().equals("f") || user.getSexe().equals("F"))
                            userSpecialty = context.getString(R.string.teacher_female);
                        else userSpecialty = context.getString(R.string.teacher_male);
                        break;
                    }
                    case 4: {
                        MyClasses.Delegue student = getDelegue(db, user.getId(), user, null);
                        userSpecialty = student.getId_level();
                        break;
                    }
                    case 5: {
                        MyClasses.ResponsibleTeacher t = getAresponsible(db, user.getId(), user, null);
                        userSpecialty = t.getId_responsibility();
                        break;
                    }
                }
            }
            pubDate = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            title = cursor.getString(cursor.getColumnIndex(MyClasses.New.TITLE));
            content = cursor.getString(cursor.getColumnIndex(MyClasses.New.CONTENT));
            String selection = "select * from " + MyClasses.Voted.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                    " where ( " + MyClasses.Voted.POST + " = " + String.valueOf(id_news) + " and " + MyClasses.Voted.ID + " = " +
                    MyClasses.Journal.ID + " and " + MyClasses.Journal.TAB + " = 38 ) order by " + MyClasses.Journal.DATE + " DESC";
            Cursor cLike = db.rawQuery(selection, null);
            ArrayList<MyClasses.Comments> comments = new ArrayList<>();
            while (cLike.moveToNext()) {
                int id_ = cLike.getInt(cursor.getColumnIndex(MyClasses.Voted.ID_USER));
                MyClasses.User u = getAuser(db, String.valueOf(id_));
                String a = u.getAvatar();
                byte[] d = Base64.decode(a, Base64.DEFAULT);
                Bitmap av = BitmapFactory.decodeByteArray(d, 0, d.length);
                String reason = cLike.getString(cLike.getColumnIndex(MyClasses.Voted.REASON));
                if (reason != null && !reason.equals("null") && !reason.equals("NULL") && !reason.equals("") && !reason.equals(" "))
                    com++;
                else reason = "";
                int agree = cLike.getInt(cLike.getColumnIndex(MyClasses.Voted.AGREE));
                boolean avis;
                if (agree == 0) {
                    avis = false;
                    dislike++;
                } else {
                    avis = true;
                    like++;
                }
                String date = cLike.getString(cLike.getColumnIndex(MyClasses.Journal.DATE));
                MyClasses.Comments comment = new MyClasses.Comments(id_, av, u.getLast_name() + " " + u.getFirst_name(),
                        reason, avis, date);
                comments.add(comment);
            }
            int seesion_id=0;String date="";
            String u=" select * from "+MyClasses.Consultation.TABLE_NAME+" where "+MyClasses.Consultation._ID+" = "+String.valueOf(id_news);
            Cursor v=db.rawQuery(u,null);
            if(v.moveToNext()){
                seesion_id=v.getInt(v.getColumnIndex(MyClasses.Consultation.ID_SESSION));
                date=v.getString(v.getColumnIndex(MyClasses.Consultation.DATE));
            }
            aNew = new MyClasses.RecyclerConsultationDAdapter.mClass(id_news, id_poster, userAvatar, username,
                    userSpecialty, pubDate, title, content, like, dislike, com, comments,null,seesion_id,date.substring
                    (0, date.length()-3));
            result.add(aNew);
        }


        return result;
    }
    public ArrayList<MyClasses.RecyclerMeetingAdapter.mClass> getAllMeetings(SQLiteDatabase db) {
        ArrayList<MyClasses.RecyclerMeetingAdapter.mClass> result=new ArrayList<>();
        MyClasses.RecyclerMeetingAdapter.mClass aNew;
        String username = null, userSpecialty = null, pubDate, title, content, avatar;
        Bitmap userAvatar = null;

        Cursor cursor = getAllFromNewsTable(db, "6");
        while (cursor.moveToNext()) {
            int like = 0, com = 0, dislike = 0;
            int id_news = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID)),
                    id_poster = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID_USER));
            MyClasses.User user = getAuser(db, String.valueOf(id_poster));
            if (user != null) {
                username = user.getLast_name() + " " + user.getFirst_name();
                avatar = user.getAvatar();
                byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                userAvatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                switch (Integer.parseInt(user.getUserType())) {
                    case 0: {
                        MyClasses.Admin admin = getAnAdmin(db, user.getId(), user);
                        userSpecialty = admin.getId_work();
                        break;
                    }
                    case 1: {
                        MyClasses.Student student = getStudent(db, user.getId(), user);
                        userSpecialty = student.getAdd_date();
                        break;
                    }
                    case 2: {
                        if (user.getSexe().equals("f") || user.getSexe().equals("F"))
                            userSpecialty = context.getString(R.string.teacher_female);
                        else userSpecialty = context.getString(R.string.teacher_male);
                        break;
                    }
                    case 4: {
                        MyClasses.Delegue student = getDelegue(db, user.getId(), user, null);
                        userSpecialty = student.getId_level();
                        break;
                    }
                    case 5: {
                        MyClasses.ResponsibleTeacher t = getAresponsible(db, user.getId(), user, null);
                        userSpecialty = t.getId_responsibility();
                        break;
                    }
                }
            }
            pubDate = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            title = cursor.getString(cursor.getColumnIndex(MyClasses.New.TITLE));
            content = cursor.getString(cursor.getColumnIndex(MyClasses.New.CONTENT));
            String selection = "select * from " + MyClasses.Voted.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                    " where ( " + MyClasses.Voted.POST + " = " + String.valueOf(id_news) + " and " + MyClasses.Voted.ID + " = " +
                    MyClasses.Journal.ID + " and " + MyClasses.Journal.TAB + " = 38 ) order by " + MyClasses.Journal.DATE + " DESC";
            Cursor cLike = db.rawQuery(selection, null);
            ArrayList<MyClasses.Comments> comments = new ArrayList<>();
            while (cLike.moveToNext()) {
                int id_ = cLike.getInt(cursor.getColumnIndex(MyClasses.Voted.ID_USER));
                MyClasses.User u = getAuser(db, String.valueOf(id_));
                String a = u.getAvatar();
                byte[] d = Base64.decode(a, Base64.DEFAULT);
                Bitmap av = BitmapFactory.decodeByteArray(d, 0, d.length);
                String reason = cLike.getString(cLike.getColumnIndex(MyClasses.Voted.REASON));
                if (reason != null && !reason.equals("null") && !reason.equals("NULL") && !reason.equals("") && !reason.equals(" "))
                    com++;
                else reason = "";
                int agree = cLike.getInt(cLike.getColumnIndex(MyClasses.Voted.AGREE));
                boolean avis;
                if (agree == 0) {
                    avis = false;
                    dislike++;
                } else {
                    avis = true;
                    like++;
                }
                String date = cLike.getString(cLike.getColumnIndex(MyClasses.Journal.DATE));
                MyClasses.Comments comment = new MyClasses.Comments(id_, av, u.getLast_name() + " " + u.getFirst_name(),
                        reason, avis, date);
                comments.add(comment);
            }
            String date="",classroom="";
            String s="select * from "+MyClasses.Meeting.TABLE_NAME+ " where "+MyClasses.Meeting.ID+" = "+String.valueOf(id_news);
            Cursor x=db.rawQuery(s,null);
            if(x.moveToNext()){
                date=x.getString(x.getColumnIndex(MyClasses.Meeting.DATE));
                String id_classroom= String.valueOf(x.getInt(x.getColumnIndex(MyClasses.Meeting.CLASSROOM_ID)));
                s=" select * from "+MyClasses.Classroom.TABLE_NAME+" where "+MyClasses.Classroom._ID+" = "+id_classroom;
                Cursor w=db.rawQuery(s,null);
                if(w.moveToNext()) classroom=w.getString(w.getColumnIndex(MyClasses.Classroom.NAME));
            }
            aNew = new MyClasses.RecyclerMeetingAdapter.mClass(id_news, id_poster, userAvatar, username,
                    userSpecialty, pubDate, title, content, like, dislike, com, comments,date.substring(0,
                    date.length()-3),classroom);
            result.add(aNew);
        }

        return  result;
    }
    public ArrayList<MyClasses.RecyclerHolydayAdapter.mClass> getAllHolydays(SQLiteDatabase db) {
        ArrayList<MyClasses.RecyclerHolydayAdapter.mClass> news=new ArrayList<>();
        MyClasses.RecyclerHolydayAdapter.mClass aNew;
        String username = null, userSpecialty = null, pubDate, title, content, avatar;
        Bitmap userAvatar = null;

        Cursor cursor = getAllFromNewsTable(db, "4");
        while (cursor.moveToNext()) {
            int like = 0, com = 0, dislike = 0;
            int id_news = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID)),
                    id_poster = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID_USER));
            MyClasses.User user = getAuser(db, String.valueOf(id_poster));
            if (user != null) {
                username = user.getLast_name() + " " + user.getFirst_name();
                avatar = user.getAvatar();
                byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                userAvatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                switch (Integer.parseInt(user.getUserType())) {
                    case 0: {
                        MyClasses.Admin admin = getAnAdmin(db, user.getId(), user);
                        userSpecialty = admin.getId_work();
                        break;
                    }
                    case 1: {
                        MyClasses.Student student = getStudent(db, user.getId(), user);
                        userSpecialty = student.getAdd_date();
                        break;
                    }
                    case 2: {
                        if (user.getSexe().equals("f") || user.getSexe().equals("F"))
                            userSpecialty = context.getString(R.string.teacher_female);
                        else userSpecialty = context.getString(R.string.teacher_male);
                        break;
                    }
                    case 4: {
                        MyClasses.Delegue student = getDelegue(db, user.getId(), user, null);
                        userSpecialty = student.getId_level();
                        break;
                    }
                    case 5: {
                        MyClasses.ResponsibleTeacher t = getAresponsible(db, user.getId(), user, null);
                        userSpecialty = t.getId_responsibility();
                        break;
                    }
                }
            }
            pubDate = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            title = cursor.getString(cursor.getColumnIndex(MyClasses.New.TITLE));
            content = cursor.getString(cursor.getColumnIndex(MyClasses.New.CONTENT));
            String selection = "select * from " + MyClasses.Voted.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                    " where ( " + MyClasses.Voted.POST + " = " + String.valueOf(id_news) + " and " + MyClasses.Voted.ID + " = " +
                    MyClasses.Journal.ID + " and " + MyClasses.Journal.TAB + " = 38 ) order by " + MyClasses.Journal.DATE + " DESC";
            Cursor cLike = db.rawQuery(selection, null);
            ArrayList<MyClasses.Comments> comments = new ArrayList<>();
            while (cLike.moveToNext()) {
                int id_ = cLike.getInt(cursor.getColumnIndex(MyClasses.Voted.ID_USER));
                MyClasses.User u = getAuser(db, String.valueOf(id_));
                String a = u.getAvatar();
                byte[] d = Base64.decode(a, Base64.DEFAULT);
                Bitmap av = BitmapFactory.decodeByteArray(d, 0, d.length);
                String reason = cLike.getString(cLike.getColumnIndex(MyClasses.Voted.REASON));
                if (reason != null && !reason.equals("null") && !reason.equals("NULL") && !reason.equals("") && !reason.equals(" "))
                    com++;
                else reason = "";
                int agree = cLike.getInt(cLike.getColumnIndex(MyClasses.Voted.AGREE));
                boolean avis;
                if (agree == 0) {
                    avis = false;
                    dislike++;
                } else {
                    avis = true;
                    like++;
                }
                String date = cLike.getString(cLike.getColumnIndex(MyClasses.Journal.DATE));
                MyClasses.Comments comment = new MyClasses.Comments(id_, av, u.getLast_name() + " " + u.getFirst_name(),
                        reason, avis, date);
                comments.add(comment);
            }
            String date1,date2,date="";
            String a="select * from "+MyClasses.Holiday.TABLE_NAME+" where "+MyClasses.Holiday.ID+" = "+String.valueOf(id_news);
            Cursor b=db.rawQuery(a,null);
            if(b.moveToNext()){
                date1=b.getString(b.getColumnIndex(MyClasses.Holiday.BEGIN_DATE));
                date2=b.getString(b.getColumnIndex(MyClasses.Holiday.END_DATE));
                if(date1.equals(date2)){
                    date=date1;
                }else{
                    date="du "+date1+" à "+date2;
                }
            }
            aNew = new MyClasses.RecyclerHolydayAdapter.mClass(id_news, id_poster, userAvatar, username,
                    userSpecialty, pubDate, title, content, like, dislike, com, comments,date);
            news.add(aNew);
        }

        return news;
    }
    public ArrayList<MyClasses.RecyclerChangeAdapter.mClass> getAllChanges(SQLiteDatabase db) {
        ArrayList<MyClasses.RecyclerChangeAdapter.mClass> news=new ArrayList<>();
        MyClasses.RecyclerChangeAdapter.mClass aNew;
        String username = null, userSpecialty = null, pubDate, title, content, avatar;
        Bitmap userAvatar = null;

        Cursor cursor = getAllFromNewsTable(db, "1");
        while (cursor.moveToNext()) {
            int like = 0, com = 0, dislike = 0;
            int id_news = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID)),
                    id_poster = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID_USER));
            MyClasses.User user = getAuser(db, String.valueOf(id_poster));
            if (user != null) {
                username = user.getLast_name() + " " + user.getFirst_name();
                avatar = user.getAvatar();
                byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                userAvatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                switch (Integer.parseInt(user.getUserType())) {
                    case 0: {
                        MyClasses.Admin admin = getAnAdmin(db, user.getId(), user);
                        userSpecialty = admin.getId_work();
                        break;
                    }
                    case 1: {
                        MyClasses.Student student = getStudent(db, user.getId(), user);
                        userSpecialty = student.getAdd_date();
                        break;
                    }
                    case 2: {
                        if (user.getSexe().equals("f") || user.getSexe().equals("F"))
                            userSpecialty = context.getString(R.string.teacher_female);
                        else userSpecialty = context.getString(R.string.teacher_male);
                        break;
                    }
                    case 4: {
                        MyClasses.Delegue student = getDelegue(db, user.getId(), user, null);
                        userSpecialty = student.getId_level();
                        break;
                    }
                    case 5: {
                        MyClasses.ResponsibleTeacher t = getAresponsible(db, user.getId(), user, null);
                        userSpecialty = t.getId_responsibility();
                        break;
                    }
                }
            }
            pubDate = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            title = cursor.getString(cursor.getColumnIndex(MyClasses.New.TITLE));
            content = cursor.getString(cursor.getColumnIndex(MyClasses.New.CONTENT));
            String selection = "select * from " + MyClasses.Voted.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                    " where ( " + MyClasses.Voted.POST + " = " + String.valueOf(id_news) + " and " + MyClasses.Voted.ID + " = " +
                    MyClasses.Journal.ID + " and " + MyClasses.Journal.TAB + " = 38 ) order by " + MyClasses.Journal.DATE + " DESC";
            Cursor cLike = db.rawQuery(selection, null);
            ArrayList<MyClasses.Comments> comments = new ArrayList<>();
            while (cLike.moveToNext()) {
                int id_ = cLike.getInt(cursor.getColumnIndex(MyClasses.Voted.ID_USER));
                MyClasses.User u = getAuser(db, String.valueOf(id_));
                String a = u.getAvatar();
                byte[] d = Base64.decode(a, Base64.DEFAULT);
                Bitmap av = BitmapFactory.decodeByteArray(d, 0, d.length);
                String reason = cLike.getString(cLike.getColumnIndex(MyClasses.Voted.REASON));
                if (reason != null && !reason.equals("null") && !reason.equals("NULL") && !reason.equals("") && !reason.equals(" "))
                    com++;
                else reason = "";
                int agree = cLike.getInt(cLike.getColumnIndex(MyClasses.Voted.AGREE));
                boolean avis;
                if (agree == 0) {
                    avis = false;
                    dislike++;
                } else {
                    avis = true;
                    like++;
                }
                String date = cLike.getString(cLike.getColumnIndex(MyClasses.Journal.DATE));
                MyClasses.Comments comment = new MyClasses.Comments(id_, av, u.getLast_name() + " " + u.getFirst_name(),
                        reason, avis, date);
                comments.add(comment);
            }
            int id_new=0,id_old=0;
            String w="select * from "+MyClasses.ChangeOfSession.TABLE_NAME+" where "+
                    MyClasses.ChangeOfSession._ID+" = "+String.valueOf(id_news);
            Cursor z=db.rawQuery(w,null);
            if(z.moveToNext()){
                id_new=z.getInt(z.getColumnIndex(MyClasses.ChangeOfSession.NEW));
                id_old=z.getInt(z.getColumnIndex(MyClasses.ChangeOfSession.OLD));
            }
            aNew = new MyClasses.RecyclerChangeAdapter.mClass(id_news, id_poster, userAvatar, username,
                    userSpecialty, pubDate, title, content, like, dislike, com, comments,id_new,id_old);
            news.add(aNew);
        }


        return news;
    }
    public ArrayList<MyClasses.RecyclerProcedureAdapter.mClass> getAllProcedures(SQLiteDatabase db) {
        ArrayList<MyClasses.RecyclerProcedureAdapter.mClass> news=new ArrayList<>();
        MyClasses.RecyclerProcedureAdapter.mClass aNew;
        String username = null, userSpecialty = null, pubDate, title, content, avatar;
        Bitmap userAvatar = null;

        Cursor cursor = getAllFromNewsTable(db, "8");
        while (cursor.moveToNext()) {
            int like = 0, com = 0, dislike = 0;
            int id_news = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID)),
                    id_poster = cursor.getInt(cursor.getColumnIndex(MyClasses.New.ID_USER));
            MyClasses.User user = getAuser(db, String.valueOf(id_poster));
            if (user != null) {
                username = user.getLast_name() + " " + user.getFirst_name();
                avatar = user.getAvatar();
                byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
                userAvatar = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                switch (Integer.parseInt(user.getUserType())) {
                    case 0: {
                        MyClasses.Admin admin = getAnAdmin(db, user.getId(), user);
                        userSpecialty = admin.getId_work();
                        break;
                    }
                    case 1: {
                        MyClasses.Student student = getStudent(db, user.getId(), user);
                        userSpecialty = student.getAdd_date();
                        break;
                    }
                    case 2: {
                        if (user.getSexe().equals("f") || user.getSexe().equals("F"))
                            userSpecialty = context.getString(R.string.teacher_female);
                        else userSpecialty = context.getString(R.string.teacher_male);
                        break;
                    }
                    case 4: {
                        MyClasses.Delegue student = getDelegue(db, user.getId(), user, null);
                        userSpecialty = student.getId_level();
                        break;
                    }
                    case 5: {
                        MyClasses.ResponsibleTeacher t = getAresponsible(db, user.getId(), user, null);
                        userSpecialty = t.getId_responsibility();
                        break;
                    }
                }
            }
            pubDate = cursor.getString(cursor.getColumnIndex(MyClasses.Journal.DATE));
            title = cursor.getString(cursor.getColumnIndex(MyClasses.New.TITLE));
            content = cursor.getString(cursor.getColumnIndex(MyClasses.New.CONTENT));
            String selection = "select * from " + MyClasses.Voted.TABLE_NAME + " , " + MyClasses.Journal.TABLE_NAME +
                    " where ( " + MyClasses.Voted.POST + " = " + String.valueOf(id_news) + " and " + MyClasses.Voted.ID + " = " +
                    MyClasses.Journal.ID + " and " + MyClasses.Journal.TAB + " = 38 ) order by " + MyClasses.Journal.DATE + " DESC";
            Cursor cLike = db.rawQuery(selection, null);
            ArrayList<MyClasses.Comments> comments = new ArrayList<>();
            while (cLike.moveToNext()) {
                int id_ = cLike.getInt(cursor.getColumnIndex(MyClasses.Voted.ID_USER));
                MyClasses.User u = getAuser(db, String.valueOf(id_));
                String a = u.getAvatar();
                byte[] d = Base64.decode(a, Base64.DEFAULT);
                Bitmap av = BitmapFactory.decodeByteArray(d, 0, d.length);
                String reason = cLike.getString(cLike.getColumnIndex(MyClasses.Voted.REASON));
                if (reason != null && !reason.equals("null") && !reason.equals("NULL") && !reason.equals("") && !reason.equals(" "))
                    com++;
                else reason = "";
                int agree = cLike.getInt(cLike.getColumnIndex(MyClasses.Voted.AGREE));
                boolean avis;
                if (agree == 0) {
                    avis = false;
                    dislike++;
                } else {
                    avis = true;
                    like++;
                }
                String date = cLike.getString(cLike.getColumnIndex(MyClasses.Journal.DATE));
                MyClasses.Comments comment = new MyClasses.Comments(id_, av, u.getLast_name() + " " + u.getFirst_name(),
                        reason, avis, date);
                comments.add(comment);
            }
            String date="",est="",docs="";
            String k= " select * from "+MyClasses.PedagogicalProcedure.TABLE_NAME+" where "+MyClasses.PedagogicalProcedure.ID+" = "
                    +String.valueOf(id_news);
            Cursor imen=db.rawQuery(k,null);
            if(imen.moveToNext()){
                date="du "+imen.getString(imen.getColumnIndex(MyClasses.PedagogicalProcedure.BEGIN_DATE))+
                        " à "+imen.getString(imen.getColumnIndex(MyClasses.PedagogicalProcedure.END_DATE));
                docs=imen.getString(imen.getColumnIndex(MyClasses.PedagogicalProcedure.DOCS));
                String id_est= String.valueOf(imen.getInt(imen.getColumnIndex(MyClasses.PedagogicalProcedure.PLACE_ID)));
                k="select * from "+MyClasses.EST.TABLE_NAME+" where "+MyClasses.EST._ID+" = "+id_est;
                Cursor manno=db.rawQuery(k,null);
                if(manno.moveToNext()) est=manno.getString(manno.getColumnIndex(MyClasses.EST.NAME));
            }
            aNew = new MyClasses.RecyclerProcedureAdapter.mClass(id_news, id_poster, userAvatar, username,
                    userSpecialty, pubDate, title, content, like, dislike, com, comments,date,est,docs);
            news.add(aNew);
        }

        return news;
    }

    public static final String CREATE_USER_TABLE = " create table " + MyClasses.User.TABLE_NAME + " ( " + MyClasses.User.ID + " INTEGER PRIMARY KEY, "
            + MyClasses.User.USER_TYPE + " INTEGER, " + MyClasses.User.EMAIL + " TEXT, " + MyClasses.User.LAST_NAME + " TEXT," + MyClasses.User.FIRST_NAME +
            " TEXT, " + MyClasses.User.SEXE + " TEXT, " + MyClasses.User.BIRTH_DATE + " TEXT, " + MyClasses.User.HOME + " TEXT, " + MyClasses.User.PASSWORD +
            " TEXT, " + MyClasses.User.PIC + " BLOB);";
    public static final String CREATE_STUDENT_TABLE = "create table " + MyClasses.Student.TABLE_NAME + " ( " + MyClasses.Student.ID +
            " INTEGER PRIMARY KEY, " +
            MyClasses.Student.MAT_NUM + " TEXT, " + MyClasses.Student.lIB_CARD_NUM + " TEXT ) ; ";
    public static final String CREATE_TEACHER_TABLE = " create table " + MyClasses.Teacher.TABLE_NAME + " ( " + MyClasses.Teacher.ID +
            " INTEGER PRIMARY KEY, " +
            MyClasses.Teacher.ID_SPACIALTY + "  INTEGER ) ;";
    public static final String CREATE_ADMIN_TABLE = " create table " + MyClasses.Admin.TABLE_NAME + " ( " + MyClasses.Teacher.ID +
            " INTEGER PRIMARY KEY, " +
            MyClasses.Admin.ID_WORK + " INTEGER );";

    public static final String CREATE_DELEGUE_TABLE = " create table " + MyClasses.Delegue.TABLE_NAME + " ( " + MyClasses.Delegue.ID +
            " INTEGER PRIMARY KEY , "
            + MyClasses.Delegue.DELEGUE_ID + " INTEGER, " + MyClasses.Delegue.LEVEL_ID + " INTEGER);";
    public static final String CREATE_RESPONSBLE_TABLE = " create table " + MyClasses.ResponsibleTeacher.TABLE_NAME + " ( " + MyClasses.ResponsibleTeacher.ID
            + " INTEGER PRIMARY KEY , " + MyClasses.ResponsibleTeacher.SPECIALTY + " INTEGER ) ;";
    public static final String CREATE_CHAGNGE_SESSION = "create table " + MyClasses.ChangeOfSession.TABLE_NAME + " ( " + MyClasses.ChangeOfSession._ID +
            " INTEGER PRIMARY KEY , " +
            MyClasses.ChangeOfSession.OLD + "  INTEGER, " + MyClasses.ChangeOfSession.NEW + " INTEGER);";


    public static final String CREATE_CONSULTATION = "create table " + MyClasses.Consultation.TABLE_NAME + " ( " + MyClasses.Consultation._ID + "" +
            " INTEGER PRIMARY KEY, " +
            MyClasses.Consultation.ID_SESSION + " INTEGER," + MyClasses.Consultation.DATE + " TEXT ) ;";

    public static final String CREATE_DOC_REQ = "create table " + MyClasses.DocumentRequest.TABLE_NAME + " ( " + MyClasses.DocumentRequest._ID +
            " INTEGER PRIMARY KEY, " + MyClasses.DocumentRequest.USER_ID + " INTEGER, " + MyClasses.DocumentRequest.DOC + " TEXT," +
            MyClasses.DocumentRequest.REASON + " TEXT ) ;";
    public static final String CREATE_ENCADREUR_TABLE = "create table " + MyClasses.Encadreur.TABLE_NAME + " ( " + MyClasses.Encadreur._ID +
            " INTEGER PRIMARY KEY, " + MyClasses.Encadreur.ENCADREUR_ID + " INTEGER, " + MyClasses.Encadreur.STUDENT_ID + " INTEGER );";

    public static final String CREATE_EXAM = "create table " + MyClasses.Exam.TABLE_NAME + " ( " + MyClasses.Exam.ID + " INTEGER PRIMARY KEY, " + MyClasses.Exam.CLASSROOM + " INTEGER, " +
            MyClasses.Exam.TYPE + " INTEGER, " + MyClasses.Exam.BEGIN_DATE + " TEXT," + MyClasses.Exam.GROUPE + " INTEGER," + MyClasses.Exam.MODULE + " INTEGER );";
    public static final String CREATE_GROUPE = "create table " + MyClasses.Group.TABLE_NAME + " ( " + MyClasses.Group._ID + " INTEGER PRIMARY KEY, "
            + MyClasses.Group.ID_SP + " TEXT, " + MyClasses.Group.ID_LEVEL + " TEXT, " + MyClasses.Group.NAME + " TEXT );";
    public static final String CREATE_GRP_MEMBER = "create table " + MyClasses.groupeMember.TABLE_NAME + " ( " + MyClasses.groupeMember.ID +
            " INTEGER PRIMARY KEY , " +
            MyClasses.groupeMember.GRP_ID + "  INTEGER, " + MyClasses.groupeMember.STUDENT_ID + " INTEGER );";
    public static final String CREATE_HOLY_DAY = "create table " + MyClasses.Holiday.TABLE_NAME + " ( " + MyClasses.Holiday.ID + " INTEGER PRIMARY KEY," +
            MyClasses.Holiday.BEGIN_DATE + " TEXT ," + MyClasses.Holiday.END_DATE + " TEXT );";
    public static final String CREATE_MARKS_DES = " create table " + MyClasses.MarksDisplayed.TABLE_NAME + " ( " + MyClasses.MarksDisplayed._ID +
            " INTEGER PRIMARY KEY, " + MyClasses.MarksDisplayed.TEACHER_ID + " INTEGER, " + MyClasses.MarksDisplayed.EXAM_ID + " INTEGER ); ";
    public static final String CREATE_MEETTING = "create table " + MyClasses.Meeting.TABLE_NAME + " ( " + MyClasses.Meeting.ID +
            " INTEGER PRIMARY KEY, " + MyClasses.Meeting.CLASSROOM_ID + " INTEGER," + MyClasses.Meeting.DATE + " TEXT );";
    public static final String CREATE_MEETING_P = " create table " + MyClasses.MeetingPresency.TABLE_NAME + " ( " + MyClasses.MeetingPresency.ID +
            " INTEGER PRIMARY KEY," +
            MyClasses.MeetingPresency.ID_USER + "  INTEGER, " + MyClasses.MeetingPresency.ID_MEETING + " INTEGER);";
    public static final String CREATE_MESSEGE = "create table " + MyClasses.MSG.TABLE_NAME + " ( " + MyClasses.MSG._ID +
            " INTEGER PRIMARY KEY, " + MyClasses.MSG.USER_ID + " INTEGER," + MyClasses.MSG.MSG + " TEXT ); ";
    public static final String CREATE_MSG_SEND_TO = "create table " + MyClasses.MsgSentTo.TABLE_NAME + " ( " + MyClasses.MsgSentTo.ID +
            " INTEGER PRIMARY KEY, " + MyClasses.MsgSentTo.MSG_ID + "  INTEGER , " + MyClasses.MsgSentTo.USER_ID + " INTEGER , " +
            MyClasses.MsgSentTo.SEEN + " INTEGER );";
    public static final String CREATE_NEWS = "create table " + MyClasses.New.TABLE_NAME + " ( " + MyClasses.New.ID + " INTEGER PRIMARY KEY," +
            MyClasses.New.ID_USER + " INTEGER, " + MyClasses.New.TYPE + " INTEGER, " + MyClasses.New.TITLE + " TEXT," + MyClasses.New.CONTENT +
            " TEXT);";
    public static final String CREATE_NEWS_visibility = "create table " + MyClasses.NewsVisibility.TABLE_NAME + " ( " + MyClasses.NewsVisibility.ID +
            " INTEGER PRIMARY KEY , " +
            MyClasses.NewsVisibility.USER_ID + "  INTEGER," + MyClasses.NewsVisibility.NEWS_ID + " INTEGER, " + MyClasses.NewsVisibility.Vote + " INTEGER );";
    public static final String CREATE_PROCEDURE = "create table " + MyClasses.PedagogicalProcedure.TABLE_NAME + " ( " + MyClasses.PedagogicalProcedure.ID + " INTEGER PRIMARY KEY, " +
            MyClasses.PedagogicalProcedure.BEGIN_DATE + " TEXT, " + MyClasses.PedagogicalProcedure.END_DATE + " TEXT, " + MyClasses.PedagogicalProcedure
            .PLACE_ID + " TEXT," + MyClasses.PedagogicalProcedure.DOCS + " TEXT ) ;";
    public static final String CREATE_REQ_DONE = "create table " + MyClasses.RequestDone.TABLE_NAME + " ( " + MyClasses.RequestDone.ID + " " +
            "INTEGER PRIMARY KEY, " + MyClasses.RequestDone.EST_ID + " INTEGER , " + MyClasses.RequestDone.ID_ADMIN + " INTEGER );";
    public static final String CREATE_VOTED = "create table " + MyClasses.Voted.TABLE_NAME + " ( " + MyClasses.Voted.ID + " " +
            "INTEGER PRIMARY KEY, " + MyClasses.Voted.ID_USER + " INTEGER, " + MyClasses.Voted.POST + " INTEGER, " +
            MyClasses.Voted.AGREE + " INTEGER, " + MyClasses.Voted.REASON + " TEXT ) ;";

    public static final String CREATE_strike = "create table " + MyClasses.Strike.TABLE_NAME + " ( " + MyClasses.Strike.ID + " INTEGER PRIMARY KEY, " +
            MyClasses.Strike.BEGIN_DATE + " TEXT , " + MyClasses.Strike.END_DATE + " TEXT," + MyClasses.Strike.CAUSE + " TEXT ) ; ";

    public static final String CREATE_JOURNAL = "create table " + MyClasses.Journal.TABLE_NAME + " ( " + MyClasses.Journal.USER_ID + " INTEGER , "
            + MyClasses.Journal.OP + " INTEGER , " + MyClasses.Journal.ID + " INTEGER, " + MyClasses.Journal.TAB + " INTEGER, " + MyClasses.Journal.DATE +
            " Text ) ;";
    public static final String CREATE_LOCATION = "create table " + MyClasses.Location.TABLE_NAME + " ( " + MyClasses.Location.ID + " INTEGER PRIMARY KEY, " +
            MyClasses.Location.ID_USER + "INTEGER, " + MyClasses.Location.ALT + " text, " + MyClasses.Location.LONG + " text, " + MyClasses.Location.TIME +
            "text ) ;";

    public static final String CREATE_MARKS = "create table " + MyClasses.Marks.TABLE_NAME + " ( " + MyClasses.Marks.ID +
            " INTEGER PRIMARY KEY, " +
            MyClasses.Marks.ID_S + " INTEGER, " + MyClasses.Marks.ID_EXAM + " INTEGER, " + MyClasses.Marks.NOTE + " REAL );";


    public static final String CREATE_Module = "create table " + MyClasses.Module.TABLE_NAME + " ( " + MyClasses.Module.ID + " INTEGER PRIMARY KEY, " +
            MyClasses.Module.NAME + " text, " + MyClasses.Module.ABRG + " text );";
    public static final String CREATE_Responsbility = "create table " + MyClasses.Responsibility.TABLE_NAME + " ( " + MyClasses.Responsibility._ID +
            " INTEGER PRIMARY KEY, " + MyClasses.Responsibility.NAME + " text );";

    public static final String CREATE_ABSENCE = "create table " + MyClasses.Absence.TABLE_NAME + " ( " +
            MyClasses.Absence.MODULE + " integer , " + MyClasses.Absence.SESSION + " integer );";



    void updateASession(SQLiteDatabase db,MyClasses.Session session){
        String selection = MyClasses.Session._ID + "=?";
        String[] selectionArgs = {session.getId()};
        Cursor cursor = db.query(MyClasses.Session.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Session._ID, Integer.parseInt(session.getId()));
            values.put(MyClasses.Session.ID_SESSION_TYPE, Integer.parseInt(session.getId_session_type()));
            values.put(MyClasses.Session.ENABLED, Integer.parseInt(session.getEnabled()));
            values.put(MyClasses.Session.ID_DAY, Integer.parseInt(session.getId_day()));
            values.put(MyClasses.Session.TIME, session.getTime());
            values.put(MyClasses.Session.ID_MODULE, Integer.parseInt(session.getId_module()));
            values.put(MyClasses.Session.ID_CLASSROOM, Integer.parseInt(session.getId_classroom()));
            values.put(MyClasses.Session.ID_TEACHER, Integer.parseInt(session.getId_teacher()));
            values.put(MyClasses.Session.ID_GRP, Integer.parseInt(session.getId_groupe()));
            values.put(MyClasses.Session.IS_SEMESTER_1, Integer.parseInt(session.getSemester_1()));
            db.update(MyClasses.Session.TABLE_NAME,  values,selection,selectionArgs);
            Log.d("update in database:", "Table:session row id" + session.getId());
        } else {
            ContentValues values = new ContentValues();
            values.put(MyClasses.Session._ID, Integer.parseInt(session.getId()));
            values.put(MyClasses.Session.ID_SESSION_TYPE, Integer.parseInt(session.getId_session_type()));
            values.put(MyClasses.Session.ENABLED, Integer.parseInt(session.getEnabled()));
            values.put(MyClasses.Session.ID_DAY, Integer.parseInt(session.getId_day()));
            values.put(MyClasses.Session.TIME, session.getTime());
            values.put(MyClasses.Session.ID_MODULE, Integer.parseInt(session.getId_module()));
            values.put(MyClasses.Session.ID_CLASSROOM, Integer.parseInt(session.getId_classroom()));
            values.put(MyClasses.Session.ID_TEACHER, Integer.parseInt(session.getId_teacher()));
            values.put(MyClasses.Session.ID_GRP, Integer.parseInt(session.getId_groupe()));
            values.put(MyClasses.Session.IS_SEMESTER_1, Integer.parseInt(session.getSemester_1()));
            db.insert(MyClasses.Session.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:sessio, row id" + session.getId());
        }
    }

    void updateAmsgSendTo(SQLiteDatabase db,MyClasses.MsgSentTo msgSentTo){
        String selection = MyClasses.MsgSentTo.ID + "=?";
        String[] selectionArgs = {msgSentTo.getId()};
        Cursor cursor = db.query(MyClasses.MsgSentTo.TABLE_NAME, null,
                selection, selectionArgs, null, null, null);
        if (!cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(MyClasses.MsgSentTo.ID, Integer.parseInt(msgSentTo.getId()));
            values.put(MyClasses.MsgSentTo.SEEN, Integer.parseInt(msgSentTo.getSeen()));
            values.put(MyClasses.MsgSentTo.USER_ID, Integer.parseInt(msgSentTo.getId_user()));
            values.put(MyClasses.MsgSentTo.MSG_ID, Integer.parseInt(msgSentTo.getId_msg()));
            db.update(MyClasses.MsgSentTo.TABLE_NAME,values,selection,selectionArgs);
            Log.d("update in database:", "Table:msgSendTo row id" + msgSentTo.getId());
        } else {
            ContentValues values = new ContentValues();
            values.put(MyClasses.MsgSentTo.ID, Integer.parseInt(msgSentTo.getId()));
            values.put(MyClasses.MsgSentTo.SEEN, Integer.parseInt(msgSentTo.getSeen()));
            values.put(MyClasses.MsgSentTo.USER_ID, Integer.parseInt(msgSentTo.getId_user()));
            values.put(MyClasses.MsgSentTo.MSG_ID, Integer.parseInt(msgSentTo.getId_msg()));
            db.insert(MyClasses.MsgSentTo.TABLE_NAME, null, values);
            Log.d("insert in database:", "Table:sessio, row id" + msgSentTo.getId());
        }
    }





}

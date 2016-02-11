package com.example.msdhainizam.loginschoolapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by IGWMobileTeam on 12/01/2016.
 */
public class DBAnnouncement {

    private AnnouncementHelper mHelper;
    private SQLiteDatabase mDatabase;

    public DBAnnouncement(Context context) {

        mHelper = new AnnouncementHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void insertAnnouncement(ArrayList<Data> listAnnouncement, boolean clearPrevious) {
        if(clearPrevious) {
            deleteAnnouncement();
        }

        //create a sql
        String sql = "INSERT INTO " + AnnouncementHelper.TABLE_ANNOUNCEMENT +
                " VALUES (?,?,?,?,?);";
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();

        for(int i=0; i<listAnnouncement.size(); i++) {
            Data currentAnnouncement = listAnnouncement.get(i);
            statement.clearBindings();

            statement.bindString(2, currentAnnouncement.getTitle());
            statement.bindString(3, currentAnnouncement.getContent());
            statement.bindString(4, currentAnnouncement.getSchoolName());
            statement.bindString(5, currentAnnouncement.getSchoolImage());

            statement.execute();
        }

        Log.d("Inserting entries: ", String.valueOf(listAnnouncement.size()));
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();

    }

    public ArrayList<Data> readAnnouncement() {
        ArrayList<Data> listAnnouncement = new ArrayList<>();

        String[] columns = {AnnouncementHelper.COLUMN_ID,
                AnnouncementHelper.COLUMN_TITLE,
                AnnouncementHelper.COLUMN_CONTENT,
                AnnouncementHelper.COLUMN_SCHOOLNAME,
                AnnouncementHelper.COLUMN_URLIMAGE
        };

        Cursor cursor = mDatabase.query(AnnouncementHelper.TABLE_ANNOUNCEMENT, columns,
                null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {

            Log.d("Loading entries: ", String.valueOf(cursor.getCount()));

            do {
                //create a new movie object and retrieve the data from the cursor to be stored in this movie object
                Data announcementData = new Data();

                announcementData.setTitle(cursor.getString(cursor.getColumnIndex(
                        AnnouncementHelper.COLUMN_TITLE)));
                announcementData.setContent(cursor.getString(cursor.getColumnIndex(
                        AnnouncementHelper.COLUMN_CONTENT)));
                announcementData.setSchoolName(cursor.getString(cursor.getColumnIndex(
                        AnnouncementHelper.COLUMN_SCHOOLNAME)));
                announcementData.setSchoolImage(cursor.getString(cursor.getColumnIndex(
                        AnnouncementHelper.COLUMN_URLIMAGE)));

                listAnnouncement.add(announcementData);

            } while (cursor.moveToNext());
        }

        return listAnnouncement;
    }

    public void deleteAnnouncement() {
        mDatabase.delete(AnnouncementHelper.TABLE_ANNOUNCEMENT, null, null);
    }

    private static class AnnouncementHelper extends SQLiteOpenHelper {

        public static final String TABLE_ANNOUNCEMENT = "announcement";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_SCHOOLNAME = "school_name";
        public static final String COLUMN_URLIMAGE = "url_image";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_ANNOUNCEMENT + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " LONG," +
                COLUMN_CONTENT + " TEXT," +
                COLUMN_SCHOOLNAME + " TEXT," +
                COLUMN_URLIMAGE + " TEXT" +
                ");";

        private static  final String DB_NAME = "personDB";
        private static  final int DB_VERSION = 1;
        private Context mContext;

        public AnnouncementHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            }catch (SQLiteException e) {
                Log.d("Database Creation: ", e.toString());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL("DROP TABLE " + TABLE_ANNOUNCEMENT + " IF EXISTS;");
                onCreate(db);
            } catch (SQLiteException e) {
                Log.d("Database Drop: ", e.toString());
            }
        }
    }
}


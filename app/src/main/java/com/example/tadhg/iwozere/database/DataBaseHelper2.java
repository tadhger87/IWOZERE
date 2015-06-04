package com.example.tadhg.iwozere.database;

/**
 *DataBaseHelper2.java
 *Rev 1
 *Date e.g. 03/04/2015
 *@reference http://androidopentutorials.com/android-sqlite-example/
 *@author Tadhg Ó Cuirrn, x14109824
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.example.tadhg.iwozere.models.Message;

public class DataBaseHelper2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "messagedb";
    private static final int DATABASE_VERSION = 1;


    public static final String MESSAGE_TABLE = "message";
  //  public static final String LATLNG_ITEM_TABLE = "latlng_item";

    public static final String _ID = "id";
    public static final String MESSAGE_COLUMN = "message";
    public static final String TIMESTAMP = "timestamp";
    public static final String LAT = "latitude";
    public static final String LNG = "longitude";
   // public static final String LATLNG_ITEM_ID = "latlng_item_id";

    public static final String CREATE_MESSAGE_TABLE = "CREATE TABLE "
            + MESSAGE_TABLE + "(" + _ID + " INTEGER PRIMARY KEY, "
            + MESSAGE_COLUMN + " TEXT, " + TIMESTAMP + " TEXT, " + LAT + " DOUBLE, " +
            LNG + " DOUBLE " + ")";




    //String messageQuery =  "SELECT * FROM MESSAGE_TABLE WHERE LAT = "
    private static DataBaseHelper2 instance;

    public static synchronized DataBaseHelper2 getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper2(context);
        return instance;
    }

  /*  public void createRow(String code, String name) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("code", MESSAGE_COLUMN);
        initialValues.put("name", LAT);
        initialValues.put("name", LNG);
        .insert(MESSAGE_TABLE, null, initialValues);
    }*/

    public void createRow(Message message) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DataBaseHelper2.MESSAGE_COLUMN,"code");
        initialValues.put(DataBaseHelper2.LAT, 53.348647);
        initialValues.put(DataBaseHelper2.LNG, -6.257222);
       // database.insert(MESSAGE_TABLE, null, initialValues);
    }

    public DataBaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MESSAGE_TABLE);
       //  db.execSQL(CREATE_LATLNG_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //  Log.w(DEBUG_TAG, "Upgrading database. Existing contents will be lost. ["
       //         + oldVersion + "]->[" + newVersion + "]");
        db.execSQL("DROP TABLE IF EXISTS " + MESSAGE_TABLE);
        onCreate(db);
    }

    public Cursor query(String tableName, String orderedBy) {
        String[] projection = { _ID, MESSAGE_COLUMN, TIMESTAMP, LAT, LNG};
        return getReadableDatabase().query(tableName, projection, null, null, null, null, orderedBy);
    }

  /*  @Override
    public synchronized Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }*/
}


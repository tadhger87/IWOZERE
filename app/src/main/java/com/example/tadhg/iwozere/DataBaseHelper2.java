package com.example.tadhg.iwozere;

/**
 * Created by Tadhg on 03/04/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DataBaseHelper2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "messagedb";
    private static final int DATABASE_VERSION = 1;



    public static final String MESSAGE_TABLE = "message";

    public static final String ID_COLUMN = "id";
    public static final String MESSAGE_COLUMN = "message";
    public static final String TIMESTAMP = "timestamp";
    public static final String LAT = "latitude";
    public static final String LNG = "longitude";

    public static final String CREATE_MESSAGE_TABLE = "CREATE TABLE "
            + MESSAGE_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY, "
            + MESSAGE_COLUMN + " TEXT, " + TIMESTAMP + " TEXT, " + LAT + " DOUBLE, " + LNG + " DOUBLE" + ")";


    //String messageQuery =  "SELECT * FROM MESSAGE_TABLE WHERE LAT = "
    private static DataBaseHelper2 instance;

    public static synchronized DataBaseHelper2 getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper2(context);
        return instance;
    }

    private DataBaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}


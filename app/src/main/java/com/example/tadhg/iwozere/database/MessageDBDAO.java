package com.example.tadhg.iwozere.database;

/**
 *MessageDBDAO.java
 *Rev 1
 *Date e.g. 01/04/2015
 *@reference http://androidopentutorials.com/android-sqlite-example/EmployeeDBDAO.java
 *@author Tadhg Ó Cuirrn, x14109824
 */
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.tadhg.iwozere.database.DataBaseHelper2;

public class MessageDBDAO {

    protected SQLiteDatabase database;
    private DataBaseHelper2 dbHelper;
    private Context mContext;

    public MessageDBDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper2.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper2.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        database = null;
    }
}

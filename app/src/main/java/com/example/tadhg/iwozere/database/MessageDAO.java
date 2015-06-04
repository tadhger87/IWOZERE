package com.example.tadhg.iwozere.database;

/**
 *RegisterActivity.java
 *Rev 1
 *Date e.g. 01/04/2015
 *@reference http://androidopentutorials.com/android-sqlite-example/EmployeeDAO.java
 *@author Tadhg Ó Cuirrn, x14109824
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.tadhg.iwozere.models.Message;

import java.util.ArrayList;

import static com.example.tadhg.iwozere.database.DataBaseHelper2.LAT;
import static com.example.tadhg.iwozere.database.DataBaseHelper2.LNG;
import static com.example.tadhg.iwozere.database.DataBaseHelper2.MESSAGE_TABLE;


public class MessageDAO extends MessageDBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper2._ID
            + " =?";


    String[] cols = {DataBaseHelper2._ID, DataBaseHelper2.MESSAGE_COLUMN, DataBaseHelper2.TIMESTAMP,
            DataBaseHelper2.LAT, DataBaseHelper2.LNG};


    public MessageDAO(Context context) {
        super(context);
    }


    public long save(Message message) {

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper2.MESSAGE_COLUMN, message.getMessage());
        values.put(DataBaseHelper2.TIMESTAMP, message.getTimestamp());
        values.put(DataBaseHelper2.LAT, message.getLat());
        values.put(DataBaseHelper2.LNG, message.getLng());
//        values.put(DataBaseHelper2.LATLNG_ITEM_ID, message.getLatlngitem().getId());

        return database.insert(MESSAGE_TABLE, null, values);
    }

    //DUMMY DATA

    /* public void loadDepartments(Message message) {
         Message message = new Message("Development");


         List<Message> messages = new ArrayList<Message>();
         messages.add(message);

         for (Message dept : messages) {
             ContentValues values = new ContentValues();
             values.put(DataBaseHelper2.MESSAGE_COLUMN, dept.getMessage());
             database.insert(DataBaseHelper2.MESSAGE_TABLE, null, values);
         }*/
    public void createRow(Message message) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DataBaseHelper2.MESSAGE_COLUMN, "code");
        initialValues.put(DataBaseHelper2.LAT, 53.348647);
        initialValues.put(DataBaseHelper2.LNG, -6.257222);
        database.insert(MESSAGE_TABLE, null, initialValues);
    }


    //USING query() method
    public ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<Message>();

        Cursor cursor = database.query(MESSAGE_TABLE,
                new String[]{DataBaseHelper2._ID,
                        DataBaseHelper2.MESSAGE_COLUMN,
                        DataBaseHelper2.TIMESTAMP,
                        DataBaseHelper2.LAT,
                        DataBaseHelper2.LNG}, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            Message message = new Message();
            message.setId(cursor.getInt(0));
            message.setMessage(cursor.getString(1));
            message.setTimestamp(cursor.getString(2));
            message.setLat(cursor.getDouble(3));
            message.setLng(cursor.getDouble(4));

            messages.add(message);
        }
        return messages;
    }


    //WHERE I SEND THE MARKERS FROM

    public ArrayList<Message> getMyMarkers() {
        ArrayList<Message> markers = new ArrayList<Message>();
        Cursor cursor = database.query(MESSAGE_TABLE, cols, null, null, null, null, null);

        cursor.moveToPosition(3);
        while (!cursor.isAfterLast()) {
            Message m = cursorToMarker(cursor);
            markers.add(m);
            cursor.moveToNext();
        }
        cursor.close();
        return markers;
    }


//WHERE I TRY TO SELECT THE LAT LNG MESSAGES

    public ArrayList<Message> getLLMessages(String lat, String lng) {
        ArrayList<Message> llmessages = new ArrayList<Message>();
        Cursor cursor = database.query(MESSAGE_TABLE,
                new String[]{DataBaseHelper2._ID,
                        DataBaseHelper2.MESSAGE_COLUMN,
                        DataBaseHelper2.TIMESTAMP,
                        DataBaseHelper2.LAT,
                        DataBaseHelper2.LNG},
                LAT + "=?" + " and " + LNG + "=?",

                new String[]{String.valueOf(lat),
                        String.valueOf(lng)},
                null, null,
                null, null);

        Message messagell = new Message();

        if (cursor != null) {

            if (!cursor.moveToFirst()) {

                return null;
            }
            messagell.setId(cursor.getInt(0));
            messagell.setMessage(cursor.getString(1));
            messagell.setTimestamp(cursor.getString(2));
            messagell.setLat(cursor.getDouble(3));
            messagell.setLng(cursor.getDouble(4));

            llmessages.add(messagell);
        } else {
            return null;
        }
        Log.d("getLLMessages(" + lat + lng + ")", messagell.toString());

        return llmessages;
    }


    private Message cursorToMarker(Cursor cursor) {
        Message m = new Message();
        m.setLat(cursor.getDouble(3));
        m.setLng(cursor.getDouble(4));

        return m;
    }


 /*   @Override
    public synchronized Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        ...
        SQLiteDatabase db = DataBaseHelper2.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }*/


    //Retrieves a single MESSAGE record with the given id
    public Message getMessage(String lat, String lng) {
        Message message = null;

        String sql = "SELECT * FROM " + DataBaseHelper2.MESSAGE_TABLE
                + " WHERE " + DataBaseHelper2.LAT + " = ?" + "and" + DataBaseHelper2.LNG + " =?";

        Cursor cursor = database.rawQuery(sql, new String[]{lat + "", lng + ""});

        if (cursor.moveToNext()) {
            message = new Message();
            message.setId(cursor.getInt(0));
            message.setMessage(cursor.getString(1));
            message.setTimestamp(cursor.getString(2));
            message.setLat(cursor.getDouble(3));
            message.setLng(cursor.getDouble(4));


        }
        return message;
    }
}

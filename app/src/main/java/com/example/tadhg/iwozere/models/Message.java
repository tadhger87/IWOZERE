package com.example.tadhg.iwozere.models;

/**
 *Message.java
 *Rev 1
 *Date e.g. 01/04/2015
 *@reference http://androidopentutorials.com/android-sqlite-example/EmpListFragment.java
 *@author Tadhg Ó Cuirrn, x14109824
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.example.tadhg.iwozere.database.LatLngItem;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Message implements Parcelable {

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);


    private int id;
    private String message;
    private String timestamp;
    private double lat;
    private double lng;

    private LatLngItem latlngitem;

    public Message() {
        super();
    }

    public Message(Parcel in) {
        super();
        this.id = in.readInt();
        this.message = in.readString();
        this.timestamp = in.readString();//new Date(in.readLong());
        this.lat = in.readDouble();
        this.lng = in.readDouble();

        this.latlngitem = in.readParcelable(LatLngItem.class.getClassLoader());
    }

    public Message(String development) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;

    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public LatLngItem getLatlngitem() {
        return latlngitem;
    }

    public void setLatlngitem(LatLngItem latlngitem) {
        this.latlngitem = latlngitem;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", message=" + message + ", timestamp="
                + timestamp + ", lat=" + lat + ", lng=" + lng + ", latlngitem="
                + latlngitem + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 1;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getMessage());
        parcel.writeString(getTimestamp());
        parcel.writeDouble(getLat());
        parcel.writeDouble(getLng());
        parcel.writeParcelable(getLatlngitem(), flags);
    }

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

}

package com.example.tadhg.iwozere.database;

/**
 *LatLngItem.java
 *Rev 1
 *Date e.g. 23/04/2015
 *@reference http://androidopentutorials.com/android-sqlite-example/EmployeeDAO.java
 *@author Tadhg Ó Cuirrn, x14109824
 */
import android.os.Parcel;
import android.os.Parcelable;

public class LatLngItem implements Parcelable {
    private int id;
    private double latitude;
    private double longitude;

    public LatLngItem() {
        super();
    }

    public LatLngItem(int id, double latitude, double longitude) {
        super();
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatLngItem(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    LatLngItem(Parcel in) {
        super();
        this.id = in.readInt();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "id:" + id + ", latitude:"
                + latitude + ", longitude:" + longitude ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeDouble(getLatitude());
        parcel.writeDouble(getLongitude());
    }

    public static final Parcelable.Creator<LatLngItem> CREATOR = new Parcelable.Creator<LatLngItem>() {
        public LatLngItem createFromParcel(Parcel in) {
            return new LatLngItem(in);
        }

        public LatLngItem[] newArray(int size) {
            return new LatLngItem[size];
        }
    };

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
        LatLngItem other = (LatLngItem) obj;
        if (id != other.id)
            return false;
        return true;
    }


}

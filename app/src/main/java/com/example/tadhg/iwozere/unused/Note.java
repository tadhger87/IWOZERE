package com.example.tadhg.iwozere.unused;

/**
 * Created by Tadhg on 20/03/2015.
 */
import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;


public class Note implements Serializable {

    public Note(String note, Date date) {// Bitmap imageC

        this.note = note;
        this.date = date;
        // this.imageC = imageC;
        /* this is the constructor */
    }


    private String note;
    private Date date;
    // private Bitmap imageC;





    public void setNote(String note) {
        this.note = note;
    }
    public String getNote() {
        return note;
    }


    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }

    // public void setImageC(Bitmap imageC){this.imageC = imageC;}
    // public Bitmap getImageC() {return imageC;}





}



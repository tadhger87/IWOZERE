package com.example.tadhg.iwozere.ui;

/**
 * Created by Tadhg on 22/05/2015.
 */
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.tadhg.iwozere.R;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class BaloonAdapter implements InfoWindowAdapter {
    LayoutInflater inflater = null;
    private TextView textViewTitle;

    public BaloonAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View v = inflater.inflate(R.layout.baloon, null);
        if (marker != null) {
            textViewTitle = (TextView) v.findViewById(R.id.title);
            textViewTitle.setText(marker.getTitle());
        }
        return (v);
    }

    @Override
    public View getInfoContents(Marker marker) {
        return (null);
    }
}

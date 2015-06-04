package com.example.tadhg.iwozere.maps;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

/**
 * Created by Tadhg on 23/04/2015.
 */
public class Geocoding {

    private class ReverseGeocodingTask extends AsyncTask<Double, Void, String> {
        Context mContext;

        public ReverseGeocodingTask(Context context){
            super();
            mContext = context;
        }
        @Override
        protected String doInBackground(Double... params) {
            Geocoder gc = new Geocoder(mContext);
            double latitude = params[0].doubleValue();
            double longitude = params[1].doubleValue();

            List<Address> addresses = null;
            String addressText="";

            try {
                addresses = gc.getFromLocation(latitude, longitude,1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(addresses != null && addresses.size() > 0 ){
                Address address = addresses.get(0);

                addressText = String.format("%s, %s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getLocality(),
                        address.getCountryName());
            }

            return addressText;
        }

        @Override
        protected void onPostExecute(String addressText) {
            // Setting address of the touched Position
            //Toast.makeText(getApplicationContext(), addressText, Toast.LENGTH_LONG).show();
        }
    }
}

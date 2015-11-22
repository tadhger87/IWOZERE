package com.example.tadhg.iwozere.maps;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tadhg.iwozere.login.SettingsActivity;
import com.example.tadhg.iwozere.models.Message;
import com.example.tadhg.iwozere.database.MessageDAO;
import com.example.tadhg.iwozere.R;

import com.example.tadhg.iwozere.models.MyItem;
import com.example.tadhg.iwozere.ui.LLTabs;
import com.example.tadhg.iwozere.database.LatLngItem;

import com.example.tadhg.iwozere.ui.Tabs;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import static com.example.tadhg.iwozere.R.drawable;
import static com.example.tadhg.iwozere.R.id;
import static com.example.tadhg.iwozere.R.layout;

/**
 * MainActivity.java
 * Rev 1
 * Date e.g. 01/04/2015
 *
 * @author Tadhg Ó Cuirrn, x14109824
 * @reference http://code.tutsplus.com/tutorials/android-sdk-working-with-google-maps-displaying-places-of-interest--mobile-16145
 */


public class MainActivity extends ActionBarActivity implements GoogleMap.OnMarkerClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    //AdapterView.OnItemClickListener,
    private static final String LOG_TAG = "ExampleApp";

    private static final String PLACES_API_BASE = "https://com.example.tadhg.iwozere.maps.googleapis.com/com.example.tadhg.iwozere.maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyBz4-4u70dsYxAibSLTqubsu5fJjICMJxI";

    LatLngItem latlngitem = null;
    AppLocationService appLocationService;
    LatLng latLng;
    MessageDAO data;
    Context context = this;
    private HashMap<Marker, Class> allMarkersMap = new HashMap<Marker, Class>();
    private GoogleMap theMap;
    private LocationManager locationManager;
    private Marker userMarker;
    private Marker messageMarker;
    private Marker testMarker;
    private Marker testMarker1;
    private Marker testMarker2;
    private Marker testMarker3;
    private int userIcon, foodIcon, drinkIcon, shopIcon, otherIcon;
    private static final LatLng ABBEY = new LatLng(53.348647, -6.257222);
    private static final LatLng EXCISE = new LatLng(53.349223, -6.243640);
    private static final LatLng BECKETT = new LatLng(53.346969, -6.241408);
    private static final LatLng SCIENCE = new LatLng(53.344343, -6.250313);

    Toolbar toolbar;


    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter() {
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // Getting view from the layout file
            View v = getLayoutInflater().inflate(R.layout.custom_info_contents, null);

            TextView tvTitle = (TextView) v.findViewById(R.id.title);
            tvTitle.setText(marker.getTitle());

            TextView address = (TextView) v.findViewById(R.id.snippet);
            address.setText(marker.getSnippet());

            return v;

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
      /*android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);*/

        userIcon = drawable.purple_point;
        foodIcon = drawable.red_point;
        otherIcon = drawable.blue_point;
        shopIcon = drawable.green_point;
        //  otherIcon = drawable.purple_point;

        appLocationService = new AppLocationService(
                MainActivity.this);

        final AutoCompleteTextView autoCompView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(autoCompView.getWindowToken(), 0);

        // autoCompView.setAdapter(new GooglePlacesAutocompleteAdapter(this, R.layout.simple_list_item_1));

        // autoCompView.setOnItemClickListener(this);


        if (theMap == null) {
            //map not instantiated yet

            theMap = ((MapFragment) getFragmentManager().findFragmentById(id.the_map)).getMap();


            if (theMap != null) {
                //ok - proceed
            }
        }
      /*  ViewTreeObserver vto;
        if (theMap.getViewTreeObserver().isAlive()) {
            theMap.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // remove the listener
                    // ! before Jelly Bean:
                    theMap.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    // ! for Jelly Bean and later:
                    //mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    // set map viewport
                    // CENTER is LatLng object with the center of the map
                    theMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CENTER, 15));
                    // ! you can query Projection object here
                    Point markerScreenPosition = theMap.getProjection().toScreenLocation(userMarker.getPosition());
                    // ! example output in my test code: (356, 483)
                    System.out.println(markerScreenPosition);
                }
            });*/
        theMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Other map types - NORMAL, TERRAIN, SATELLITE, HYBRID

        theMap.setInfoWindowAdapter(new MyInfoWindowAdapter());

        Button btn_find = (Button) findViewById(R.id.btn_find);

        View.OnClickListener findClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String location = autoCompView.getText().toString();

                if (location != null && !location.equals("")) {
                    new GeocoderTask().execute(location);
                }
            }
        };

        // Setting button click event listener for the find button
        btn_find.setOnClickListener(findClickListener);


        Location gpsLocation = appLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);

        if (gpsLocation != null) {
            double lat = gpsLocation.getLatitude();
            double lng = gpsLocation.getLongitude();

            double nlat = (roundFourDecimals(lat));
            double nlng = (roundFourDecimals(lng));

            LatLng lastLatLng = new LatLng(lat, lng);
            LatLng newLatLng = new LatLng(nlat, nlng);

            //Executing ReverseGeocodingTask to get Address
            new ReverseGeocodingTask(getBaseContext()).execute(lat, lng);



            userMarker = theMap.addMarker(new MarkerOptions()

                    .position(newLatLng)
                    .title("Welcome to IWOZERE!")
                    .snippet("Click on the blue markers to view the messages there, or click on the green marker to leave a new marker")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    ));

            allMarkersMap.put(userMarker, Tabs.class);


            userMarker.showInfoWindow();

            Projection projection = theMap.getProjection();

            LatLng markerLocation = userMarker.getPosition();

            Point screenPosition = projection.toScreenLocation(markerLocation);

            Toast.makeText(this, "Point - " + screenPosition, Toast.LENGTH_LONG).show();


            //SOME TEST MARKERS...
            testMarker = theMap.addMarker(new MarkerOptions()
                    .position(ABBEY)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            allMarkersMap.put(testMarker, LLTabs.class);

            testMarker1 = theMap.addMarker(new MarkerOptions()
                    .position(EXCISE)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            allMarkersMap.put(testMarker1, LLTabs.class);

            testMarker2 = theMap.addMarker(new MarkerOptions()
                    .position(BECKETT)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            allMarkersMap.put(testMarker2, LLTabs.class);

            testMarker3 = theMap.addMarker(new MarkerOptions()
                    .position(SCIENCE)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            allMarkersMap.put(testMarker3, LLTabs.class);


            SharedPreferences.Editor editor = getSharedPreferences("MyPref", 0).edit();
            editor.putString("latitude", "" + nlat);
            editor.putString("longitude", "" + nlng);
            editor.apply();


            theMap.animateCamera(CameraUpdateFactory.newLatLng(lastLatLng), 3000, null);


        } else {

        }


        data = new MessageDAO(context);
        try {
            data.open();

        } catch (Exception e) {
            Log.i("hello", "hello");
        }

//RECEIVE COORDINATES FROM DATABASE


        List<Message> m = data.getMyMarkers();
        for (int i = 0; i < m.size(); i++) {

            final double ssLat = m.get(i).getLat();
            final double ssLng = m.get(i).getLng();
            LatLng messageLatLng = new LatLng(ssLat, ssLng);

            messageMarker = theMap.addMarker(new MarkerOptions()
                    .position(messageLatLng)
                    .title("Message here!")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                    ));

            allMarkersMap.put(messageMarker, LLTabs.class);


        }


        data.close();


        theMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()

        {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Class cls = allMarkersMap.get(marker);

                LatLng position = marker.getPosition(); //

                SharedPreferences.Editor editor = getSharedPreferences("MyPref", 0).edit();
                editor.putString("currentlatitude", "" + position.latitude);
                editor.putString("currentlongitude", "" + position.longitude);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, cls);
                startActivity(intent);
                return true;
            }
        });

      /*  Projection projection = theMap.getProjection();

        LatLng markerLocation = userMarker.getPosition();

        Point screenPosition = projection.toScreenLocation(markerLocation);

        Toast.makeText(this, "Point - " + screenPosition, Toast.LENGTH_LONG).show();*/
    }



    public double roundFourDecimals(double d) {
        DecimalFormat fourDForm = new DecimalFormat("#.####");
        return Double.valueOf(fourDForm.format(d));
    }


    private void handleIntent(Intent intent) {
        intent.setAction(Intent.ACTION_SEARCH);
        if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
            doSearch(intent.getStringExtra(SearchManager.QUERY));
        } else if (intent.getAction().equals(Intent.ACTION_VIEW)) {
            getPlace(intent.getStringExtra(SearchManager.EXTRA_DATA_KEY));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void doSearch(String query) {
        Bundle data = new Bundle();
        data.putString("query", query);
        getSupportLoaderManager().restartLoader(0, data, this);
    }

    private void getPlace(String query) {
        Bundle data = new Bundle();
        data.putString("query", query);
        getSupportLoaderManager().restartLoader(1, data, this);
    }


    private class ReverseGeocodingTask extends AsyncTask<Double, Void, String> {
        Context mContext;

        public ReverseGeocodingTask(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected String doInBackground(Double... params) {
            Geocoder gc = new Geocoder(mContext);
            double latitude = params[0].doubleValue();
            double longitude = params[1].doubleValue();

            List<Address> addresses = null;
            String addressText = "";

            try {
                addresses = gc.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addresses != null && addresses.size() > 0) {
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
            Toast.makeText(getApplicationContext(), addressText, Toast.LENGTH_LONG).show();

        }
    }

    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if (addresses == null || addresses.size() == 0) {
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            //theMap.clear();

            // Adding Markers on Google Map for each matching address
            for (int i = 0; i < addresses.size(); i++) {

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                latLng = new LatLng(address.getLatitude(), address.getLongitude());

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());
                Marker searchMarker = theMap.addMarker(new MarkerOptions()
                        .title("Here")
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE))//.fromResource(otherIcon))
                        .position(latLng));
                if (i == 0)
                    theMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


                searchMarker.remove();

                // Locate the first location

            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        menu.findItem(R.id.action_settings).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle query) {
        CursorLoader cLoader = null;

        if (arg0 == 0)
            cLoader = new CursorLoader(getBaseContext(), PlaceProvider.SEARCH_URI, null, null, new String[]{query.getString("query")}, null);
        else if (arg0 == 1)
            cLoader = new CursorLoader(getBaseContext(), PlaceProvider.DETAILS_URI, null, null, new String[]{query.getString("query")}, null);
        return cLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0,
                               Cursor c) {

        showLocations(c);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub
    }

    private void showLocations(Cursor c) {
        MarkerOptions markerOptions = null;
        LatLng position = null;
        theMap.clear();
        while (c.moveToNext()) {
            markerOptions = new MarkerOptions();
            position = new LatLng(Double.parseDouble(c.getString(1)), Double.parseDouble(c.getString(2)));
            markerOptions.position(position);
            markerOptions.title(c.getString(0));
            theMap.addMarker(markerOptions);
        }
        if (position != null) {
            CameraUpdate cameraPosition = CameraUpdateFactory.newLatLng(position);
            theMap.animateCamera(cameraPosition);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}

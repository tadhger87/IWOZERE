package com.example.tadhg.iwozere;

/**
 *App.java
 *Rev 1
 *Date e.g. 02/05/2015
 *@reference https://www.parse.com/tutorials/anywall-android#1-overview
 *@author Tadhg Ó Cuirrn, x14109824
 */
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import java.text.ParseException;

public class App extends Application {

    public static final boolean APPDEBUG = false;

    // Debugging tag for the application
    public static final String APPTAG = "AnyWall";

    // Used to pass location from MainActivity to PostActivity
    public static final String INTENT_EXTRA_LOCATION = "location";

    // Key for saving the search distance preference
    private static final String KEY_SEARCH_DISTANCE = "searchDistance";

    private static final float DEFAULT_SEARCH_DISTANCE = 250.0f;

    private static SharedPreferences preferences;

    private static ConfigHelper configHelper;

    public App() {
    }
    @Override public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "1sJ6agDVFZ8U3vcMMzlQ2FYJcoc4KlZJAu4JOirz", "aKiGH3eGy0uWV71jazyfbzsFS0qP5NBJU2HkkQpE"); // Your Application ID and Client Key are defined elsewhere
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        preferences = getSharedPreferences("com.example.tadhg.iwozere", Context.MODE_PRIVATE);
//parse.anywall
        configHelper = new ConfigHelper();
        configHelper.fetchConfigIfNeeded();

    }

    public static float getSearchDistance() {
        return preferences.getFloat(KEY_SEARCH_DISTANCE, DEFAULT_SEARCH_DISTANCE);
    }

    public static ConfigHelper getConfigHelper() {
        return configHelper;
    }

    public static void setSearchDistance(float value) {
        preferences.edit().putFloat(KEY_SEARCH_DISTANCE, value).commit();
    }
}

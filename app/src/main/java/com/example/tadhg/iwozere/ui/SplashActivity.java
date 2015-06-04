package com.example.tadhg.iwozere.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;

import com.example.tadhg.iwozere.R;
import com.example.tadhg.iwozere.login.LoginActivity;
import com.example.tadhg.iwozere.login.SignUpActivity;

/**
 *SplashActivity.java
 *Rev 1
 *Date e.g. 01/04/2015
 *@author Tadhg Ó Cuirrn, x14109824
 */


public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

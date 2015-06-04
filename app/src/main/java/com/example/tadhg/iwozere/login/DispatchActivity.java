package com.example.tadhg.iwozere.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.tadhg.iwozere.maps.MainActivity;
import com.example.tadhg.iwozere.ui.Welcome;
import com.parse.ParseUser;

/**
 *DispatchActivity.java
 *Rev 1
 *Date e.g. 02/05/2015
 *@author Tadhg Ó Cuirrn, x14109824
 */
public class DispatchActivity extends Activity {

  public DispatchActivity() {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Check if there is current user info
    if (ParseUser.getCurrentUser() != null) {
      // Start an intent for the logged in activity
      startActivity(new Intent(this, Welcome.class));
    } else {
      // Start and intent for the logged out activity
      startActivity(new Intent(this, SignUpActivity.class));
    }
  }

}

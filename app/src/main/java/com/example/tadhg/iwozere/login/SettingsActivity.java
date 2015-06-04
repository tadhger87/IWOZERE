package com.example.tadhg.iwozere.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.tadhg.iwozere.App;
import com.example.tadhg.iwozere.R;
import com.parse.ParseUser;

import java.util.Collections;
import java.util.List;

/**
 *SettingsActivity.java
 *Rev 1
 *Date e.g. 02/05/2015
 *@reference https://www.parse.com/tutorials/anywall-android#1-overview
 *@author Tadhg Ó Cuirrn, x14109824
 */
public class SettingsActivity extends Activity {

  private List<Float> availableOptions = App.getConfigHelper().getSearchDistanceAvailableOptions();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_settings);

    float currentSearchDistance = App.getSearchDistance();
    if (!availableOptions.contains(currentSearchDistance)) {
      availableOptions.add(currentSearchDistance);
    }
    Collections.sort(availableOptions);
/*
    // The search distance choices
    RadioGroup searchDistanceRadioGroup = (RadioGroup) findViewById(R.id.searchdistance_radiogroup);

    for (int index = 0; index < availableOptions.size(); index++) {
      float searchDistance = availableOptions.get(index);

      RadioButton button = new RadioButton(this);
      button.setId(index);
      button.setText(getString(R.string.settings_distance_format, (int)searchDistance));
      searchDistanceRadioGroup.addView(button, index);

      if (currentSearchDistance == searchDistance) {
        searchDistanceRadioGroup.check(index);
      }
    }

    // Set up the selection handler to save the selection to the application
    searchDistanceRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        App.setSearchDistance(availableOptions.get(checkedId));
      }
    });*/

    // Set up the log out button click handler
    Button logoutButton = (Button) findViewById(R.id.logout_button);
    logoutButton.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        // Call the Parse log out method
        ParseUser.logOut();
        // Start and intent for the dispatch activity
        Intent intent = new Intent(SettingsActivity.this, DispatchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    });
  }
}

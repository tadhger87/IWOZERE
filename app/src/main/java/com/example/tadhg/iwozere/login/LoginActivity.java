package com.example.tadhg.iwozere.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tadhg.iwozere.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
/**
 *LoginActivity.java
 *Rev 1
 *Date e.g. 21/05/2015
 *@reference https://www.parse.com/tutorials/anywall-android#1-overview
 *@author Tadhg Ó Cuirrn, x14109824
 */
public class LoginActivity extends Activity {
  // UI references.
  private EditText usernameEditText;
  private EditText passwordEditText;
    private Button btnLinkToRegister;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//      Parse.enableLocalDatastore(this);
    //  Parse.initialize(this, "1sJ6agDVFZ8U3vcMMzlQ2FYJcoc4KlZJAu4JOirz", "aKiGH3eGy0uWV71jazyfbzsFS0qP5NBJU2HkkQpE");
    setContentView(R.layout.activity_login);

    // Set up the login form.
      btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
    usernameEditText = (EditText) findViewById(R.id.username);
    passwordEditText = (EditText) findViewById(R.id.password);
    passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == R.id.edittext_action_login ||
            actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
          login();
          return true;
        }
        return false;
      }
    });

    // Set up the submit button click handler
      // Set up the submit button click handler
      Button actionButton = (Button) findViewById(R.id.action_button);
      actionButton.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
              login();
          }
      });

      // Link to Register Screen
      btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

          public void onClick(View view) {
              Intent i = new Intent(getApplicationContext(),
                      SignUpActivity.class);
              startActivity(i);
              finish();
          }
      });
  }

  private void login() {
    String username = usernameEditText.getText().toString().trim();
    String password = passwordEditText.getText().toString().trim();

    // Validate the log in data
    boolean validationError = false;
    StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
    if (username.length() == 0) {
      validationError = true;
      validationErrorMessage.append(getString(R.string.error_blank_username));
    }
    if (password.length() == 0) {
      if (validationError) {
        validationErrorMessage.append(getString(R.string.error_join));
      }
      validationError = true;
      validationErrorMessage.append(getString(R.string.error_blank_password));
    }
    validationErrorMessage.append(getString(R.string.error_end));

    // If there is a validation error, display the error
    if (validationError) {
      Toast.makeText(LoginActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
          .show();
      return;
    }

    // Set up a progress dialog
    final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
    dialog.setMessage(getString(R.string.progress_login));
    dialog.show();
    // Call the Parse login method
    ParseUser.logInInBackground(username, password, new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
        dialog.dismiss();
        if (e != null) {
          // Show the error message
          Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        } else {
          // Start an intent for the dispatch activity
          Intent intent = new Intent(LoginActivity.this, DispatchActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        }
      }
    });


  }

}

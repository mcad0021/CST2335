package com.example.tdog1.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

    protected static final String ACTIVITY_NAME = "LoginActivity";
    private static final String MY_PREF = "MY_PREF";
    private static final String EMAIL_KEY = "EMAIL_KEY";

    private SharedPreferences sharedPref;

    private Button loginButton;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);
        emailEditText = findViewById(R.id.email);

        sharedPref = getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
        String defaultEmail = sharedPref.getString(EMAIL_KEY, "email@domain.com");
        emailEditText.setText(defaultEmail);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String result = emailEditText.getText().toString();
                final SharedPreferences.Editor edit = sharedPref.edit();
                edit.putString(EMAIL_KEY, result);
                edit.commit();
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}

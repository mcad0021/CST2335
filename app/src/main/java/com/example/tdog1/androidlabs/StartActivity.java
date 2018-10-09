package com.example.tdog1.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends Activity {

    protected static final String ACTIVITY_NAME = "StartActivity";

    private Button listItemsActivityStart;
    private Button startChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startChat = findViewById(R.id.startchat_button);
        startChat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent intent = new Intent(StartActivity.this, ChatWindow.class);
                startActivityForResult(intent, 50);
            }
        });
        
        listItemsActivityStart = findViewById(R.id.startNextActivity);
        listItemsActivityStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 50);
            }
        });
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent data){
       if(requestCode == 50 && responseCode == Activity.RESULT_OK){
           Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
           String messagePassed = data.getStringExtra("Response");
           CharSequence text = messagePassed;// "Switch is Off"
           int duration = Toast.LENGTH_SHORT; //= Toast.LENGTH_LONG if Off

           Toast toast = Toast.makeText(StartActivity.this , text, duration); //this is the ListActivity
           toast.show(); //display your message box

       }
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

package com.example.tdog1.androidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {
    private ListView listView;
    private EditText sendMessage;
    private Button sendButton;
    private ArrayList<String> list;
    private SQLiteDatabase db;

    protected static final String ACTIVITY_NAME = "ChatWindow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listView = findViewById(R.id.list_view);

        sendMessage = findViewById(R.id.send_message);
        sendButton = findViewById(R.id.send_button);
        list = new ArrayList<>();
        //in this case, “this” is the ChatWindow, which is-A Context object
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);
        ChatDatabaseHelper databaseHelper = new ChatDatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();
        /*Cursor cursor = db.rawQuery("SELECT " + ChatDatabaseHelper.KEY_MESSAGE + "," + ChatDatabaseHelper.KEY_ID + " FROM "
                + ChatDatabaseHelper.TABLE_NAME, null);*/

        Cursor cursor = db.query(ChatDatabaseHelper.TABLE_NAME, null, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(
                    cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }
        Log.i(ACTIVITY_NAME, "Cursor's  column count =" + cursor.getColumnCount());

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, "Cursor's Column Name =" + cursor.getColumnName(i));
        }


        final ContentValues values = new ContentValues();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount() & getView()
                list.add(sendMessage.getText().toString());
                values.put(ChatDatabaseHelper.KEY_MESSAGE, sendMessage.getText().toString());
                db.insert(ChatDatabaseHelper.TABLE_NAME, null, values);
                sendMessage.setText("");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
        db.close();
    }


    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return list.size();
        }

        public String getItem(int position) {
            return list.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position % 2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;
        }

        public long getItemId(int position) {
            return position;
        }

    }
}



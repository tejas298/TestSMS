package com.example.root.testsms;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 7/5/17.
 */

public class Message_Layout extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;
    List<SMSAdapter> list;
    Button addButton,deleteButton;
    SMSAdapter adapter;
    SQLHelperClass helper;
    String display_time;

    public final String MyPrefernces = "myprefer";
    public final String Add = "add";
    public final String Delete = "delete";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_layout);

        sharedPreferences = getApplicationContext().getSharedPreferences(MyPrefernces,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        list = new ArrayList<>();
        textView = (TextView) findViewById(R.id.messages);

        addButton = (Button) findViewById(R.id.add_button);
        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setEnabled(false);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new SQLHelperClass(getApplicationContext());

        Uri uriSms = Uri.parse("content://sms/inbox");

        Cursor cursor = getContentResolver().query(uriSms,null,null,null,null);


        //list = message_list.getUniqueList();

        //Toast.makeText(this, list.get(0).getAddress(), Toast.LENGTH_SHORT).show();
        cursor.moveToPosition(getIntent().getIntExtra("main_message",0));
        textView.setText(cursor.getString(12));

        //----------------------------------

        String body = cursor.getString(12);

        StringBuffer buff = new StringBuffer();
        buff.append(body);
        String chindi = buff.substring(0,30);

        String piddi = chindi.replaceAll("\\s+"," ");


        //-----------------------------------------------------




        String date = cursor.getString(4);
        Long timestamp = Long.valueOf(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);

        Date finaldate = calendar.getTime();
        String smsdate = finaldate.toString();

        display_time = smsdate.substring(4,10);

        adapter = new SMSAdapter(cursor.getInt(0),cursor.getString(2),piddi,display_time);


        //------------------------------------------------

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Message_Layout.this, "ADD THIS TO BACKUP", Toast.LENGTH_SHORT).show();
                deleteButton.setEnabled(true);
                addButton.setEnabled(false);


                helper.addRecord(adapter);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Message_Layout.this, "DELETE FROM BACKUP", Toast.LENGTH_SHORT).show();
                deleteButton.setEnabled(false);
                addButton.setEnabled(true);
            }
        });
    }
}

package com.example.root.testsms;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.root.testsms.settings.SettingActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@TargetApi(Build.VERSION_CODES.KITKAT)
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MainActivity extends AppCompatActivity {

    public int star=0;

    private List<SMSAdapter> smsAdapterList = new ArrayList<>();

    private List<SMSAdapter> aaplaAdapter = new ArrayList<>();
    private RecyclerView recyclerView;
    private SingleSMSAdapter adapter;

    public String display_time;
    public String one_message = "";
    Context context;
    ImageView kk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.gi);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Tejas");
        toolbar.setTitleTextColor(Color.WHITE);

kk = (ImageView) findViewById(R.id.homeUp);
        kk.setVisibility(View.GONE);

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new SingleSMSAdapter(smsAdapterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        fetchInbox();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recyclerView,new ClickListener(){

            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(view.getContext(),Message_Layout.class);

                star = position;
                intent.putExtra("main_message",star);

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        //ListView view = (ListView) findViewById(R.id.listView);
        SQLHelperClass aapla_class = new SQLHelperClass(this);


        //SQLiteDatabase db = aapla_class.getWritableDatabase();
        //int version  = db.getVersion();

        //Toast.makeText(context, "db version "+version, Toast.LENGTH_SHORT).show();
        //aapla_class.onUpgrade(db,1,2);
        //aapla_class.onCreate(db);


        /*if(fetchInbox()!=null) {
            //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, fetchInbox());
            //view.setAdapter(adapter);



            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    StringBuffer ip = (StringBuffer) parent.getAdapter().getItem(position);
                    int start = ip.indexOf("null");

                    String date = ip.substring(start + 5, start + 18);

                    Long timestamp = Long.valueOf(date);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(timestamp);

                    Date finaldate = calendar.getTime();
                    String smsdate = finaldate.toString();
                    //Toast.makeText(context, smsdate, Toast.LENGTH_SHORT).show();

                }
            });

        }*/

        }


    public void fetchInbox()
    {
        SMSAdapter smsAdapter ;
        int counter = 0;

        //ArrayList sms = new ArrayList();

        Uri uriSms = Uri.parse("content://sms/inbox");

        Cursor cursor = getContentResolver().query(uriSms,null,null,null,null);


        cursor.moveToFirst();
        do{
            /*
            String address = cursor.getString(1);
            String body = cursor.getString(3);
            String date = cursor.getString(2);

            System.out.println("======&gt; Mobile number =&gt; "+address);
            System.out.println("=====&gt; SMS Text =&gt; "+body);

           */

            //StringBuffer op = new StringBuffer();


            //--------------------body part---------------------

                String body = cursor.getString(12);
            one_message = cursor.getString(12);
            StringBuffer buff = new StringBuffer();
            buff.append(body);
            String chindi = buff.substring(0,30);

                String piddi = chindi.replaceAll("\\s+"," ");


            //-------------------time part-------------------------


            String date = cursor.getString(4);
            Long timestamp = Long.valueOf(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);

            Date finaldate = calendar.getTime();
            String smsdate = finaldate.toString();

            display_time = smsdate.substring(4,10);
            //Toast.makeText(context, smsdate, Toast.LENGTH_SHORT).show();

                smsAdapter = new SMSAdapter(cursor.getInt(0),cursor.getString(2),piddi+" ....",display_time);

                smsAdapterList.add(smsAdapter);

            smsAdapter = new SMSAdapter(cursor.getInt(0),cursor.getString(2),cursor.getString(12),display_time);

            aaplaAdapter.add(smsAdapter);



        }
        while  (cursor.moveToNext());
       //message_list.setUniqueList(aaplaAdapter);

        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.backup:
                Intent intent = new Intent(getApplicationContext(),BackupActivity.class);
                startActivity(intent);
                return true;

            case R.id.setting:
                //Toast.makeText(context, "Kasli setting pahige.", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent1);
                return true;

            case R.id.help:
                Toast.makeText(context, "We don't provide any kind of help.", Toast.LENGTH_SHORT).show();
                return true;



        }
        return super.onOptionsItemSelected(item);
    }


}

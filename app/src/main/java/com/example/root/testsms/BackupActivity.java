package com.example.root.testsms;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/5/17.
 */

public class BackupActivity extends AppCompatActivity {

    List<SMSAdapter> adapterList;
    SQLHelperClass helperClass;
    Bundle bundle;

/*
    RecyclerView recyclerView;
    SingleSMSAdapter adapter;
    SMSAdapter smsAdapter;
    List<SMSAdapter> adapterList;
    SQLHelperClass helperClass;

  */
int co = R.color.colorTejas;
    int po = R.color.colorPrimary;
    static boolean toggleListener = false;
    View view;
    ListView listView;
    ArrayAdapter<String> adapter;
    TejasAdapter tejasAdapter;
    List<String> addresses = new ArrayList<>();
    List<String> bodies = new ArrayList<>();
    List<String> dates = new ArrayList<>();
    MultiWidow widow ;
    ActionMode mActionMode;
    Toolbar toolbar;
    Menu menu = null;
    ImageView img;
    TextView tit;

    String[] tejas = new String[]{"tejas","harshad","dsjd","dsghdgshd","sjdsj"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backup_activity);

bundle = savedInstanceState;
        toolbar = (Toolbar) findViewById(R.id.ji);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);

        img = (ImageView) findViewById(R.id.homeUp);
        img.setVisibility(View.GONE);
        img.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        img.setX(0);
        img.setY(0);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tit = (TextView) findViewById(R.id.titile_app);

        listView = (ListView) findViewById(R.id.list_iew);
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tejas);



        helperClass = new SQLHelperClass(getApplicationContext());

        adapterList  = helperClass.getAllRecords();


        for(int i = 0;i<adapterList.size();i++){
            String head = adapterList.get(i).getAddress();
            String center = adapterList.get(i).getBody();
            String right = adapterList.get(i).getDate();

            addresses.add(head);
            bodies.add(center);
            dates.add(right);
        }

        tejasAdapter = new TejasAdapter(this,addresses,bodies,dates);
        listView.setAdapter(tejasAdapter);


    /*    listView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toggleListener++;
                Toast.makeText(getApplicationContext(), ""+toggleListener, Toast.LENGTH_SHORT).show();



                //  toggleListener = widow.getToggle();
                // Toast.makeText(getApplicationContext(), ""+toggleListener, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
*/


    listView.setOnItemLongClickListener(new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            toggleListener = true;
            setToggle(true);

            MenuInflater inflater = getMenuInflater();
            try{
                getSupportActionBar().setTitle("");
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                toolbar.setBackgroundColor(Color.GRAY);
                img.setVisibility(View.VISIBLE);
            }catch (NullPointerException e){
                Toast.makeText(BackupActivity.this, ""+e.getCause(), Toast.LENGTH_SHORT).show();
            }

            inflater.inflate(R.menu.ajun_ek_menu,menu);



            return true;
        }
    });




            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onClickEventApla(toggleListener,position,view,parent);
                }
            });


    /*
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

        widow = new MultiWidow(toggleListener,this);
        listView.setMultiChoiceModeListener(widow);

        int id = AbsListView.generateViewId();
        //Toast.makeText(this, " "+id, Toast.LENGTH_SHORT).show();


*/


       /* if(toggleListener == 1)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getAdapter().getItem(position);
                view.setBackgroundColor(Color.GRAY);

            }
        });

*/
    }
    public void setToggle(boolean b){
toggleListener = b;
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        if(toggleListener){
            goBack();
            //onCreate(this.bundle);
            onClickEventApla();
        }
        else{
            super.onBackPressed();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toggleListener = false;
    }
int p = 0;
    public void onClickEventApla(boolean value, int position, View vie, ViewGroup group){
        toggleListener = value;
        int pos = position;
        if(toggleListener){
            //listView.setBackgroundColor(Color.YELLOW);
            View v = listView.getChildAt(position);
            v.setBackgroundColor(Color.GREEN);
            p++;
            //getSupportActionBar().setTitle(p+ " Selected");
            tit.setText(p+ " Selected");
            //img.setVisibility(View.GONE);


        }
    }

    public void onClickEventApla(){
        Adapter adap = listView.getAdapter();
        int count = adap.getCount();
        for(int i = 0; i<count;i++){
            listView.getChildAt(i).setBackgroundColor(Color.parseColor("#f9f9f9"));
        }

    }

    public void goBack(){

        toggleListener  = false;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tit.setText("");
        img.setVisibility(View.GONE);
        getSupportActionBar().setTitle("Backup Messages");
        toolbar.getMenu().clear();
        toolbar.setBackgroundColor(Color.parseColor("#3F51B5"));
        MenuInflater inflat = getMenuInflater();
        inflat.inflate(R.menu.empty_menu,menu);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.delete_all:
                SQLHelperClass helper = new SQLHelperClass(this);
                helper.deleteAll();



                List<SMSAdapter> list = helper.getAllRecords();
                List<String> addre = new ArrayList<>();
                List<String> bod = new ArrayList<>();
                List<String> dat = new ArrayList<>();

                for(int i = 0;i<list.size();i++){
                    String head = list.get(i).getAddress();
                    String center = list.get(i).getBody();
                    String right = list.get(i).getDate();

                    addre.add(head);
                    bod.add(center);
                    dat.add(right);
                }
                TejasAdapter tejas = new TejasAdapter(this,addre,bod,dat);
                listView.setAdapter(tejas);
                img.setVisibility(View.VISIBLE);
                tit.setText("");

                break;
            case R.id.selectAll:
                break;
            case R.id.back:
                goBack();
                onClickEventApla();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    /*
        recyclerView = (RecyclerView) findViewById(R.id.backup_recycler);
        helperClass = new SQLHelperClass(getApplicationContext());

        adapterList  = helperClass.getAllRecords();
        adapter = new SingleSMSAdapter(adapterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recyclerView,new ClickListener(){

            @Override
            public void onClick(View view, int position) {

                smsAdapter = adapterList.get(position);
                helperClass.deleteRecord(smsAdapter);

                adapterList  = helperClass.getAllRecords();
                adapter = new SingleSMSAdapter(adapterList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onLongClick(View view, int position) {


            }
        }));

*/

/*


    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.select_multiple_item_menu, menu);
        toggleListener = 1;
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        toggleListener = 0;
    }*/
}



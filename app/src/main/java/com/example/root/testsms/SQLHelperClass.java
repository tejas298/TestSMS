package com.example.root.testsms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.LightingColorFilter;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 30/4/17.
 */

public class SQLHelperClass extends SQLiteOpenHelper {
    
   // Context con = new MainActivity();

    Context context;

    private static final String DATABASE_NAME = "myFirstDatabase.db";
    private static final String TABLE_NAME = "Messages";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_KEY = "_id";
    private static final String ADDRESS_COLOUMN = "address";
    private static final String BODY_OF_THE_MESSAGE = "body";
    private static final String INBOX_ID = "main_id";
    private static final String DATE_OF_SMS_RECEIVED = "date";

    private static final String DATABASE_CREATE = "create table "+ TABLE_NAME+" ("+TABLE_KEY+" integer " +
            "primary key autoincrement, "+ADDRESS_COLOUMN+" text not null, "+BODY_OF_THE_MESSAGE+
            " text not null, "+INBOX_ID+" int not null, "+DATE_OF_SMS_RECEIVED+" text not null);";
    public SQLHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        //Toast.makeText(, "onCreate called", Toast.LENGTH_SHORT).show();
        Log.d("CHECKING :","onCreate called ");
        //Toast.makeText(context, "onCreate called", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(db);
    }

    public void addRecord(SMSAdapter adapter){
        //Toast.makeText(context, "add Record called", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ADDRESS_COLOUMN,adapter.getAddress());
        values.put(BODY_OF_THE_MESSAGE,adapter.getBody());
        values.put(INBOX_ID,adapter.getId());
        values.put(DATE_OF_SMS_RECEIVED,adapter.getDate());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void deleteRecord(SMSAdapter adapter){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,INBOX_ID + " = ?",new String[]{String.valueOf(adapter.getId())});
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,null,null);
    }

    public SMSAdapter getOneRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ADDRESS_COLOUMN,BODY_OF_THE_MESSAGE,INBOX_ID},INBOX_ID + " = ?",new String[]{String.valueOf(id)},null,null,null);

        if(cursor != null){
            cursor.moveToFirst();

            SMSAdapter smsAdapter = new SMSAdapter(Integer.parseInt(cursor.getString(2)),cursor.getString(0),cursor.getString(1),null);

            return smsAdapter;
        }
        else {
            return null;
        }
    }

    public List<SMSAdapter> getAllRecords(){
        List<SMSAdapter> adapters = new ArrayList<>();

        String selectQuery = "select * from "+ TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.rawQuery(selectQuery,null);
        Cursor cursor = db.query(TABLE_NAME,new String[]{ADDRESS_COLOUMN,BODY_OF_THE_MESSAGE,INBOX_ID,DATE_OF_SMS_RECEIVED,TABLE_KEY},null,null,null,null,null);

        //Toast.makeText(context, cursor.getString(0), Toast.LENGTH_SHORT).show();

        if(cursor != null){
            cursor.moveToFirst();

            do{
                try {
                    SMSAdapter smsAdapter = new SMSAdapter();
                    smsAdapter.setAddress(cursor.getString(0));
                    smsAdapter.setBody(cursor.getString(1));
                    smsAdapter.setId(Integer.parseInt(cursor.getString(2)));
                    smsAdapter.setDate(cursor.getString(3));

                    adapters.add(smsAdapter);
                }
                catch (Exception e){
                    //Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }while (cursor.moveToNext());

        }
        return adapters;
    }
}


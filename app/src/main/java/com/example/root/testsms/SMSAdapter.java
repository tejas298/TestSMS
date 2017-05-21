package com.example.root.testsms;

/**
 * Created by root on 4/5/17.
 */

public class SMSAdapter {

    private String body,address,date;
    private int id;

    public SMSAdapter(){

    }

    public SMSAdapter(int id,String address,String body,String date){
        this.id = id;
        this.address = address;
        this.body = body;
        this.date = date;

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

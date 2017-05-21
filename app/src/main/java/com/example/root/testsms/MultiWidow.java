package com.example.root.testsms;

import android.app.Activity;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

/**
 * Created by root on 13/5/17.
 */

public class MultiWidow implements AbsListView.MultiChoiceModeListener {

    private int toggle = 0;
    Context context;
    View v;

    MultiWidow(int toggle, Context context){
        this.toggle = toggle+1;
        this.context = context;
        //Toast.makeText(this.context, ""+this.toggle, Toast.LENGTH_SHORT).show();


    }

    public void ekMethod(){
        LayoutInflater inflater;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
       // mode.getMenuInflater().inflate(R.menu.select_multiple_item_menu, menu);

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

    }

    public int getToggle() {
        return toggle;
    }

    public void setToggle(int toggle) {
        this.toggle = toggle + 1;
    }
}

package com.example.root.testsms;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 11/5/17.
 */

public class TejasAdapter extends ArrayAdapter {

    Context context;
    List<String> address,body,date;
    private SparseBooleanArray mSelectedItemsIds;




    public TejasAdapter(@NonNull Context context, List<String> address,List<String> body,List<String> date) {
        super(context, R.layout.list_row, R.id.address, address);

        this.context = context;
        this.address = address;
        this.body = body;
        this.date = date;
        mSelectedItemsIds = new SparseBooleanArray();


    }

    public void toggleSelection(int position)
    {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value)
    {
        if(value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();// mSelectedCount;
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_row,parent,false);

        TextView myAddress = (TextView) row.findViewById(R.id.address);
        TextView myBody = (TextView) row.findViewById(R.id.body);
        TextView myDate = (TextView) row.findViewById(R.id.date);
        CheckBox box = (CheckBox) row.findViewById(R.id.box);

        myAddress.setText(address.get(position));
        myBody.setText(body.get(position));
        myDate.setText(date.get(position));

        box.setVisibility(View.GONE);



        return row;
    }
}

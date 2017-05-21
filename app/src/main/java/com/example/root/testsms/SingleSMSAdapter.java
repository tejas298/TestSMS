package com.example.root.testsms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 4/5/17.
 */

public class SingleSMSAdapter extends RecyclerView.Adapter<SingleSMSAdapter.MyHolder> {

    private List<SMSAdapter> sms;
    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView contact,small_body,sent_date;
        public MyHolder(View itemView) {
            super(itemView);

            contact = (TextView) itemView.findViewById(R.id.address);
            small_body = (TextView) itemView.findViewById(R.id.body);
            sent_date = (TextView) itemView.findViewById(R.id.date);

        }
    }

    public SingleSMSAdapter(List<SMSAdapter> sms){
        this.sms = sms;

    }
    @Override
    public SingleSMSAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(SingleSMSAdapter.MyHolder holder, int position) {
        SMSAdapter adapter = sms.get(position);
        holder.contact.setText(adapter.getAddress());
        holder.small_body.setText(adapter.getBody());
        holder.sent_date.setText(adapter.getDate());

    }

    @Override
    public int getItemCount() {
        return sms.size();
    }


}

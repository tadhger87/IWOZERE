package com.example.tadhg.iwozere.adapters;

/**MessageListAdapter.java
        *Rev 1
        *Date e.g. 01/04/2015
        *@reference http://androidopentutorials.com/android-sqlite-example/
        *@author Tadhg Ó Cuirrn, x14109824
        */
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tadhg.iwozere.R;
import com.example.tadhg.iwozere.models.Message;


public class MessageListAdapter extends ArrayAdapter<Message> {

    private Context context;
    List<Message> messages;



    public MessageListAdapter(Context context, List<Message> messages) {
        super(context, R.layout.item, messages);
        this.context = context;
        this.messages = messages;
    }

    private class ViewHolder {
        TextView messageTxt;
        TextView timestampTxt;


    }


    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return super.getItem(getCount() - position - 1);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, null);
            holder = new ViewHolder();

            holder.messageTxt = (TextView) convertView
                    .findViewById(R.id.txt_message);
            holder.timestampTxt = (TextView) convertView
                    .findViewById(R.id.txt_timestamp);
         /*   holder.latTxt = (TextView) convertView
                    .findViewById(R.id.txt_lat);
            holder.lngTxt = (TextView) convertView
                    .findViewById(R.id.txt_lng);*/


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Message message = (Message) getItem(position);
       // holder.empIdTxt.setText(message.getId() + "");
        holder.messageTxt.setText(message.getMessage());
        holder.timestampTxt.setText(message.getTimestamp());
       // holder.latTxt.setText( message.getLat() + "");
     //   holder.lngTxt.setText(message.getLng() + "");


        return convertView;
    }


}


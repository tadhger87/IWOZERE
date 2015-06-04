package com.example.tadhg.iwozere.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tadhg.iwozere.models.Message;
import com.example.tadhg.iwozere.R;

import java.util.List;

/**llMessageListAdapter.java
 *Rev 1
 *Date e.g. 11/04/2015
 *@reference http://androidopentutorials.com/android-sqlite-example/
 *@author Tadhg Ó Cuirrn, x14109824
 */
public class llMessageListAdapter extends ArrayAdapter<Message> {

    private Context context;
    List<Message> llmessages;



    public llMessageListAdapter(Context context, List<Message> llmessages) {
        super(context, R.layout.llitem, llmessages);
        this.context = context;
        this.llmessages = llmessages;
    }

    private class ViewHolder {
        TextView messageTxt;
        TextView timestampTxt;
       // TextView latTxt;
       // TextView lngTxt;
        Button likeButton;

    }


    @Override
    public int getCount() {
        return llmessages.size();
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
            convertView = inflater.inflate(R.layout.llitem, null);
            holder = new ViewHolder();

            holder.messageTxt = (TextView) convertView
                    .findViewById(R.id.txt_ll_message);
            holder.timestampTxt = (TextView) convertView
                    .findViewById(R.id.txt_ll_timestamp);
          /*  holder.latTxt = (TextView) convertView
                    .findViewById(R.id.txt_ll_lat);
            holder.lngTxt = (TextView) convertView
                    .findViewById(R.id.txt_ll_lng);
            holder.likeButton = (Button) convertView
                    .findViewById(R.id.like_button);*/


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Message message = (Message) getItem(position);
        // holder.empIdTxt.setText(message.getId() + "");
        holder.messageTxt.setText(message.getMessage());
        holder.timestampTxt.setText(message.getTimestamp());
       // holder.latTxt.setText( message.getLat() + "");
      //  holder.lngTxt.setText(message.getLng() + "");


        return convertView;
    }


}


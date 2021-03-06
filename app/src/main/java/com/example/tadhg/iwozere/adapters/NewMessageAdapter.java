package com.example.tadhg.iwozere.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tadhg.iwozere.R;
import com.example.tadhg.iwozere.models.Message;
//import com.example.tadhg.iwozere.models.Person;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tadhg on 17/06/2015.
 */
public class NewMessageAdapter extends RecyclerView.Adapter<NewMessageAdapter.MessageViewHolder> {

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        Message myMessage;
        private Context mContext;
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        MessageViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    List<Message> myMessage;

    public NewMessageAdapter(Activity activity, List<Message> myMessage){
        this.myMessage = myMessage;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, null);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //width
                ViewGroup.LayoutParams.WRAP_CONTENT);//height
        v.setLayoutParams(lp);//override default layout params
        MessageViewHolder mvh = new MessageViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder messageViewHolder, int i) {
        messageViewHolder.personName.setText(myMessage.get(i).getMessage());
        messageViewHolder.personAge.setText(myMessage.get(i).getTimestamp());
        //personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);

    }

    @Override
    public int getItemCount() {
        return (null != myMessage ? myMessage.size() : 0);
//        return myMessage.size();
    }
}
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

import java.util.List;

/**
 * Created by Tadhg on 28/06/2015.
 */
public class NewLLMessageAdapter extends RecyclerView.Adapter<NewLLMessageAdapter.LLMessageViewHolder> {

    public static class LLMessageViewHolder extends RecyclerView.ViewHolder {

        Message myMessage;
        private Context mContext;
        CardView cv;
        TextView messageContent;
        TextView timestamp;
        ImageView personPhoto;

        LLMessageViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.ll_cv);
            messageContent = (TextView)itemView.findViewById(R.id.ll_message_content);
            timestamp = (TextView)itemView.findViewById(R.id.ll_timestamp);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    List<Message> myMessage;

    public NewLLMessageAdapter(Activity activity, List<Message> myMessage){
        this.myMessage = myMessage;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public LLMessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ll_item_card, null);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //width
                ViewGroup.LayoutParams.WRAP_CONTENT);//height
        v.setLayoutParams(lp);//override default layout params
        LLMessageViewHolder mvh = new LLMessageViewHolder(v);
        return mvh;
    }





    @Override
    public void onBindViewHolder(LLMessageViewHolder messageViewHolder, int i) {
        messageViewHolder.messageContent.setText(myMessage.get(i).getMessage());
        messageViewHolder.timestamp.setText(myMessage.get(i).getTimestamp());
        //personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);

    }

    @Override
    public int getItemCount() {
        return (null != myMessage ? myMessage.size() : 0);
//        return myMessage.size();
    }
}

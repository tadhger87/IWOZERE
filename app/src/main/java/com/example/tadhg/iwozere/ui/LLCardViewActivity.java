package com.example.tadhg.iwozere.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tadhg.iwozere.R;

/**
 * Created by Tadhg on 28/06/2015.
 */
public class LLCardViewActivity extends Activity {

    TextView messageContent;
    TextView timestamp;
    ImageView personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ll_card_test);
        messageContent = (TextView)findViewById(R.id.ll_message_content);
        timestamp = (TextView)findViewById(R.id.ll_timestamp);
        personPhoto = (ImageView)findViewById(R.id.ic_launcher);

        messageContent.setText("Emma Wilson");
        timestamp.setText("23 years old");
        personPhoto.setImageResource(R.drawable.ic_launcher);
    }
}
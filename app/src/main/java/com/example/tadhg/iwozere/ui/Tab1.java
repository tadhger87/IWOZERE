package com.example.tadhg.iwozere.ui;

/**
 * Created by Tadhg on 06/06/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tadhg.iwozere.R;
import com.example.tadhg.iwozere.adapters.MessageListAdapter;
import com.example.tadhg.iwozere.adapters.NewMessageAdapter;
import com.example.tadhg.iwozere.database.MessageDAO;
import com.example.tadhg.iwozere.models.Message;
import com.example.tadhg.iwozere.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1 extends Fragment {

    Activity activity;
    private List<Person> persons;
    ListView messageListView;
    RecyclerView recView;
    ArrayList<Message> messages;

    MessageListAdapter messageListAdapter;
    MessageDAO messageDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        messageDAO = new MessageDAO(activity);
        initializeData();




    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v =inflater.inflate(R.layout.tab_1,container,false);
        recView = (RecyclerView)v.findViewById(R.id.cardList);
        recView.setHasFixedSize(true);

        recView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recView.setItemAnimator(new DefaultItemAnimator());

        NewMessageAdapter adapter = new NewMessageAdapter(persons);
        recView.setAdapter(adapter);


        return v;
    }
/*
    private void findViewById(View view){





    }

    private void initializeAdapter(){
        NewMessageAdapter adapter = new NewMessageAdapter(messages);
        recView.setAdapter(adapter);
    }*/

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.ic_launcher));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.ic_launcher));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.ic_launcher));
    }
}

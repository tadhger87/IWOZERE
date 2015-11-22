package com.example.tadhg.iwozere.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tadhg.iwozere.R;
import com.example.tadhg.iwozere.adapters.NewMessageAdapter;
import com.example.tadhg.iwozere.database.MessageDAO;
import com.example.tadhg.iwozere.models.Message;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tadhg on 28/06/2015.
 */
public class ViewLLMessages extends Fragment {

    Activity activity;
    private List<Message> myMessage;
    private GetMessageTask task;
    ListView messageListView;
    RecyclerView recView;
    ArrayList<Message> messages;

    NewMessageAdapter messageListAdapter;
    MessageDAO messageDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        messageDAO = new MessageDAO(activity);





    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v =inflater.inflate(R.layout.view_ll_messages,container,false);
        recView = (RecyclerView)v.findViewById(R.id.cardList3);
        recView.setHasFixedSize(true);

        recView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recView.setItemAnimator(new DefaultItemAnimator());

        NewMessageAdapter adapter = new NewMessageAdapter(activity, myMessage);
        recView.setAdapter(adapter);

        task = new GetMessageTask(activity);
        task.execute((Void) null);
//        Collections.sort(messages);
        return v;
    }


    public class GetMessageTask extends AsyncTask<Void, Void, ArrayList<Message>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetMessageTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<Message> doInBackground(Void... arg0) {

            SharedPreferences prefs = getActivity().getSharedPreferences("MyPref", 0);
            String sLat = prefs.getString("currentlatitude", null);
            String sLng = prefs.getString("currentlongitude", null);
            ArrayList<Message> messageList = messageDAO.getLLMessages(sLat, sLng);

            return messageList;
        }

        @Override
        protected void onPostExecute(ArrayList<Message> messageList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                Log.d("messages", messageList.toString());
                messages = messageList;
                if (messageList != null) {
                    if (messageList.size() != 0) {
                        messageListAdapter = new NewMessageAdapter(activity,
                                messageList);
                        recView.setAdapter(messageListAdapter);
                    } else {
                        Toast.makeText(activity, "No Messages",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
    }
}


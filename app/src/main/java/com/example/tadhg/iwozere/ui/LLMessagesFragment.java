package com.example.tadhg.iwozere.ui;

/**
 *LLMessagesFragment.java
 *Rev 1
 *Date e.g. 24/04/2015
 *@reference http://androidopentutorials.com/android-sqlite-example/EmpListFragment.java
 *@author Tadhg Ó Cuirrn, x14109824
 */

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.tadhg.iwozere.database.DataBaseHelper2;
import com.example.tadhg.iwozere.database.MyDatabaseHelper;
import com.example.tadhg.iwozere.models.Message;
import com.example.tadhg.iwozere.adapters.MessageListAdapter;
import com.example.tadhg.iwozere.R;
import com.example.tadhg.iwozere.database.MessageDAO;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class LLMessagesFragment extends Fragment {

    Message message = null;
    private MessageDAO dbHelper;
    private SimpleCursorAdapter dataAdapter;

    Activity activity;
    ListView messageListView;
    ArrayList<Message> messages;

    MessageListAdapter llMessageListAdapter;
    MessageDAO messageDAO;

    private GetMessageTask task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        messageDAO = new MessageDAO(activity);

        messageDAO.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ll_messsages_fragment, parent,
                false);
        findViewsById(view);

        task = new GetMessageTask(activity);
        task.execute((Void) null);


        return view;

    }

    private void findViewsById(View view) {
        messageListView = (ListView) view.findViewById(R.id.list_message1);
    }


//WHERE I TRY TO QUERY THE TABLE TO POPULATE THE LIST

    public class GetMessageTask extends AsyncTask<Void, Void, ArrayList<Message>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetMessageTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<Message> doInBackground(Void... arg1) {
            SharedPreferences prefs = getActivity().getSharedPreferences("MyPref", 0);
            String sLat = prefs.getString("currentlatitude", null);
            String sLng = prefs.getString("currentlongitude", null);

            ArrayList<Message> messageList = messageDAO.getLLMessages(sLat, sLng);    //CALLING THE METHOD HERE
            return messageList;
        }


        @Override
        protected void onPostExecute(ArrayList<Message> messageList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                //Log.d("messages", messageList.toString());
                messages = messageList;
                if (messageList != null) {
                    if (messageList.size() != 0) {
                        llMessageListAdapter = new MessageListAdapter(getActivity(),
                                messageList);
                        messageListView.setAdapter(llMessageListAdapter);
                    } else {
                        Toast.makeText(activity, "No Messages",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
    }


    public void updateView() {
        task = new GetMessageTask(activity);
        task.execute((Void) null);
    }

}



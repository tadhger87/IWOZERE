package com.example.tadhg.iwozere.ui;

/**
 * Created by Tadhg on 06/06/2015.
 */
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tadhg.iwozere.R;
import com.example.tadhg.iwozere.database.MessageDAO;
import com.example.tadhg.iwozere.models.Message;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hp1 on 21-01-2015.
 */
public class CreateMessage extends Fragment implements View.OnClickListener {

    // UI references
    private EditText messageEtxt;
    private EditText timestampEtxt;
    private EditText latEtxt;
    private EditText lngEtxt;
    private Button submitButton;
    private Button resetButton;

    Calendar dateCalendar;
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            " HH:mm:ss     dd-MMMM-yyy", Locale.ENGLISH);

//yyyy-MM-dd


    Message message = null;
    Message timestamp = null;
    private MessageDAO messageDAO;
    private AddMessageTask task;

    public static final String ARG_ITEM_ID = "emp_add_fragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageDAO = new MessageDAO(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_message,container,false);

        SharedPreferences prefs = getActivity().getSharedPreferences("MyPref", 0);
        String sLat = prefs.getString("latitude", null);
        String sLng = prefs.getString("longitude", null);
        // Toast.makeText(this.getActivity(), "Lat - " + sLat + "\nLng - " + sLng, Toast.LENGTH_LONG).show();
        Double l1;
        Double l2;

        l1 = Double.parseDouble(sLat);
        l2 = Double.parseDouble(sLng);

        findViewsById(v);

        setListeners();


        if (savedInstanceState != null) {
            dateCalendar = Calendar.getInstance();
            if (savedInstanceState.getLong("dateCalendar") != 0)
                dateCalendar.setTime(new Date(savedInstanceState
                        .getLong("dateCalendar")));
        }
        return v;
    }

    private void setListeners() {
        messageEtxt.setOnClickListener(this);

        timestampEtxt.setOnClickListener(this);
        latEtxt.setOnClickListener(this);
        lngEtxt.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    protected void resetAllFields() {

        messageEtxt.setText("");


    }

    private void setMessage() {
        message = new Message();


        if ("".equals(messageEtxt.getText().toString())) {
            Toast toast = Toast.makeText(this.getActivity(), "You have to input some text!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            message.setMessage(messageEtxt.getText().toString());

            message.setTimestamp(timestampEtxt.getText().toString());//)
            message.setLat(Double.parseDouble(latEtxt.getText()
                    .toString()));
            //(latEtxt.getText().toString());
            message.setLng(Double.parseDouble(lngEtxt.getText()
                    .toString()));
            //(lngEtxt.getText().toString());
        }


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (dateCalendar != null)
            outState.putLong("dateCalendar", dateCalendar.getTime().getTime());
    }

    private void findViewsById(View rootView) {

        SharedPreferences prefs = getActivity().getSharedPreferences("MyPref", 0);
        String sLat = prefs.getString("latitude", null);
        String sLng = prefs.getString("longitude", null);
        dateCalendar = Calendar.getInstance();

        Calendar newCalendar = Calendar.getInstance();
        String timestamp = formatter.format(dateCalendar.getTime());
        // Toast.makeText(this.getActivity(), "Time is - " + timestamp, Toast.LENGTH_LONG).show();


        messageEtxt = (EditText) rootView.findViewById(R.id.etxt_message);


        timestampEtxt = (EditText) rootView.findViewById(R.id.etxt_timestamp);
        timestampEtxt.setText(timestamp);
        timestampEtxt.setEnabled(false);


        String ssLat = Double.toString(Double.parseDouble(sLat));
        latEtxt = (EditText) rootView.findViewById(R.id.etxt_lat);
        latEtxt.setText(ssLat); //, TextView.BufferType.EDITABLE"Google is your friend."
        latEtxt.setEnabled(false);

        String ssLng = Double.toString(Double.parseDouble(sLng));
        lngEtxt = (EditText) rootView.findViewById(R.id.etxt_lng);
        lngEtxt.setText(sLng);
        lngEtxt.setEnabled(false);

        submitButton = (Button) rootView.findViewById(R.id.button_submit);
        resetButton = (Button) rootView.findViewById(R.id.button_reset);


    }

    @Override
    public void onClick(View view) {


        if (view == submitButton) {
            setMessage();
           /* if(messageEtxt.getText().toString().isEmpty()){
                Toast.makeText(getActivity(), "Please Enter Message", Toast.LENGTH_SHORT).show();
            }*/

            task = new AddMessageTask(getActivity());
            task.execute((Void) null);
            messageEtxt.setText("");

        } else if (view == resetButton) {
            resetAllFields();
        }
    }

    public class AddMessageTask extends AsyncTask<Void, Void, Long> {

        private final WeakReference<Activity> activityWeakRef;

        public AddMessageTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected Long doInBackground(Void... arg0) {

            long result = messageDAO.save(message);
            return result;
        }

        @Override
        protected void onPostExecute(Long result) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                if (result != -1)
                    Toast.makeText(activityWeakRef.get(), "Message Saved",
                            Toast.LENGTH_LONG).show();
            }
        }
    }
}



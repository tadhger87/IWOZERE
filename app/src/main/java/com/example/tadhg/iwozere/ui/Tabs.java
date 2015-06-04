package com.example.tadhg.iwozere.ui;

/**
*Tabs.java
*Rev 1
*Date e.g. 21/05/2015
 *@author Tadhg Ó Cuirrn, x14109824
 */
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tadhg.iwozere.R;
import com.example.tadhg.iwozere.adapters.TabsPagerAdapter;

public class Tabs extends FragmentActivity  {


    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = {"Create Messages", "View Messages"};

    private MessageListFragment employeeListFragment;
    private MessageAddFragment employeeAddFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);



        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();

        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);


    }







    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    /*@Override
    public void onFinishDialog() {
        if (employeeListFragment != null) {
            employeeListFragment.updateView();
        }*/
    }









  /* @Override
    public void sendData(String message) {
        Fragment vm = getFragmentManager().findFragmentById(R.id.viewM);
        vm.getData(message);
    }*/





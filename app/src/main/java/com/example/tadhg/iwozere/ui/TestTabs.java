package com.example.tadhg.iwozere.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tadhg.iwozere.R;
import com.example.tadhg.iwozere.adapters.TestTabsPagerAdapter;
/**
 *TestTabs.java
 *Rev 1
 *Date e.g. 01/04/2015
 *@author Tadhg Ó Cuirrn, x14109824
 */

public class TestTabs extends FragmentActivity {


    private ViewPager viewPager;
    private TestTabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = {"View Messages", "Tut List"};

    private LLMessagesFragment llMessagesFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_tabs);


        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();

        mAdapter = new TestTabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
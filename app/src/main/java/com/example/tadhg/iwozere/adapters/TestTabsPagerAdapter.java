package com.example.tadhg.iwozere.adapters;

/**
 *TestTabsPagerAdapter.java
 *Rev 1
 *Date e.g. 01/05/2015
 *@author Tadhg Ó Cuirrn, x14109824
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tadhg.iwozere.ui.LLMessagesFragment;



public class TestTabsPagerAdapter extends FragmentPagerAdapter {

    public TestTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    private String tabtitles[] = new String[] { "View Messages" };
    Context context;


    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Create message fragment activity
                return new LLMessagesFragment();            //CreateMessage



        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }

}


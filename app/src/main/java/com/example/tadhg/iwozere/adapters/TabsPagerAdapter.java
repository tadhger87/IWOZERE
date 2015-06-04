package com.example.tadhg.iwozere.adapters;
/**
 *TabsPagerAdapter.java
 *Rev 1
 *Date e.g. 25/03/2015
 *@reference http://androidopentutorials.com/android-sqlite-example/
 *@author Tadhg Ó Cuirrn, x14109824
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tadhg.iwozere.ui.MessageAddFragment;
import com.example.tadhg.iwozere.ui.MessageListFragment;


public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    private String tabtitles[] = new String[] { "Create Message", "View Messages" };
    Context context;


    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Create message fragment activity
                return new MessageAddFragment();            //CreateMessage
            case 1:
                // View messages fragment activity
                return new MessageListFragment();           //ViewMessages

        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }

}

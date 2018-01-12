package com.example.fh.meinstundenplan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentMonday tabMonday = new FragmentMonday();
                return tabMonday;
            case 1:
                FragmentTuesday tabTuesday = new FragmentTuesday();
                return tabTuesday;
            case 2:
                FragmentWednesday tabWednesday = new FragmentWednesday();
                return tabWednesday;
            case 3:
                FragmentThursday tabThursday = new FragmentThursday();
                return tabThursday;
            case 4:
                FragmentFriday tabFriday = new FragmentFriday();
                return tabFriday;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
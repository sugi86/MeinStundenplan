package com.example.fh.meinstundenplan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//PagerAdapter für das Tablayout
class PagerAdapter extends FragmentStatePagerAdapter {
    private final int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FragmentMonday();
            case 1:
                return new FragmentTuesday();
            case 2:
                return new FragmentWednesday();
            case 3:
                return new FragmentThursday();
            case 4:
                return new FragmentFriday();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
package com.example.fh.meinstundenplan;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowStundenplanFragment extends Fragment {


    public ShowStundenplanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_stundenplan, container, false);


        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);


        tabLayout.addTab(tabLayout.newTab().setText(R.string.Monday));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Tuesday));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Wednesday));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Thursday));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.Friday));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        final ViewPager pager = (ViewPager) view.findViewById(R.id.pager);

        final Stundenplan activity =  (Stundenplan) getActivity();

        FragmentManager fragmentManager = getFragmentManager();
        final PagerAdapter adapter = new PagerAdapter(fragmentManager, tabLayout.getTabCount());

        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}
package com.example.fh.meinstundenplan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWednesday extends Fragment {


    ListView list;

    public FragmentWednesday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_wednesday, container, false);

        list = (ListView) view.findViewById(R.id.listView_wednesday);

        final Stundenplan activity =(Stundenplan) getActivity();
        final UsersAdapter_Tage usersAdapter = new UsersAdapter_Tage(activity, activity.Mittwoch);
        list.setAdapter(usersAdapter);

        return view;
    }
}
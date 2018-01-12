package com.example.fh.meinstundenplan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentTuesday extends Fragment {


    ListView list;

    public FragmentTuesday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tuesday, container, false);

        list = (ListView) view.findViewById(R.id.listView_tuesday);

        final Stundenplan activity =(Stundenplan) getActivity();
        final UsersAdapter usersAdapter = new UsersAdapter(activity, activity.Dienstag);
        list.setAdapter(usersAdapter);

        return view;
    }
}
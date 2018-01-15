package com.example.fh.meinstundenplan;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentTuesday extends ShowStundenplanFragment {

    public FragmentTuesday() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        ListView list = view.findViewById(R.id.listView_day);
        final Stundenplan activity = (Stundenplan) getActivity();
        final UsersAdapter_Tage usersAdapter = new UsersAdapter_Tage(activity, activity.Dienstag);
        list.setAdapter(usersAdapter);
        return view;
    }
}
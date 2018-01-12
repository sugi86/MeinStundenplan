package com.example.fh.meinstundenplan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentMonday extends Fragment {

    ListView list;

    public FragmentMonday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_monday, container, false);

        list = (ListView) view.findViewById(R.id.listView_monday);

        final Stundenplan activity =(Stundenplan) getActivity();
        final UsersAdapter_Tage usersAdapter = new UsersAdapter_Tage(activity, activity.Montag);
        list.setAdapter(usersAdapter);

        return view;
    }
}
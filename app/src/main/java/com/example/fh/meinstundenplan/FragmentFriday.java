package com.example.fh.meinstundenplan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentFriday extends Fragment {

    ListView list;

    public FragmentFriday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_friday, container, false);

        list = (ListView) view.findViewById(R.id.listView_friday);

        final Stundenplan activity =(Stundenplan) getActivity();
        final UsersAdapter_Tage usersAdapter = new UsersAdapter_Tage(activity, activity.Freitag);
        list.setAdapter(usersAdapter);

        return view;
    }
}

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
public class KatalogFragment extends Fragment {

    public KatalogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_katalog, container, false);

        ListView list = view.findViewById(R.id.listView_list);
        final Stundenplan activity = (Stundenplan) getActivity();
        final UsersAdapter usersAdapter = new UsersAdapter(activity, activity.Katalog);
        list.setAdapter(usersAdapter);
        // Inflate the layout for this fragment
        return view;
    }
}

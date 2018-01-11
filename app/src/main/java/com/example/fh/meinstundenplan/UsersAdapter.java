package com.example.fh.meinstundenplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sugi86 on 11.01.2018.
 */

public class UsersAdapter extends ArrayAdapter<Fach> {
    public UsersAdapter(Context context, ArrayList<Fach> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Fach fach = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fach, parent, false);
        }
        // Lookup view for data population
        TextView titel = (TextView) convertView.findViewById(R.id.Fach_Titel);
        TextView attribute = (TextView) convertView.findViewById(R.id.Fach_Attribut);
        // Populate the data into the template view using the data object
        titel.setText(fach.createTitle());
        attribute.setText(fach.createAttribute());
        // Return the completed view to render on screen
        return convertView;
    }
}
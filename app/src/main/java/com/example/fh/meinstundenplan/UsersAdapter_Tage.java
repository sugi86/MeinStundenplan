package com.example.fh.meinstundenplan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class UsersAdapter_Tage extends ArrayAdapter<Fach> {
    public UsersAdapter_Tage(Context context, ArrayList<Fach> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Fach fach = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fach, parent, false);
        }
        // Lookup view for data population
        TextView titel = convertView.findViewById(R.id.Fach_Titel);
        TextView attribute = convertView.findViewById(R.id.Fach_Attribut);
        // Populate the data into the template view using the data object
        assert fach != null;
        titel.setText(fach.createTitle_ohne_Tag());
        attribute.setText(fach.createAttribute());
        // Return the completed view to render on screen
        return convertView;
    }
}

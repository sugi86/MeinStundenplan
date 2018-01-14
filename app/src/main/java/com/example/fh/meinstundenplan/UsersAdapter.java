package com.example.fh.meinstundenplan;

import android.content.Context;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sugi86 on 11.01.2018.
 */

public class UsersAdapter extends ArrayAdapter<Fach> {
    private ArrayList<Fach> list = new ArrayList<Fach>();
    private Context context;



    public UsersAdapter(Context context, ArrayList<Fach> users) {
        super(context, 0, users);
        this.list = users;
        this.context = context;
    }

    public void updateArray(ArrayList<Fach> newlist){
        list.clear();
        list.addAll(newlist);
        this.notifyDataSetChanged();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Fach fach = getItem(position);

        Boolean tmp;
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fach_katalog, parent, false);
        }
        // Lookup view for data population
        TextView titel = (TextView) convertView.findViewById(R.id.Fach_Titel);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        Button button = (Button) convertView.findViewById(R.id.button_delete);


        checkBox.setTag(position);
        titel.setText(fach.createTitle());
        checkBox.setChecked(fach.isChecked());


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position =(Integer) buttonView.getTag();
                Fach fach = getItem(position);
                if (isChecked) {
                    fach.setChecked(true);
                }
                else{
                    fach.setChecked(false);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                Snackbar.make(v, "Fach erfolgreich aus dem Katalog gelöscht", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // Return the completed view to render on screen
        return convertView;
    }
}

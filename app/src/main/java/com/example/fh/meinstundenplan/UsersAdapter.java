package com.example.fh.meinstundenplan;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.Spinner;
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
        Button button_delete = (Button) convertView.findViewById(R.id.button_delete);
        Button button_edit = (Button) convertView.findViewById(R.id.button_edit);


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

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                Snackbar.make(v, "Fach erfolgreich aus dem Katalog gelöscht", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.fragment_edit);
                dialog.setTitle("Fach bearbeiten");

                Button buttonok;
                Button buttoncancel;

                final EditText InputSemester;
                final EditText InputName;
                final Spinner InputTag;
                final EditText InputBeginn;
                final EditText InputEnde;
                final EditText InputRaum;
                final EditText InputDozent;
                final EditText InputKuerzel;

                InputSemester =dialog.findViewById(R.id.input_semester);
                InputName = dialog.findViewById(R.id.input_name);
                InputTag = dialog.findViewById(R.id.input_tag);
                InputBeginn = dialog.findViewById(R.id.input_beginn);
                InputEnde = dialog.findViewById(R.id.input_ende);
                InputRaum = dialog.findViewById(R.id.input_raum);
                InputDozent = dialog.findViewById(R.id.input_dozent);
                InputKuerzel = dialog.findViewById(R.id.input_kuerzel);
                buttonok = (Button) dialog.findViewById(R.id.dialogButtonOK);
                buttoncancel = (Button) dialog.findViewById(R.id.dialogButtonCancel);


                InputSemester.setText(list.get(position).getSemester());
                InputName.setText(list.get(position).getName());
                InputBeginn.setText(list.get(position).getBeginn());
                InputEnde.setText(list.get(position).getEnde());
                InputRaum.setText(list.get(position).getRaum());
                InputDozent.setText(list.get(position).getDozent());
                InputKuerzel.setText(list.get(position).getKuerzel());
                InputTag.setSelection(Integer.parseInt(list.get(position).getId())-1);




                buttoncancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dialog.dismiss();
                        Snackbar.make(v, "Fach nicht geändert", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });



                buttonok.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String tag = String.valueOf(InputTag.getSelectedItem());
                        String id = "";
                        if(tag.equals("Montag"))
                        {
                            id = "1";
                        }
                        else if(tag.equals("Dienstag"))
                        {
                            id = "2";
                        }
                        else if(tag.equals("Mittwoch"))
                        {
                            id = "3";
                        }
                        else if(tag.equals("Donnerstag"))
                        {
                            id = "4";
                        }
                        else if(tag.equals("Freitag"))
                        {
                            id = "5";
                        }
                        list.get(position).setSemester(InputSemester.getText().toString());
                        list.get(position).setName(InputName.getText().toString());
                        list.get(position).setTag(tag);
                        list.get(position).setBeginn(InputBeginn.getText().toString());
                        list.get(position).setEnde(InputEnde.getText().toString());
                        list.get(position).setRaum(InputRaum.getText().toString());
                        list.get(position).setDozent(InputDozent.getText().toString());
                        list.get(position).setKuerzel(InputKuerzel.getText().toString());
                        list.get(position).setId(id);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Snackbar.make(v, "Fach erfolgreich geändert", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });

                dialog.show();
            }

        });
        // Return the completed view to render on screen
        return convertView;
    }
}

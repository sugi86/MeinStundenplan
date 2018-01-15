package com.example.fh.meinstundenplan;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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


class UsersAdapter extends ArrayAdapter<Fach> {
    private final Context context;
    private ArrayList<Fach> list = new ArrayList<>();


    public UsersAdapter(Context context, ArrayList<Fach> users) {
        super(context, 0, users);
        this.list = users;
        this.context = context;
    }

// --Commented out by Inspection START (15.01.2018 01:58):
//    public void updateArray(ArrayList<Fach> newlist){
//        list.clear();
//        list.addAll(newlist);
//        this.notifyDataSetChanged();
//    }
// --Commented out by Inspection STOP (15.01.2018 01:58)


    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position

        Fach fach = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_fach_katalog, parent, false);
        }
        // Lookup view for data population
        TextView titel = convertView.findViewById(R.id.Fach_Titel);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        Button button_delete = convertView.findViewById(R.id.button_delete);
        Button button_edit = convertView.findViewById(R.id.button_edit);


        checkBox.setTag(position);
        assert fach != null;
        titel.setText(fach.createTitle());
        checkBox.setChecked(fach.isChecked());


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position = (Integer) buttonView.getTag();
                Fach fach = getItem(position);
                if (isChecked) {
                    assert fach != null;
                    fach.setChecked(true);
                } else {
                    assert fach != null;
                    fach.setChecked(false);
                }
            }
        });

        button_delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                Snackbar.make(v, "Fach erfolgreich aus dem Katalog gelöscht",
                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return false;
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

                InputSemester = dialog.findViewById(R.id.input_semester);
                InputName = dialog.findViewById(R.id.input_name);
                InputTag = dialog.findViewById(R.id.input_tag);
                InputBeginn = dialog.findViewById(R.id.input_beginn);
                InputEnde = dialog.findViewById(R.id.input_ende);
                InputRaum = dialog.findViewById(R.id.input_raum);
                InputDozent = dialog.findViewById(R.id.input_dozent);
                InputKuerzel = dialog.findViewById(R.id.input_kuerzel);
                buttonok = dialog.findViewById(R.id.dialogButtonOK);
                buttoncancel = dialog.findViewById(R.id.dialogButtonCancel);


                InputSemester.setText(list.get(position).getSemester());
                InputName.setText(list.get(position).getName());
                InputBeginn.setText(list.get(position).getBeginn());
                InputEnde.setText(list.get(position).getEnde());
                InputRaum.setText(list.get(position).getRaum());
                InputDozent.setText(list.get(position).getDozent());
                InputKuerzel.setText(list.get(position).getKuerzel());
                InputTag.setSelection(Integer.parseInt(list.get(position).getId()) - 1);


                buttoncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Snackbar.make(v, "Fach nicht geändert",
                                Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                });


                buttonok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tag = String.valueOf(InputTag.getSelectedItem());
                        String id = "";
                        switch (tag) {
                            case "Montag":
                                id = "1";
                                break;
                            case "Dienstag":
                                id = "2";
                                break;
                            case "Mittwoch":
                                id = "3";
                                break;
                            case "Donnerstag":
                                id = "4";
                                break;
                            case "Freitag":
                                id = "5";
                                break;
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
                        Snackbar.make(v, "Fach erfolgreich geändert",
                                Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                });

                dialog.show();
            }

        });
        // Return the completed view to render on screen
        return convertView;
    }
}

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

//Custom Adapter für den Katalog
class UsersAdapter extends ArrayAdapter<Fach> {
    private final Context context;
    private ArrayList<Fach> list = new ArrayList<>();

    public UsersAdapter(Context context, ArrayList<Fach> faecher) {
        super(context, 0, faecher);
        this.list = faecher;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        Fach fach = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_fach_katalog, parent, false);
        }

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

                final EditText InputSemester = dialog.findViewById(R.id.input_semester);
                final EditText InputName = dialog.findViewById(R.id.input_name);
                final Spinner InputTag = dialog.findViewById(R.id.input_tag);
                final EditText InputBeginn = dialog.findViewById(R.id.input_beginn);
                final EditText InputEnde = dialog.findViewById(R.id.input_ende);
                final EditText InputRaum = dialog.findViewById(R.id.input_raum);
                final EditText InputDozent = dialog.findViewById(R.id.input_dozent);
                final EditText InputKuerzel = dialog.findViewById(R.id.input_kuerzel);
                Button buttonok = dialog.findViewById(R.id.dialogButtonOK);
                Button buttoncancel = dialog.findViewById(R.id.dialogButtonCancel);

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
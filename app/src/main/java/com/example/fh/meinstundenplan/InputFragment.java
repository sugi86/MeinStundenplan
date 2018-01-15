package com.example.fh.meinstundenplan;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    private EditText InputSemester;
    private EditText InputName;
    private Spinner InputTag;
    private EditText InputBeginn;
    private EditText InputEnde;
    private EditText InputRaum;
    private EditText InputDozent;
    private EditText InputKuerzel;


    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        InputSemester = view.findViewById(R.id.input_semester);
        InputName = view.findViewById(R.id.input_name);
        InputTag = view.findViewById(R.id.input_tag);
        InputBeginn = view.findViewById(R.id.input_beginn);
        InputEnde = view.findViewById(R.id.input_ende);
        InputRaum = view.findViewById(R.id.input_raum);
        InputDozent = view.findViewById(R.id.input_dozent);
        InputKuerzel = view.findViewById(R.id.input_kuerzel);
        Button buttonadd = view.findViewById(R.id.button_input);

        final Stundenplan activity = (Stundenplan) getActivity();

        InputTag.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        buttonadd.setOnClickListener(new View.OnClickListener() {
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

                activity.addFach(InputSemester.getText().toString(), InputName.getText().toString(), tag, InputBeginn.getText().toString(), InputEnde.getText().toString(), InputRaum.getText().toString(), InputDozent.getText().toString(), InputKuerzel.getText().toString(), true, id);
                InputSemester.setText(null);
                InputName.setText(null);
                InputBeginn.setText(null);
                InputEnde.setText(null);
                InputRaum.setText(null);
                InputDozent.setText(null);
                InputKuerzel.setText(null);


                InputSemester.requestFocus();
                InputMethodManager mgr = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                assert mgr != null;
                mgr.hideSoftInputFromWindow(InputSemester.getWindowToken(), 0);
                Snackbar.make(v, "Fach erfolgreich zum Katalog hinzugefügt", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


}

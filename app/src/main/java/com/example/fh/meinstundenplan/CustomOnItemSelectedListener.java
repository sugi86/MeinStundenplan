package com.example.fh.meinstundenplan;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class CustomOnItemSelectedListener implements OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
}

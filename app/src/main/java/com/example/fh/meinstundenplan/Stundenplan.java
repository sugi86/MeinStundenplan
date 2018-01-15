package com.example.fh.meinstundenplan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class Stundenplan extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public final ArrayList<Fach> Katalog = new ArrayList<>();
    public final ArrayList<Fach> Montag = new ArrayList<>();
    public final ArrayList<Fach> Dienstag = new ArrayList<>();
    public final ArrayList<Fach> Mittwoch = new ArrayList<>();
    public final ArrayList<Fach> Donnerstag = new ArrayList<>();
    public final ArrayList<Fach> Freitag = new ArrayList<>();

    //Funktion um ein Fach dem Katalog hinzuzufügen
    public void addFach(String s, String n, String t, String b, String e,
                        String r, String d, String k, boolean ak, String id) {
        Fach tmpfach = new Fach(s, n, t, b, e, r, d, k, ak, id);
        Katalog.add(tmpfach);
    }

    //Funktion um ein Fach-ArrayList nach dem Attribut Beginn zu sortieren
    private void sortTage_Beginn(ArrayList<Fach> tmp) {
        Collections.sort(tmp, new Comparator<Fach>() {
            @Override
            public int compare(Fach o2, Fach o1) {
                return (o2.getBeginn().compareTo(o1.getBeginn()));
            }
        });
    }

    //Funktion um ein Fach-ArrayList nach dem Attribut Semester zu sortieren
    private void sortTage_Semester(ArrayList<Fach> tmp) {
        Collections.sort(tmp, new Comparator<Fach>() {
            @Override
            public int compare(Fach o2, Fach o1) {
                return (o2.getSemester().compareTo(o1.getSemester()));
            }
        });
    }

    //Funktion um ein Fach-ArrayList nach der Wochentag id zu sortieren
    private void sortTage_Tage(ArrayList<Fach> tmp) {
        Collections.sort(tmp, new Comparator<Fach>() {
            @Override
            public int compare(Fach o2, Fach o1) {
                return (o2.getId().compareTo(o1.getId()));
            }
        });
    }

    private void readStundenplan() {
        InputStream is = getResources().openRawResource(R.raw.ini_ws17);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("ISO-8859-1")));
        String id = "";
        String line = "";
        try {
            // Erste Zeile mit Überschrift ignorieren
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                //Log.d("MyActivity", "Line: " + line);
                // Am ';' trennen
                String[] tokens = line.split(";");

                switch (tokens[2]) {
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
                //Neues Fach mit den Daten füllen
                addFach(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                        tokens[5], tokens[6], tokens[7], false, id);
                //Log.d("ReadStundenplan", "Just created: " + Katalog.get(Katalog.size() - 1));
            }
        } catch (IOException e) {
            //Log.wtf("MyActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }

    //Funktion um ein Backup des aktuellen Katalogs in die SharedPrefences schreiben
    private void writeBackup() {
        try {
            SharedPreferences sf = getSharedPreferences("Backup", 0);
            SharedPreferences.Editor editor = sf.edit();
            //altes Backup löschen
            editor.clear();
            editor.apply();
            String tmpstring;
            //Neues Backup schreiben
            for (int i = 0; i < Katalog.size(); i++) {
                tmpstring = Integer.toString(i);
                editor.putString(tmpstring, Katalog.get(i).CSVtoString());
                editor.commit();
                //Log.d("writeSharedPref", sf.getString(tmpstring, "Keiner!"));
            }
        } catch (Exception ex) {
            Log.wtf("InternerSpeicher", "Fehler beim Lesen", ex);
        }
    }

    //Funktion um den Katalog aus dem aktuellen Backup zu füllen
    private void readBackup() {
        String tmpstring;
        boolean check;
        //Katalog leeren
        Katalog.clear();
        SharedPreferences sf = getSharedPreferences("Backup", 0);
        //Backup lesen
        Map<String, ?> keys = sf.getAll();

        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            tmpstring = entry.getValue().toString();
            String[] tokens = tmpstring.split(";");
            //Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            check = tokens[8].equals("true");
            //Log.d("Check :" + check, "token 7: " + tokens[8]);
            addFach(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    tokens[5], tokens[6], tokens[7], check, tokens[9]);
        }
    }

    //Funktion um die Arrays für die einzelnen Tage zu füllen
    private void createStundenplan() {
        String cmp;
        Montag.clear();
        Dienstag.clear();
        Mittwoch.clear();
        Donnerstag.clear();
        Freitag.clear();
        for (int i = 0; i < Katalog.size(); i++) {
            cmp = Katalog.get(i).getTag();
            if (cmp.equals("Montag") && Katalog.get(i).isChecked()) {
                Montag.add(Katalog.get(i));
            } else if (cmp.equals("Dienstag") && Katalog.get(i).isChecked()) {
                Dienstag.add(Katalog.get(i));
            } else if (cmp.equals("Mittwoch") && Katalog.get(i).isChecked()) {
                Mittwoch.add(Katalog.get(i));
            } else if (cmp.equals("Donnerstag") && Katalog.get(i).isChecked()) {
                Donnerstag.add(Katalog.get(i));
            } else if (cmp.equals("Freitag") && Katalog.get(i).isChecked()) {
                Freitag.add(Katalog.get(i));
            }
        }
        //Arrays sortieren
        sortTage_Semester(Montag);
        sortTage_Semester(Dienstag);
        sortTage_Semester(Mittwoch);
        sortTage_Semester(Donnerstag);
        sortTage_Semester(Freitag);
        sortTage_Beginn(Montag);
        sortTage_Beginn(Dienstag);
        sortTage_Beginn(Mittwoch);
        sortTage_Beginn(Donnerstag);
        sortTage_Beginn(Freitag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readBackup();
        setContentView(R.layout.activity_stundenplan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        createStundenplan();
        fragment = new ShowStundenplanFragment();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stundenplan, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment = new KatalogFragment();
        int id = item.getItemId();

        //Navigationsmenü
        if (id == R.id.Sidebar_Stundenplan_anzeigen) {
            createStundenplan();
            fragment = new ShowStundenplanFragment();
        } else if (id == R.id.Sidebar_Stundenplan_verwalten) {
            //Katalog sortieren
            sortTage_Semester(Katalog);
            sortTage_Beginn(Katalog);
            sortTage_Tage(Katalog);
            fragment = new KatalogFragment();
        } else if (id == R.id.Sidebar_Neues_Fach) {
            fragment = new InputFragment();
        } else if (id == R.id.Sidebar_Einlesen) {
            readStundenplan();
            sortTage_Beginn(Katalog);
            sortTage_Tage(Katalog);
            setContentView(R.layout.activity_stundenplan);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            Snackbar.make(navigationView, "Katalog erfolgreich aus CSV-Datei importiert",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (id == R.id.Sidebar_read_Backup) {
            readBackup();
        } else if (id == R.id.Sidebar_write_Backup) {
            writeBackup();
        } else if (id == R.id.Sidebar_katalog_clear){
            Katalog.clear();
        }
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
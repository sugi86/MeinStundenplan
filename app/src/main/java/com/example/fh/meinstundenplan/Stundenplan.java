package com.example.fh.meinstundenplan;

import android.content.Context;
import android.content.EntityIterator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TabHost;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

public class Stundenplan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public ArrayList<Fach> Katalog = new ArrayList<>();
    public ArrayList<Fach> Montag = new ArrayList<>();
    public ArrayList<Fach> Dienstag = new ArrayList<>();
    public ArrayList<Fach> Mittwoch = new ArrayList<>();
    public ArrayList<Fach> Donnerstag = new ArrayList<>();
    public ArrayList<Fach> Freitag = new ArrayList<>();

    public void addFach(String s, String n, String t, String b, String e, String r, String d, String k, boolean ak)
    {
        Fach tmpfach = new Fach(s, n, t, b, e, r, d, k, ak);
        Katalog.add(tmpfach);
    }




    private void readStundenplan() {
        InputStream is = getResources().openRawResource(R.raw.ini_ws17);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8") ));

        String line = "";
        try {

            // Ignore first Line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Log.d("MyActivity", "Line: " + line);
                // Split by ';'
                String[] tokens = line.split(";");

                //Read the data
                addFach(tokens[0], tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7], false);
                Log.d("ReadStundenplan", "Just created: " + Katalog.get(Katalog.size() - 1));

                //
            }
        }
        catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }

    }

    private void writeBackup(){
        try {

            String state = Environment.getExternalStorageState();
            if(state.equals(Environment.MEDIA_MOUNTED))
            {
                Environment.getExternalStorageDirectory();
                File externalSD = Environment.getExternalStorageDirectory();
                File specialExternalDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            }

            SharedPreferences sf = getSharedPreferences("Backup", 0);
            SharedPreferences.Editor editor = sf.edit();
            /*Map<String,?> keys = sf.getAll();
            for(Map.Entry<String,?> entry: keys.entrySet())
            {
                editor.remove(entry.getKey());
                editor.commit();
            }
            */
            editor.clear();
            editor.commit();
            String tmpstring;

            for (int i = 0; i < Katalog.size(); i++) {
                tmpstring = Integer.toString(i);
                editor.putString(tmpstring,Katalog.get(i).CSVtoString());
                editor.commit();
                Log.d("writeSharedPref",  sf.getString(tmpstring,"Keiner!"));
            }
            Log.d("SharedPref: ", sf.getString("0", "Failed!"));
            Log.d("SharedPref: ", sf.getString("1", "Failed!"));
        }
        catch(Exception ex) {
            Log.wtf("InternerSpeicher", "Fehler beim Lesen", ex);
        }
    }

    private void readBackup() {
        String tmpstring;
        boolean check;
        Katalog.clear();
        SharedPreferences sf = getSharedPreferences("Backup", 0);
        Map<String,?> keys = sf.getAll();

        for(Map.Entry<String,?> entry: keys.entrySet())
        {
            tmpstring=entry.getValue().toString();
            String[] tokens = tmpstring.split(";");

            Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());
            check = Boolean.valueOf(tokens[8]);
            addFach(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], check);
        }


        /*
        Katalog.clear();
        int i = 0;
        String endstring = "Initialized";
        boolean check;
        Log.d("Vor While Schleife", "Test: " + endstring);
        while (true)
        {
            endstring = sf.getString(Integer.toString(i), "Keiner!");
            if (endstring.equals("Keiner!"))
            {
                Log.d("Abbruchbedingung", "Endstring: " + endstring);
                break;
            }
            else {
                String[] tokens = endstring.split(";");
                check = Boolean.valueOf(tokens[8]);
                addFach(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], check);
                Log.d("ReadSharedPref", "Just created: " + Katalog.get(Katalog.size() - 1));
                i++;
                Log.d("In While Schleife", "Zählvariable " + i);
            }
        }
        */
    }

    private void createStundenplan()
    {
        String cmp;
        Montag.clear();
        Dienstag.clear();
        Mittwoch.clear();
        Donnerstag.clear();
        Freitag.clear();
        for (int i = 0; i<Katalog.size();i++)
        {
            cmp = Katalog.get(i).getTag();
            if (cmp.equals("Montag"))
            {
                Montag.add(Katalog.get(i));
            }
            else if (cmp.equals("Dienstag"))
            {
                Dienstag.add(Katalog.get(i));
            }
            else if (cmp.equals("Mittwoch"))
            {
                Mittwoch.add(Katalog.get(i));
            }
            else if (cmp.equals("Donnerstag"))
            {
                Donnerstag.add(Katalog.get(i));
            }
            else if (cmp.equals("Freitag"))
            {
                Freitag.add(Katalog.get(i));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_stundenplan);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            Fragment fragment;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragment = new KatalogFragment();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment = new KatalogFragment();

        int id = item.getItemId();

        if (id == R.id.Sidebar_Stundenplan_anzeigen) {
            createStundenplan();
            fragment = new ShowStundenplanFragment();

        } else if (id == R.id.Sidebar_Stundenplan_verwalten) {
            fragment = new KatalogFragment();
        } else if (id == R.id.Sidebar_Neues_Fach) {
            fragment = new InputFragment();
        } else if (id == R.id.Sidebar_Einlesen) {
            readStundenplan();
            setContentView(R.layout.activity_stundenplan);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Einlesen erfolgt", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        } else if (id == R.id.Sidebar_read_Backup){
            readBackup();
        }else if (id == R.id.Sidebar_write_Backup){
            writeBackup();
        }
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

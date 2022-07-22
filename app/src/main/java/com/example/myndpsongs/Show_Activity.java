package com.example.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Show_Activity extends AppCompatActivity {
    Button btnFilter;
    ArrayList<Song> alSongs;
    Spinner spinner;
    ListView lv;
    CustomAdapter aaSongs;
    ArrayAdapter<String> aaYear;
    ArrayList<String> alYear;
    DBHelper db = new DBHelper(Show_Activity.this);
    boolean CHECKED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv = findViewById(R.id.ListViewSong);
        btnFilter = findViewById(R.id.buttonShow);

        spinner = findViewById(R.id.spinner);
        alYear = new ArrayList<String>();

        aaYear = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alYear);
        load();
        spinner.setAdapter(aaYear);
        spinner.setSelected(false);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CHECKED) {
                    alSongs.clear();
                    alSongs.addAll(db.get5Stars());
                    btnFilter.setText("SHOW ALL SONGS");
                    aaSongs.notifyDataSetChanged();
                    CHECKED = true;
                } else {
                    alSongs.clear();
                    alSongs.addAll(db.getAllSongs());
                    CHECKED = false;
                    aaSongs.notifyDataSetChanged();
                    btnFilter.setText("SHOW ALL SONGS WITH 5 STARS");

                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                alSongs.clear();
                Log.d("info", alYear.get(i) + "");
                Toast.makeText(Show_Activity.this, alYear.get(i),
                        Toast.LENGTH_SHORT).show();
                if (!alYear.get(i).equalsIgnoreCase("NONE")) {
                    alSongs.addAll(db.getSongsByYear(Integer.parseInt(alYear.get(i))));
                    aaSongs.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Show_Activity.this, "Please  select something",
                        Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song selected = alSongs.get(position);
                Intent i = new Intent(Show_Activity.this,
                        Edit_Delete.class);
                i.putExtra("Selected", selected);
                startActivity(i);
            }
        });

    }

    private void load() {
        alSongs = db.getAllSongs();
        aaSongs = new CustomAdapter(this,
                R.layout.row, alSongs);
        lv.setAdapter(aaSongs);
        loadSpinner();
    }

    private void loadSpinner() {
        alYear.clear();
        alYear.addAll(db.getYear());
        Toast.makeText(Show_Activity.this, alYear.get(0) + "",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }
}

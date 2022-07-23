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
    ArrayList<Song> ALSongs;
    Spinner spinner;
    ListView lv;
    CustomAdapter AASongs;
    ArrayAdapter<String> AAYear;
    ArrayList<String> ALYear;
    DBHelper dbh = new DBHelper(Show_Activity.this);
    boolean CHECKED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //Linking the UI to the variable
        lv = findViewById(R.id.ListViewSong);
        btnFilter = findViewById(R.id.buttonShow);

        spinner = findViewById(R.id.spinner);
        ALYear = new ArrayList<String>();

        AAYear = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ALYear);
        load();
        spinner.setAdapter(AAYear);
        spinner.setSelected(false);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CHECKED) {
                    ALSongs.clear();
                    ALSongs.addAll(dbh.get5Stars());
                    btnFilter.setText("SHOW ALL SONGS");
                    AASongs.notifyDataSetChanged();
                    CHECKED = true;
                } else {
                    ALSongs.clear();
                    ALSongs.addAll(dbh.getAllSongs());
                    AASongs.notifyDataSetChanged();
                    CHECKED = false;
                    btnFilter.setText("SHOW ALL SONGS WITH 5 STARS");

                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ALSongs.clear();
                Log.d("info", ALYear.get(i) + "");
                Toast.makeText(Show_Activity.this, ALYear.get(i),
                        Toast.LENGTH_SHORT).show();
                if (!ALYear.get(i).equalsIgnoreCase("NONE")) {
                    ALSongs.addAll(dbh.getSongsByYear(Integer.parseInt(ALYear.get(i))));
                    AASongs.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Show_Activity.this, "Please  select something",
                        Toast.LENGTH_LONG).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song Data = ALSongs.get(position);
                Intent i = new Intent(Show_Activity.this,
                        Edit_Delete.class);
                i.putExtra("Selected", Data);
                startActivity(i);
            }
        });

    }

    private void load() {
        ALSongs = dbh.getAllSongs();
        AASongs = new CustomAdapter(this,
                R.layout.row, ALSongs);
        lv.setAdapter(AASongs);
        loadSpinner();
    }

    private void loadSpinner() {
        ALYear.clear();
        ALYear.addAll(dbh.getYear());
        Toast.makeText(Show_Activity.this, ALYear.get(0) + "",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }
}

package com.example.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Edit_Delete extends AppCompatActivity {
    Button BtnUpdate, BtnDelete,BtnCancel;
    EditText ETTitle,ETSingers,ETYear,ETID;
    RadioGroup rgStars;
    int stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ETID=findViewById(R.id.ID);
        BtnCancel=findViewById(R.id.buttonCancel);
        BtnUpdate =findViewById(R.id.buttonUpdate);
        BtnDelete =findViewById(R.id.buttonDelete);
        ETSingers=findViewById(R.id.editTextSingers);
        ETTitle=findViewById(R.id.editTextSong);
        ETYear=findViewById(R.id.editTextYear);
        rgStars=findViewById(R.id.RGStars);

        Intent i = getIntent();
        Song song = (Song) i.getSerializableExtra("Selected");
        ETID.setText(song.get_id()+"");
        ETID.setEnabled(false);
        ETTitle.setText(song.getTitle());
        ETSingers.setText(song.getSingers());
        ETYear.setText(song.getYear()+"");
        rgStars.check(song.getStar());

        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d1 = ETTitle.getText().toString();
                String d2 = ETSingers.getText().toString();

                DBHelper db=new DBHelper(Edit_Delete.this);
                song.updateDetails(d2,d1,
                        Integer.parseInt(ETYear.getText().toString()),rgStars.getCheckedRadioButtonId());
                db.updateSong(song);
                db.close();
                finish();

            }
        });

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(Edit_Delete.this);
                db.DeleteSong(song.get_id());
                finish();
            }
        });

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}


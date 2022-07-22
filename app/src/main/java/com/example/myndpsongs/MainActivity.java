package com.example.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioGroup;
import android.widget.Toast;
import android.annotation.SuppressLint;



public class MainActivity extends AppCompatActivity {
    Button BtnInsert,BtnShow;
    EditText ETTitle,ETSingers,ETYear;
    RadioGroup rgStars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnInsert=findViewById(R.id.buttonInsert);
        BtnShow=findViewById(R.id.buttonShow);
        ETSingers=findViewById(R.id.editTextSingers);
        ETTitle=findViewById(R.id.editTextSong);
        ETYear=findViewById(R.id.editTextYear);
        rgStars=findViewById(R.id.RGStars);


        BtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        Show_Activity.class);
                startActivity(i);
            }
        });


        BtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(MainActivity.this);
                String e1 = ETTitle.getText().toString();
                String e2 = ETSingers.getText().toString();
                long inserted_id=db.InsertSong(e1 ,e2, Integer.parseInt(ETYear.getText().toString()),
                        rgStars.getCheckedRadioButtonId());

                if(inserted_id!=-1)
                {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @SuppressLint("NonConstantResourceId")
    public int getStars(RadioGroup rg)
    {
        int stars=0;
        switch (rg.getCheckedRadioButtonId())
        {
            case R.id.radioButton1:
                stars=1;
                break;
            case R.id.radioButton2:
                stars=2;
                break;
            case R.id.radioButton3:
                stars=3;
                break;
            case R.id.radioButton4:
                stars=4;
                break;
            case R.id.radioButton5:
                stars=5;
                break;

        }

        return stars;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
package com.example.bloodhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selectactivity extends AppCompatActivity implements View.OnClickListener {

    private Button getData, enteredData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectactivity);

        getData = (Button) findViewById(R.id.getData);
        getData.setOnClickListener(this);

        enteredData = (Button) findViewById(R.id.enterdData);
        enteredData.setOnClickListener(this);

    }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.getData:
                    startActivity(new Intent(this, BloodData.class));
                    break;

                case R.id.enterdData:
                    startActivity(new Intent(this, Registeryourself.class));
                    break;
            }

    }

}
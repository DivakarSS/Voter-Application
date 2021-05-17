package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dissable extends AppCompatActivity {

    Button buzzer;
    FloatingActionButton signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dissable);
        buzzer = findViewById(R.id.button2);
        signout = findViewById(R.id.floatingActionButton);
        buzzer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dissable.this, Aadhar_validation.class);
                startActivity(intent);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Dissable.this,Validate_signout.class);
                startActivity(intent1);
            }
        });
    }
    @Override
    public void onBackPressed()
    {

    }
}
package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Votebuzzer extends AppCompatActivity {
    Button buzzer;
    FloatingActionButton signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votebuzzer);
        buzzer = findViewById(R.id.button2);
        Intent intent = getIntent();
        String Logined_Id = intent.getStringExtra("Voter_ID");
        signout = findViewById(R.id.floatingActionButton);
        buzzer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Votebuzzer.this, Aadhar_validation.class);
                intent.putExtra("Voter_ID",Logined_Id);
                startActivity(intent);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    public void openDialog(){

    }
}
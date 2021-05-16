package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Vote_count extends AppCompatActivity {
    TextView csetext,ittext,eeetext,civiltext,mechtext,ecetext;
    Button count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_count);
        csetext = findViewById(R.id.cse_text);
        ittext = findViewById(R.id.it_text);
        eeetext = findViewById(R.id.eee_text);
        civiltext = findViewById(R.id.civil_text);
        mechtext = findViewById(R.id.mech_text);
        ecetext = findViewById(R.id.ece_text);
        count = findViewById(R.id.button4);
    }
}
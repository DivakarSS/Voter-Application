package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class Vote_poling extends AppCompatActivity {
    TextView line1,line2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_poling);

        line1 = findViewById(R.id.textView9);
        line1.setGravity(Gravity.CENTER_HORIZONTAL);

    }
}
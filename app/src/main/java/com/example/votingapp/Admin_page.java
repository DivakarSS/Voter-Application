package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class Admin_page extends AppCompatActivity {
    TextView line1, line2;
    ExtendedFloatingActionButton create,count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        line1 = findViewById(R.id.textView6);
        line2 = findViewById(R.id.textView7);
        line1.setGravity(Gravity.CENTER_HORIZONTAL);
        line2.setGravity(Gravity.CENTER_HORIZONTAL);

        create = findViewById(R.id.voter);
        count = findViewById(R.id.count);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(Admin_page.this,Create.class);
                startActivity(log);
            }
        });
    }
}
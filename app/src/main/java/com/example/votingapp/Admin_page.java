package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Admin_page extends AppCompatActivity {
    TextView line1, line2;
    ExtendedFloatingActionButton create,count;
    FloatingActionButton sign_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        line1 = findViewById(R.id.textView6);
        line2 = findViewById(R.id.textView7);
        line1.setGravity(Gravity.CENTER_HORIZONTAL);
        line2.setGravity(Gravity.CENTER_HORIZONTAL);
        sign_out  = findViewById(R.id.floatingActionButton);

        create = findViewById(R.id.voter);
        count = findViewById(R.id.count);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(Admin_page.this,Create.class);
                startActivity(log);
            }
        });
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(Admin_page.this,Vote_count.class);
                startActivity(log);
            }
        });
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor  = preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                finish();
            }
        });


    }
}
package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Vote_count extends AppCompatActivity {
    TextView csetext,ittext,eeetext,civiltext,mechtext,ecetext,cse,it,mech,civil,eee,ece;
    Button count;
    private DatabaseReference reference;
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
        cse = findViewById(R.id.cse);
        it = findViewById(R.id.it);
        ece = findViewById(R.id.ece);
        eee = findViewById(R.id.eee);
        mech = findViewById(R.id.mech);
        civil = findViewById(R.id.civil);

        count = findViewById(R.id.button4);
        reference = FirebaseDatabase.getInstance().getReference().child("Parties");
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String CSE =  snapshot.child("CSE").getValue().toString();
                    String IT = snapshot.child("IT").getValue().toString();
                    String EEE = snapshot.child("EEE").getValue().toString();
                    String CIVIL = snapshot.child("CIVIL").getValue().toString();
                    String MECH = snapshot.child("MECH").getValue().toString();
                    String ECE = snapshot.child("ECE").getValue().toString();

                    cse.setText(CSE);
                    it.setText(IT);
                    eee.setText(EEE);
                    civil.setText(CIVIL);
                    mech.setText(MECH);
                    ece.setText(ECE);
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }
            });

            }
        });
    }
}
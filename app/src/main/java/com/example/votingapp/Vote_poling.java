package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Vote_poling extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 30000;
    TextView line1,cse_vote,it_vote,ece_vote,eee_vote,civil_vote,mech_vote;
    ImageButton cse,eee,it,mech,civil,ece;
    FirebaseDatabase database;
    DatabaseReference ref;
    public Integer csecount=0,eeecount=0,itcount=0,ececount=0,mechcount=0,civilcount=0;
    public String vote;
    private  Vote votes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_poling);

        line1 = findViewById(R.id.textView9);
        line1.setGravity(Gravity.CENTER_HORIZONTAL);

        cse = findViewById(R.id.cse);
        eee = findViewById(R.id.eee);
        it = findViewById(R.id.it);
        mech = findViewById(R.id.mech);
        civil = findViewById(R.id.civil);
        ece = findViewById(R.id.ece);

        cse_vote = findViewById(R.id.cse_text);
        eee_vote = findViewById(R.id.eee_text);
        it_vote = findViewById(R.id.it_text);
        mech_vote = findViewById(R.id.mech_text);
        civil_vote = findViewById(R.id.civil_text);
        eee_vote = findViewById(R.id.eee_text);

        votes = new Vote();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Parties");

        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                csecount= csecount+1;
                vote = csecount.toString();

                String cse = "CSE";
                votes.setCse(cse);
                ref.child(votes.getCse()).setValue(vote).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Vote_poling.this,Last_page.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Vote_poling.this, "Failed....",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        eee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eeecount= eeecount+1;
                vote = eeecount.toString();
                String eee = "EEE";
                votes.setEee(eee);
                ref.child(votes.getEee()).setValue(vote).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Vote_poling.this,Last_page.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Vote_poling.this, "Failed....",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itcount= itcount+1;
                vote = itcount.toString();
                String it = "IT";
                votes.setIt(it);
                ref.child(votes.getIt()).setValue(vote).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Vote_poling.this,Last_page.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Vote_poling.this, "Failed....",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mechcount= mechcount+1;
                vote = mechcount.toString();
                String mech ="MECH";
                votes.setMech(mech);
                ref.child(votes.getMech()).setValue(vote).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Vote_poling.this,Last_page.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Vote_poling.this, "Failed....",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                civilcount= mechcount+1;
                vote = mechcount.toString();
                String civil = "CIVIL";
                votes.setCivil(civil);
                ref.child(votes.getCivil()).setValue(vote).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Vote_poling.this,Last_page.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Vote_poling.this, "Failed....",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ececount= ececount+1;
                vote = ececount.toString();
                String ece = "ECE";
                votes.setEce(ece);
                ref.child(votes.getEce()).setValue(vote).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Vote_poling.this,Last_page.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Vote_poling.this, "Failed....",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(Vote_poling.this, Last_page.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);


    }
    @Override
    public void onBackPressed()
    {

    }
}
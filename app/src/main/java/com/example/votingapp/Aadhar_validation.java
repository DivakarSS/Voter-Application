package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Aadhar_validation extends AppCompatActivity {
    private static final Pattern AADHAR_NUMBER =
            Pattern.compile("^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$");
    private static final Pattern VOTER_ID =
            Pattern.compile("^([a-zA-Z]){3}([0-9]){7}?$");
    TextInputEditText voterid_text,aadhar_text;
    TextInputLayout voterid_layout,aadhar_layout;
   
    Button validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_validation);


        voterid_text = findViewById(R.id.voterid_edittext);
        aadhar_text = findViewById(R.id.aadhar_edittext);

        voterid_layout = findViewById(R.id.voterid_layout);
        aadhar_layout = findViewById(R.id.aadhar_layout);

        validate = findViewById(R.id.button);



    }
    @Override
    public void onBackPressed()
    {

    }
    private boolean validateAadhar(){
        String aadhar = aadhar_layout.getEditText().getText().toString().trim();

        if(aadhar.isEmpty())
        {
            aadhar_layout.setError("Field can't be empty");
            return false;
        }else if(!AADHAR_NUMBER.matcher(aadhar).matches()){
            aadhar_layout.setError("Enter a valid Aadhar number");
            return  false;
        } else{
            aadhar_layout.setError(null);
            return true;
        }
    }

    private boolean validateVoter(){
        String voter = voterid_layout.getEditText().getText().toString().trim();

        if(voter.isEmpty())
        {
            voterid_layout.setError("Field can't be empty");
            return false;
        }else if(!VOTER_ID.matcher(voter).matches()){
            voterid_layout.setError("Enter a valid Voter Id");
            return  false;
        } else{
            voterid_layout.setError(null);
            return true;
        }
    }

    public void Validate(View view){
        if(!validateAadhar() | !validateVoter() ){
            return;
        }

        String voterid = voterid_layout.getEditText().getText().toString().trim();
        String aadhar_no = aadhar_layout.getEditText().getText().toString().trim();
        Intent log = getIntent();


        String Logined_Id = log.getStringExtra("Voter_ID");
        Query checkAadhar = FirebaseDatabase.getInstance().getReference("Votersdb").orderByChild("voter_Id").equalTo(voterid);
        checkAadhar.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(voterid.equals(Logined_Id))
                {
                    if(snapshot.exists())
                    {
                        voterid_layout.setError(null);
                        voterid_layout.setErrorEnabled(false);

                        String aadhar = snapshot.child(voterid).child("aadhar").getValue(String.class);
                        if(aadhar.equals(aadhar_no)){
                            aadhar_layout.setError(null);
                            aadhar_layout.setErrorEnabled(false);

                            Intent intent = new Intent(Aadhar_validation.this,Fingerprint_validation.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Aadhar_validation.this,"Voter Id does not exist!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(Aadhar_validation.this,"Password does not match!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(Aadhar_validation.this, "Enter Logined Voter id...!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }
}
package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Create extends AppCompatActivity {
    private static  final Pattern PHONE_NUMBER =
            Pattern.compile("[5-9][0-9]{9}");
    private static final Pattern AADHAR_NUMBER =
            Pattern.compile("^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$");
    private static final Pattern VOTER_ID =
            Pattern.compile("^([a-zA-Z]){3}([0-9]){7}?$");
    private static final Pattern PASSWORD =
            Pattern.compile("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$");

    TextInputLayout name_layout,aadhar_layout,voter_layout,phone_layout,dob_layout,address_layout;
    TextInputEditText name_text,aadhar_text,voter_text,phone_text,dob_text,address_text;
    FirebaseDatabase database;
    DatabaseReference ref;
    private  Voters voters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        name_layout = findViewById(R.id.name_layout);
        aadhar_layout = findViewById(R.id.aadhar_layout);
        voter_layout = findViewById(R.id.voterid_layout);
        phone_layout = findViewById(R.id.phone_layout);
        dob_layout = findViewById(R.id.dob_layout);
        address_layout = findViewById(R.id.address_layout);

        name_text = findViewById(R.id.name_edittext);
        aadhar_text = findViewById(R.id.aadhar_edittext);
        voter_text = findViewById(R.id.voterid_edittext);
        phone_text = findViewById(R.id.phone_edittext);
        dob_text = findViewById(R.id.dob_edittext);
        address_text = findViewById(R.id.address_edittext);

        voters = new Voters();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Votersdb");

    }

    private boolean validateName(){
        String name = name_layout.getEditText().getText().toString().trim();
        if(name.isEmpty())
        {
            name_layout.setError("Field can't be empty");
            return false;
        }
        else if(name.length()>15){
            name_layout.setError("Name is too long");
            return false;
        }
        else {
            name_layout.setError(null);
            return true;
        }
    }

    private boolean validatePhone(){
        String phone = phone_layout.getEditText().getText().toString().trim();

        if(phone.isEmpty())
        {
            phone_layout.setError("Field can't be empty");
            return false;
        }else if(!PHONE_NUMBER.matcher(phone).matches()){
            phone_layout.setError("Enter a valid phone number");
            return  false;
        } else{
            phone_layout.setError(null);
            return true;
        }
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
        String voter = voter_layout.getEditText().getText().toString().trim();

        if(voter.isEmpty())
        {
            voter_layout.setError("Field can't be empty");
            return false;
        }else if(!VOTER_ID.matcher(voter).matches()){
            voter_layout.setError("Enter a valid Voter Id");
            return  false;
        } else{
            voter_layout.setError(null);
            return true;
        }
    }
    private boolean validateDOB(){
        String dob = dob_layout.getEditText().getText().toString().trim();

        if(dob.isEmpty())
        {
            dob_layout.setError("Field can't be empty");
            return false;
        }else if(!PASSWORD.matcher(dob).matches()){
            dob_layout.setError("Enter a valid D.O.B");
            return  false;
        } else{
            dob_layout.setError(null);
            return true;
        }
    }

    private boolean validateAddress(){
        String address = address_layout.getEditText().getText().toString().trim();
        if(address.isEmpty())
        {
            address_layout.setError("Field can't be empty");
            return false;
        }
        else if(address.length()<10){
            address_layout.setError("Address is too short");
            return false;
        }
        else {
            address_layout.setError(null);
            return true;
        }
    }

    public  void Validate(View view){
        if(!validateName() | !validatePhone() | !validateAadhar() | !validateVoter() | !validateDOB() | !validateAddress()){
            return;
        }
        String Name = name_layout.getEditText().getText().toString().trim();
        String Aadhar = aadhar_layout.getEditText().getText().toString().trim();
        String Voter_Id = voter_layout.getEditText().getText().toString().trim();
        String DOB = dob_layout.getEditText().getText().toString().trim();
        String Phone = phone_layout.getEditText().getText().toString().trim();
        String Address = address_layout.getEditText().getText().toString().trim();

        voters.setName(Name);
        voters.setAadhar(Aadhar);
        voters.setVoter_Id(Voter_Id);
        voters.setDOB(DOB);
        voters.setPhone(Phone);
        voters.setAddress(Address);

        ref.child(voters.getVoter_Id()).setValue(voters).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull  Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Create.this,"Updated Successful",Toast.LENGTH_LONG).show();
                    Intent log = new Intent(Create.this,Admin_page.class);
                    startActivity(log);
                }
                else{
                    Toast.makeText(Create.this, "Failed....",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
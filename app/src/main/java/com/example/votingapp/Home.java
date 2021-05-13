package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class Home extends AppCompatActivity {
    private static final Pattern VOTER_ID =
            Pattern.compile("^([a-zA-Z]){3}([0-9]){7}?$");
    private static final Pattern PASSWORD =
            Pattern.compile("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$");
    TextView admin_login,terms;
    TextInputEditText voterid_text,password_text;
    TextInputLayout voterid_layout,password_layout;
    Button login;
    CheckBox remember_me;
    String logined_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        admin_login = findViewById(R.id.admin);
        terms = findViewById(R.id.textView4);
        login = findViewById(R.id.button);
        remember_me = findViewById(R.id.checkBox);
        voterid_layout = findViewById(R.id.voterid_layout);
        password_layout = findViewById(R.id.password_layout);

        voterid_text = findViewById(R.id.voterid_edittext);
        password_text = findViewById(R.id.password_edittext);

        SharedPreferences preferences = getSharedPreferences("check",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true"))
        {
            Intent intent = new Intent(Home.this,Votebuzzer.class );
            SharedPreferences sharedPreferences = getSharedPreferences("remember",MODE_PRIVATE);


            startActivity(intent);
        }else if(checkbox.equals("false"))
        {
            Toast.makeText(this, "Please sign in", Toast.LENGTH_SHORT).show();
        }

        remember_me.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("check",MODE_PRIVATE);
                    SharedPreferences.Editor editor  = preferences.edit();
                    editor.putString("remember","true");

                    editor.apply();
                    Toast.makeText(Home.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else if(!buttonView.isChecked())
                {
                    SharedPreferences preferences = getSharedPreferences("check",MODE_PRIVATE);
                    SharedPreferences.Editor editor  = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(Home.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });


        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Admin_login.class);
                startActivity(intent);
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Terms.class);
                startActivity(intent);
            }
        });
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
    private boolean validatePassword(){
        String password = password_layout.getEditText().getText().toString().trim();
        if(password.isEmpty())
        {
            password_layout.setError("Field can't be empty");
            return false;
        }else if(!PASSWORD.matcher(password).matches()){
            password_layout.setError("Enter a valid password");
            return  false;
        }
        else {
            password_layout.setError(null);
            return true;
        }
    }

    public void Validate(View view){

        if(!validateVoter() | !validatePassword()){
            return;
        }

        String id = voterid_layout.getEditText().getText().toString().trim();
        String password = password_layout.getEditText().getText().toString().trim();
        Query checkVoter = FirebaseDatabase.getInstance().getReference("Votersdb").orderByChild("voter_Id").equalTo(id);
        logined_id = id;
        checkVoter.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    voterid_layout.setError(null);
                    voterid_layout.setErrorEnabled(false);

                    String pass = snapshot.child(id).child("dob").getValue(String.class);
                    if(pass.equals(password)){
                        password_layout.setError(null);
                        password_layout.setErrorEnabled(false);

                        Intent intent = new Intent(Home.this,Votebuzzer.class);
                        intent.putExtra("Voter_ID",logined_id);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Home.this,"Voter Id does not exist!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(Home.this,"Password does not match!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(Home.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }
}
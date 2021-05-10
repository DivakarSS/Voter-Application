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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Queue;
import java.util.regex.Pattern;

public class Admin_login extends AppCompatActivity {

    TextInputEditText admin_text,password_text;
    TextInputLayout admin_layout,password_layout;
    CheckBox remember_me;
    Button login;
    private static final Pattern ADMIN_ID =
            Pattern.compile("^([A-Z]){5}([0-9]){3}?$");
    private static final Pattern PASSWORD =
            Pattern.compile("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().hide();

        admin_text = findViewById(R.id.adminid_edittext);
        password_text = findViewById(R.id.password_edittext);

        admin_layout = findViewById(R.id.adminid_layout);
        password_layout = findViewById(R.id.password_layout);

        remember_me = findViewById(R.id.checkBox);

        login = findViewById(R.id.button);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true"))
        {
            Intent intent = new Intent(Admin_login.this,Admin_page.class );
            startActivity(intent);
        }else if(checkbox.equals("false"))
        {
            Toast.makeText(this, "Please sign in", Toast.LENGTH_SHORT).show();
        }

        remember_me.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor  = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(Admin_login.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else if(!buttonView.isChecked())
                {
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor  = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(Admin_login.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private boolean validateAdmin(){
        String admin = admin_layout.getEditText().getText().toString().trim();
        if(admin.isEmpty())
        {
            admin_layout.setError("Field can't be empty");
            return false;
        }else if(!ADMIN_ID.matcher(admin).matches()){
            admin_layout.setError("Enter a valid password");
            return  false;
        }
        else {
            admin_layout.setError(null);
            return true;
        }
    }

    public void Validate(View view){
        if(!validateAdmin() | !validatePassword()){
            return;
        }
        String adminid = admin_layout.getEditText().getText().toString().trim();
        String admin_password = password_layout.getEditText().getText().toString().trim();
        Query checkAdmin = FirebaseDatabase.getInstance().getReference("Admin").orderByChild("id").equalTo(adminid);

        checkAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    admin_layout.setError(null);
                    admin_layout.setErrorEnabled(false);

                    String pass = snapshot.child(adminid).child("password").getValue(String.class);
                    if(pass.equals(admin_password)){
                        password_layout.setError(null);
                        password_layout.setErrorEnabled(false);

                        Intent intent = new Intent(Admin_login.this, Admin_page.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Admin_login.this,"Password does not exist!",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(Admin_login.this,"No such Admin exist!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Admin_login.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
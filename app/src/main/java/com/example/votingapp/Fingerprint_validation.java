package com.example.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class Fingerprint_validation extends AppCompatActivity {
    TextView msg_txt;
    Button validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_validation);

        msg_txt = findViewById(R.id.txt_msg);
        validate = findViewById(R.id.login_btn);

       BiometricManager biometricManager = BiometricManager.from(this);
       switch (biometricManager.canAuthenticate()){
           case BiometricManager.BIOMETRIC_SUCCESS:
               msg_txt.setText("You can use the fingerprint sensor to put an vote");
               msg_txt.setTextColor(Color.parseColor("#EB042A"));
               break;
           case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
               msg_txt.setText("This device don't have a fingerprint sensor");
               break;
           case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
               msg_txt.setText("This biometric sensor is currently unavailable");
               validate.setVisibility(View.GONE);
               break;
           case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
               msg_txt.setText("This device don't have any fingerprint saved, please check your security settings");
               validate.setVisibility(View.GONE);
               break;
       }


       Executor executor = ContextCompat.getMainExecutor(this);

        BiometricPrompt biometricPrompt = new BiometricPrompt(Fingerprint_validation.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);

            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(Fingerprint_validation.this, "Validation Successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Fingerprint_validation.this,Vote_poling.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();

            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                            .setTitle("Validate")
                            .setDescription("Use Your fingerprint to put an vote")
                            .setNegativeButtonText("Cancel")
                            .build();

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
    @Override
    public void onBackPressed()
    {

    }
}
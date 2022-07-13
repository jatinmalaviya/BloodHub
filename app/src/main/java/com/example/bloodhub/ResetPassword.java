package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetpasswordbtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        emailEditText = (EditText) findViewById(R.id.email);
        resetpasswordbtn = (Button) findViewById(R.id.resetpassword);

        mAuth = FirebaseAuth.getInstance();

        resetpasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();
            }
        });
    }
    private void resetpassword(){

        String email = emailEditText.getText().toString().trim();


        if(email.isEmpty()){
            emailEditText.setError("Enter a Email address!");
            emailEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a Valid Email!");
            emailEditText.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ResetPassword.this, "Check your Gmail to reset your password!!",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(ResetPassword.this, "Try again!! Something Went Wrong!",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
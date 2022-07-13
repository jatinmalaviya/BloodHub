package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterNow extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button registeruser;
    private EditText editname, editTextemail, editTextpassword;
    //private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_now);

        mAuth = FirebaseAuth.getInstance();

        registeruser = (Button) findViewById(R.id.registernow);
        registeruser.setOnClickListener(this);

        editname = (EditText) findViewById(R.id.name);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        registeruser();

    }

    private void registeruser() {

        String name = editname.getText().toString().trim();
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if(name.isEmpty()){
            editname.setError("Enter a full name!");
            editname.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextemail.setError("Enter a Email!");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Enter a Valid Email!");
            editTextemail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextpassword.setError("Enter a Password!");
            editTextpassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextpassword.setError("Password should be six numbers!");
            editTextpassword.requestFocus();
            return;
        }

        //progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterNow.this, "Successfully Registered!", Toast.LENGTH_LONG).show();
                                        //progressBar.setVisibility(View.GONE);

                                    } else {
                                        Toast.makeText(RegisterNow.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        //progressBar.setVisibility(View.GONE);

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(RegisterNow.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility(View.GONE);

                        }

                    }
                });
    }
}
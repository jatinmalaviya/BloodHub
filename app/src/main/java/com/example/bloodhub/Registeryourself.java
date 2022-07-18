package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registeryourself extends AppCompatActivity {

    private EditText username, userage, useremail, usernumber;
    private Button submitbtn;
    private FirebaseAuth mAuth;
    private Spinner gender, bloodgroup;

    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeryourself);

        username = (EditText) findViewById(R.id.name);
        userage = (EditText) findViewById(R.id.age);
        useremail = (EditText) findViewById(R.id.emailid);
        usernumber = (EditText) findViewById(R.id.phonenumber);

        gender = findViewById(R.id.gender);
        bloodgroup = findViewById(R.id.inputBloodGroup);


        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();



//        if(mAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        submitbtn = (Button) findViewById(R.id.submit);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString().trim();
                String age = userage.getText().toString().trim();
                String email = useremail.getText().toString().trim();
                String number = usernumber.getText().toString().trim();



                if (name.isEmpty()) {
                    username.setError("Enter a full name!");
                    username.requestFocus();
                    return;
                }

                if (age.isEmpty()) {
                    userage.setError("Enter your age!");
                    userage.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    useremail.setError("Enter a Email!");
                    useremail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    useremail.setError("Enter a Valid Email!");
                    useremail.requestFocus();
                    return;
                }

                if (number.isEmpty()) {
                    usernumber.setError("Enter a your phone number!");
                    usernumber.requestFocus();
                    return;
                }


                addDataToFirestore(name, age, email, number);

            }

        });
    }




    private void addDataToFirestore(String name, String age, String email, String number){

        //Log.d("info", "working");

        CollectionReference registerdata = fstore.collection("Userdata");


        Userdata userdata = new Userdata(name, age, email, number);

        registerdata.add(userdata).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(Registeryourself.this,"Your Data has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Registeryourself.this, MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registeryourself.this, "Fail to add your data"+e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
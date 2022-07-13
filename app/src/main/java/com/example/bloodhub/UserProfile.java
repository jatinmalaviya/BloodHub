package com.example.bloodhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ResourceBundle;

public class UserProfile extends AppCompatActivity {

    private TextView uname, uage, uemail, unumber;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fstore;
    private String userid;
    private Button logoutbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        uname = (TextView) findViewById(R.id.name);
        uage = (TextView) findViewById(R.id.age);
        uemail = (TextView) findViewById(R.id.email);
        unumber = (TextView) findViewById(R.id.number);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userid = fAuth.getCurrentUser().getUid();

        logoutbtn = (Button) findViewById(R.id.logout);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserProfile.this, MainActivity.class));
            }
        });

//        DocumentReference documentReference = fstore.collection("users").document(userid);
//
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            //private ResourceBundle documentSnapshot;
//
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//
//                uname.setText(documentSnapshot.getString("name"));
//                uage.setText(documentSnapshot.getString("age"));
//                uemail.setText(documentSnapshot.getString("email"));
//                unumber.setText(documentSnapshot.getString("number"));
//
//                Log.d("info", "working");
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentid = user.getUid();
        DocumentReference reference;
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        reference = firestore.collection("Userdata").document(currentid);

        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()){

                            String name = task.getResult().getString("name");
                            String age = task.getResult().getString("age");
                            String email = task.getResult().getString("email");
                            String number = task.getResult().getString("number");

                            uname.setText(name);
                            uage.setText(age);
                            uemail.setText(email);
                            unumber.setText(number);


                        }
//                        else {
//                            Intent intent = new Intent(Registeryourself.this.startActionMode(), UserProfile.class);
//
//
//                        }
                    }
                });
    }
}
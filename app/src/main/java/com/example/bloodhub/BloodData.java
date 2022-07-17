package com.example.bloodhub;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BloodData extends AppCompatActivity {

    private FirebaseFirestore db;
    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_data);
        db = FirebaseFirestore.getInstance();
        recycleView = findViewById(R.id.recycleView);
        db.collection("Userdata").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<modelClass> modelClasses = new ArrayList<>();
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                String age = d.getString("age");
                                String email = d.getString("email");
                                String name = d.getString("name");
                                String number = d.getString("number");

                                modelClass temp = new modelClass(age, email, name, number);
                                modelClasses.add(temp);
                            }

                            setDataAdapter(modelClasses);
                        } else {
                            Toast.makeText(BloodData.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BloodData.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    void setDataAdapter( List<modelClass> modelClasses) {
        adapterView adapterView = new adapterView(modelClasses);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(BloodData.this));
        recycleView.setAdapter(adapterView);
    }
}
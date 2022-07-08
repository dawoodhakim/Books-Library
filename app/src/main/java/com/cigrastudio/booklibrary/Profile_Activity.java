package com.cigrastudio.booklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Profile_Activity extends AppCompatActivity {
        EditText name,age,hobby;
        Button done;
        FirebaseAuth firebaseAuth;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        FirebaseUser user;
        String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.name_profile_acivity);
        age=findViewById(R.id.age_profile_activity);
        hobby=findViewById(R.id.hobby_profile_activity);
        done=findViewById(R.id.btn_done_profile_activity);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("users");
        user=firebaseAuth.getCurrentUser();
        userId=user.getUid();

//        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.child("name").exists()&&snapshot.child("age").exists()&&snapshot.child("hobby").exists()){
//                    name.setText(String.valueOf(snapshot.child("name").getValue()));
//                    age.setText(String.valueOf(snapshot.child("age").getValue()));
//                    hobby.setText(String.valueOf(snapshot.child("hobby").getValue()));
//                    Intent intent = new Intent(Profile_Activity.this, Bottom_navigation_activity.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    name.setText("");
//                    age.setText("");
//                    hobby.setText("");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=name.getText().toString();
                String userage=age.getText().toString();
                String userhobby=hobby.getText().toString();
                HashMap<String,Object> user=new HashMap<>();
                user.put("name",username);
                user.put("age",userage);
                user.put("hobby",userhobby);

                databaseReference.child(userId).updateChildren(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(Profile_Activity.this, Bottom_navigation_activity.class);
                        startActivity(intent);
                        Toast.makeText(Profile_Activity.this, "successfull", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile_Activity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}
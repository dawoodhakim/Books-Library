package com.cigrastudio.booklibrary.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cigrastudio.booklibrary.AdapterClass;
import com.cigrastudio.booklibrary.ModelClass;
import com.cigrastudio.booklibrary.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class profileFragment extends Fragment {

    EditText name,age,hobby;
    Button done;
    TextView txt_profile;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String userId;
    RecyclerView recyclerView;
    Button view_profile;
    ArrayList<ModelClass> userData;
Context context;
    public profileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.cigrastudio.booklibrary.fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView=view.findViewById(R.id.recyclerview_profile_fragment);
        name=view.findViewById(R.id.name_profile_fragment);
        age=view.findViewById(R.id.age_profile_fragment);
        hobby=view.findViewById(R.id.hobby_profile_fragment);
        done=view.findViewById(R.id.btn_done_profile_fragment);
        view_profile=view.findViewById(R.id.btn_uploaded_profile);
        txt_profile=view.findViewById(R.id.heading);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
//        databaseReference=firebaseDatabase.getReference().child("users");
        user=firebaseAuth.getCurrentUser();
        userId=user.getUid();
        databaseReference=firebaseDatabase.getReference().child("users");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("name").exists() && snapshot.child("age").exists() && snapshot.child("hobby").exists()){
                    name.setText(String.valueOf(snapshot.child("name").getValue()));
                    hobby.setText(String.valueOf(snapshot.child("hobby").getValue()));
                    age.setText(String.valueOf(snapshot.child("age").getValue()));

                }else{
                    name.setText("");
                    hobby.setText("");
                    age.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
//                        Intent intent = new Intent(Profile_Activity.this, Bottom_navigation_activity.class);
//                        startActivity(intent);
                        Toast.makeText(getActivity(), "successfull", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = user.getUid().toString();
                name.setVisibility(View.GONE);
                age.setVisibility(View.GONE);
                hobby.setVisibility(View.GONE);
                done.setVisibility(View.GONE);
                txt_profile.setVisibility(View.GONE);
                view_profile.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                databaseReference.child(uid).child("uploads").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userData=new ArrayList<>();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            ModelClass model = dataSnapshot.getValue(ModelClass.class);
                            String userid = dataSnapshot.getKey();
                            model.setUserid(userid);
                            userData.add(model);
                        }
                        AdapterClass adaptor=new AdapterClass(userData,context);
                        recyclerView.setAdapter(adaptor);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


        return  view;
    }
}
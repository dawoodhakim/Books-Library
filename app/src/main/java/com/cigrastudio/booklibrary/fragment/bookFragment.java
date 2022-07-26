package com.cigrastudio.booklibrary.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cigrastudio.booklibrary.AdapterClass;
import com.cigrastudio.booklibrary.AdapterClass_Book_fragment;
import com.cigrastudio.booklibrary.ModelClass;
import com.cigrastudio.booklibrary.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class bookFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ArrayList<ModelClass> userData;
    FirebaseUser currentuser;
    RecyclerView recyclerView;
    Context context;
    String key;

    ModelClass model;

    public bookFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.cigrastudio.booklibrary.fragment
        View view= inflater.inflate(R.layout.fragment_book, container, false);
        recyclerView=view.findViewById(R.id.recyclerview_book_fragment);
        firebaseAuth=FirebaseAuth.getInstance();
        currentuser=firebaseAuth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("books");
        Log.d("TAG", "onCreateView: " + firebaseDatabase);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userData=new ArrayList<>();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid().toString();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        model = dataSnapshot1.getValue(ModelClass.class);
                        String userid = dataSnapshot.getKey();
                        key = dataSnapshot.getKey();
                        model.setKey(key);
                        userData.add(model);
                    }


                    Log.d("TAG", "Key is: " + key);
                }
                AdapterClass_Book_fragment adaptor=new AdapterClass_Book_fragment(userData,context);
                recyclerView.setAdapter(adaptor);

//                Log.d("TAG", "User data is: " + userData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}
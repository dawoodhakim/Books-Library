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

import com.cigrastudio.booklibrary.AdapterClass_Book_fragment;
import com.cigrastudio.booklibrary.AdapterClass_favourite_fragment;
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


public class favouriteFragment extends Fragment {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<ModelClass> userData;
    RecyclerView recyclerView;
    Context context;
    String uid = user.getUid();


    public favouriteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.cigrastudio.booklibrary.fragment
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView=view.findViewById(R.id.recycler_view_favourite_fragment);
        firebaseDatabase=FirebaseDatabase.getInstance();
        String uid = user.getUid().toString();
        databaseReference=firebaseDatabase.getReference().child("users");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child(uid).child("Favourite").addValueEventListener(new ValueEventListener() {
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
                AdapterClass_favourite_fragment adaptor=new AdapterClass_favourite_fragment(userData,context);
                recyclerView.setAdapter(adaptor);

//                Log.d("TAG", "User data is: " + userData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return  view;
    }
}
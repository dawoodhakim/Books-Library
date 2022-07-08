package com.cigrastudio.booklibrary.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cigrastudio.booklibrary.Login_Activity;
import com.cigrastudio.booklibrary.R;
import com.google.firebase.auth.FirebaseAuth;


public class logoutFragment extends Fragment {

    Button logout;
    public logoutFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this com.cigrastudio.booklibrary.fragment
       View view= inflater.inflate(R.layout.fragment_logout, container, false);
        logout=view.findViewById(R.id.btn_logout_fragment);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("LOGOUT");
                alertDialog.setMessage("Are sure you want to logout?");
                alertDialog.setCancelable(false);

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();


                        Intent intent=new Intent(getActivity(), Login_Activity.class);
                        startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.create().show();


            }
        });
       return  view;
    }
}
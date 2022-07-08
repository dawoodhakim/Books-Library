package com.cigrastudio.booklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Signup_Activity extends AppCompatActivity {
        EditText email,password,confirm;
        Button signup;
        TextView have_account;
        FirebaseAuth firebaseAuth;
        ProgressBar progressBar;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.email_signup);
        password=findViewById(R.id.password_signup);
        confirm=findViewById(R.id.confirm_signup);
        signup=findViewById(R.id.btn_signup);
        have_account=findViewById(R.id.have_an_account);
        firebaseAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email=email.getText().toString();
                String user_password=password.getText().toString();
                String user_confirm=confirm.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                if(user_email.equals("")){
                email.setError("please enter your email");
                progressBar.setVisibility(View.GONE);
                }
                else if(user_password.equals("")){
                    password.setError("please enter your password");
                    progressBar.setVisibility(View.GONE);
                }else if(user_confirm.equals("")){
                    confirm.setError("please enter your password again");
                    progressBar.setVisibility(View.GONE);
                }else{
                    if(user_password.length()<6){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Signup_Activity.this, "your password is too short", Toast.LENGTH_SHORT).show();
                    }
                    else{if(!user_password.equals(user_confirm)){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Signup_Activity.this, "please confirm your password again", Toast.LENGTH_SHORT).show();
                    }
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Signup_Activity.this, "account created successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Signup_Activity.this,Login_Activity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Signup_Activity.this, "Error!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    }
                }
            }
        });
        have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup_Activity.this,Login_Activity.class);
                startActivity(intent);
            }
        });

    }
}
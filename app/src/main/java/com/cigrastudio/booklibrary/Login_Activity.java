package com.cigrastudio.booklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Login_Activity extends AppCompatActivity {
        EditText email,password;
        Button login;
        TextView no_account;
        FirebaseAuth firebaseAuth;
        ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);
        login=findViewById(R.id.btn_login);
        no_account=findViewById(R.id.no_account);
        progressBar=findViewById(R.id.progressBar_login);
        firebaseAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail=email.getText().toString();
                String userpassword=password.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                if(useremail.equals("")){
                    email.setError("please enter your password");
                    progressBar.setVisibility(View.GONE);
                }else if(userpassword.equals("")){
                    password.setError("please enter your password");
                    progressBar.setVisibility(View.GONE);
                }else{
                    firebaseAuth.signInWithEmailAndPassword(useremail,userpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login_Activity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            Intent intent=new Intent(Login_Activity.this,Profile_Activity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Login_Activity.this, "Error!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,Signup_Activity.class);
                startActivity(intent);
            }
        });
    }
}
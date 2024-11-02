package com.example.taskmanageemnt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerActivity extends AppCompatActivity {
    private EditText editTextEmail,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Button button1=  findViewById(R.id.button_sign_in);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registerActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });

        Toast.makeText(registerActivity.this, "You can register", Toast.LENGTH_SHORT).show();
        editTextEmail=findViewById(R.id.editTextText);
        editTextPassword=findViewById(R.id.editTextTextPassword);
        Button buttonSignup=findViewById(R.id.buttonSignUp);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(registerActivity.this,"Please enter Email",Toast.LENGTH_LONG).show();
                    editTextEmail.setError("Email is required");
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(registerActivity.this,"Please enter Password",Toast.LENGTH_LONG).show();
                    editTextPassword.setError("Password is required");
                }
                else{
                    registerUser(email,password);
                }

            }
        });
    }
    //Register User
    private void registerUser(String email,String password)
    {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(registerActivity.this,"User Registere Successfully",Toast.LENGTH_LONG).show();
                    FirebaseUser firebaseUser=auth.getCurrentUser();

                    //send verification
                    firebaseUser.sendEmailVerification();
                    Intent intent = new Intent(registerActivity.this,ProfileActivity.class);
                    //prevent user from register page
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }
        });

    }
}
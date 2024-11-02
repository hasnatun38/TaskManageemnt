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

public class SigninActivity extends AppCompatActivity {
    private EditText editTextEmail,editTextPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Button button1=  findViewById(R.id.buttonSignUp);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });
        Toast.makeText(SigninActivity.this, "You can sign in", Toast.LENGTH_SHORT).show();
        editTextEmail=findViewById(R.id.editTextText);
        editTextPassword=findViewById(R.id.editTextTextPassword);
        Button buttonSignup=findViewById(R.id.button_sign_in);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SigninActivity.this,"Please enter Email",Toast.LENGTH_LONG).show();
                    editTextEmail.setError("Enter Email");
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(SigninActivity.this,"Please enter Password",Toast.LENGTH_LONG).show();
                    editTextPassword.setError("Enter Password");
                }
                else{
                    loginUser(email,password);
                }

            }
        });
    }
    private void loginUser(String email,String password)
    {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SigninActivity.this,"Sign in Successful",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SigninActivity.this,ProfileActivity.class);
                    //prevent user from register page
                    startActivity(intent);



                }else{
                    Toast.makeText(SigninActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
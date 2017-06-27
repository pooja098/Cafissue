
package com.example.pooja.cafissues;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupUser extends AppCompatActivity {

    Button signUp,back;
    EditText email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user);

        auth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        signUp = (Button) findViewById(R.id.signupUserBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String emailt = email.getText().toString().trim();
               final String passt = password.getText().toString().trim();

                if (TextUtils.isEmpty(emailt)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passt)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(emailt, passt)
                        .addOnCompleteListener(signupUser.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(signupUser.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(signupUser.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    SharedPreferences preferences = getSharedPreferences("Login",MODE_PRIVATE);
                                    preferences.edit().putString("user",emailt);
                                    startActivity(new Intent(signupUser.this, usersActivity.class));
                                    finish();
                                }
                            }
                });
            }
        });

        back = (Button) findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupUser.this,Signup.class));
            }
        });


    }
}
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

public class signinUser extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button signin,back;
    private FirebaseAuth auth;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_user);

        auth = FirebaseAuth.getInstance();

        /*if (auth.getCurrentUser() != null) {
            startActivity(new Intent(signinUser.this, usersActivity.class));
            finish();
        }*/

        inputEmail = (EditText) findViewById(R.id.editText);
        inputPassword = (EditText) findViewById(R.id.editText2);
        signin = (Button) findViewById(R.id.signinBtn);
        back = (Button) findViewById(R.id.button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signinUser.this,Signup.class));
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signinUser.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        Toast.makeText(signinUser.this,"Password must be 6 characters long.",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(signinUser.this,"Authentication failed,check your email or password", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    ID=email;
                                    SharedPreferences preferences = getSharedPreferences("Login",MODE_PRIVATE);
                                    preferences.edit().putString("user",email);
                                    Intent intent = new Intent(signinUser.this,usersActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }
                        });
            }
        });

    }
    public String GetID(){
        return ID;
    }
}

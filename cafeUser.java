package com.example.pooja.cafissues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cafeUser extends AppCompatActivity {

    Button auth,back;
    EditText pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_user);

        auth = (Button) findViewById(R.id.authbtn);
        back = (Button) findViewById(R.id.button3);
        pass = (EditText) findViewById(R.id.editText3);

        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().equals("12345678")){
                    startActivity(new Intent(cafeUser.this,committeeUserActivity.class));
                }
                else{
                    Toast.makeText(cafeUser.this,"Wrong Password. Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
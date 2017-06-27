package com.example.pooja.cafissues;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Signup extends AppCompatActivity {

    Button signup,signin,cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup = (Button) findViewById(R.id.sign_up_button);
        signin = (Button) findViewById(R.id.sign_in_button);
        cafe = (Button) findViewById(R.id.cafe_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,signupUser.class));
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,signinUser.class));
            }
        });

        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,cafeUser.class));
            }
        });
    }
}
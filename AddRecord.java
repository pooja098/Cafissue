package com.example.pooja.cafissues;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AddRecord extends AppCompatActivity {

    ImageButton button;
    private int CAMERA_REQUEST = 1;
    Button submit,back;
    EditText sub,desc,email;
    Firebase Ref,Old;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        sub = (EditText)findViewById(R.id.subET);
        desc = (EditText)findViewById(R.id.descET);
        email = (EditText)findViewById(R.id.Email);
        submit = (Button)findViewById(R.id.submitbtn);
        back = (Button)findViewById(R.id.discardbtn);
        Firebase.setAndroidContext(this);
        Ref = new Firebase("https://cafissues-51859.firebaseio.com/");
        Old = new Firebase("https://cafissues-51859.firebaseio.com/Current");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String title = sub.getText().toString();
                String descr = desc.getText().toString();
                String Email = email.getText().toString();
                Old.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        value = dataSnapshot.getValue(Integer.class);

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                value++;
                String Str = String.valueOf(value);
                Firebase childID = Ref.push();
                Firebase child = childID.child("Title");
                child.setValue(title);
                child = childID.child("Desc");
                child.setValue(descr);
                child = childID.child("UserID");
                child.setValue(Email);
                child = childID.child("Checked");
                child.setValue("false");
                child = childID.child("Varified");
                child.setValue("false");
                Old.setValue(value);
                startActivity(new Intent(AddRecord.this,usersActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddRecord.this,usersActivity.class));
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            button.setImageBitmap(photo);
        }
    }
}
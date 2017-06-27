package com.example.pooja.cafissues;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Map;

public class usersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    FloatingActionButton fab;
    ListView l;
    ArrayList<Issue> list = new ArrayList<Issue>();
    CustomListAdapter adapter;
    private FirebaseAuth auth;
    Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        auth = FirebaseAuth.getInstance();

        l = (ListView) findViewById(R.id.listView);

        ref = new Firebase("https://cafissues-51859.firebaseio.com/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,String> map = dataSnapshot.getValue(Map.class);

                String email = map.get("UserID");
                String sub = map.get("Title");
                String desc = map.get("Desc");
                /*String status = map.get("Checked");
                System.out.println(status);

                if(status.equals("True") || status.equals("1")) {
                    Issue issue = new Issue(sub, desc, email, true);
                    list.add(issue);
                }
                else{
                    Issue issue = new Issue(sub, desc, email, false);
                    list.add(issue);
                }*/
                Issue issue = new Issue(sub,desc,email,false);
                list.add(issue);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(usersActivity.this,"An Error Occured",Toast.LENGTH_SHORT).show();
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(usersActivity.this,AddRecord.class));
            }
        });
        Resources res = getResources();
        adapter = new CustomListAdapter(usersActivity.this,list,res);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this,EditIssue.class);
        i.putExtra("position",position);
        startActivity(i);;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_usersactivity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_all){
            startActivity(new Intent(usersActivity.this,othersActivity.class));
        }
        else if(id == R.id.action_logout2){
            auth.signOut();
            FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        startActivity(new Intent(usersActivity.this, Signup.class));
                        finish();
                    }
                }
            };
        }
        return true;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete?");

        builder.setPositiveButton("Sure",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.setMessage("Are you sure you want to delete the record?");
        return false;
    }
}

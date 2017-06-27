
package com.example.pooja.cafissues;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class othersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView l;
    ArrayList<Issue> list = new ArrayList<>();
    CustomListAdapter adapter;
    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);

        l = (ListView) findViewById(R.id.listView3);
        Resources res = getResources();
        adapter = new CustomListAdapter(this,list,res);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);

        ref = new Firebase("https://cafissues-51859.firebaseio.com/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot post : dataSnapshot.getChildren()){
                    Map<String,String> map = dataSnapshot.getValue(Map.class);

                    String email = map.get("UserID");
                    String sub = map.get("Title");
                    String desc = map.get("Desc");
                    Issue issue = new Issue(sub,desc,email,false);
                    System.out.println("------------------------"+issue.getSubject());

                    list.add(issue);
                    System.out.println("------------------------"+list.get(list.size()-1).getSubject());
                    l.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(othersActivity.this,"An Error Occured",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this,EditIssueCafe.class);
        i.putExtra("position",position);
        startActivity(i);
    }
}

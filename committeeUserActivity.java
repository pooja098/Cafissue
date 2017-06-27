package com.example.pooja.cafissues;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class committeeUserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView l;
    CustomListAdapter adapter;
    ArrayList<Issue> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_user);

        l = (ListView) findViewById(R.id.listView2);

        Resources res = getResources();
        adapter = new CustomListAdapter(this,list,res);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(committeeUserActivity.this,EditIssueCafe.class);
        i.putExtra("position",position);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_committee_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            startActivity(new Intent(this,Signup.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}

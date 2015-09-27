package com.example.hp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
    }

    public void eventsButtonClick(View v) {
        ((TextView)findViewById(R.id.worktext)).setText("changed");
        setContentView(R.layout.activity_home_page);
    }
    public void todoButtonClick(View v) {
        ((TextView)findViewById(R.id.worktext)).setText("changed");
        setContentView(R.layout.activity_home_page);
    }
    public void weatherkButtonClick(View v) {
        ((TextView)findViewById(R.id.worktext)).setText("changed");
        setContentView(R.layout.activity_home_page);
    }

    public void workButtonClick(View v) {
        ((TextView)findViewById(R.id.worktext)).setText("changed");
        setContentView(R.layout.work_page);
    }

    public void physButtonClick(View v) {
        ((TextView)findViewById(R.id.phystext)).setText("changed");
        setContentView(R.layout.physical_page);
    }

    public void foodButtonClick(View v) {
        ((TextView)findViewById(R.id.foodtext)).setText("changed");
        setContentView(R.layout.food_page);
    }

    public void otherButtonClick(View v){
        ((TextView)findViewById(R.id.misctext)).setText("changed");
        setContentView(R.layout.other_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

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

    public void backHomeButtonClick(View v) {
        setContentView(R.layout.homepage);
    }
    public void backEventButtonClick(View v) {
        setContentView(R.layout.event_home);
    }
    public void backCalendarButtonClick(View v) {
        setContentView(R.layout.weather_home);
    }
    public void backToDoButtonClick(View v) {
        setContentView(R.layout.activity_home_page);
    }

    public void eventsButtonClick(View v) {
        setContentView(R.layout.event_home);
    }
    public void todoButtonClick(View v) {
        setContentView(R.layout.activity_home_page);
    }
    public void weatherButtonClick(View v) {
        setContentView(R.layout.weather_home);
    }

    public void workButtonClick(View v) {
        setContentView(R.layout.work_page);
    }
    public void physButtonClick(View v) {
        setContentView(R.layout.physical_page);
    }
    public void foodButtonClick(View v) {
        setContentView(R.layout.food_page);
    }
    public void otherButtonClick(View v){
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

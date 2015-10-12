package com.example.hp.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> EXTRA_MESSAGE_EVENT2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
    }

    /********************************  For the Main Menu ****************************************
     *
     * @description
     *
     *********************************************************************************************/
    public void eventsButtonClick(View v) {
        setContentView(R.layout.activity_event_home);
        Intent intent = new Intent(this, eventHomeActivity.class);
        startActivity(intent);
    }
    public void todoButtonClick(View v) {
        setContentView(R.layout.activity_todo_home);
        Intent intent = new Intent(this, todoHomeActivity.class);
        startActivity(intent);
    }
    public void weatherButtonClick(View v) {

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

package com.example.hp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ViewEvent extends AppCompatActivity {

    public static String[] EXTRA_MESSAGE_EVENT = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String[] temp = extras.getStringArray("eventView");

        String[] events = new String[3];
        for(int i=0;i<3;i++){events[i] = "Empty";}
        if(temp != null){
            for(int i=0;i<3;i++)
                events[i] = temp[i];
        }

        TextView t = (TextView) findViewById(R.id.eventTitle); t.setText(events[0]);
        TextView da = (TextView) findViewById(R.id.eventDate); da.setText(events[1]);
        TextView de = (TextView) findViewById(R.id.eventDesc); de.setText(events[2]);

    }

    public void backToEvents(View v) {
        Intent intent = new Intent(this, eventHomeActivity.class);
        readFromFile();
        intent.putExtra("events", EXTRA_MESSAGE_EVENT);
        startActivity(intent);
    }

    private void readFromFile() {
        try {
            FileInputStream fis = openFileInput("eventNames");
            for(int i=0;i<20;i++) {EXTRA_MESSAGE_EVENT[i] = "";}

            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                int count = 0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    Log.e("login activity", "Line: " + count + "| Value: " + receiveString);
                    EXTRA_MESSAGE_EVENT[count] = receiveString;
                    count++;
                }
                fis.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
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

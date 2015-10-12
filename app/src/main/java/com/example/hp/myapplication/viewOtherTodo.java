package com.example.hp.myapplication;

import android.content.Context;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class viewOtherTodo extends AppCompatActivity {

    public static ArrayList<String> EXTRA_MESSAGE_EVENT = new ArrayList<String>();
    public static String title;
    public static String dateE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_other_todo);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String[] temp = extras.getStringArray("todoView");

        String[] events = new String[4];
        for(int i=0;i<4;i++){events[i] = "Empty";}
        if(temp != null){
            for(int i=0;i<4;i++)
                events[i] = temp[i];
        }
        title = events[0];
        dateE = events[1];TextView t = (TextView) findViewById(R.id.eventTitle); t.setText(events[0]);
        TextView da = (TextView) findViewById(R.id.eventDate); da.setText(events[1]);
        TextView de = (TextView) findViewById(R.id.eventDesc); de.setText(events[2]);
        TextView time = (TextView) findViewById(R.id.eventTime); de.setText(events[3]);
        TextView priority = (TextView) findViewById(R.id.eventPriority); de.setText(events[4]);

    }

    public void backToOther(View v) {
        Intent intent = new Intent(this, todoOtherHomeActivity.class);
        startActivity(intent);
    }

    public void deleteOtherTodo(View v) {
        int index = 0;
        readFromFile();

        for(int i=0;i<EXTRA_MESSAGE_EVENT.size();i++){
            //Log.e("Exception", "deleteFailed: " + EXTRA_MESSAGE_EVENT.get(i));
            //Log.e("Exception", "deleteFailed: " + title);
            if(EXTRA_MESSAGE_EVENT.get(i).equals("(" + dateE + ") " + title)){
                index = i;
            }
        }

        //removing event from eventName file
        EXTRA_MESSAGE_EVENT.remove(index);

        //split data again
        String titleTemp;
        String dateTemp;

        //Overwriting the file
        try {
            FileOutputStream fos = openFileOutput("OtherTester1", Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            for(int i=0;i<EXTRA_MESSAGE_EVENT.size();i++){
                dateTemp = EXTRA_MESSAGE_EVENT.get(i).substring(1,11);
                titleTemp = EXTRA_MESSAGE_EVENT.get(i).substring(13);
                outputStreamWriter.write(titleTemp + "\n" + dateTemp + "\n");
            }

            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        Intent intent = new Intent(this, todoOtherHomeActivity.class);
        startActivity(intent);
    }

    private void readFromFile() {
        try {
            EXTRA_MESSAGE_EVENT = new ArrayList<String>();
            FileInputStream fis = openFileInput("OtherTester1");

            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ( (receiveString = bufferedReader.readLine()) != null) {
                    //Log.e("login activity","| Value: " + receiveString);
                    EXTRA_MESSAGE_EVENT.add("(" + bufferedReader.readLine() + ") " + receiveString);
                }
                fis.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        Collections.sort(EXTRA_MESSAGE_EVENT);
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
package com.example.hp.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class eventAddActivity extends AppCompatActivity {

    public static ArrayList<String> EXTRA_MESSAGE_EVENT2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        getIntent();
        readFromFile();
    }

    private void readFromFile() {
        try {
            EXTRA_MESSAGE_EVENT2 = new ArrayList<String>();
            FileInputStream fis = openFileInput("test5");

            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ( (receiveString = bufferedReader.readLine()) != null) {
                    //Log.e("login activity","| Value: " + receiveString);
                    EXTRA_MESSAGE_EVENT2.add("(" + bufferedReader.readLine() + ")" + receiveString);
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

    public void saveeventButtonClick(View v) {
        final EditText editTitle =  (EditText) findViewById(R.id.titletext);
        final EditText editDate =  (EditText) findViewById(R.id.datetext);
        final EditText editDescr =  (EditText) findViewById(R.id.descriptext);

        String title = editTitle.getText().toString();
        String date = editDate.getText().toString();
        String descr = editDescr.getText().toString();

        //Upload new data to server
        writeToFile(title, date);
        newEventFile(title, date, descr);

        Intent intent = new Intent(this, eventHomeActivity.class);
        startActivity(intent);
    }

    private void writeToFile(String data, String date) {
        try {
            FileOutputStream fos = openFileOutput("test5", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data + "\n" + date + "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void newEventFile(String t,String dat,String des){
        String data = t + "\n" + dat + "\n" + des;
        try {
            FileOutputStream fos = openFileOutput(t, Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        Collections.sort(EXTRA_MESSAGE_EVENT2);
    }

    public void backEventButtonClick(View v) {
        Intent intent = new Intent(this, eventHomeActivity.class);
        startActivity(intent);
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

package com.example.hp.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class eventAddActivity extends AppCompatActivity {

    public static String[] EXTRA_MESSAGE_EVENT = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

        Intent intent = getIntent();
        String message = intent.getStringExtra(eventHomeActivity.EXTRA_MESSAGE);
    }

    public void saveeventButtonClick(View v) {
        final EditText editTitle =  (EditText) findViewById(R.id.titletext);
        final EditText editDate =  (EditText) findViewById(R.id.datetext);
        final EditText editDescr =  (EditText) findViewById(R.id.descriptext);

        String title = editTitle.getText().toString();
        String date = editDate.getText().toString();
        String descr = editDescr.getText().toString();

        //Upload new data to server
        //Start the new activity
        writeToFile(title + '\n');

        Intent intent = new Intent(this, eventHomeActivity.class);
        readFromFile();
        intent.putExtra("events", EXTRA_MESSAGE_EVENT);
        startActivity(intent);
    }

    private void readFromFile() {
        try {
            //AssetManager assManager = getApplicationContext().getAssets();
            //InputStream inputStream = assManager.open("events.txt");
            FileInputStream fis = openFileInput("eventsNew");
            for(int i=0;i<20;i++) {
                EXTRA_MESSAGE_EVENT[i] = "";
            }

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

    private void writeToFile(String data) {
        try {
            FileOutputStream fos = openFileOutput("eventsNew", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
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

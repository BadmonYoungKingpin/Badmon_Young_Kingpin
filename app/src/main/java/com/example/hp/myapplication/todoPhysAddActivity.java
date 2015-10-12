package com.example.hp.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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

public class todoPhysAddActivity extends AppCompatActivity {

    public static ArrayList<String> EXTRA_MESSAGE_WORK = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_phys_add);
        getIntent();
        readFromFile();
    }

    private void readFromFile() {
        try {
            EXTRA_MESSAGE_WORK = new ArrayList<String>();
            FileInputStream fis = openFileInput("PhysTester1");

            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ( (receiveString = bufferedReader.readLine()) != null) {
                    //Log.e("login activity","| Value: " + receiveString);
                    EXTRA_MESSAGE_WORK.add("(" + bufferedReader.readLine() + ")" + receiveString);
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

    public void savePhysButton(View v) {
        final EditText editTitle =  (EditText) findViewById(R.id.physical_title_content);
        final EditText editDate =  (EditText) findViewById(R.id.physical_date_content);
        final EditText editDescr =  (EditText) findViewById(R.id.physical_desc_content);
        final EditText editTime =  (EditText) findViewById(R.id.physical_time_content);
        final EditText editPriority =  (EditText) findViewById(R.id.work_priority_content);


        String title = editTitle.getText().toString();
        String date = editDate.getText().toString();
        String descr = editDescr.getText().toString();
        String time = editTime.getText().toString();
        String priority = editPriority.getText().toString();

        //Upload new data to server
        writeToFile(title, date);
        newWorkFile(title, date, descr,time,priority);

        Intent intent = new Intent(this, todoPhysHomeActivity.class);
        startActivity(intent);
    }

    public void backToPhys(View v) {
        Intent intent = new Intent(this, todoPhysHomeActivity.class);
        startActivity(intent);
    }

    private void writeToFile(String data, String date) {
        try {
            FileOutputStream fos = openFileOutput("PhysTester1", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data + "\n" + date + "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void newWorkFile(String t,String dat,String des,String time,String prior){
        String data = t + "\n" + dat + "\n" + des + "\n" +time + "\n" + prior +"\n";
        try {
            FileOutputStream fos = openFileOutput(t, Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        Collections.sort(EXTRA_MESSAGE_WORK);
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

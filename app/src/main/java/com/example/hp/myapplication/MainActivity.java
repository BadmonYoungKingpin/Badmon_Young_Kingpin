package com.example.hp.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String[] EXTRA_MESSAGE_EVENT = new String[20];
    public static String EXTRA_MESSAGE_TODO = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        readFromFile();
    }

    private void readFromFile() {
        try {
            for(int i=0;i<20;i++){EXTRA_MESSAGE_EVENT[i] = "";}
            FileInputStream fis = openFileInput("eventNames");

            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                int count = 0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    //Log.e("login activity", "Line: " + count + "| Value: " + receiveString);
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

    /********************************  For the Main Menu ****************************************
     *
     * @description
     *
     *********************************************************************************************/
    public void eventsButtonClick(View v) {
        setContentView(R.layout.activity_event_home);
        Intent intent = new Intent(this, eventHomeActivity.class);

        intent.putExtra("events", EXTRA_MESSAGE_EVENT);
        startActivity(intent);
    }
    public void todoButtonClick(View v) {
        setContentView(R.layout.activity_todo_home);
        Intent intent = new Intent(this, todoHomeActivity.class);

        //Send data along to retrieve and use with new activity
        //Data to be sent is events stored on the server
        //Retrieve data, put into list1
        String list1 = "asdasdasd";
        intent.putExtra(EXTRA_MESSAGE_TODO, list1);
        //Start the new activity
        startActivity(intent);
    }
    public void weatherButtonClick(View v) {


        
    }

    /********************************  For the WEATHERDisplay *************************************
     *
     * @description
     *
     *********************************************************************************************/


    /********************************  For the TODOList *******************************************
     *
     * @description
     *
     *********************************************************************************************/
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


    /********************************  DEFAULT STUFF **********************************************
     *
     * @description
     *
     *********************************************************************************************/
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

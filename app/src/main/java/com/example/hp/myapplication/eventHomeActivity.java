package com.example.hp.myapplication;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class eventHomeActivity extends ListActivity {

    private static String[] EVENT_DATA = new String[4];
    public static ArrayList<String> EXTRA_MESSAGE_EVENT = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);

        getIntent();
        readFromFile();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, EXTRA_MESSAGE_EVENT);
        getListView().setAdapter(adapter);

        //On item click
        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(eventHomeActivity.this, ViewEvent.class);
                String val =(String) parent.getItemAtPosition(position);

                if(val != "EMPTY") {
                    readEventFile(val);
                    intent.putExtra("eventView", EVENT_DATA);
                    startActivity(intent);
                }
            }
        });
    }

    /********************************  For the EVENTSList ****************************************
     *
     * @description xczc
     *
     *********************************************************************************************/
    private void readFromFile() {
        try {
            EXTRA_MESSAGE_EVENT = new ArrayList<String>();
            FileInputStream fis = openFileInput("test5");

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

    public void addeventButtonClick(View v) {
        Intent intent2 = new Intent(this, eventAddActivity.class);
        startActivity(intent2);
    }

    private void readEventFile(String title){
        try {
            for(int i=0;i<4;i++) {EVENT_DATA[i] = "";}

            String newTitleString = title.substring(13);
            FileInputStream fis = openFileInput(newTitleString);

            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                int count = 0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    //Log.e("login activity", "Line: " + count + "| Value: " + receiveString);
                    EVENT_DATA[count] = receiveString;
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

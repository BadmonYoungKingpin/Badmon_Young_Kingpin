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

public class eventHomeActivity extends ListActivity {

    private static String[] EVENT_DATA = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String[] temp = extras.getStringArray("events");

        String[] message = new String[20];
        for(int i=0;i<20;i++){message[i] = "Empty";}
        if(temp != null){
            for(int i=0;i<20;i++){
                if(temp[i] != "")
                    message[i] = temp[i];
            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, message);
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
    public void addeventButtonClick(View v) {
        Intent intent2 = new Intent(this, eventAddActivity.class);
        startActivity(intent2);
    }

    private void readEventFile(String title){
        for(int x=0;x<3;x++)
            EVENT_DATA[x] = "EMPTY";

        try {
            FileInputStream fis = openFileInput(title);
            for(int i=0;i<3;i++) {EVENT_DATA[i] = "";}

            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                int count = 0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    Log.e("login activity", "Line: " + count + "| Value: " + receiveString);
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

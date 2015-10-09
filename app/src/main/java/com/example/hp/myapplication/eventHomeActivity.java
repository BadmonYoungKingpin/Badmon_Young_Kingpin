package com.example.hp.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class eventHomeActivity extends ListActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();

        String[] temp = new String[20];
        temp = extras.getStringArray("events");

        String[] message = new String[20];
        for(int i=0;i<20;i++){message[i] = "Empty";}
        if(temp != null){
            for(int i=0;i<20;i++)
                message[i] = temp[i];
        }

        ListView listV = new ListView(this);

        //access EVENTDATA from intent
        //list1 needs to be populated with EVENTDATA

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, message);
        getListView().setAdapter(adapter);
    }

    /********************************  For the EVENTSList ****************************************
     *
     * @description xczc
     *
     *********************************************************************************************/
    public void addeventButtonClick(View v) {
        //setContentView(R.layout.activity_event_add);
        Intent intent2 = new Intent(this, eventAddActivity.class);
        //Send data along to retrieve and use with new activity
        //Data to be sent is events stored on the server
        //Retrieve data, put into list1
        //Start the new activity
        startActivity(intent2);
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

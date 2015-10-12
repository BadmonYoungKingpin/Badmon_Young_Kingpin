package com.example.hp.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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

public class todoOtherHomeActivity extends ListActivity {

    private static String[] EVENT_DATA = new String[4];
    public static ArrayList<String> EXTRA_MESSAGE_TODO = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_other_home);
        getIntent();
        readFromFile();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, EXTRA_MESSAGE_TODO);
        getListView().setAdapter(adapter);

        //On item click
        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(todoOtherHomeActivity.this, viewOtherTodo.class);
                String val =(String) parent.getItemAtPosition(position);

                if(val != "EMPTY") {
                    readTodoFile(val);
                    intent.putExtra("todoView", EVENT_DATA);
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
            EXTRA_MESSAGE_TODO = new ArrayList<String>();
            FileInputStream fis = openFileInput("OtherTester");

            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ( (receiveString = bufferedReader.readLine()) != null) {
                    //Log.e("login activity","| Value: " + receiveString);
                    EXTRA_MESSAGE_TODO.add("(" + bufferedReader.readLine() + ") " + receiveString);
                }
                fis.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        Collections.sort(EXTRA_MESSAGE_TODO);
    }

    public void addOtherButtonClick(View v) {
        Intent intent2 = new Intent(this, todoOtherAddActivity.class);
        startActivity(intent2);
    }
    public void back(View v){
        Intent intent2 = new Intent(this, todoHomeActivity.class);
        startActivity(intent2);
    }

    private void readTodoFile(String title){
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
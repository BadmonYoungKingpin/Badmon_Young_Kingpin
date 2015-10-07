package com.example.hp.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
    }

    /********************************  For the BACKButtons ****************************************
     *
     * @description
     *
     *********************************************************************************************/
    public void backHomeButtonClick(View v) {
        setContentView(R.layout.homepage);
    }
    public void backEventButtonClick(View v) {
        setContentView(R.layout.event_home);
    }
    public void backCalendarButtonClick(View v) {
        setContentView(R.layout.weather_home);
    }
    public void backToDoButtonClick(View v) {
        setContentView(R.layout.activity_home_page);
    }

    /********************************  For the Main Menu ****************************************
     *
     * @description
     *
     *********************************************************************************************/
    public void eventsButtonClick(View v) {
        setContentView(R.layout.event_home);
        Intent intent = new Intent(this, eventListPopActivity.class);

        /*ListView editText = (ListView) findViewById(android.R.id.list);
        String[] list1 = {"asdasdasd","addddaadad","hhfghfsdsa"};*/

        //Send data along to retrieve and use with new activity
        String list1 = "asdasdasd";
        intent.putExtra(EXTRA_MESSAGE, list1);
        //Start the new activity
        startActivity(intent);
    }
    public void todoButtonClick(View v) {
        setContentView(R.layout.activity_home_page);
    }
    public void weatherButtonClick(View v) {
        setContentView(R.layout.weather_home);
    }

    /********************************  For the EVENTSList ****************************************
     *
     * @description xczc
     *
     *********************************************************************************************/
    public void addeventButtonClick(View v) {
        setContentView(R.layout.event_new);
    }

    public void saveeventButtonClick(View v) {

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

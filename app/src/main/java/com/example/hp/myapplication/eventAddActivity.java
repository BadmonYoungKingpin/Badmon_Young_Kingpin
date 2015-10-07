package com.example.hp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class eventAddActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_new);

        Intent intent = getIntent();
        String message = intent.getStringExtra(eventListPopActivity.EXTRA_MESSAGE);
    }

    public void saveeventButtonClick(View v) {
        final EditText editTitle =  (EditText) findViewById(R.id.titletext);
        final EditText editDate =  (EditText) findViewById(R.id.datetext);
        final EditText editDescr =  (EditText) findViewById(R.id.descriptext);

        String title = editTitle.getText().toString();
        String date = editDate.getText().toString();
        String descr = editDescr.getText().toString();

        Intent intent = new Intent(this, eventListPopActivity.class);

        //Upload new data to server
        //Start the new activity
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

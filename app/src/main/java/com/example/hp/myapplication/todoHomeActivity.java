package com.example.hp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class todoHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_home);
    }

    public void todoWorkButtonClick(View v) {
        Intent intent = new Intent(this, todoWorkHomeActivity.class);

        startActivity(intent);
    }

    public void todoPhysButtonClick(View v) {
        Intent intent = new Intent(this, todoPhysHomeActivity.class);
        setContentView(R.layout.activity_todo_phys_home);
        startActivity(intent);
    }
    public void todoFoodButtonClick(View v) {
        Intent intent = new Intent(this, todoFoodHomeActivity.class);
        setContentView(R.layout.activity_todo_food_home);
        startActivity(intent);
    }
    public void todoOtherButtonClick(View v) {
        Intent intent = new Intent(this, todoOtherHomeActivity.class);
        setContentView(R.layout.activity_todo_other_home);
        startActivity(intent);
    }

    public void backButton(){
        Intent intent = new Intent(this, MainActivity.class);
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

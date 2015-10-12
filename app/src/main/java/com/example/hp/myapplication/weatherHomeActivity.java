package com.example.hp.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class weatherHomeActivity extends AppCompatActivity {
    private GPSTracker gps;
    private double latitude;
    private double longitiue;
    public static String[] HOME_LOCATION = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_home);
        getIntent();
        readFromFile();
    }

    public void showLocation(View v) {
        gps = new GPSTracker(this);

        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitiue = gps.getLongtitude();

            Toast.makeText(getApplicationContext(),
                    "Your Location is -\nLat: " + latitude + "\nLong: " + longitiue,
                    Toast.LENGTH_LONG).show();
        }else{
            gps.showSettingsAlert();
        }
    }

    public void viewWeatherfun(View v) {
        Intent intent = new Intent(this, yahooWeatherActivity.class);
        startActivity(intent);
    }

    public void saveLocation(View v) {
        gps = new GPSTracker(this);

        if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitiue = gps.getLongtitude();

        }else{
            gps.showSettingsAlert();
        }

        String lat = String.valueOf(latitude);
        String longi = String.valueOf(longitiue);

        //Upload new data to server
        newLocationFile(lat, longi);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void readFromFile() {
        try {
            FileInputStream fis = openFileInput("homeLocation");
            for(int i=0;i<2;i++) {HOME_LOCATION[i] = "";}

            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                int count = 0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    Log.e("login activity", "Coordinates: " + receiveString);
                    HOME_LOCATION[count] = receiveString;
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

    private void newLocationFile(String la,String lo){
        String data = la + "\n" + lo;
        try {
            FileOutputStream fos = openFileOutput("homeLocation", Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

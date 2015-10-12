package com.example.hp.myapplication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.myapplication.data.Chanel;
import com.example.hp.myapplication.data.Item;
import com.example.hp.myapplication.service.WeatherServiceCallback;
import com.example.hp.myapplication.service.YahooWeatherService;

public class yahooWeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private ProgressDialog dialog;

    private YahooWeatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yahoo_weather);

        temperatureTextView = (TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);

        service = new YahooWeatherService(this);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        service.refreshWeather("Austin, TX");

    }

    @Override
    public void serviceSuccess(Chanel chanel){
        dialog.hide();

        Item item = chanel.getItem();
        temperatureTextView.setText(item.getCondition().getTemperature() + " " + chanel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getlocation());
    }
    @Override
    public void serviceFailure(Exception exception){
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
    }

}

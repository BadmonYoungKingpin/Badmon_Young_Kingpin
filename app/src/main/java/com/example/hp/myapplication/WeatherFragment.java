package com.example.hp.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherFragment extends Fragment {

    TextView cityField;
    TextView updateFeild;
    TextView detailsFeild;
    TextView currentTempFeild;
    TextView weatherIcon;
    Handler handler;

    public WeatherFragment(){
        handler = new Handler();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        updateWeatherData(new CityPreference(getActivity()).getCity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false );

        cityField = (TextView) rootView.findViewById(R.id.city_field);
        updateFeild = (TextView) rootView.findViewById(R.id.update_field);
        detailsFeild = (TextView) rootView.findViewById(R.id.details_field);
        currentTempFeild = (TextView) rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView) rootView.findViewById(R.id.weather_icon);

        return rootView;
    }

    private void updateWeatherData(final String city){
        new Thread(){
            @Override
            public void run(){
                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);
                if(json == null){
                    handler.post(new Runnable(){
                        @Override
                        public void run(){
                            Log.e("login activity", "JSON IS EMPTY");
                        }
                    });
                }else{
                    handler.post(new Runnable(){
                        @Override
                        public  void run(){
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json){
        try{
            cityField.setText(json.getString("name").toUpperCase(Locale.UK) + ", " + json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");

            detailsFeild.setText(details.getString("description").toUpperCase(Locale.UK) + "\n"
                    + "Humidity: " + main.getString("humidity") + "%"  + "\n" + "Pressure: "
                    + main.getString("pressure") + "hPa");

            currentTempFeild.setText(String.format("%.2f", main.getDouble("temp")) + " C");

            DateFormat dt = DateFormat.getDateInstance();
            String updateOn = dt.format(new Date(json.getLong("dt") * 1000));
            updateFeild.setText("Last update: " + updateOn);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public  void changeCity(String city){
        updateWeatherData(city);
    }

}


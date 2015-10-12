package com.example.hp.myapplication.service;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hp.myapplication.data.Chanel;
import com.example.hp.myapplication.data.JSONPopulator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by HP on 2015/10/12.
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback callback){
        this.callback = callback;
    }

    public String getlocation(){
        return location;
    }

    public void refreshWeather(final String l){
        this.location = l;

        new AsyncTask<String, Void, String>(){
            @Override
            protected String doInBackground(String ... strings){

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s, ak\")", strings[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
                Log.e("Internet", "about to connect");
                try {
                    URL url = new URL(endpoint);
                    Log.e("Internet", "opening connection");
                    URLConnection connection = url.openConnection();
                    Log.e("Internet", "inpuyStrea ");
                    InputStream inputStream = connection.getInputStream();
                    Log.e("Internet", "bufferReader");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    Log.e("Internet", "reading");
                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }

                    return result.toString();
                } catch (Exception e) {
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s){
                if(s==null && error !=null) {
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    Log.e("Internet", "new JSON object");
                    JSONObject data = new JSONObject(s);
                    Log.e("Internet", "results");
                    JSONObject queryResults = data.optJSONObject("query");
                    int count = queryResults.optInt("count");
                    if (count == 0) {
                        callback.serviceFailure(new LocationWeatherException("No weather found for " + location));
                        return;
                    }

                    Chanel chanel = new Chanel();
                    Log.e("Internet", "populating channel");
                    chanel.populate(queryResults.optJSONObject("results").optJSONObject("chanel"));
                    Log.e("Internet", "successful callback");
                    callback.serviceSuccess(chanel);
                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }
            }

        }.execute(location);


    }

    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String detailMessage){
            super(detailMessage);
        }
    }
}

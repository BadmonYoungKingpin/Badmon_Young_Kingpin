package com.example.hp.myapplication.data;

import org.json.JSONObject;

/**
 * Created by HP on 2015/10/12.
 */
public class Units implements JSONPopulator{
    private String temperature;

    public String getTemperature(){
        return temperature;
    }

    @Override
    public void populate(JSONObject data){
        temperature = data.optString("temperature");
    }
}

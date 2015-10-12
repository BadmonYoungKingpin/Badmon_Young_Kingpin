package com.example.hp.myapplication.data;

import org.json.JSONObject;

/**
 * Created by HP on 2015/10/12.
 */
public class Condition implements JSONPopulator{
    private int code;
    private int temperature;
    private String description;
    @Override
    public void populate(JSONObject data){
        code = data.optInt("code");
        temperature = data.optInt("temperature");
        description = data.optString("description");

    }

    public int getCode(){
        return code;
    }

    public int getTemperature(){
        return temperature;
    }

    public String getDescription(){
        return description;
    }
}

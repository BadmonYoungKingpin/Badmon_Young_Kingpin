package com.example.hp.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by HP on 2015/10/12.
 */
public class CityPreference {

    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    String getCity(){
        return prefs.getString("city", "Sydney, AU");
    }

    void setCIty(String city){
        prefs.edit().putString("city", city).commit();
    }
}

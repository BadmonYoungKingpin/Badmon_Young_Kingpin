package com.example.hp.myapplication.data;

import org.json.JSONObject;

/**
 * Created by HP on 2015/10/12.
 */
public class Chanel implements JSONPopulator{
    private Item item;
    private Units units;

    @Override
    public void populate(JSONObject data){
        item = new Item();
        item.populate(data.optJSONObject("item"));
        units = new Units();
        units.populate(data.optJSONObject("units"));

    }

    public Units getUnits(){
        return units;
    }

    public Item getItem(){
        return item;
    }
}

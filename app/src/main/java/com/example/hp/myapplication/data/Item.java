package com.example.hp.myapplication.data;

import org.json.JSONObject;

/**
 * Created by HP on 2015/10/12.
 */
public class Item implements JSONPopulator{
    private Condition condition;
    @Override
    public void populate(JSONObject data){
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }

    public Condition getCondition(){
        return condition;
    }
}

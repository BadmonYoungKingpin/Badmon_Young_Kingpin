package com.example.hp.myapplication.service;

import com.example.hp.myapplication.data.Chanel;

/**
 * Created by HP on 2015/10/12.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Chanel chanel);
    void serviceFailure(Exception excepton);

}

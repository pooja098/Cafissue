package com.example.pooja.cafissues;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by pooja on 11/9/16.
 */
public class Cafissues extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}

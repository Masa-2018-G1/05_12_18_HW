package com.sheygam.masa_2018_g1_05_12_18_hw;

import android.app.Application;

import com.sheygam.masa_2018_g1_05_12_18_hw.data.StoreProvider;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StoreProvider.getInstance().setContext(this);
    }
}

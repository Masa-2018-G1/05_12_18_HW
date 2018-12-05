package com.sheygam.masa_2018_g1_05_12_18_hw;

import android.app.Application;

public class App extends Application {
    private static App instance;
    public App(){
        super();
        instance = this;
    }
    public static App get(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        StoreProvider.getInstance().setContext(this);
    }
}

package com.sheygam.masa_2018_g1_05_12_18_hw;

class MySingletone {
    private static final MySingletone ourInstance = new MySingletone();

    static MySingletone getInstance() {
        return ourInstance;
    }

    private MySingletone() {
    }
}

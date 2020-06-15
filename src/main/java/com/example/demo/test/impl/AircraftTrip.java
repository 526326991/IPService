package com.example.demo.test.impl;

import com.example.demo.test.Strategy;

public class AircraftTrip implements Strategy {
    @Override
    public void travelMode(String level) {
        if ("钻石".equals(level))
            System.out.println("飞机出行");
        else
            System.out.println("未能识别你的等级");
    }
}

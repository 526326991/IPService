package com.example.demo.test.impl;

import com.example.demo.test.Strategy;

public class BicycleTrip implements Strategy {

    @Override
    public void travelMode(String level) {
        if ("白银".equals(level))
            System.out.println("自行车出行");
        else
            System.out.println("未能识别你的等级");
    }
}

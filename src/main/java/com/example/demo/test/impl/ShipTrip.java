package com.example.demo.test.impl;

import com.example.demo.test.Strategy;

public class ShipTrip implements Strategy {

    @Override
    public void travelMode(String level) {
        if ("黄金".equals(level))
            System.out.println("航船出行");
        else
            System.out.println("未能识别你的等级");
    }
}

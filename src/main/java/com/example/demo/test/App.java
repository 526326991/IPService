package com.example.demo.test;

import com.example.demo.test.impl.AircraftTrip;
import com.example.demo.test.impl.BicycleTrip;
import com.example.demo.test.impl.ShipTrip;

public class App {

    public static void main(String[] args) {
        String level = "黄金";//this is variable
        /*
         * 先有个会员制度，按照会员等级享受出行方式
         * 会员等级 白银  黄金  钻石
         * 1.如果是 白银 享受 自信车出行
         * 2.如果是 黄金 享受 航船出行
         * 3.如果是 钻石 享受 飞机出行
         * */
        //传统if/else 方法
        if ("白银".equals(level)) {
            System.out.println("自信车出行");
        } else if ("黄金".equals(level)) {
            System.out.println("航船出行");
        } else if ("钻石".equals(level)) {
            System.out.println("飞机出行");
        } else {
            System.out.println("未能识别你的等级");
        }

        //策略模式 下
        Strategy aircraft = new AircraftTrip();
        Strategy ship = new ShipTrip();
        Strategy bicycle = new BicycleTrip();


        Context contextA = new Context(aircraft);
        contextA.execute(level);

        Context contextS = new Context(ship);
        contextS.execute(level);

        Context contextB = new Context(bicycle);
        contextB.execute(level);

    }
}

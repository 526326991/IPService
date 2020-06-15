package com.example.demo.test;

public class Context {

    private Strategy strategy;


    //重写构造函数
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(String level){
        strategy.travelMode(level);
    }
}

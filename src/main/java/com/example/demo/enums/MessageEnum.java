package com.example.demo.enums;

import java.util.HashMap;
import java.util.Map;

public enum MessageEnum {

    dwc(0, "待完成"),
    bmyc(1, "补码异常"),
    yc(2, "异常"),
    wc(3, "完成"),
    yxj(4, "异形件"),
    ;


    private final Integer number;
    private final String name;


    MessageEnum(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, MessageEnum> maps = new HashMap<>();

    static {
        for (MessageEnum item : MessageEnum.values()) {
            maps.put(item.getNumber(), item);
        }
    }

    public static MessageEnum getByType(final Integer number) {
        if (number == null) {
            return null;
        }
        return maps.get(number);
    }

}
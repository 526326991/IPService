package com.example.demo.topic;

public class Test01 {

    //数组中只出现一次的数字

    public int singleNumber(int[] nums) {
        int num = nums[0];
        if (nums.length>1){
            for (int i = 0; i < nums.length; i++) {
                num=num^nums[i];
            }
        }
        return num;
    }

    public static void main(String[] args) {
    }
}

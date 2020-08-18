package com.example.demo.topic;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * Java
 *
 */
public class Test04 {

    public static int[] twoSum(int[] nums, int target) {
        int[] sz = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int sub = target - num;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j]==sub){
                    sz[0]=i;
                    sz[1]=j;
                    return sz;
                }
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int[] nums={2, 7, 11, 15};
        int target=9;
        int[] ints = twoSum(nums, target);
        for (int anInt : ints) {
            System.out.print(anInt+",");
        }
    }
}

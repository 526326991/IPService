package com.example.demo.topic;

/**
 * 最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 * <p>
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 */
public class Test09 {

    public static int findMaxConsecutiveOnes(int[] nums) {
        int sum = 0;
        int count = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == 1) {
                count += 1;
            } else {
                if (sum < count)
                    sum = count;
                count = 0;
            }

            if (fast == nums.length-1) {
                if (sum < count)
                    sum = count;
                count = 0;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] sz = {1, 1, 0, 1, 1, 1};
        System.out.println(findMaxConsecutiveOnes(sz));
    }
}

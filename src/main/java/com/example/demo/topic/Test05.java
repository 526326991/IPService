package com.example.demo.topic;

import net.minidev.json.JSONUtil;

/**
 * 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xm77tm/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Test05 {

    public static int pivotIndex(int[] nums)  {
        int s=0;
        int leftSum=0;
        for (int num : nums)s+=num;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum==s-nums[i]-leftSum) return i;
            leftSum+=nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums={-1,-1,-1,-1,-1,0};
        int i = pivotIndex(nums);
        System.out.println(i);
    }
}

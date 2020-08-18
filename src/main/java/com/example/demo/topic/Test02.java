package com.example.demo.topic;

/**
 *给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2cv1c/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 */
public class Test02 {

    public static int[] plusOne(int[] digits) {
        //增加长度的
        boolean f=false;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i]==9){
                f=true;
                continue;
            }
            f=false;
            break;
        }
        if (f){
            int[] sz = new int[digits.length + 1];
            for (int i = 0; i < sz.length; i++) {
                if (i==0)
                    sz[i]=1;
                else
                    sz[i]=0;
            }
            return sz;
        }
        
        //常规
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i];
            if (digit==9) {
                digits[i] = 0;
            }
            else {
                digits[i] = digits[i] + 1;
                break;
            }

        }
        return digits;
    }

    public static void main(String[] args) {
        int[] digits={9,9,9};
        int[] ints = plusOne(digits);
        for (int anInt : ints) {
            System.out.print(anInt+",");
        }
    }
}

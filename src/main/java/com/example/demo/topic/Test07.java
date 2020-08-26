package com.example.demo.topic;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 */
public class Test07 {

    public static void reverseString(char[] s) {
        int y=s.length-1;
        int i=0;
        while (i<y){
            char sz=s[i];
            s[i++]=s[y];
            s[y--]=sz;
        }

    }

    public static void main(String[] args) {
        char [] s ={'h','e','l','l','o'};
        reverseString(s);
        for (char c : s) {
            System.out.print(c+",");
        }
    }
}


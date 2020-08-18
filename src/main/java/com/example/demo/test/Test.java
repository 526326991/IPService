package com.example.demo.test;

import java.util.*;

public class Test {

    /**
     * 返回交集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums2.length>nums1.length)
            intersect(nums2,nums1);

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] nums11=nums1;
        int[] nums22=nums2;
        int count = getCount(nums11, nums22);

        int [] sz=new int[count];

        int n=0;
        for (int i = 0; i < nums1.length; i++) {
            int num1 = nums1[i];
            for (int j = 0; j < nums2.length; j++) {
                int num2 = nums2[j];
                if (num1==num2){
                    sz[n]=num1;
                    nums2=minusOne(nums2,j);
                    n++;
                }
            }
        }
        return sz;
    }

    private static int getCount(int[] nums1, int[] nums2) {
        int count=0;
        for (int i = 0; i < nums1.length; i++) {
            int num1 = nums1[i];
            for (int j = 0; j < nums2.length; j++) {
                int num2 = nums2[j];
                if (num1==num2){
                    count++;
                    nums2=minusOne(nums2,j);
                }
            }
        }
        return count;
    }

    private static int[] minusOne(int[] nums,int index){
        List<String> list=new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i==index)
                continue;
            list.add(nums[i]+"");
        }

        int[] sz=new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Integer ii = Integer.valueOf(list.get(i));
            sz[i]=ii;
        }
        return sz;
    }



    public static void main(String[] args) {
        //[54,93,21,73,84,60,18,62,59,89,83,89,25,39,41,55,78,27,65,82,94,61,12,38,76,5,35,6,51,48,61,0,47,60,84,9,13,28,38,21,55,37,4,67,64,86,45,33,41]
        //[17,17,87,98,18,53,2,69,74,73,20,85,59,89,84,91,84,34,44,48,20,42,68,84,8,54,66,62,69,52,67,27,87,49,92,14,92,53,22,90,60,14,8,71,0,61,94,1,22,84,10,55,55,60,98,76,27,35,84,28,4,2,9,44,86,12,17,89,35,68,17,41,21,65,59,86,42,53,0,33,80,20]
        int[] nums1={54,93,21,73,84,60,18,62,59,89,83,89,25,39,41,55,78,27,65,82,94,61,12,38,76,5,35,6,51,48,61,0,47,60,84,9,13,28,38,21,55,37,4,67,64,86,45,33,41};
        int[] nums2={17,17,87,98,18,53,2,69,74,73,20,85,59,89,84,91,84,34,44,48,20,42,68,84,8,54,66,62,69,52,67,27,87,49,92,14,92,53,22,90,60,14,8,71,0,61,94,1,22,84,10,55,55,60,98,76,27,35,84,28,4,2,9,44,86,12,17,89,35,68,17,41,21,65,59,86,42,53,0,33,80,20};
        int[] intersect = intersect(nums1, nums2);
        for (int i : intersect) {
            System.out.print(i+",");
        }
        Integer[] num1 = Arrays.stream(nums1).boxed().toArray(Integer[]::new);
        Integer[] num2 = Arrays.stream(nums2).boxed().toArray(Integer[]::new);

        System.out.println();
        Integer[] j = getJ(num1, num2);
        for (Integer in : j) {
            System.out.print(in+",");
        }
    }

    /**
     * @param m
     * @param n
     * @return
     */
    private static Integer[] getJ(Integer[] m, Integer[] n)
    {
        List<Integer> objects = new ArrayList<>();
        Integer[] integers = m.length > n.length ? m : n;
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(integers));
        for (Integer i : m.length > n.length ? n : m)
            if (set.contains(i))
                objects.add(i);

        Integer[] arr = {};
        return objects.toArray(arr);
    }

    public static int[] intersectJ(int[] nums1, int[] nums2) {
        Integer[] num1 = Arrays.stream(nums1).boxed().toArray(Integer[]::new);
        Integer[] num2 = Arrays.stream(nums2).boxed().toArray(Integer[]::new);
        Integer[] j = getJ(num1, num2);
        int[] ints = Arrays.stream(j).mapToInt(Integer::intValue).toArray();
        return ints;
    }


}

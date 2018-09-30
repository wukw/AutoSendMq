package com.yunbao.autosendmq;

import java.util.Arrays;

class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        //注意这里，对数组进行排序处理
        Arrays.sort(nums);

        int len = nums.length;
        //最小距离0   最大距离
        int left = 0, right = nums[len - 1] - nums[0];

        while(left < right){
            int mid =  (right + left) / 2;
            int count = getCount(nums, mid);
            System.out.println("mid" + mid + "count" + count);
            //注意这里的二分查找左边界和右边界更新的标准
            //小于k，mid必然非最终解，因此left = mid + 1
            //大于等于k，可以将mid作为候选解
            if(count < k)   left = mid + 1;
            else            right = mid;
        }

        return left;
    }
    //计数方式
    public int getCount(int[] nums, int mid){
        int count = 0;

        int left = 0;
        //注意这里的left，在迭代的过程中没有重置，而是一直右移
        //很容易理解，i右移，nums[i]变大(注意对数组进行过排序)，而nums[left](上一轮的)不变
        //因此差增大，必然还是比mid大，因此不需要再迭代一遍
        for(int i = 1; i < nums.length;i++){
            while(nums[i] - nums[left] > mid)   left++;
            count += i - left;
        }

        return count;
    }

    public static void main(String[] args) {

        int[] a = {1,1,3,5,6,111,2523,811};
        System.out.println(new Solution().smallestDistancePair(a, 6));
    }
}


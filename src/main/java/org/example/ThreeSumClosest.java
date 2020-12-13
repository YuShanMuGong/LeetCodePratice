package org.example;

import java.util.Arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,2,1,-4};
        System.out.println(threeSumClosest(nums,1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3){
            return 0;
        }
        Arrays.sort(nums);
        int mSub = nums[0] + nums[1] + nums[2];
        int minClose = Math.abs(target - mSub);
        for (int i = 0 ; i < nums.length - 2 ; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int start = i + 1 ;
            int end = nums.length - 1;
            while (start < end){
                int r = nums[i] + nums[start] + nums[end];
                int close = Math.abs(target - r);
                if(close == 0){
                    // 直接相等，，没有最靠近的了，直接返回
                    return 0;
                }
                if(close < minClose){
                    mSub = r;
                }
                if(r > target){
                    end -- ;
                }
                if(r < target){
                    start ++ ;
                }
            }
        }
        return mSub;
    }

}

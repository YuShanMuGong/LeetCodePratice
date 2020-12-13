package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 */
public class FourSum {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0,0};
        List<List<Integer>> res = fourSum(nums,0);
        for (List<Integer> item : res){
            System.out.println(Arrays.toString(item.toArray()));
        }
    }

    public static List<List<Integer>> fourSum(int[] nums,int target) {
        if(nums == null || nums.length < 4){
            return new ArrayList<>();
        }
        List<List<Integer>> resList = new ArrayList<>();
        Arrays.sort(nums);
        // i 最多能取到 nums.length - 4 这个位置的值；
        for (int i = 0 ; i < nums.length - 3 ; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            for (int j = i + 1 ; j < nums.length - 2 ; j++){
                if(j > i + 1 && nums[j] == nums[j-1]){
                    continue;
                }
                int start = j + 1 ;
                int end = nums.length - 1;
                while (start < end){
                    int r = nums[i] + nums[j] + nums[start] + nums[end];
                    if(r == target ){
                        List<Integer> array = new ArrayList<>();
                        array.add(nums[i]);
                        array.add(nums[j]);
                        array.add(nums[start]);
                        array.add(nums[end]);
                        resList.add(array);
                        // 如果下一个 start 和 当前值相同，需要跳过，否则会产生重复数据
                        while (start < nums.length - 2 && nums[start + 1] == nums[start]){
                            start ++ ;
                        }
                        start ++ ;
                    }
                    if(r > target){
                        end -- ;
                    }
                    if(r < target){
                        start ++ ;
                    }
                }
            }
        }
        return resList;
    }

}

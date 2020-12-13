package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = threeSum(nums);
        for (List<Integer> item : res){
            System.out.println(Arrays.toString(item.toArray()));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length < 3){
            return new ArrayList<>();
        }
        List<List<Integer>> resList = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0 ; i < nums.length - 2 ; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int start = i + 1 ;
            int end = nums.length - 1;
            while (start < end){
                int r = nums[i] + nums[start] + nums[end];
                if(r == 0 ){
                    List<Integer> array = new ArrayList<>();
                    array.add(nums[i]);
                    array.add(nums[start]);
                    array.add(nums[end]);
                    resList.add(array);
                    // 如果下一个 start 和 当前值相同，需要跳过，否则会产生重复数据
                    while (start < nums.length - 2 && nums[start + 1] == nums[start]){
                        start ++ ;
                    }
                    start ++ ;
                }
                if(r > 0){
                    end -- ;
                }
                if(r < 0){
                    start ++ ;
                }
            }
        }
        return resList;
    }

}

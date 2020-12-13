package org.example;

/**
 * 删除排序数组中的重复项
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,2}));
    }

    public static int removeDuplicates(int[] nums) {
        int i = 0 ;
        for (int j = 1 ; j < nums.length ; j++){
            if(nums[j] != nums[i]){
                nums[i + 1] = nums[j];
                i ++;
            }
        }
        return i + 1;
    }

}

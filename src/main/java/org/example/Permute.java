package org.example;

import java.util.*;

/**
 * 全排列
 * <p>
 * https://leetcode-cn.com/problems/permutations/
 */
public class Permute {

    public static void main(String[] args) {
        List<List<Integer>> result = permute(new int[]{1,2,3});
        for (List<Integer> list : result){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helpPermute(nums,0,new HashSet<>(),result,new ArrayList<>());
        return result;
    }

    private static void helpPermute(int[] nums, int index, Set<Integer> positioned,
                                    List<List<Integer>> result, List<Integer> out) {
        if(index == nums.length){
            result.add(new ArrayList<>(out));
        }
        for (int i = 0 ; i < nums.length ; i ++){
            if(positioned.contains(i)){
                continue;
            }
            positioned.add(i);
            out.add(nums[i]);
            helpPermute(nums,index + 1 , positioned,result,out);
            out.remove(new Integer(nums[i]));
            positioned.remove(i);
        }
    }
}

package org.example;

/**
 * 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class MaxArea {

    /**
     * 双指针法
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int l = 0 ;
        int r = height.length - 1;
        int area = 0 ;
        while ( l < r ){
            int len = r - l ;
            int h = Math.min(height[l],height[r]);
            if(area < len * h){
                area = len * h ;
            }
            if(height[l] > height[r]){
                r -- ;
            }else{
                l++ ;
            }
        }
        return area;
    }
}

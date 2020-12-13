package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * <p>
 * https://leetcode-cn.com/problems/spiral-matrix/
 */
public class SpiralOrderTest {

    public static void main(String[] args) {
        int[][] array = new int[][]{
                {1,2,3},
                {4,5,6}
        };

        System.out.println(array[0][1]);
        System.out.println(array[1][1]);
    }

    /**
     * m * n 的 二维数组
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int minX = 0;
        int maxX = m - 1;
        int minY = 0 ;
        int maxY = n - 1;
        int mode = 0 ;
        int tem ;
        int x = 0 ;
        int y = 0 ;
        List<Integer> res = new ArrayList<>();
        w : while (true){
            res.add(matrix[x][y]);
            switch (mode){
                case 0:
                    tem = y + 1;
                    if(tem > maxY){
                        mode = nextMode(mode);
                        if(++minY > maxY){
                            break w;
                        }
                        x ++ ;
                    }else{
                        y = tem;
                    }
                    break;
                case 1:
                    tem = x + 1;
                    if(tem > maxX){
                        mode = nextMode(mode);
                        if(--maxX < minX ){
                            break w;
                        }
                        y -- ;
                    }else{
                        x = tem;
                    }
                    break;
                case 2:
                    tem = y - 1;
                    if(tem < minY){
                        mode = nextMode(mode);
                        if(++minY > maxY){
                            break w;
                        }
                        x -- ;
                    }else{
                        y = tem;
                    }
                    break;
                case 3:
                    tem = x - 1;
                    if(tem < minX){
                        mode = nextMode(mode);
                        if(++minX > maxX){
                            break w;
                        }
                        y ++;
                    }else{
                        x = tem;
                    }
                    break;
            }
        }
        return res;
    }

    private int nextMode(int mode){
        if(mode < 3){
            return ++mode;
        }
        return 0;
    }
}

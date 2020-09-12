package mxn矩阵若有元素为0则行列设置为0;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 叶立新
 * @date 2020/8/7 20:22
 */
class point{
    int x;
    int y;
    point(int x,int y){
        this.x=x;
        this.y=y;
    }
}
public class Solution {
    public void setZeroes(int[][] matrix) {
       List<point> list=new LinkedList();
        for(int i=0;i< matrix.length;i++){
            for(int j=0;j< matrix[i].length;j++){
                if(matrix[i][j]==0){
                    list.add(new point(i,j));
                }
            }
        }
        for(int i=0;i<list.size();i++){
            for(int k=0;k<matrix[0].length;k++){
                matrix[list.get(i).x][k]=0;
            }
            for(int l=0;l< matrix.length;l++){
                matrix[l][list.get(i).y]=0;
            }
        }


    }
}
class Main{
    public static void main(String[] args) {
        int[][] matrix={{1,1,1},{1,0,1},{1,1,1}};
        Solution solution=new Solution();
        solution.setZeroes(matrix);
        for(int i=0;i< matrix.length;i++){
            for(int j=0;j< matrix[i].length;j++){
                System.out.println(matrix[i][j]);
            }
        }
    }
}
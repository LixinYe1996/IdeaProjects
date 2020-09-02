package 剑指offer.T4_二维数组中的查找;

class Solution {
    public boolean findNumberIn2DArray(int[][]matrix, int target) {
        if (matrix.length==0){
            return false;
        }
        //列
        int columns=matrix[0].length;
        //行
        int rows=matrix.length;
        int row=0;
        int column=columns-1;
        while (row<rows&&column>=0){

            if(matrix[row][column]==target){
                return true;
            }
            if(matrix[row][column]>target){
                column--;
            }else{
                row++;
            }

        }
        return false;
    }
}
class Main{
    public static void main(String[]args) {
        int [][]matrix={
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
  {3,   6,  9, 16, 22},
  {10, 13, 14, 17, 24},
  {18, 21, 23, 26, 30}};
        int target=5;
        Solution solution=new Solution();
        System.out.println(solution.findNumberIn2DArray(matrix,target));

    }
}
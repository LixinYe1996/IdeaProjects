package 剑指offer.T11_旋转数组的最小数字_P82;

import java.util.Arrays;

/*
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  


 */
class Solution {
    public int minArray(int[] numbers) {
       if(numbers==null||numbers.length<=0){
           try {
               throw new Exception("Invalid parameters");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       int left=0;
       int right=numbers.length-1;
       int mid=0;
       while (numbers[left]>=numbers[right]){
           if(right-mid==1){
               mid=right;
               break;
           }
           mid=(right+left)/2;
           if(numbers[right]==numbers[mid]&&numbers[mid]==numbers[left])
               return MinInOrder(numbers,left,right);
           if(numbers[left]<=numbers[mid]){
               left=mid;
           }else if(numbers[mid]<=numbers[right]) {
               right=mid;
           }
       }
       return numbers[mid];
    }
    int MinInOrder(int [] numbers,int left,int right){
        int result=numbers[left];
        for(int i=left;i<=right;i++){
            if(result>numbers[i]){
                result=numbers[i];
            }
        }
           return result;
        }

}
class Main{
    public static void main(String[] args) {
        int []data={2,2,2,0,1};
        new Solution().minArray(data);
        System.out.println(Arrays.toString(data));
    }
}
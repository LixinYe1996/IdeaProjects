package 二分查找;

/**
 * @author 叶立新
 * @date 2020/8/17 13:30
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 */
public class Solution {
    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while (left<right){
            int mid=(left+right)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        if(nums[left]==target){
            return left;
        }
        return -1;


    }
}
class Main{
    public static void main(String[] args) {
       int[] nums = {-1,0,3,5,9,12};
       int target = 9;
       Solution solution=new Solution();
        System.out.println(solution.search(nums,target));
    }
}
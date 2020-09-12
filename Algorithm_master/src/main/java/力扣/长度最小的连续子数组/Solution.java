package 长度最小的连续子数组;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 叶立新
 * @date 2020/8/10 13:16
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 *
 *
 */
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0;
        int minSub = nums.length;
        for (int i = 0; i < nums.length; i++) {

            sum = sum + nums[i];
            queue.add(nums[i]);
            if (sum >= s) {
                while (sum-queue.peek()>=s){
                    sum=sum-queue.peek();
                    queue.remove();
                }
                if (queue.size() <= minSub) {
                    minSub = queue.size();
                }
            }

        }
        if(sum<s){
            return 0;
        }
        return minSub;
    }
}

class Main{
    public static void main(String[] args) {
        int []nums = {2,3,1,2,4,3};
        int s=7;
        Solution solution=new Solution();
        System.out.println(solution.minSubArrayLen(s,nums));
    }
}

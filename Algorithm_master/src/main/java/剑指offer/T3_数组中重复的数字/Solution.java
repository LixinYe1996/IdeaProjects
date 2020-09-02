package 剑指offer.T3_数组中重复的数字;


class Solution {
    public int findRepeatNumber(int[] nums) {
        if(nums.length<=0){
            return -1;
        }
        for(int i=0;i<nums.length;i++){
                while (nums[i]!=i){
                    if(nums[i]==nums[nums[i]]){
                        return nums[i];
                    }
                    int t=nums[i];
                    nums[i]=nums[t];
                    nums[t]=t;
                }
        }
        return -1;

    }
}

class Main{
    public static void main(String[] args) {
        int [] nums={2, 3, 1, 0, 2, 5, 3};
        Solution solution=new Solution();
        System.out.println(solution.findRepeatNumber(nums));
    }
}
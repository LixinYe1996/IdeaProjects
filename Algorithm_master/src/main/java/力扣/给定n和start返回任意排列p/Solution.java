package 给定n和start返回任意排列p;

import java.util.List;

/**
 * @author 叶立新
 * @date 2020/8/10 13:40
 * 给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：
 * p[0] = start
 * p[i] 和 p[i+1] 的二进制表示形式只有一位不同
 * p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
 */
//public class Solution {
//    public List<Integer> circularPermutation(int n, int start) {
//
//    }
//}
//class Main{
//    public static void main(String[] args) {
//        Solution solution=new Solution();
//        int n=2;
//        int start=3;
//        System.out.println(solution.circularPermutation(n,start));
//    }
//}

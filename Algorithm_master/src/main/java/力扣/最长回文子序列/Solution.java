package 最长回文子序列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 叶立新
 * @date 2020/8/17 13:50
 */
public class Solution {
    public int longestPalindromeSubseq(String s) {
        int l = s.length();
        boolean [][] dp = new boolean[l][l];
        String res = "";
        for(int m = 0; m < l;  ++m){
            for(int i = 0; i < l; ++i){
                int j = i + m;
                if(j >= l) break;
                if(m == 0) dp[i][j] = true;
                else if(m == 1) dp[i][j] = (s.charAt(i) == s.charAt(j));
                else{
                    dp[i][j] = dp[i+1][j-1] && (s.charAt(i) == s.charAt(j));
                }
                if(m+1 > res.length() && dp[i][j]){
                    res = s.substring(i, j+1);
                }
            }
        }
        return res.length();
    }
}
class Main{
    public static void main(String[] args) {
        String s="cbbd";
        Solution solution=new Solution();
        System.out.println(solution.longestPalindromeSubseq(s));
    }
}

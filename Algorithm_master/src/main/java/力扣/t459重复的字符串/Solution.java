package t459重复的字符串;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 叶立新
 * @date 2020/8/24 10:37
 */
public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

}
class  Main{
    public static void main(String[] args) {
        String s="abcabcabcabc";
        Solution solution=new Solution();
        System.out.println(solution.repeatedSubstringPattern(s));
    }
}

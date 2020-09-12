package 无重复字符的最长子串;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 叶立新
 * @date 2020/8/6 23:35
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        Queue<Character> queue=new LinkedList<>();
        int max=0;
        for(int i=0;i<s.length();i++){
            while (queue.contains(s.charAt(i))){
                queue.remove();
            }
            queue.add(s.charAt(i));

            if(queue.size()>max){
                max=queue.size();
            }

        }
        return max;
//        if (s.length()==0) return 0;
//        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
//        int max = 0;
//        int left = 0;
//        for(int i = 0; i < s.length(); i ++){
//            if(map.containsKey(s.charAt(i))){
//                left = Math.max(left,map.get(s.charAt(i)) + 1);
//            }
//            map.put(s.charAt(i),i);
//            max = Math.max(max,i-left+1);
//        }
//        return max;



    }

    public static void main(String[] args) {
        String s="pwwkew";
        Solution solution=new Solution();
        System.out.println(  solution.lengthOfLongestSubstring(s));

    }
}

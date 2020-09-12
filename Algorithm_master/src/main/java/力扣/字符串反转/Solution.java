package 字符串反转;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author 叶立新
 * @date 2020/8/7 20:07
 */
class Solution {
    public String reverseWords(String s) {
        Stack<Character> stack=new Stack<>();
        String reverseString="";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=' '){
                stack.push(s.charAt(i));
            }else {
                while (!stack.isEmpty()){
                    Character c=stack.pop();
                    reverseString=reverseString+c;
                }
                reverseString=reverseString+' ';
            }
            if(i==s.length()-1){
                while (!stack.isEmpty()){
                    Character c=stack.pop();
                    reverseString=reverseString+c;
                }
            }

        }
        return  reverseString;

    }
}

class Main{
    public static void main(String[] args) {
        String s= "Let's take LeetCode contest";
        Solution solution=new Solution();
        System.out.println(solution.reverseWords(s));
    }
}
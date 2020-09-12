package 字符串S划分;

import java.util.*;

/**
 * @author 叶立新
 * @date 2020/8/8 10:30
 */
//public class Solution {
//    public List<Integer> partitionLabels(String S) {
//        int l=0,mid=0,r=S.length();
//        while (l<=r){
//            mid=(l+r)/2;
//            if(check(mid,S)){
//
//
//            }
//            else {
//
//            }
//        }
//
//    }
//    int check(String S){
//        Set<Character> leftSet=new HashSet<>();
//        Set<Character> rightSet=new HashSet<>();
//
//        for(int i=0;i<S.length();i++){
//            char[] leftChars = new char[S.length()];
//            char[] rightChars=new char[S.length()];
//            S.getChars(0,i,leftChars,0);
//            S.getChars(i,S.length()-1,rightChars,0);
//            leftSet.
//            }
//
//        }
//
//    }
//
//}
//class  Main{
//    public static void main(String[] args) {
//        String S="ababcbacadefegdehijhklij";
//        Solution solution=new Solution();
//        solution.partitionLabels(S);
//    }
//}
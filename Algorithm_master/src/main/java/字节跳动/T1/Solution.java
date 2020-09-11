package 字节跳动.T1;

import java.util.*;

public class Solution {
    int getResult(String s){
        Set set=new HashSet<Character>();
        set.add('+');
        set.add('-');
        int[] intlist=new int[s.length()];
        char[] charlist=new char[s.length()];
        int intcount=0;
        int charcount=0;
        int index=0;
        if(set.contains(s.charAt(0))){
            charlist[charcount]=s.charAt(0);
            charcount++;
            index++;
        }else {
            charlist[charcount]='+';
            charcount++;
        }
        int t=0;
        for(;index<s.length();index++){
            if(set.contains(s.charAt(index))){
                charlist[charcount]=s.charAt(index);
                charcount++;
                intlist[intcount]=t;
                t=0;
                intcount++;
            }else {
                t=t*10+(s.charAt(index)-48);
            }
            if(index==s.length()-1){
                intlist[intcount]=t;
            }
        }
        int sum=0;
        for(int k=0;k<=intcount;k++){
            if(charlist[k]=='-'){
                sum=sum-intlist[k];
            }else {
                sum=sum+intlist[k];
            }
            System.out.println(sum);
        }
        System.out.println(Arrays.toString(intlist));
        System.out.println(Arrays.toString(charlist));
        return sum;
    }

}
class Main{
    public static void main(String[] args) {
        String s="1+2-33";
        System.out.println(  new Solution().getResult(s));


//        String i="1";
//        int t=Integer.parseInt(i);
//        System.out.println(t);
        char [] chars={1,2,3,4,5,6};


    }
}

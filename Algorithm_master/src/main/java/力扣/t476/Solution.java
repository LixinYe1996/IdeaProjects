package t476;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 叶立新
 * @date 2020/8/8 10:01
 */
public class Solution {
    public int bitwiseComplement(int N) {
        if(N==0){return 0;}
        List<Integer> list= new LinkedList<>();
        while (N!=0){
            int cur=N%2;
            N=(N-cur)/2;
            list.add(cur);
        }
        int reverse=0;
        for(int i=list.size()-1;i>=0;i--){
            if(list.get(i)==0){
                reverse= reverse+(int) (1*Math.pow(2,i));
            }
            else {
                reverse= reverse+(int) (0*Math.pow(2,i));

            }
        }
        return reverse;
    }
}
class Main{
    public static void main(String[] args) {
        int N=5;
        Solution solution=new Solution();
        System.out.println(solution.bitwiseComplement(N));

    }
}
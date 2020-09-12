package 给定整数数组的牌分为W组每组W张;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 叶立新
 * @date 2020/8/10 13:52
 */
public class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if(hand.length%W!=0){return false;}
        int nums=hand.length/W;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i< hand.length;i++){
            if(map.keySet().contains(hand[i])){
                map.put(hand[i],map.get(hand[i])+1);
            }else {
                map.put(hand[i],1);
            }
        }
        while (nums>0){
            Object[] array=map.keySet().toArray();
            int max= (int) array[0];
            for(int i=0;i<array.length;i++){
                if((int)array[i]>max){
                    max=(int) array[i];
                }
            }
            map.put(max,map.get(max)-1);
            if(map.get(max)==0){
                map.remove(max);
            }
            for(int j=0;j<W-1;j++){
                max--;
                if(map.containsKey(max)){
                    map.put(max,map.get(max)-1);
                    if(map.get(max)==0){
                        map.remove(max);
                    }
                }else {
                    return false;
                }
            }
            nums--;

        }
        return true;

    }
}
class Main{
    public static void main(String[] args) {
       int []hand = {1,2,3,6,2,3,4,7,8};
       int W=3;
       Solution solution=new Solution();
        System.out.println(solution.isNStraightHand(hand,W));
    }
}

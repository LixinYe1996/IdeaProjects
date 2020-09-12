package 小张刷题计划;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

/**
 * @author 叶立新
 * @date 2020/8/7 21:08
 */
public class Solution {
    public int minTime(int[] time, int m) {
        int left=0,right=0,middle=0;
        for(int i=0;i<time.length;i++){
            right=right+time[i];
        }

        while (left<=right){
            middle=(left+right)>>1;
            if(check(middle,time,m)){
               right=middle-1;
            }else {
                left=middle+1;
            };
        }
        return left;
    }
    boolean check(int limit,int[] time,int day){
        int useday=1;
        int maxtime=0;
        int totaltime=0;
        for(int i=0;i<time.length;i++){
            int nexttime=Math.min(maxtime,time[i]);
            if(nexttime+totaltime<=limit){
                totaltime=nexttime+totaltime;
                maxtime= Math.max(maxtime,time[i]);
            }else {
                useday++;
                totaltime=0;
                maxtime=time[i];
            }
        }
        return useday<=day;
    }
}
class Main{
    public static void main(String[] args) {
    int [] time={1,2,3};
    int m=1;
    Solution solution=new Solution();
        System.out.println(solution.minTime(time,m));
    }
}
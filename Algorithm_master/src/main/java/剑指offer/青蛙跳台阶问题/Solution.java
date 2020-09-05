package 剑指offer.青蛙跳台阶问题;
/*
* 一只青蛙一次可以跳上一个台阶，也可以跳上两个台阶，求该青蛙跳上一个n级的台阶总共有多少种方法
* 一级台阶  只有一种调法 二级台阶 右两种跳法
* */
class Solution {
    public int numWays(int n) {
        if(n==0){
            return 1;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        int fibA=1;
        int fibB=2;
        int fib=0;
        for(int i=3;i<=n;i++){
            fib=fibA+fibB;
            fib=fib%1000000007;
            fibA=fibB;
            fibB=fib;
        }

        return fib;
    }
}

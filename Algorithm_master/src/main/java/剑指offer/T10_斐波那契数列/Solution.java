package 剑指offer.T10_斐波那契数列;
/*      0
f(n)=   1
        f(n-1)+f(n-2)
 */
public class Solution {
    long Fibonacci(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return Fibonacci(n-1)+Fibonacci(n-2);
    }
    long Fibonacci_unrecursion(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        long fibA=0;
        long fibB=1;
        long fib=0;
        for(int i=2;i<=n;i++){
            fib=fibA+fibB;
            fibA=fibB;
            fibB=fib;
        }
        return fib;
    }
}
class Main{
    public static void main(String[] args) {
        System.out.println(new Solution().Fibonacci_unrecursion(10));
    }
}

package t146;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;



public class Main {
    public static void main(String[] args) {
        String s="ABCDEfghijklmn";
        int n=7;
        int p=s.length();
        Run runIstance=new Run(n,p,s);
        runIstance.run();

    }
}
class Run{
    int n;
    int p;
    ReverseString reverseString;
    Run(int n,int p,String s){
        this.n=n;
        this.p=p;
        this.reverseString=new ReverseString(s);
    }
    String run(){
        int ls=0;
        int le=n-1;
        int rs=n;
        int re=p-1;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new Reverse(ls,le,this.reverseString));
        executor.submit(new Reverse(rs,re,this.reverseString));
        executor.shutdown();
        return reverseString.getString();
    }
}

class ReverseString{
    private char[] A;
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private final Lock rlock = rwlock.readLock();
    private final Lock wlock = rwlock.writeLock();
    ReverseString(String A){
        this.A=A.toCharArray();
    }
    public  char[] read() {
        rlock.lock(); // 加读锁
        try {
            return A;
        } finally {
            rlock.unlock(); // 释放读锁
        }
    }

    public void write(int start,char[] Subsequence) {
        System.out.println(start+""+String.valueOf(Subsequence));
        wlock.lock(); // 加写锁
        try {
            for(int i=0;i< Subsequence.length;i++){
                this.A[i+start]=Subsequence[i];
            }
            System.out.println(String.valueOf(this.A));
        } finally {
            wlock.unlock(); // 释放写锁
        }
    }
    public  String getString(){
        return String.valueOf(this.A);
    }
}
class Reverse implements Runnable{
    int start;
    int end;
    ReverseString reverseString;
    Reverse(int start,int end,ReverseString reverseString){
        this.start=start;
        this.end=end;
        this.reverseString=reverseString;
    }
    @Override
    public void run() {
        char[] A=reverseString.read();
        int n;
        if(start==0){
            n=A.length-(end-start+1);
        }else {
            n=0;
        }
        reverseString.write(n,Arrays.copyOfRange(A,start,end+1));

    }
}
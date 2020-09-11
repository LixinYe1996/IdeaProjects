package 剑指offer.快速排序;
import java.util.Random;
public class Solution {
    void QuickSort(int data[],int start,int end){
            int i=start;
            int j=end;
            int temp=data[start];
            while(i<j){
                while (i<j&&temp<data[j])j--;
                if(i<j){
                    data[i]=data[j];
                    i++;
                }
                while (i<j&&data[i]<temp)i++;
                if(i<j){
                    data[j]=data[i];
                    j--;
                }
            }
            data[i]=temp;
            if(start<i)QuickSort(data,start,i-1);
            if(i<end)QuickSort(data,i+1,end);
    }
}
class Main{
    public static void main(String[] args) {
        int [] data={5,2,3,4,5,6,0,9,10,11};
        new Solution().QuickSort(data,0,data.length-1);
        for(int i=0;i<data.length;i++){
            System.out.println(data[i]);
        }
//        int start=0;
//        int end=10;
//        double d = Math.random();
//        int index = (int)(d*(end-start))+start;
//        System.out.println(index);
    }
}
package 常用算法.快速排序;

public  class Solution {
    void QuickSort(int data[],int start,int end){
        Number I;
        int i=start;
        int j=end;
        int temp=data[start];
        while (i<j){
            while(i<j&&temp<data[j])j--;
            if(i<j){
                data[i]=data[j];
                i++;
            }
            while(i<j&&temp>data[i])i++;
            if(i<j){
                data[j]=data[i];
                j--;
            }
        }
            data[i]=temp;
            for(int k=0;k<data.length;k++){
                System.out.print(data[k]+"  ");
            }
            System.out.println("");
        System.out.println(i+" "+start+" "+end);
            if(start<i){
                QuickSort(data,start,i-1);
            }
            if(i<end){
                QuickSort(data,i+1,end);
            }


    }
}

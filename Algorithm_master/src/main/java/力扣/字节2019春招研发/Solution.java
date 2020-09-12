package 字节2019春招研发;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 叶立新
 * @date 2020/8/29 10:04
 */

class Solution{
    public int get_index(String[] points){
        float []x=new float[points.length];
        float []y=new float[points.length];
        float zhixinx=0;
        float zhixiny=0;
        for(int i=0;i< points.length;i++){
            String [] xy=points[i].split(",");
            x[i]=Float.parseFloat(xy[0]);
            y[i]=Float.parseFloat(xy[1]);
            zhixinx=zhixinx+ x[i];
            zhixiny=zhixiny+y[i];
        }
        zhixinx=zhixinx/ points.length;
        zhixiny=zhixiny/points.length;
//        System.out.println(zhixinx+" "+zhixiny);
        float distance=getdistance(x[0],y[0],zhixinx,zhixiny);
//        System.out.println(distance);
        int index=0;
        for(int i=0;i< points.length;i++){
            float tdistance=getdistance(x[i],y[i],zhixinx,zhixiny);
//            System.out.println(x[i]+" "+y[i]);
//            System.out.println(tdistance);
            if(tdistance<distance){
                index=i;
//                System.out.println(index);
                distance=tdistance;
            }
        }
        return index;
    }
    float getdistance(float x1,float y1,float x2,float y2){
        return  (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
    }
}
 class Main {
    public static void main(String[] args) {
        String[] points = {"1,1", "2,2", "1,2", "1,3"};
        Solution solution = new Solution();
        System.out.println(solution.get_index(points));
        List<Character> list=new ArrayList<>();
    }
}
package 字节t11盛最多水的容器;

/**
 * @author 叶立新
 * @date 2020/8/8 14:35
 */
public class Sloution {
    public int maxArea(int[] height) {
        int left=0;
        int right=height.length-1;
        int maxA=(right-left)*Math.min(height[left],height[right]);
        while (left<=right){

            int area=(right-left)*Math.min(height[left],height[right]);
            if(area>maxA){
                maxA=area;
            }
            if (height[left]<height[right]){
                left++;
            }else {
                right--;
            }
        }
        return maxA;
    }
}

class Main{
    public static void main(String[]args){
//        int []height={1,8,6,2,5,4,8,3,7};
        int []height={2,3,4,5,18,17,6};
//        int []height={1,1}
        Sloution sloution=new Sloution();
        System.out.println(sloution.maxArea(height));
        }

}

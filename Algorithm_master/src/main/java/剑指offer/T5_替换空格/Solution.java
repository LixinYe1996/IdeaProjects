package 剑指offer.T5_替换空格;

public class Solution {
    public String replaceSpace(String s) {
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=' '){
                stringBuilder.append(s.charAt(i));
            }else {
                stringBuilder.append("%20");
            }
        }
        return  stringBuilder.toString();
    }
}
class Main{
    public static void main(String[] args) {
        System.out.println(new Solution().replaceSpace("We are happy."));
    }
}
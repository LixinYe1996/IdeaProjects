package 字节t2两数相加;

/**
 * @author 叶立新
 * @date 2020/8/7 10:35
 */


class ListNode {
   int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0;
        ListNode head=new ListNode(-1);
        ListNode p=new ListNode(-1);
        head.next=p;
        while (l1!=null||l2!=null){
            int x,y;
            if(l1==null){
                x=0;
            }else {
                x=l1.val;
            }
            if(l2==null){
                y=0;
            }else {
                y=l2.val;
            }
            int sum=x+y+carry;
            carry=sum/10;
            p.next=new ListNode(sum%10);
            p=p.next;
            if(l1!=null){l1=l1.next;}
            if(l2!=null){l2=l2.next;}
        }
        if(carry==1){
            p.next=new ListNode(1);
        }
        return  head.next.next;
    }
}
class Main{
    public static void main(String[] args) {
//            ListNode listNode11=new ListNode(2);
//            ListNode listNode12=new ListNode(4);
//            ListNode listNode13=new ListNode(3);
//
//            ListNode listNode21=new ListNode(5);
//            ListNode listNode22=new ListNode(6);
//            ListNode listNode23=new ListNode(4);
//            listNode11.next=listNode12;
//            listNode12.next=listNode13;
//            listNode21.next=listNode22;
//            listNode22.next=listNode23;
//
//            Solution s=new Solution();
//            ListNode result=s.addTwoNumbers(listNode11,listNode21);
//            while (result!=null){
//            System.out.println(result.val);
//            result=result.next;
//        }
        int sum=0;
     for(int i =0;i<20;i++){
         sum=i/10;
         System.out.println(sum);
     }
    }
}
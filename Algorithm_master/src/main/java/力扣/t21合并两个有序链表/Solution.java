package t21;

/**
 * @author 叶立新
 * @date 2020/8/6 15:31
 */

//Definition for singly-linked list.
class ListNode {
   int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode r=new ListNode();
        ListNode p=new ListNode();
        p.next=r;
        while (l1!=null&&l2!=null){
            if(l1.val>=l2.val){
                ListNode t=new ListNode(l2.val);
                r.next=t;
                r=r.next;
                l2=l2.next;
                continue;
            }
            if(l1.val<l2.val){
                ListNode t=new ListNode(l1.val);
                r.next=t;
                r=r.next;
                l1=l1.next;
                continue;
            }
        }
        if(l1!=null){
            r.next=l1;
        }
        if(l2!=null){
            r.next=l2;
        }
        return p.next.next;
   }
}
class Main{
    public static void main(String[] args) {

            ListNode listNode11=new ListNode(1);
            ListNode listNode12=new ListNode(2);
            ListNode listNode13=new ListNode(4);

            ListNode listNode21=new ListNode(1);
            ListNode listNode22=new ListNode(3);
            ListNode listNode23=new ListNode(4);
            listNode11.next=listNode12;
            listNode12.next=listNode13;
            listNode21.next=listNode22;
            listNode22.next=listNode23;
            Solution s=new Solution();
            ListNode result=s.mergeTwoLists(listNode11,listNode21);
            while (result!=null){
                System.out.println(result.val);
                result=result.next;
            }
        }
    }

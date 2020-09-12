package t206反转链表;

/**
 * @author 叶立新
 * @date 2020/8/6 16:14
 */
//反转一个单链表。
//  示例:
//  输入: 1->2->3->4->5->NULL
//  输出: 5->4->3->2->1->NULL
// 进阶:
// 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？



class ListNode {
      int val;
     ListNode next;
    ListNode(int x) { val = x; }
  }

public class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode cur=head;
        while (cur!=null){
            ListNode t=cur.next;
            cur.next=prev;
            prev=cur;
            cur=t;

        }
        return prev;
    }

}

class Main{
    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(2);
        ListNode l3=new ListNode(3);
        ListNode l4=new ListNode(4);
        ListNode l5=new ListNode(5);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        l5.next=null;
        Solution s=new Solution();
        ListNode t=s.reverseList(l1);
        while (t!=null){
            System.out.println(t.val);
            t=t.next;
        }
    }
}
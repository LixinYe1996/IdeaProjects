package t141环形链表;

/**
 * @author 叶立新
 * @date 2020/8/6 19:23
 */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }

public class Solution {
//    public int hasCycle(ListNode head) {
//        Map<ListNode, Integer> map = new HashMap<>();
//        Integer i = 0;
//        while (head != null) {
//            if (map.containsKey(head)) {
//                return map.get(head);
//            } else {
//                map.put(head, i);
//            }
//            i++;
//            head = head.next;
//        }
//        return -1;   }
//
//}
//    public int hasCycle(ListNode head) {
//        Map<ListNode, Integer> map = new HashMap<>();
//        Integer i = 0;
//        while (head != null) {
//            if (map.containsKey(head)) {
//                return map.get(head);
//            } else {
//                map.put(head, i);
//            }
//            i++;
//            head = head.next;
//        }
//        return -1;   }
//     public boolean hasCycle(ListNode head){
//         if(head==null||head.next==null){
//             return false;
//         }
//         ListNode fast=head.next;
//         ListNode slow=head;
//         while (slow!=fast){
//             if(fast==null||fast.next==null){
//                 return false;
//             }
//             slow=slow.next;
//             fast=fast.next.next;
//         }
//         return true;
//
//     }
public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
        return false;
    }
    ListNode slow = head.next;
    ListNode fast = head;
    while (slow != fast) {
        System.out.println(slow.val+" "+ fast.val);
        if (fast == null || fast.next == null||fast.next.next==null) {
            return false;
        }
        slow = slow.next;
        fast = fast.next.next.next.next;
    }
    return true;
}
        //        return false;
//        while(head != null){
//            if(head == head.next){
//                return true;
//            }
//            if(head.next != null){
//                head.next = head.next.next;
//
//            }
//            head = head.next;
//        }
//        return false;
//    }

}


class Main{
    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(2);
        l1.next=l2;
        l2.next=l1;
        Solution s=new Solution();
        System.out.println(s.hasCycle(l1));
    }

}
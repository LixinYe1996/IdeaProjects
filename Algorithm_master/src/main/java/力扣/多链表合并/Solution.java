package 多链表合并;

import javax.sound.midi.Soundbank;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author 叶立新
 * @date 2020/8/17 14:04
 */

class ListNode implements Comparable<ListNode>{
    int val;
   ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public int compareTo( ListNode o2) {
        if (this.val < o2.val) return -1;
        else if (this.val == o2.val) return 0;
        else return 1;
    }
}


public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode>queue=new PriorityQueue<>((o1,o2)->{
            if(o1.val<o2.val)return-1;
            else if(o1.val==o2.val) return 0;
            else return 1;
        });

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) queue.add(p.next);
        }

        return dummy.next;

    }

}
class ListNodeComparator implements Comparator<ListNode>{
    @Override
    public int compare(ListNode o1, ListNode o2) {
        if(o1.val>o2.val){
            return 1;
        }else if(o1.val==o2.val){
            return 0;
        }else {
            return -1;
        }
    }
}
class  Main{
    public static void main(String[] args) {

        ListNode l11=new ListNode(1);
        ListNode l12=new ListNode(3);
        ListNode l21=new ListNode(2);
        ListNode l22=new ListNode(4);
        l11.next=l12;
        l21.next=l22;
        ListNode[]listNode=new ListNode[2];
        listNode[0]=l11;
        listNode[1]=l21;
        Solution solution=new Solution();
        ListNode t=solution.mergeKLists(listNode);
        String s="s";
        while (t!=null){
            System.out.println(t.val);
            t=t.next;
        }
    }
}

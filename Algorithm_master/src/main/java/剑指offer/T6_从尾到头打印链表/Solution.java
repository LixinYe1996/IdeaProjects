package 剑指offer.T6_从尾到头打印链表;

import java.util.Stack;

class Node{
    int value;
    Node next;
    Node(int value){
        this.value=value;
    }
}
public class Solution {
    public int[] reversePrint(Node head) {
        Stack<Integer> stack=new Stack<Integer>();
        int length=0;
        while (head!=null){
            stack.push(head.value);
            head=head.next;
            length++;
        }
        int []reverse=new int[length];
        int i=0;
        while (!stack.empty()){
           reverse[i]=stack.pop();
           i++;
        }
        return reverse;
    }
}

class Main{
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(3);
        Node node3=new Node(2);
        node1.next=node2;
        node2.next=node3;
        System.out.println(new Solution().reversePrint(node1));
    }
}
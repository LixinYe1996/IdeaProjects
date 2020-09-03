package 剑指offer.T9_用两个栈实现队列;

import java.util.Stack;

public class CQueue {
    Stack<Integer> stack1=new Stack<Integer>();
    Stack<Integer> stack2=new Stack<Integer>();
    public CQueue() {
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack1.isEmpty()&&stack2.isEmpty()){
            return -1;
        }
        if(stack2.isEmpty()){
            while (!stack1.isEmpty()){
                int i=stack1.pop();
                stack2.push(i);
            }
        }
        return  stack2.pop();
    }
}
class Main{
    public static void main(String[] args) {
        CQueue obj=new CQueue();
        obj.appendTail(1);
        int param_2=obj.deleteHead();
        System.out.println(param_2);
    }
}

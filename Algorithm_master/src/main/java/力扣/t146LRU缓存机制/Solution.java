package t146LRU缓存机制;

/**
 * @author 叶立新
 * @date 2020/8/20 10:36
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//12

class LRUCache {
    class DListNode{
        int key;
        int value;
        DListNode prev;
        DListNode next;
        DListNode(){}
        DListNode(int key,int value){
         this.key=key;
         this.value=value;
        }


    }
    int size;
    int capacity;
    Map<Integer,DListNode> map=new HashMap<>();
    DListNode head;
    DListNode tail;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.size=0;
        head=new DListNode();
        tail=new DListNode();
        head.next=tail;
        tail.prev=head;

    }

    public int get(int key) {
        DListNode node=map.get(key);
        if(node==null){
            return -1;
        }
        removeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DListNode node=map.get(key);
        if(node==null){
            DListNode newNode=new DListNode(key,value);
            head.next.prev=newNode;
            newNode.next=head.next;
            head.next=newNode;
            newNode.prev=head;
            map.put(key,newNode);
            size++;
            if(size>capacity){
                removeTail();
            }
        }else {
            removeToHead(node);
            node.value=value;
        }

    }
    private  void removeNode(DListNode node){
        node.prev.next=node.next;
        node.next.prev=node.prev;


    }
    private  void removeToHead(DListNode node){
        removeNode(node);
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
        node.prev=head;

    }
    private  void removeTail(){
        size--;
        map.remove(tail.prev.key);
        tail.prev.prev.next=tail;
        tail.prev=tail.prev.prev;
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


class Main{
    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
//        PriorityQueue <Integer> priorityQueue=new PriorityQueue<>();
//        priorityQueue.add(1);
//        priorityQueue.add(5);
//        priorityQueue.add(-1);
//        priorityQueue.add(2);
//        System.out.println(  priorityQueue.remove());


    }

}

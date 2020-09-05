package 力扣.T146_LRU缓存机制;
import java.util.HashMap;
import java.util.Map;

class DLinkedeNode{
    int key;
    int value;
    DLinkedeNode prev;
    DLinkedeNode next;
    public DLinkedeNode(){}
    public DLinkedeNode(int key,int value){this.key=key;this.value=value;}
}
public class LRUCache {
    private int size;
    private int capacity;
    private DLinkedeNode head,tail;
    Map<Integer,DLinkedeNode> cahe=new HashMap<Integer, DLinkedeNode>();
    public  LRUCache(int capacity) {
        this.size=0;
        this.capacity=capacity;
        head=new DLinkedeNode();
        tail=new DLinkedeNode();
        head.next=tail;
        tail.prev=head;
    }
    public int get(int key) {
        DLinkedeNode node=cahe.get(key);
        if(node==null){
            return -1;
        }
        moveTohead(node);
        return node.value;
    }
    void moveTohead(DLinkedeNode node){
        removeNode(node);
        addTohead(node);
    }
    void addTohead(DLinkedeNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;

    }
    void removeNode(DLinkedeNode node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    DLinkedeNode removeTail(){
        DLinkedeNode node=tail.prev;
        removeNode(node);
        return node;
    }
    public void put(int key, int value) {
        DLinkedeNode node=cahe.get(key);
        if(node!=null){
            node.value=value;
            moveTohead(node);
        }else {

            DLinkedeNode newnode=new DLinkedeNode(key,value);
            addTohead(newnode);
            cahe.put(key,newnode);
            size++;
            if(size>capacity){
                DLinkedeNode tail=removeTail();
                cahe.remove(tail.key);
                size--;
            }
        }
    }
}
class Main{
    public static void main(String[] args) {
        LRUCache cache=new LRUCache(2);

    }
}
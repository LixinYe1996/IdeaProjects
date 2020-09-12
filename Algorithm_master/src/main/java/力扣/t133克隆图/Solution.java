package t133克隆图;

import java.util.*;

/**
 * @author 叶立新
 * @date 2020/8/12 10:27
 *给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。

 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 *测试用例格式：
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 *
 */

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Solution {
//    private HashMap<Node,Node> visited=new HashMap<>();
//    public Node cloneGraph(Node node) {
//        if(node==null){
//            return node;
//        }
//        if(visited.containsKey(node)){
//            return visited.get(node);
//        }
//        Node t=new Node(node.val,new ArrayList<>());
//        visited.put(node,t);
//        for(Node neighbor: node.neighbors){
//            t.neighbors.add(cloneGraph(neighbor));
//        }
//        return t;
//
//    }

    public Node cloneGraph(Node node) {
        if(node==null){
            return node;
        }
        HashMap<Node,Node> visited=new HashMap<>();
        Queue<Node> queue=new LinkedList<>();
        queue.add(node);
        visited.put(node,new Node(node.val,new ArrayList<>()));
        while (!queue.isEmpty()){
            Node t=queue.remove();
            for(Node neighbor:t.neighbors ){
                if(!visited.containsKey(neighbor)){
                    visited.put(neighbor,new Node(neighbor.val,new ArrayList<>()));
                    queue.add(neighbor);
                }
                visited.get(t).neighbors.add(visited.get(neighbor));
            }

        }
        return visited.get(node);
    }

//    public Node cloneGraph(Node node) {
//        if (node == null) {
//            return node;
//        }
//
//        HashMap<Node, Node> visited = new HashMap();
//
//        // 将题目给定的节点添加到队列
//        LinkedList<Node> queue = new LinkedList<Node>();
//        queue.add(node);
//        // 克隆第一个节点并存储到哈希表中
//        visited.put(node, new Node(node.val, new ArrayList()));
//
//        // 广度优先搜索
//        while (!queue.isEmpty()) {
//            // 取出队列的头节点
//            Node n = queue.remove();
//            // 遍历该节点的邻居
//            for (Node neighbor : n.neighbors) {
//                if (!visited.containsKey(neighbor)) {
//                    // 如果没有被访问过，就克隆并存储在哈希表中
//                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
//                    // 将邻居节点加入队列中
//                    queue.add(neighbor);
//                }
//                // 更新当前节点的邻居列表
//                visited.get(n).neighbors.add(visited.get(neighbor));
//            }
//        }
//    }
}
class Main{
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        Solution solution=new Solution();
        solution.cloneGraph(node1);
    }
}

package 剑指offer.T7_重建二叉树;

import java.util.HashMap;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
  }
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder==null||preorder.length==0){
            return null;
        }
        int length=preorder.length;
        Map<Integer,Integer> indexmap=new HashMap<Integer, Integer>();
        for(int i=0;i<length;i++){
            indexmap.put(inorder[i],i);
        }
        TreeNode root=buildTree(preorder,inorder,0,length-1,0,length-1,indexmap);
        return root;
    }
    public  TreeNode buildTree(int[]preorder,int[]inorder,int prestart,int preend,int instart,int inend,Map<Integer,Integer> map){
        if(prestart>preend){
            return null;
        }
        int rootval=preorder[prestart];
        TreeNode root=new TreeNode(rootval);
        if(prestart==preend){
            return root;
        }else {
            int rootindex=map.get(rootval);
            int left=rootindex-instart;
            int right=inend-rootindex;
            //pre 前序遍历 in中序遍历
            TreeNode leftSubTree=buildTree(preorder,inorder,prestart+1,prestart+left,instart,instart+left-1,map);
            TreeNode rightSubTree=buildTree(preorder,inorder,preend-right+1,preend,rootindex+1,inend,map);
            root.left=leftSubTree;
            root.right=rightSubTree;
            return  root;
        }

    }
}
class Main{
    public static void main(String[] args) {
//        int [] preorder = {1,2,4,7,3,5,6,8};
//        int [] inorder={4,7,2,1,5,3,8,6};
        int [] preorder = {3,9,20,15,7};
        int [] inorder = {9,3,15,20,7};
        new Solution().buildTree(preorder,inorder);
    }

}



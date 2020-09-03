package 剑指offer.T8_二叉树的下一个节点;


class TreeNode {
    char val;
    TreeNode left;
     TreeNode right;
     TreeNode parent;
     TreeNode(char x) { val = x; }
  }
public class Solution {
    TreeNode GetNext(TreeNode node){
        if(node==null){
            return null;
        }
        TreeNode p=null;
        if(node.right!=null){
            TreeNode r=p.right;
            while (r.left!=null)
                r=r.left;
            p=r;
        }else if(p.parent!=null){
            TreeNode c=p;
            TreeNode parent=p.parent;
            while (parent!=null&&c==p.right){
                c=parent;
                parent=parent.parent;
            }
            p=parent;
        }
        return p;
    }

}
class  Main{
    public static void main(String[] args) {
        TreeNode a=new TreeNode('a');

    }
}

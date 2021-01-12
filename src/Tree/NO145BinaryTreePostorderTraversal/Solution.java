package Tree.NO145BinaryTreePostorderTraversal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        HashSet<TreeNode> isVisitedNode = new HashSet<>();
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> path = new Stack<>();

        if (root != null)
            path.push(root);
        while (!path.empty()) {
            TreeNode node = path.peek();//只是查看顶部元素 不弹出
            boolean leftVisited = true;
            boolean rightVisited = true;//用来标记左右子树是否已经遍历过了
            if (node.right != null && !isVisitedNode.contains(node.right)) {//如果当前节点node的右节点（stack入栈的原因）存在且没有遍历（输出过）
                //一定要先right后left
                rightVisited = false;
                path.push(node.right);
            }
            if (node.left != null && !isVisitedNode.contains(node.left)) {
                leftVisited = false;
                path.push(node.left);
            }
            if (leftVisited == true && rightVisited == true) {
                //TreeNode topNode = path.pop();
                //result.add(topNode.val);
                //isVisitedNode.add(topNode);
                isVisitedNode.add(node);
                result.add(node.val);
                path.pop();
            }
        }
        return result;
    }

    //入栈法 树遍历本质 每个点都会遍历两次 前序遍历和中序遍历中的第二次是隐式的
    public List<Integer> postorderTraversalForStack(TreeNode root) {
        Stack<TreeNode> path=new Stack<>();
        List<Integer> result=new ArrayList<>();
        while(true){
            while(root!=null){
                path.push(root);
                path.push(root);
                root=root.left;
            }
            if(path.isEmpty())break;
            root=path.pop();
            if(!path.isEmpty()&&root==path.peek()){//path.isEmpty()也是root！=path.peek()的特殊情况（也就是二次遍历）
                root=root.right;//进入此分支 表示节点仅仅减去1次遍历（也就是最初正向遍历到的节点）
            }else{
                result.add(root.val);
                root=null;//表示当前node第二次遍历结束 第二次遍历结束后获得node值则为后序遍历
            }
        }
        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

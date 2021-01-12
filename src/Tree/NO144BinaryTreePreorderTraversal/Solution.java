package Tree.NO144BinaryTreePreorderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    //利用循环而非递归遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        ArrayList<Integer> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        //stack.push(root);//头部插入元素
        while (!stack.isEmpty()) {
            TreeNode tempNode = stack.pollLast();
            output.add(tempNode.val);
            if (tempNode.right != null) {
                stack.add(tempNode.right);
            }
            if (tempNode.left != null) {
                stack.add(tempNode.left);
            }
        }
        return output;
    }

    //Morris遍历 空间复杂度为O(1) 利用节点中空的左值或者右值来存放暂时的信息
    public List<Integer> preorderTraversalOfMorris(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                //找到左子树的最右侧节点
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;//找到左子树的最右侧叶子节点 当然这个叶子节点可能会出现 左子树就只有一个根节点
                                    //那么这个叶子节点也就是左子树的根节点
                                    //       1
                                    //      /  \
                                    //     2    3
                                    //    /  \   \
                                    //   4    5   6
                                    //
                                    //
                                    //
                if (pre.right == null) {//只要右子树为空的情况 就记录下当前cur值
                    result.add(cur.val);
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;//右子树为之前设置的cur值 此时还原（相当于检测循环）
                    cur = cur.right;
                }
            } else {
                result.add(cur.val);//4节点（叶子节点）会走到这一条分支
                cur = cur.right;
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

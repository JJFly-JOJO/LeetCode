package Tree.NO94BinaryTreeInorderTraversal;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    //递归遍历
    public void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null)
                helper(root.left, res);
            res.add(root.val);//inorder
            if (root.right != null)
                helper(root.right, res);
        }
    }

    //Morris中序遍历
    public List<Integer> inorderTraversalForMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    result.add(cur.val);
                    cur = cur.right;
                }
            } else {
                result.add(cur.val);
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

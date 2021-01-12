package Tree.NO101SymmetricTree;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<Integer> leftRes = new LinkedList<>();
        preOrder(root.left, leftRes);
        return compare(root.right, leftRes);
    }

    private void preOrder(TreeNode node, Queue<Integer> res) {
        if (node == null) {
            res.add(null);
            return;
        }
        res.add(node.val);
        preOrder(node.left, res);
        preOrder(node.right, res);
    }

    private boolean compare(TreeNode node, Queue<Integer> res) {
        if (node == null) {
            if (res.poll() != null) {
                return false;
            }
            return true;
        }
        if (res.peek() == null) {
            return false;
        } else {
            if (node.val != res.poll()) {
                return false;
            }
        }
        if (!compare(node.right, res)) {
            return false;
        }
        if (!compare(node.left, res)) {
            return false;
        }
        return true;
    }

    /**
     * 两端同时递归 左端按照左子树优先 右端按照右子树优先 同时比较！！！
     *
     * @param root
     * @return
     */
    public boolean isSymmetricForTwo(TreeNode root) {
        if (root == null) {
            return true;
        }
        return preOrderForTwoOri(root.left,root.right);
    }

    private boolean preOrderForTwoOri(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        if (preOrderForTwoOri(left.left, right.right) && preOrderForTwoOri(left.right, right.left)) {
            return true;
        }
        return false;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

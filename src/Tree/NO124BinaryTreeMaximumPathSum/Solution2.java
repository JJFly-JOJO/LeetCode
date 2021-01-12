package Tree.NO124BinaryTreeMaximumPathSum;

import Util.TreeNode;

/**
 * @author zzj
 * @version 2.0
 * @date 2020/9/22 17:13
 */
public class Solution2 {

    private int maxVal = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxVal = root.val;
        int left = 0;
        int right = 0;
        if (root.val > 0) {
            if (root.left != null) {
                left = DFS(root.left, root.val);
            }
            if (root.right != null) {
                right = DFS(root.right, root.val);
            }
        } else {
            if (root.left != null) {
                left = DFS(root.left, 0);
            }
            if (root.right != null) {
                right = DFS(root.right, 0);
            }
        }
        maxVal = Math.max(maxVal, root.val + left);
        maxVal = Math.max(maxVal, root.val + right);
        return Math.max(root.val + left + right, maxVal);
    }

    private int DFS(TreeNode node, int val) {
        int left = 0;
        int right = 0;
        if (val != 0) {
            maxVal = Math.max(maxVal, val);
        }
        maxVal = Math.max(node.val, maxVal);
        if (node.val > 0) {
            if (node.left != null) {
                left = DFS(node.left, node.val + val);
            }
            if (node.right != null) {
                right = DFS(node.right, node.val + val);
            }
        } else {
            if (node.left != null) {
                left = DFS(node.left, 0);
            }
            if (node.right != null) {
                right = DFS(node.right, 0);
            }
        }
        left += node.val;
        maxVal = Math.max(maxVal, left + right);
        right += node.val;
        maxVal = Math.max(maxVal, left);
        maxVal = Math.max(maxVal, right);
        int temp = Math.max(left, right);
        temp = Math.max(temp, node.val);
        return temp;
    }
}

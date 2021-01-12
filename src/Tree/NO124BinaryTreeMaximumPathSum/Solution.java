package Tree.NO124BinaryTreeMaximumPathSum;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/22 15:52
 *
 * --------------该解法·题意理解有误  该路径只能走一遍 不能一个节点走多次
 *
 */
public class Solution {

    private long maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        long sum = 0;
        DFS(root);
        return (int) maxSum;
    }

    private int DFS(TreeNode node) {
        long left = Integer.MIN_VALUE;
        if (node.left != null) {
            left = DFS(node.left);
        }
        long right = Integer.MIN_VALUE;
        if (node.right != null) {
            right = DFS(node.right);
        }
        int tempMax = node.val;
        if (node.val > 0) {
            if (left > 0 && right < 0) {
                tempMax = node.val + (int) left;
            } else if (left < 0 && right > 0) {
                tempMax = node.val + (int) right;
            } else if (left > 0 && right > 0) {
                tempMax = node.val + (int) left + (int) right;
            }
            maxSum = Math.max(tempMax, maxSum);

        } else {
            if (left > 0 && right < 0) {
                tempMax = node.val + (int) left;
                maxSum = Math.max(left, maxSum);
            } else if (left < 0 && right > 0) {
                tempMax = node.val + (int) right;
                maxSum = Math.max(right, maxSum);
            } else if (left > 0 && right > 0) {
                tempMax = node.val + (int) left + (int) right;
                maxSum = Math.max(maxSum, tempMax);
            } else {
                if (maxSum < 0) {
                    maxSum = Math.max(maxSum, Math.max(left, right));
                    maxSum = Math.max(maxSum, node.val);
                }
            }
        }
        return tempMax;
    }

}

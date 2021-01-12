package Tree.NO124BinaryTreeMaximumPathSum;

import Util.TreeNode;

/**
 * @author zzj
 * @version 3.0
 * @date 2020/9/22 20:19
 * --------------优雅的写法---------
 * 之前写复杂的原因：
 *              -2
 *                -3
 *                  2<------------在递归返回到此节点时 就已经在sum=left（left由于左子节点是负值 因此返回0）+right（右子树的最大值为3+1）+node.val(此时节点值为2)
 *               -1  3
 *                    1
 */
public class Solution3 {
    private int maxVal = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        return Math.max(getMax(root), maxVal);
    }

    private int getMax(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(getMax(node.left), 0);
        int right = Math.max(getMax(node.right), 0);
        int sum = left + right + node.val;
        if (sum > maxVal) {
            maxVal = sum;
        }
        return node.val + Math.max(left, right);
    }
}

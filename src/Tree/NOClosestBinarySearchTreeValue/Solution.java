package Tree.NOClosestBinarySearchTreeValue;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/18 15:01
 * @description ----------暴力遍历---------
 */
public class Solution {

    private double dis = (double) Integer.MAX_VALUE * 2;

    private int res = 0;

    public int closestValue(TreeNode root, double target) {
        dfs(root, target);
        return res;
    }

    private void dfs(TreeNode node, double target) {
        if (node.left != null) {
            dfs(node.left, target);
        }
        double t = Math.abs((double) node.val - target);
        if (t <= dis) {
            dis = t;
            res = node.val;
        }
        if (node.right != null) {
            dfs(node.right, target);
        }
    }

}

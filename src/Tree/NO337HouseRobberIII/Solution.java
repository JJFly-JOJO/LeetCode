package Tree.NO337HouseRobberIII;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/24 14:40
 * ---------------该方法存在超时情况 存在太多重复搜索-----------
 *
 */
public class Solution {

    public int rob(TreeNode root) {
        int val1 = DFS(root, true);
        int val2 = DFS(root, false);
        return Math.max(val1, val2);
    }

    private int DFS(TreeNode node, boolean choose) {
        if (node == null) {
            return 0;
        }
        if (choose) {
            if (node.left == null && node.right == null) {
                return node.val;
            } else {
                return node.val + DFS(node.left, false) + DFS(node.right, false);
            }
        } else {
            if (node.left == null && node.right == null) {
                return 0;
            } else {
                //当前节点没选 那么左右节点可选可不选
                //选 选
                int max1 = DFS(node.left, true) + DFS(node.right, true);
                //选 不选
                int max2 = DFS(node.left, true) + DFS(node.right, false);
                //不选 选
                int max3 = DFS(node.left, false) + DFS(node.right, true);
                //不选 不选
                int max4 = DFS(node.left, false) + DFS(node.right, false);
                return Math.max(Math.max(max1, max2), Math.max(max3, max4));
                //return DFS(node.left, true) + DFS(node.right, true);
            }
        }
    }

}

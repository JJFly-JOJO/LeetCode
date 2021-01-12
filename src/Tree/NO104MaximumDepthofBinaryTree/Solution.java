package Tree.NO104MaximumDepthofBinaryTree;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/21 17:01
 */
public class Solution {

    private int maxDepth = 0;

    public int maxDepth(TreeNode root) {
        if (root != null) {
            DFS(root, 1);
        }
        return maxDepth;
    }

    private void DFS(TreeNode node, int level) {
        if (node.left != null) {
            DFS(node.left, level + 1);
        }
        if (node.right != null) {
            DFS(node.right, level + 1);
        }
        if (node.left == null && node.right == null) {
            //到叶子层
            maxDepth = Math.max(maxDepth, level);
        }
    }

}

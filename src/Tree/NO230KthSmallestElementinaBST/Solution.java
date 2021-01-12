package Tree.NO230KthSmallestElementinaBST;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/8 10:21
 * @description
 */
public class Solution {

    private TreeNode kNode;

    private int count = 0;

    private int k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return kNode.val;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if (count >= k) {
            return;
        }
        dfs(node.left);
        count++;
        if (count == k) {
            kNode = node;
        }
        dfs(node.right);
    }

}

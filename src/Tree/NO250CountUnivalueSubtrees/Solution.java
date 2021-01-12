package Tree.NO250CountUnivalueSubtrees;

import Util.TreeNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/9 20:09
 * @description
 */
public class Solution {

    private int count;

    public static void main(String[] args) {
        TreeNode root = Util.createTree(new Integer[]{5, 5, 5, 5, 5, null, 5});
        System.out.println(new Solution().countUnivalSubtrees(root));
    }

    public int countUnivalSubtrees(TreeNode root) {
        return count;
    }

    private boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean lb = dfs(node.left);
        boolean rb = dfs(node.right);
        if (lb && rb) {
            TreeNode l = node.left;
            if (l != null && node.val != l.val) {
                return false;
            }
            TreeNode r = node.right;
            if (r != null && node.val != r.val) {
                return false;
            }
            count++;
            return true;
        }
        return false;
    }
}

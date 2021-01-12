package Tree.NO235LowestCommonAncestorofaBinarySearchTree;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/26 15:54
 */
public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        //p q谁大谁小还未知
        if (root.val >= p.val && root.val <= q.val || root.val <= p.val && root.val >= q.val) {
            return root;
        }
        TreeNode tempNode = null;
        if ((tempNode = lowestCommonAncestor(root.left, p, q)) != null) {
            return tempNode;
        }
        if ((tempNode = lowestCommonAncestor(root.right, p, q)) != null) {
            return tempNode;
        }
        return null;
    }
}

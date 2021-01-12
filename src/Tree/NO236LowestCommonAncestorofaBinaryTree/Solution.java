package Tree.NO236LowestCommonAncestorofaBinaryTree;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/20 15:06
 * @description
 */
public class Solution {

    private TreeNode res;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        find(root, p, q);
        return res;
    }

    private int find(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return 0;
        }
        int a = 0;
        if (node.val == p.val || node.val == q.val) {
            a = 1;
        }
        if ((a += find(node.left, p, q)) == 2 && res == null) {
            res = node;
        }
        if ((a += find(node.right, p, q)) == 2 && res == null) {
            res = node;
        }
        return a;
    }

}

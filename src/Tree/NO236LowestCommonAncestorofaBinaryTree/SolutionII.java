package Tree.NO236LowestCommonAncestorofaBinaryTree;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/20 15:15
 * @description --------------更加巧妙的递归策略-------------
 * 思路：当q节点是p节点的父节点或者p节点是q节点的父节点时，我们其实都不在需要往下递归，也就是说到了p或者q（父节点）
 * 就可以直接返回了，因为结果一定是该节点，如果两个p、q节点是分布在二叉树两侧呢？
 */
public class SolutionII {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l != null && r != null) {
            return root;
        }
        if (l != null) {
            return l;
        }
        return r;
    }

}

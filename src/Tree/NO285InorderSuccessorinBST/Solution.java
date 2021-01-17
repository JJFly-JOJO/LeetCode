package Tree.NO285InorderSuccessorinBST;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/15 22:06
 * @description
 */
public class Solution {

    private TreeNode pNode;

    private TreeNode res;

    private boolean find;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        pNode = p;
        return dfs(root);
    }

    private TreeNode dfs(TreeNode node) {
        TreeNode t;
        if (node.left != null) {
            t = dfs(node.left);
            if (t != null) {
                return t;
            }
        }
        if (find) {
            return node;
        }
        if (pNode == node) {
            find = true;
        }
        if (node.right != null) {
            t = dfs(node.right);
            return t;
        }
        return null;
    }

}

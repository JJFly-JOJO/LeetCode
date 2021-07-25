package Tree.NO114FlattenBinaryTreetoLinkedList;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/20 15:35
 * @description
 */
public class SolutionI {

    public void flatten(TreeNode root) {
        dfs(root);
    }

    private TreeNode dfs(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = dfs(node.left);
        if (left == null) {
            if (node.right == null) {
                return node;
            }
            return dfs(node.right);
        }
        TreeNode temp = node.right;
        node.right = node.left;
        left.right = temp;
        node.left = null;
        return temp == null ? left : dfs(temp);
    }

}

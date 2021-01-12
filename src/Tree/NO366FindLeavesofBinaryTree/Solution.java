package Tree.NO366FindLeavesofBinaryTree;

import Util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/11 14:50
 * @description
 */
public class Solution {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        while (root.left != null || root.right != null) {
            List<Integer> l = new ArrayList<>();
            dfs(root, l, res);
            res.add(l);
        }
        List<Integer> l = new ArrayList<>();
        l.add(root.val);
        res.add(l);
        root = null;
        return res;
    }

    private TreeNode dfs(TreeNode node, List<Integer> l, List<List<Integer>> res) {
        if (node.left == null && node.right == null) {
            l.add(node.val);
            return null;
        }
        if (node.left != null) {
            node.left = dfs(node.left, l, res);
        }
        if (node.right != null) {
            node.right = dfs(node.right, l, res);
        }
        return node;
    }

}

package Tree.NO98ValidateBinarySearchTree;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/25 21:39
 * ------------------笔记--------------
 */
public class Solution {

    /**
     * 递归-----当我们函数传入的形参存在0值比较 但是传入的参数又想表示当前值没有时 我们可以用Integer包装类
     * 通过null来表示当前值不存在
     *
     * @param root
     * @return
     */
    public boolean isValidBSTForDFS(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode node, Integer leftMax, Integer rightMin) {
        if (node == null) {
            return true;
        }
        if (node.left != null && node.val <= node.left.val) {
            return false;
        }
        if (node.right != null && node.val >= node.right.val) {
            return false;
        }
        if (leftMax != null && node.val <= leftMax || !helper(node.left, leftMax, node.val)) {
            return false;
        }
        if (rightMin != null && node.val >= rightMin || !helper(node.right, node.val, rightMin)) {
            return false;
        }
        return true;
    }

    /**
     * 更加简化的写法 因为是当前节点与左侧最大值 以及 右侧最小值进行比较
     * 其实该比较就包含了当前节点与左节点以及当前节点与右节点的比较
     *
     * @param node
     * @param leftMax
     * @param rightMin
     * @return
     */
    private boolean helper2(TreeNode node, Integer leftMax, Integer rightMin) {
        if (node == null) {
            return true;
        }
        if (leftMax != null && node.val <= leftMax) {
            return false;
        }
        if (rightMin != null && node.val >= rightMin) {
            return false;
        }
        if (!helper2(node.left, leftMax, node.val)) {
            return false;
        }
        if (!helper2(node.right, node.val, rightMin)) {
            return false;
        }
        return true;
    }

}

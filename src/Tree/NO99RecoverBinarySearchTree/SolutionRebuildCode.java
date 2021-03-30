package Tree.NO99RecoverBinarySearchTree;

import Util.TreeNode;

import java.util.ArrayDeque;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/19 14:24
 * @description -------------【重构代码】------------
 */
public class SolutionRebuildCode {

    public void recoverTree(TreeNode root) {
        TreeNode x = null;
        TreeNode y = null;
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val < pre.val) {
                //第二次遇到的是错误交换的小数
                y = root;
                if (x == null) {
                    //第一次遇到的 是错误交换的大数
                    x = pre;
                } else {
                    break;
                }
            }
            pre = root;
            root = root.right;
        }
        swap(x, y);
    }

    private void swap(TreeNode x, TreeNode y) {
        int t = x.val;
        x.val = y.val;
        y.val = t;
    }

}

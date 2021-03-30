package Tree.NO99RecoverBinarySearchTree;

import Util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/19 13:58
 * @description ------------抓住题干 只存在两个节点被错误交换以及二叉搜索树遍历时得到的数是从小到大----------
 */
public class Solution {

    List<TreeNode[]> list = new ArrayList<>();

    public void recoverTree(TreeNode root) {
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (list.size() <= 2 && (!stack.isEmpty() || root != null)) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val < pre.val) {
                list.add(new TreeNode[]{pre, root});
            }
            pre = root;
            root = root.right;
        }
        if (list.size() == 1) {
            TreeNode[] ex = list.get(0);
            int t = ex[0].val;
            ex[0].val = ex[1].val;
            ex[1].val = t;
            return;
        }
        TreeNode[] ex1 = list.get(0);
        TreeNode[] ex2 = list.get(1);
        int t = ex1[0].val;
        ex1[0].val = ex2[1].val;
        ex2[1].val = t;
    }

}

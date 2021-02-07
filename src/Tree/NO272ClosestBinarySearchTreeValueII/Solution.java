package Tree.NO272ClosestBinarySearchTreeValueII;

import Util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/19 11:05
 * @description --------------抓住二叉搜索树 中序遍历 值是从小到达开始遍历的 使用栈的方法类比：NO1716（双周赛43场）--------------
 */
public class Solution {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        ArrayDeque<TreeNode> sTreeNode = new ArrayDeque<>();
        ArrayDeque<Integer> sInteger = new ArrayDeque<>();
        int count = k;
        //迭代模拟中序遍历
        while (count > 0 && (!sTreeNode.isEmpty() || root != null)) {
            while (root != null) {
                sTreeNode.push(root);
                root = root.left;
            }
            //中序
            root = sTreeNode.pop();
            if (root.val >= target) {
                while (count > 0 && !sInteger.isEmpty() && Math.abs(root.val - target) >= Math.abs(sInteger.peek() - target)) {
                    res.add(sInteger.pop());
                    count--;
                }
                if (count > 0) {
                    res.add(root.val);
                    count--;
                }
            } else {
                //小于target 入栈
                sInteger.push(root.val);
            }
            root = root.right;
        }
        //前k个还没有算完 小于栈的元素还有剩余
        while (count > 0) {
            res.add(sInteger.pop());
            count--;
        }
        return res;
    }

}

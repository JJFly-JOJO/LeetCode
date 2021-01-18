package Tree.NOClosestBinarySearchTreeValue;

import Util.TreeNode;

import java.util.ArrayDeque;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/18 15:02
 * @description ---------使用迭代来控制遍历--------
 * 注意：我们的中序遍历二叉搜索树是一定可以保证数值是从小到大，也就是当遇到target>=preVal&&tartget<=curVal
 * 我们一定就可以返回了，因为再往后遍历那么target都小于curVal和preVal了
 */
public class SolutionII {

    public int closestValue(TreeNode root, double target) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        long preVal = Long.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            //左递归到最小值处
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (target >= preVal && target <= root.val) {
                break;
            }
            preVal = root.val;
            root = root.right;
        }
        long rootVal = root == null ? Long.MIN_VALUE : root.val;
        return Math.abs(target - preVal) < Math.abs(target - rootVal) ? (int) preVal : (int) rootVal;
    }

}

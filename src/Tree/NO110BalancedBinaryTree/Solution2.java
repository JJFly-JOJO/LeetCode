package Tree.NO110BalancedBinaryTree;

import Util.TreeNode;

/**
 * @author zzj
 * @version 2.0
 * @date 2020/9/21 19:43
 * -------------更简洁的写法 但是会重复遍历树---------
 * 区别Solution1 Solution1在递归的时候我们会用一个对象记录并且在当前层递归结束后更新上一层节点的高度值
 * （节点高度值是左右子树中高度对高的那一个 作为该节点的高度值）
 */
public class Solution2 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1
                    && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }
}

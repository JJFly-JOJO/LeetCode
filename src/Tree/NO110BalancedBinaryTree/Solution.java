package Tree.NO110BalancedBinaryTree;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/21 17:15
 */
class TempValue {
    public int value = 0;

    public TempValue() {
    }

    public TempValue(int v) {
        value = v;
    }
}

public class Solution {


    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        //Integer中的value成员类型是final类型 private final int value;
        //因此修改Integer中的value值是会创建新的Integer对象的
        //Integer leftHeight = 0;
        //Integer rightHeight = 0;
        TempValue leftHeight = new TempValue();
        TempValue rightHeight = new TempValue();
        if (root.left != null) {
            if (!DFS(root.left, leftHeight)) {
                return false;
            }
        }
        if (root.right != null) {
            if (!DFS(root.right, rightHeight)) {
                return false;
            }
        }
        return Math.abs(leftHeight.value - rightHeight.value) <= 1;
    }

    private boolean DFS(TreeNode node, TempValue height) {
        TempValue leftHeight = new TempValue(height.value + 1);
        TempValue rightHeight = new TempValue(height.value + 1);
        if (node.left != null) {
            if (!DFS(node.left, leftHeight)) {
                return false;
            }
        }
        if (node.right != null) {
            if (!DFS(node.right, rightHeight)) {
                return false;
            }
        }
        height.value = Math.max(leftHeight.value, rightHeight.value);
        return Math.abs(leftHeight.value - rightHeight.value) <= 1;
    }

}

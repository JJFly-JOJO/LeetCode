package Tree.NO173BinarySearchTreeIterator;

import Util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/7 14:33
 * @description ---------------中序遍历的受控递归---------------
 * 思路:栈模拟中序dfs，但是这里我们要能够控制dfs的时间 该方法空间复杂度O(h)(h为树的高度)
 */
public class BSTIteratorII {

    /**
     * 栈顶元素永远要满足是当前还未遍历的最小值（搜索树的最左值）
     */
    Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIteratorII(TreeNode root) {
        inorderLeft(root);
    }

    /**
     * 当前节点左递归到最深处 递归同时压栈
     *
     * @param node
     */
    private void inorderLeft(TreeNode node) {
        if (node != null) {
            stack.push(node);
            inorderLeft(node.left);
        }
    }

    /**
     * 也可以使用while的方法模拟递归入栈
     *
     * @param node
     */
    private void inorderLeftII(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        inorderLeft(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

}

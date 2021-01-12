package Tree.NO173BinarySearchTreeIterator;

import Util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/7 14:16
 * @description -----------方法一：扁平化二叉树--------
 * 思路：二叉树本质是维护了一个有序的集合，我们按中序遍历将其放入一个一位数组中，我们的迭代器只用操作该一位数组即可
 */
public class BSTIterator {

    List<TreeNode> list = new ArrayList<>();

    private int index = 0;

    public BSTIterator(TreeNode root) {
        dfs(root);
    }

    private void dfs(TreeNode node) {
        if (node.left != null) {
            dfs(node.left);
        }
        list.add(node);
        if (node.right != null) {
            dfs(node.right);
        }
    }

    public int next() {
        return list.get(index++).val;
    }

    public boolean hasNext() {
        return index < list.size();
    }

}

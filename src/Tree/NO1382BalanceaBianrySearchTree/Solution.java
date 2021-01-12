package Tree.NO1382BalanceaBianrySearchTree;

import Util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/30 15:33
 * @description
 */
public class Solution {

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortedArr = new ArrayList<>();
        //中序遍历二叉搜索树得到有序数组
        dfs(sortedArr, root);
        return getBSTRoot(0, sortedArr.size() - 1, sortedArr);
    }

    private TreeNode getBSTRoot(int start, int end, List<Integer> list) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(list.get(end));
        }
        int mid = (start + end) >>> 1;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = getBSTRoot(start, mid - 1, list);
        node.right = getBSTRoot(mid + 1, end, list);
        return node;
    }

    private void dfs(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            dfs(list, node.left);
        }
        list.add(node.val);
        if (node.right != null) {
            dfs(list, node.right);
        }
    }

}

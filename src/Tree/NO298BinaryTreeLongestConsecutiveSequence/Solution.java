package Tree.NO298BinaryTreeLongestConsecutiveSequence;

import Util.TreeNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/10 16:03
 * @description
 */
public class Solution {

    private int l = 0;

    public static void main(String[] args) {
        TreeNode root = Util.createTree(new Integer[]{1, null, 3, 2, 4, null, null, null, 5});
        System.out.println(new Solution().longestConsecutive(root));
    }

    public int longestConsecutive(TreeNode root) {
        //return Math.max(l, dfs(root));
        //函数传参 参数是顺序拷贝的
        return Math.max(dfs(root), l);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lLen = dfs(node.left);
        if (node.left != null && node.left.val - node.val != 1) {
            l = Math.max(l, lLen);
            lLen = 0;
        }
        int rLen = dfs(node.right);
        if (node.right != null && node.right.val - node.val != 1) {
            l = Math.max(l, rLen);
            rLen = 0;
        }
        return Math.max(lLen, rLen) + 1;
    }
}

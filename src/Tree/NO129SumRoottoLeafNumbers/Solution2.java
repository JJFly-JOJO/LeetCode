package Tree.NO129SumRoottoLeafNumbers;

import Util.TreeNode;
import Util.Util;

public class Solution2 {

    public static void main(String[] args) {
        TreeNode root = Util.createTree(new Integer[]{1, 2, 3});
        System.out.println(new Solution().sumNumbers(root));
    }

    public int sumNumbers(TreeNode root) {
        return DFS(root, 0, 0);
    }

    private int DFS(TreeNode root, int sum, int res) {
        if (root == null) {
            return res;
        }
        sum = sum * 10 + root.val;
        if (root.left != null) {
            res = DFS(root.left, sum, res);
        }
        if (root.right != null) {
            res = DFS(root.right, sum, res);
        }
        if (root.right == null && root.left == null) {
            return res+=sum;
        }
        return res;
    }

}

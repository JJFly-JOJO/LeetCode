package Tree.NO95UniqueBinarySearchTreesII;

import Util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/5 23:12
 * @description
 */
public class Solution {

    private List<TreeNode>[][] dp;

    public List<TreeNode> generateTrees(int n) {
        dp = new List[n + 1][n + 1];
        return dividing(1, n);
    }

    private List<TreeNode> dividing(int l, int r) {
        if (l > r) {
            List<TreeNode> res = new ArrayList<>();
            res.add(null);
            return res;
        }
        if (dp[l][r] != null) {
            return dp[l][r];
        }
        List<TreeNode> res = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            List<TreeNode> left = dividing(l, i - 1);
            List<TreeNode> right = dividing(i + 1, r);
            for (TreeNode lNode : left) {
                for (TreeNode rNode : right) {
                    TreeNode node = new TreeNode(i);
                    node.left = lNode;
                    node.right = rNode;
                    res.add(node);
                }
            }
        }
        dp[l][r] = res;
        return res;
    }
}

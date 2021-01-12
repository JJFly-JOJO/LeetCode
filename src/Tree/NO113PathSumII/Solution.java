package Tree.NO113PathSumII;

import Util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        int curSum = 0;
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        DFS(root, sum, curSum, path, res);
        return res;
    }

    private void DFS(TreeNode root, int sum, int curSum, List<Integer> path, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        curSum += root.val;
        path.add(root.val);
        if (root.left != null) {
            DFS(root.left, sum, curSum, path, res);
        }
        if (root.right != null) {
            DFS(root.right, sum, curSum, path, res);
        }
        if (root.left == null && root.right == null) {
            if (curSum == sum) {
                res.add(new ArrayList<>(path));
            }
        }
        path.remove(path.size() - 1);
        return;
    }

}

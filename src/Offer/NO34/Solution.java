package Offer.NO34;

import Util.TreeNode;
import Util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/14 15:01
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = Util.createTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1});
        List<List<Integer>> res = new Solution().pathSum(root, 22);
        for (List<Integer> l : res) {
            System.out.println(l.toString());
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, 0, path, res, target);
        return res;
    }

    private boolean dfs(TreeNode node, int sum, List<Integer> path,
                        List<List<Integer>> res, int target) {
        if (node == null) {
            return true;
        }
        path.add(node.val);
        boolean left = dfs(node.left, sum + node.val, path, res, target);
        boolean right = dfs(node.right, sum + node.val, path, res, target);
        if (left && right && sum + node.val == target) {
            res.add(new ArrayList<>(path));

        }
        path.remove(path.size() - 1);
        return false;
    }

}

package Tree.NO437PathSumIII;

import Util.TreeNode;
import Util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/4 10:40
 * @description
 */
public class Solution {

    private int cnt = 0;

    public static void main(String[] args) {
        TreeNode root = Util.createTree(new Integer[]{1, -2, -3, 1, 3, -2, null, -1});
        System.out.println(new Solution().pathSum(root, -1));
    }

    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return cnt;
    }

    private Map<Integer, Integer> dfs(TreeNode node, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        if (node == null) {
            return map;
        }
        Map<Integer, Integer> lSet = dfs(node.left, target);
        Map<Integer, Integer> rSet = dfs(node.right, target);
        int val = target - node.val;
        if (val == 0) {
            cnt++;
        }
        if (lSet.containsKey(val)) {
            cnt += lSet.get(val);
        }
        if (rSet.containsKey(val)) {
            cnt += rSet.get(val);
        }
        map.put(node.val, 1);
        for (Map.Entry<Integer, Integer> e : lSet.entrySet()) {
            int sum = e.getKey() + node.val;
            map.put(sum, map.getOrDefault(sum, 0) + e.getValue());
        }
        for (Map.Entry<Integer, Integer> e : rSet.entrySet()) {
            int sum = e.getKey() + node.val;
            map.put(sum, map.getOrDefault(sum, 0) + e.getValue());
        }
        return map;
    }

}

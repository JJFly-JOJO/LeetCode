package Tree.NO108ConvertSortedArraytoBinarySearchTree;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/30 15:10
 * @description
 */
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(0, nums.length - 1, nums);
    }

    private TreeNode dfs(int start, int end, int[] nums) {
        if (end < start) {
            return null;
        }
        if (end == start) {
            return new TreeNode(nums[end]);
        }
        int mid = (start + end) >>> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(start, mid - 1, nums);
        node.right = dfs(mid + 1, end, nums);
        return node;
    }
}

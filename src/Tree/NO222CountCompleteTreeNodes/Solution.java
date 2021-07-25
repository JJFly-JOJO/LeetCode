package Tree.NO222CountCompleteTreeNodes;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/11 11:07
 * @description
 */
public class Solution {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h = findH(root);
        if (h == 0) {
            return 1;
        }
        //第h层只有一个元素
        int left = (int) Math.pow(2, h);
        int right = (int) Math.pow(2, h + 1) - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (findNode(root, mid, h) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private int findNode(TreeNode root, int val, int h) {
        int digit = 1 << (h - 1);
        int digitCount = h - 1;
        while (digit > 0) {
            int v = (val & digit) >>> digitCount;
            if (v == 0) {
                root = root.left;
            } else {
                root = root.right;
            }
            digit = digit >>> 1;
            digitCount--;
        }
        return root == null ? -1 : 1;
    }

    private int findH(TreeNode root) {
        int h = 0;
        while (root.left != null) {
            h++;
            root = root.left;
        }
        return h;
    }

}

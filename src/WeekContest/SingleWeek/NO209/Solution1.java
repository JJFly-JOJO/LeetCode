package WeekContest.SingleWeek.NO209;

import Util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/4 10:18
 */
public class Solution1 {
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int button = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode lastOne = null;
            for (int i = 0; i < size; i++) {
                if (button == 1) {
                    TreeNode temp = queue.poll();
                    if ((temp.val & 1) == 0) {
                        return false;
                    }
                    if (lastOne != null && lastOne.val >= temp.val) {
                        return false;
                    }
                    if (temp.left != null) {
                        queue.add(temp.left);
                    }
                    if (temp.right != null) {
                        queue.add(temp.right);
                    }
                    lastOne = temp;
                } else {
                    TreeNode temp = queue.poll();
                    if ((temp.val & 1) == 1) {
                        return false;
                    }
                    if (lastOne != null && lastOne.val <= temp.val) {
                        return false;
                    }
                    if (temp.left != null) {
                        queue.add(temp.left);
                    }
                    if (temp.right != null) {
                        queue.add(temp.right);
                    }
                    lastOne = temp;
                }
            }
            button ^= 1;
        }
        return true;
    }
}

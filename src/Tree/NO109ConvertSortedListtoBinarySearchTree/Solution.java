package Tree.NO109ConvertSortedListtoBinarySearchTree;

import Util.ListNode;
import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/7 11:16
 * @description
 */
public class Solution {

    public TreeNode sortedListToBST(ListNode head) {
        return dfs(head, null);
    }

    private TreeNode dfs(ListNode start, ListNode end) {
        if (start == end) {
            return null;
        }
        ListNode mid = findMid(start, end);
        TreeNode node = new TreeNode(mid.val);
        node.left = dfs(start, mid);
        node.right = dfs(mid.next, end);
        return node;
    }

    private ListNode findMid(ListNode start, ListNode end) {
        ListNode fast = start;
        ListNode slow = start;
        while (fast.next != end && fast.next.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}

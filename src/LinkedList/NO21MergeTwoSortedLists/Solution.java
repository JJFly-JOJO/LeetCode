package LinkedList.NO21MergeTwoSortedLists;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/1 18:45
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return dfs(l1, l2);
    }

    private ListNode dfs(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = dfs(l1.next, l2);
            return l1;
        } else {
            l2.next = dfs(l1, l2.next);
            return l2;
        }
    }

}

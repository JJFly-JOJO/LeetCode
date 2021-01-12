package LinkedList.NO82RemoveDuplicatesfromSortedListII;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/29 17:08
 * 单向链表
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        return dfs(null, head, head.next);
    }

    //[1,1,1,2,3]
    private ListNode dfs(ListNode last, ListNode cur, ListNode next) {
        if (cur == null) {
            return cur;
        }
        if (next == null) {
            if (last == null || last.val != cur.val) {
                return cur;
            } else {
                return null;
            }
        }
        if ((last == null || last.val != cur.val) && cur.val != next.val) {
            cur.next = dfs(cur, next, next.next);
            return cur;
        } else {
            return dfs(cur, next, next.next);
        }
    }

}

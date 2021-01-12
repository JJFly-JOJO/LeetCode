package LinkedList.NO19RemoveNthNodeFromEndofList;

import Util.ListNode;

/**
 * 双指针
 */
public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode nextHead = head;
        for (int i = 1; i <= n; i++) {
            nextHead = nextHead.next;
        }
        if (nextHead == null) {
            //移除倒数第一个
            return head.next;
        }
        head.next = remove(head.next, nextHead.next);
        return head;
    }

    private ListNode remove(ListNode head, ListNode nextHead) {
        if (nextHead == null) {
            return head.next;
        }
        head.next = remove(head.next, nextHead.next);
        return head;
    }

}

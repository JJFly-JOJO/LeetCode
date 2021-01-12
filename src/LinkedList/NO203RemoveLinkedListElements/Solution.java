package LinkedList.NO203RemoveLinkedListElements;

import Util.ListNode;

public class Solution {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode lastOne = null;
        ListNode curOne = head;
        while (curOne != null) {
            if (curOne.val == val) {
                if (lastOne == null) {
                    head = head.next;
                } else {
                    lastOne.next = curOne.next;
                }
            } else {
                lastOne = curOne;
            }
            curOne = curOne.next;
        }
        return head;
    }

}

package LinkedList.NO83RemoveDuplicatesfromSortedList;

import Util.ListNode;

public class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode curNode = head;
        while (curNode != null && curNode.next != null) {
            if (curNode.next.val == curNode.val) {
                curNode.next = curNode.next.next;
                continue;
            }
            curNode = curNode.next;
        }
        return head;
    }

}

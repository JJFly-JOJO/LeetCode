package LinkedList.NO206ReverseLinkedList;

public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode iter = head.next;
        ListNode temp = head;
        head.next = null;
        for (; iter != null; ) {
            ListNode tempNode = iter.next;
            iter.next = temp;
            temp = iter;
            iter = tempNode;
        }
        return temp;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}

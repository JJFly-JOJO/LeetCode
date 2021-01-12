package LinkedList.NO237DeleteNodeinaLinkedList;

import Util.ListNode;

public class Solution {

    private ListNode head;

    public void deleteNode(ListNode node) {
        if (head.val == node.val) {
            head = head.next;
        }
        ListNode lastOne = head;
        ListNode curOne = head.next;
        while (curOne.val != node.val) {
            lastOne = curOne;
            curOne = curOne.next;
        }
        lastOne.next = curOne.next;
    }

    public void deleteNode2(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }

}

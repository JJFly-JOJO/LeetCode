package LinkedList.NO328OddEvenLinkedList;

public class Solution {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode oddList = head;
        ListNode evenList = head.next;
        ListNode oddNode = head;
        ListNode evenNode = head.next;
        ListNode cur = head.next.next;
        while (cur != null && cur.next != null) {
            oddNode.next = cur;
            oddNode = cur;
            evenNode.next = cur.next;
            evenNode = cur.next;
            cur = cur.next.next;
        }
        if (cur == null) {
            oddNode.next = evenList;
        } else {
            oddNode.next = cur;
            evenNode.next = null;//evenList的最后一个偶数元素连接着当前的cur奇数元素 因此要断开
            cur.next = evenList;
        }
        return oddList;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}

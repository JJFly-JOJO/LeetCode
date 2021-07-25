package LinkedList.NO445AddTowNumbersII;

import Util.ListNode;

import java.util.ArrayDeque;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/31 10:56
 * @description
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayDeque<ListNode> stack1 = new ArrayDeque<>();
        ArrayDeque<ListNode> stack2 = new ArrayDeque<>();
        insertNode(stack1, l1);
        insertNode(stack2, l2);
        if (stack2.size() > stack1.size()) {
            ArrayDeque<ListNode> tmp = stack1;
            stack1 = stack2;
            stack2 = tmp;
            l1 = l2;
        }
        int digit = 0;
        while (!stack2.isEmpty()) {
            ListNode n1 = stack1.pop();
            ListNode n2 = stack2.pop();
            int val = n1.val + n2.val + digit;
            digit = val / 10;
            val = val % 10;
            n1.val = val;
        }
        while (!stack1.isEmpty() && digit == 1) {
            ListNode n1 = stack1.pop();
            int val = n1.val + digit;
            digit = val / 10;
            val = val % 10;
            n1.val = val;
        }
        if (digit == 1) {
            ListNode head = new ListNode(1);
            head.next = l1;
            l1 = head;
        }
        return l1;
    }

    private void insertNode(ArrayDeque<ListNode> stack, ListNode node) {
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
    }

}

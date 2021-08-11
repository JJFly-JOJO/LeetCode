package Lilith.NO01;

import Util.ListNode;

import java.util.ArrayDeque;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/11 7:27 下午
 * @description
 */
public class Solution {

    public ListNode formatList(ListNode head) {
        // write code here
        ArrayDeque<ListNode> aq = new ArrayDeque<>();
        boolean flag = true;
        while (head != null) {
            if (flag) {
                aq.addFirst(head);
                flag = false;
            } else {
                aq.addLast(head);
                flag = true;
            }
            head = head.next;
        }
        ListNode sentinel = new ListNode(0);
        ListNode h = sentinel;
        for (ListNode node : aq) {
            h.next = node;
            h = h.next;
        }
        h.next = null;
        return sentinel.next;
    }

}

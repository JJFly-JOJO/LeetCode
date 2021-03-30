package LinkedList.NO25ReverseNodesinkGroup;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/7 21:51
 * @description
 */
public class SolutionII {

    public static void main(String[] args) {
        ListNode head = Util.createLinkedList(new int[]{1, 2, 3, 4, 5, 6});
        Util.printLinkedList(new SolutionII().reverseKGroup(head, 3));

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode node = head;
        while (node != null) {
            count++;
            node = node.next;
        }
        if (count < k) {
            return head;
        }
        ListNode result = head;
        for (int i = 1; i < k; i++) {
            result = result.next;
        }
        ListNode first;
        ListNode pre;
        while (count >= k) {
            first = head;
            pre = null;
            for (int i = 0; i < k; i++) {
                ListNode t = head.next;
                head.next = pre;
                pre = head;
                head = t;
            }
            count -= k;
            ListNode t = head;
            if (count >= k) {
                for (int i = 1; i < k; i++) {
                    t = t.next;
                }
            }
            first.next = t;
        }
        return result;
    }

}

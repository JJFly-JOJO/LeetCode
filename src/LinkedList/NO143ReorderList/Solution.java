package LinkedList.NO143ReorderList;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/2 10:31
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class Solution {

    public static void main(String[] args) {
        ListNode head = Util.createLinkedList(new int[]{1, 2, 3, 4});
        new Solution().reorderList(head);
    }

    public void reorderList(ListNode head) {
        ListNode tail = reverseList(head);
        head.next = dfs(head.next, tail, 1);
        return;
    }

    /**
     * 易错点：认为这是两个链表 实际上我们是在对一个链表操作 如果链表反转了 那么正序的链表也就不存在了
     * ----------解决方法  我们不应该反转整个链表 而是反转后一半链表！！！！！！！！--------
     * @param head
     * @param tail
     * @param i
     * @return
     */
    private ListNode dfs(ListNode head, ListNode tail, int i) {
        if (head.val == tail.val) {
            return head;
        }
        if (i == 1) {
            tail.next = dfs(head, tail.next, 0);
            return tail;
        } else if (i == 0) {
            head.next = dfs(head.next, tail, 1);
            return head;
        }
        return null;
    }

    private ListNode reverseList(ListNode head) {
        ListNode lastNode = null;
        ListNode curNode = head;
        while (curNode.next != null) {
            ListNode temp = curNode.next;
            curNode.next = lastNode;
            lastNode = curNode;
            curNode = temp;
        }
        curNode.next = lastNode;
        return curNode;
    }

}

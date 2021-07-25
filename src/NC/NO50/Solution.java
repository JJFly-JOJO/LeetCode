package NC.NO50;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/11 17:01
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        Util.printLinkedList(new Solution().reverseKGroup(Util.createLinkedList(new int[]{1, 2, 3, 4, 5}), 2));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // write code here
        ListNode nextTail = getKNode(head, k);
        ListNode ans = nextTail;
        while (head != null && head != nextTail) {
            ListNode nextHead = reverseList(head, k);
            nextTail = getKNode(nextHead, k);
            head.next = nextTail;
            head = nextHead;
        }
        return ans;
    }

    private ListNode getKNode(ListNode node, int cnt) {
        ListNode head = node;
        while (node != null && cnt > 1) {
            node = node.next;
            cnt--;
        }
        return node == null ? head : node;
    }

    private ListNode reverseList(ListNode node, int cnt) {
        ListNode pre = null;
        while (node != null && cnt > 0) {
            ListNode tmp = node.next;
            node.next = pre;
            pre = node;
            node = tmp;
            cnt--;
        }
        return node;
    }

}

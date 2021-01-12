package LinkedList.NO61RotateList;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/10 11:44
 * @description
 */
public class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int l = 1;
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
            l++;
        }
        k = l - k % l;
        if (k == l) {
            return head;
        }
        ListNode node = head;
        while (k > 1) {
            node = node.next;
            k--;
        }
        ListNode res = node.next;
        node.next = null;
        last.next = head;
        return res;
    }

}

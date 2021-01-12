package LinkedList.NO86PartitionList;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/10 11:45
 * @description
 */
public class Solution {

    public ListNode partition(ListNode head, int x) {
        ListNode guardLess = new ListNode(1);
        ListNode res = guardLess;
        ListNode guardBigger = new ListNode(1);
        ListNode n = guardBigger;
        while (head != null) {
            if (head.val < x) {
                guardLess.next = head;
                guardLess = guardLess.next;
            } else {
                guardBigger.next = head;
                guardBigger = guardBigger.next;
            }
            head = head.next;
        }
        guardLess.next = n.next;
        guardBigger.next = null;
        return res.next;
    }

}

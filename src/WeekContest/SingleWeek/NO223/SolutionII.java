package WeekContest.SingleWeek.NO223;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/10 10:39
 * @description
 */
public class SolutionII {

    public ListNode swapNodes(ListNode head, int k) {
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        ListNode n1 = head;
        for (int i = 1; i < k; i++) {
            n1 = n1.next;
        }
        ListNode n2 = head;
        int last = len - k + 1;
        for (int i = 1; i < last; i++) {
            n2 = n2.next;
        }
        int v = n1.val;
        n1.val = n2.val;
        n2.val = v;
        return head;
    }

}

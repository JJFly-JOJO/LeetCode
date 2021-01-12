package LinkedList.NO142LinkedListCycleII;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/24 15:17
 * @description -----------------快慢指针（+1 +2的特点：路程永远都是1/2） 以及追及问题-------------------
 */
public class SolutionII {

    public ListNode detectCycle(ListNode head) {
        ListNode mark = head;
        ListNode slow = head;
        ListNode fast = head;
        /*while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }*/
        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);
        while (mark != fast) {
            mark = mark.next;
            fast = fast.next;
        }
        return mark;
    }


}

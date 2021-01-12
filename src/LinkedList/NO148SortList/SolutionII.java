package LinkedList.NO148SortList;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/27 9:34
 * @description --------------使用冒泡排序的思路 但是时间复杂度为O(n2)--------------
 */
public class SolutionII {

    public static void main(String[] args) {
        ListNode head = Util.createLinkedList(new int[]{-1, 5, 3, 4, 0});
        head = new SolutionII().sortList(head);
        int stop = 0;
    }

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        boolean tag;
        do {
            ListNode lastLast = null;
            ListNode last = head;
            ListNode cur = head.next;
            if (cur != null && cur.val < last.val) {
                head = cur;
            }
            tag = false;
            while (cur != null) {
                if (cur.val < last.val) {
                    last.next = cur.next;
                    cur.next = last;
                    if (lastLast != null) {
                        lastLast.next = cur;
                    }
                    ListNode t = cur;
                    cur = last;
                    last = t;
                    tag = true;
                }
                lastLast = last;
                last = cur;
                cur = cur.next;

            }
        } while (tag);
        return head;
    }

}

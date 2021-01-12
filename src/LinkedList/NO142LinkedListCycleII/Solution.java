package LinkedList.NO142LinkedListCycleII;

import Util.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/24 10:54
 * @description
 */
public class Solution {

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return head;
        }
        Set<ListNode> isVisited = new HashSet<>();
        isVisited.add(head);
        while (head.next != null) {
            head = head.next;
            if (isVisited.contains(head)) {
                return head;
            }
            isVisited.add(head);
        }
        return null;
    }

}

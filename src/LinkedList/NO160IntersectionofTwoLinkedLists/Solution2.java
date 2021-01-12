package LinkedList.NO160IntersectionofTwoLinkedLists;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/1 18:08
 */
public class Solution2 {

    /**
     * 用循环代替递归解决
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aHead = headA;
        ListNode bHead = headB;
        while (true) {
            if (headA == headB) {
                return headA;
            }
            if (headA == null) {
                headA = bHead;
            } else {
                headA = headA.next;
            }
            if (headB == null) {
                headB = aHead;
            } else {
                headB = headB.next;
            }
        }
    }

}

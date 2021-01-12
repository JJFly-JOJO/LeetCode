package LinkedList.NO148SortList;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/27 10:44
 * @description ------------------归并排序--------------
 * 思路：链表适合使用归并排序 时间复杂度O(nlogn)
 * 空间复杂度 O(logn)（空间复杂度的计算 我们考虑到递归栈空间的使用 注意递归并不是同时递归，
 * 递归是先走到最深处 然后ret 因此空间复杂度是递归深度）
 */
public class SolutionIII {

    public static void main(String[] args) {
        ListNode head = Util.createLinkedList(new int[]{4, 2, 1, 3});
        head = new SolutionIII().sortList(head);
        int stop = 0;
    }

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        return mergeSort(head, null);
    }

    /**
     * ----------------注意！！！-------------
     * 这里需要思考：找链表中点函数 我们的快慢指针是应该同时从head出发 还是head和head.next出发？
     * @param head
     * @param tail
     * @return
     */
    private ListNode findMid(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        //---------------------------------快慢指针初始点应该是head还是head和head.next
        //head与head才能够保证将链表拆分成最小链表-----即一个节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergeSort(ListNode node, ListNode tail) {
        ListNode mid = findMid(node, tail);
        if (mid == node) {
            //----------------------------------技巧！归并算法采用分治思想
            // --------------------------那么我们链表分治成片段period后 我们要将其尾部节点指向null
            // --------------------------这样既可以让我们将整个链表问题看作几个单独链表合并 又可以防止链表出现环
            node.next = null;
            return node;
        }
        ListNode l = mergeSort(node, mid);
        ListNode r = mergeSort(mid, tail);
        //merge
        //添加哨兵 避免头部讨论的情况------------------------------技巧！！
        ListNode guard = new ListNode(0);
        ListNode head = guard;
        while (l != null && r != null) {
            if (l.val < r.val) {
                head.next = l;
                l = l.next;
            } else {
                head.next = r;
                r = r.next;
            }
            head = head.next;
        }
        //---------------归并不是同时add 本质上是一个一个添加
        // （脑袋里不要有同时添加的场景 同时添加其实是先添加小的元素 然后此时一侧元素添加完毕 为empty while停止）
        // 因此一定有且只有有一边有剩下的元素！！！！！
        if (l == null) {
            head.next = r;
        }
        if (r == null) {
            head.next = l;
        }
        return guard.next;
    }

}

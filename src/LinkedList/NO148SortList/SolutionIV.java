package LinkedList.NO148SortList;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/27 14:43
 * @description -----------自底向上的归并排序 解决了递归使用栈造成的空间开销 利用迭代可以使得空间开销为常数---
 */
public class SolutionIV {

    public static void main(String[] args) {
        ListNode head = Util.createLinkedList(new int[]{-1, 5, 3, 4, 0});
        head = new SolutionIV().sortList(head);
        while (head != null) {
            System.out.println(head.val + " ");
            head = head.next;
        }
    }

    public ListNode sortList(ListNode head) {
        int len = 0;
        ListNode t = head;
        while (t != null) {
            len++;
            t = t.next;
        }
        if (len == 0) {
            return head;
        }
        //自底向上 从len=1开始归并 归纳证明：（1）当问题规模为1时 两个1的子问题归并为2能够保证有序
        // （2）对于任意两个问题规模为n的有序子数组 归并为规模为2n的数组 能够保证数组有序
        //添加一个哨兵节点
        ListNode guard = new ListNode(0, head);
        for (int i = 1; i <= len; i = i << 1) {
            ListNode cur = guard.next;
            //append用来记录拼接点
            ListNode append = guard;
            while (cur != null) {

                ListNode head1 = cur;
                for (int j = 1; j < i && cur != null; j++) {
                    cur = cur.next;
                }
                ListNode head2 = null;
                if (cur != null) {
                    head2 = cur.next;
                    //截断子链表尾部 防止环出现
                    cur.next = null;
                    cur = head2;
                }
                for (int j = 1; j < i && cur != null; j++) {
                    cur = cur.next;
                }
                ListNode temp = null;
                //截断第二个子链表尾部 此时要先记录下cur.next
                if (cur != null) {
                    temp = cur.next;
                    cur.next = null;
                }
                append.next = mergeSort(head1, head2);
                //下一个拼接点
//                while (append.next != null) {
//                    append = append.next;
//                }
                append = cur;
                //-----------------------归还上一个被截断的链表 保证我们下一次循环能够找到2*length的拼接点
                cur = temp;
            }
        }
        return guard.next;
    }

    private ListNode mergeSort(ListNode head1, ListNode head2) {
        ListNode guard = new ListNode(0);
        ListNode cur = guard;
        while (head1 != null && head2 != null) {
            //  = 保证排序稳定性
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        if (head1 == null) {
            cur.next = head2;
        }
        if (head2 == null) {
            cur.next = head1;
        }
        return guard.next;
    }

}

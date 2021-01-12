package LinkedList.NO92ReverseLinkedListII;

import Util.ListNode;
import Util.Util;

/**
 * 递归与迭代的区别：递归可以理解为 迭代进去了 还能够迭代出来（因为入栈出栈的关系） 当然如果返回void 出栈回来也相当于没出
 *              此时相当于一个单次迭代
 *              递归同样也可以理解为 由于栈的开销 递归拥有了记忆（缓存）
 *
 *
 * 迭代算法与递归算法解决链表反转问题:注意效率上来说 迭代更高 迭代空间复杂度为O（1）
 * <p>
 * （1）反转整个链表
 * ListNode reverse(ListNode head) {
 * if (head.next == null) return head;
 * ListNode last = reverse(head.next);
 * head.next.next = head;
 * head.next = null;
 * return last;
 * }
 * （2）反转前n个节点
 * ListNode successor = null; // 后驱节点
 * <p>
 * // 反转以 head 为起点的 n 个节点，返回新的头结点
 * ListNode reverseN(ListNode head, int n) {
 * if (n == 1) {
 * // 记录第 n + 1 个节点
 * successor = head.next;
 * return head;
 * }
 * // 以 head.next 为起点，需要反转前 n - 1 个节点
 * ListNode last = reverseN(head.next, n - 1);
 * <p>
 * head.next.next = head;
 * // 让反转之后的 head 节点和后面的节点连起来
 * head.next = successor;
 * return last;
 * }
 * （3）反转一部分节点
 * ListNode reverseBetween(ListNode head, int m, int n) {
 * // base case
 * if (m == 1) {
 * return reverseN(head, n);
 * }
 * // 前进到反转的起点触发 base case
 * head.next = reverseBetween(head.next, m - 1, n - 1);
 * return head;
 * }
 */
public class Solution {

    ListNode successor = null;//记录后驱节点

    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 4};
        ListNode head = Util.createLinkedList(nums);
        Util.printLinkedList(new Solution().reverseBetween2(head, 1, 2));
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }
        ListNode res = head;
        ListNode first = head;
        for (int i = 1; i < m; i++) {
            first = head;
            head = head.next;
        }
        if (n - m == 1) {
            first.next = head.next;
            head.next.next = head;
            head.next = null;
            return res;
        }
        ListNode nextNext = first.next.next;
        ListNode lastOne = head;
        ListNode cur;
        for (int i = 1; i <= (n - m); i++) {
            cur = nextNext;
            nextNext = cur.next;
            cur.next = lastOne;
            lastOne = cur;
        }
        first.next = lastOne;
        head.next = nextNext;
        return res;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }
        ListNode res = head;
        ListNode first = head;
        for (int i = 1; i < m; i++) {
            first = head;
            head = head.next;
        }
        if (m == 1) {
            first = null;
            ListNode last = first;
            ListNode cur = head;
            int count = (n - m) + 1;
            for (int i = 1; i <= count; i++) {
                ListNode temp = cur.next;
                cur.next = last;
                last = cur;
                cur = temp;
            }
            head.next = cur;
            res = last;
            return res;
        }
        ListNode last = first;
        ListNode cur = head;
        int count = (n - m) + 1;
        for (int i = 1; i <= count; i++) {
            ListNode temp = cur.next;
            cur.next = last;
            last = cur;
            cur = temp;
        }
        head.next = cur;
        first.next = last;
        return res;
    }

    /**
     * 递归解法
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetweenForRecursive(ListNode head, int m, int n) {
        //区别m=1与m！=1 m=1相当于反转前n个节点
        if (m == 1) {
            return reversN(head, n);
        }
        //-----------------------------------------------------------------------!!!!巧妙的使用递归
        //当然这里也可以先迭代到m处 再反转前n个（记录一下m-1节点即可）
        //如果m！=1 那么我们就走到m处按照反转前n个节点的方法进行反转
        head.next = reverseBetweenForRecursive(head.next, m - 1, n - 1);
        return head;
    }

    public ListNode reversN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        //递归的作用相当于找到要反转的前n个节点的第n个节点 并且还要记录下这个第N个节点的后继节点success
        //然后我们一层层返回时 将当前层节点指向反向
        //success节点作为暂时的供当前层head节点暂时表示反向的临时节点
        ListNode last = reversN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

}

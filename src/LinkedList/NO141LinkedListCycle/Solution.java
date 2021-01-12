package LinkedList.NO141LinkedListCycle;

import java.util.LinkedHashSet;
import java.util.Set;

public class Solution {

    public static final int MAX_INT = 0X07fffffff;

    //审题 仅仅是返回是否存在环
    //解法一 使用hash表
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new LinkedHashSet<>();
        while (head != null) {
            if (set.contains(head))
                return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }

    //递归标记法
    public boolean hasCycle2(ListNode head) {
        if (head == null)
            return false;
        if (head.val == MAX_INT)
            return true;
        head.val = MAX_INT;
        return hasCycle2(head.next);
    }

    //快慢指针 如果存在环 让快指针多个循环后自己追上慢指针
    public boolean hasCycle3(ListNode head) {
        if (head == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {//由于fast每次跳两格 slow每次跳一格 fast一定要保证fast.next非空 否则fast.next.next空指针异常
//由异常可以考虑 报异常 直接返回false
            if (slow.equals(fast))
                return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    //破坏链表结构 类似标记法 走过的node 自环
    public boolean hasCycle4(ListNode head) {
        if (head == null || head.next == null)
            return false;
        if (head == head.next)
            return true;
        ListNode tempNode = head.next;
        head.next = head;
        return hasCycle4(tempNode);
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}

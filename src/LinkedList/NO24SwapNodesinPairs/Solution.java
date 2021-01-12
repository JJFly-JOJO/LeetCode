package LinkedList.NO24SwapNodesinPairs;

import sun.swing.plaf.synth.DefaultSynthStyle;

public class Solution {
    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        new Solution().swapPairs2(head);

    }

    //递归思想 不要考虑全局 只考虑最小单元方法如何解决
    public ListNode swapPairs(ListNode head) {
        //终止条件
        if (head == null || head.next == null)
            return head;//每次以head next两个元素为单位进行交换
        ListNode temp = head.next.next;
        head.next.next = head;
        ListNode headForUnit = head.next;
        head.next = swapPairs(temp);//head.next连接下一个单元（两个元素）
        return headForUnit;
    }

    //非递归
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        //处理首节点
        ListNode result = head.next;
        ListNode pre = head;
        pre.next = head.next.next;
        result.next = head;
        while (pre.next != null && pre.next.next != null) {
            ListNode tempFirst = pre.next;
            ListNode nextAfterSecond = pre.next.next.next;
            pre.next = pre.next.next;
            pre.next.next = tempFirst;
            tempFirst.next = nextAfterSecond;
            pre = tempFirst;
        }
        return result;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}

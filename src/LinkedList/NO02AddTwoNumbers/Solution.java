package LinkedList.NO02AddTwoNumbers;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/30 10:12
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * 除了数字 0 之外，这两个数都不会以 0 开头
 */
public class Solution {

    private int choose = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        dfs(l1, l2, 0);
        if (choose == 0) {
            //等长 l1.next=dfs(l1.next, l2.next, i);返回l1
            return l1;
        }
        return choose == 1 ? l1 : l2;
    }

    private ListNode dfs(ListNode l1, ListNode l2, int i) {

        if (l1 != null && l2 != null) {
            int digitSum = l1.val + l2.val + i;
            if (digitSum > 9) {
                i = 1;
                digitSum -= 10;
            } else {
                i = 0;
            }
            l1.val = digitSum;
            l2.val = digitSum;
            //任意修改连接都可以 因为当前保证了l1 l2的元素值都相同
            //特殊情况 l1 l2等长 并且最后都进位了
            l1.next = dfs(l1.next, l2.next, i);
            return l1;
        } else if (l1 != null && l2 == null) {
            int digitSum = l1.val + i;
            if (digitSum > 9) {
                i = 1;
                l1.val = 0;
            } else {
                //不进位 直接返回
                l1.val = digitSum;
                return l1;
            }
            l1.next = dfs(l1.next, null, i);
            choose = 1;
            return l1;
        } else if (l1 == null && l2 != null) {
            int digitSum = l2.val + i;
            if (digitSum > 9) {
                i = 1;
                l2.val = 0;
            } else {
                l2.val = digitSum;
                return l2;
            }
            l2.next = dfs(l2.next, null, i);
            choose = 2;
            return l2;
        } else {
            if (i == 1) {
                return new ListNode(1);
            } else {
                return null;
            }
        }
    }

}

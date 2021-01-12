package LinkedList.NO25ReverseNodesinkGroup;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/7 17:04
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        new Solution().reverseKGroup(Util.createLinkedList(new int[]{1, 2, 3, 4, 5}), 2);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode res = null;
        ListNode node = head;
        //代码整洁
        /*for (int i = 1; i < k; i++) {
            head = head.next;
        }
        res = head;*/
        ListNode first = node;
        ListNode lastFirst = null;
        while (node != null) {
            int i = 1;
            while (node != null && i < k) {
                node = node.next;
                i++;
            }
            if (res == null) {
                res = node;
            }
            if (node != null) {
                //注意这里必须要t来暂存node.next 因为在下面的循环反转链表中 当first=node时
                //此时将first（node）.next修改为last 会造成下一次循环不会终止
                ListNode t = node.next;
                ListNode f = first;
                ListNode last = node.next;
                //反转子链表
                while (first != t) {
                    ListNode next = first.next;
                    first.next = last;
                    last = first;
                    first = next;
                }
                if (lastFirst != null) {
                    lastFirst.next = node;
                }
                lastFirst = f;
                node = t;
            }
        }
        return res;
    }

}

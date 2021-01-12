package LinkedList.NO369PlusOneLinkedList;

import Util.ListNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/4 13:52
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        int val = 1;
        System.out.println((val = val + 1) > val);
        System.out.println(val < (val = val + 1));
    }

    public ListNode plusOne(ListNode head) {
        if (dfs(head) == 1) {
            ListNode h = new ListNode(1);
            h.next = head;
            return h;
        }
        return head;
    }

    private int dfs(ListNode node) {
        if (node == null) {
            return 1;
        }
        //int temp = node.val;
        //return (node.val = (node.val + dfs(node.next)) % 10) < temp ? 1 : 0;
        return node.val > (node.val = (node.val + dfs(node.next)) % 10) ? 1 : 0;
    }

}

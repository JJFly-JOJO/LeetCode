package LinkedList.NO143ReorderList;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/2 11:50
 * ------------------递归方法 快慢指针思路 递归到中点 然后向上返回-------------
 */
public class Solution2 {

    public static void main(String[] args) {
        ListNode head = Util.createLinkedList(new int[]{1, 2, 3, 4});
        new Solution2().reorderList(head);
    }

    public void reorderList(ListNode head) {
        //特判
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        //无论如何 我们必须要知道链表长度来辅助我们解决问题
        int length = 0;
        //易错点！！！！！！！！！！head=head.next会造成节点走到最后 我们一定要记录下head
        ListNode markHead = head;
        while (head != null) {
            length++;
            head = head.next;
        }
        //-2是利用快慢指针的思想 快速走到中点处
        dfs(markHead, length);
        return;
    }

    private ListNode dfs(ListNode node, int len) {
        //走到中点的情况有两种 一种是奇数情况时的一个中点 另一种是偶数情况的两个中点
        if (len == 1) {
            ListNode temp = node.next;
            node.next = null;
            return temp;
        }
        if (len == 2) {
            ListNode temp = node.next.next;
            node.next.next = null;
            return temp;
        }
        //返回的子层的尾部节点的next
        //此递归就相当于中序遍历 先递归到最底层 再一层一层往外返回
        ListNode reTail = dfs(node.next, len - 2);
        //递归返回后 先记录下当前层的下一个节点 目的是让返回的尾结点
        ListNode curNodeNext = node.next;
        //temp是要返回给上层的当前尾结点的next
        ListNode temp = reTail.next;
        reTail.next = curNodeNext;
        node.next = reTail;
        return temp;
    }
}

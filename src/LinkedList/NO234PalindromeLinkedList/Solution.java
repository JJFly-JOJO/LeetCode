package LinkedList.NO234PalindromeLinkedList;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/1 19:08
 * <p>
 * 输入: 1->2
 * 输出: false
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 */
public class Solution {

    public static void main(String[] args) {
        ListNode head = Util.createLinkedList(new int[]{1, 4, -1, -1, 4, 1});
        System.out.println(new Solution().isPalindrome(head));
    }

    /**
     * 解法一：先利用双指针找到中点 然后反转reverse后面一部分的节点 然后再一一比较
     * <p>
     * 弊端：此方法会改变链表结构
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        //特判
        if (head == null || head.next == null) {
            return true;
        }
        //ListNode markHead=head;
        ListNode midHead = findMid(head);
        ListNode reverseNode = reverseList(midHead);
        while (true) {
            //reverseNode会先到null
            if (reverseNode == null) {
                return true;
            }
            if (head.val != reverseNode.val) {
                return false;
            }
            head = head.next;
            reverseNode = reverseNode.next;
        }
    }

    private ListNode reverseList(ListNode midHead) {
        //特判
        /*if (midHead.next == null) {
            return midHead;
        }*/
        ListNode tempNNode = null;
        ListNode tempNode = midHead;
        //midHead.next = null;
        while (tempNode.next != null) {
            ListNode temp = tempNode.next;
            tempNode.next = tempNNode;
            tempNNode = tempNode;
            tempNode = temp;
        }
        tempNode.next = tempNNode;
        return tempNode;
    }

    /**
     * 要找到中间节点 我们可以利用双指针 快指针每次比慢指针多走一步（因为中间点是结尾点的1/2）
     * 这样当快指针走到end处 此时慢指针只走了1/2 end
     *
     * @param head
     * @return
     */
    private ListNode findMid(ListNode head) {
        ListNode quickPointer = head;
        ListNode slowPointer = head;
        while (quickPointer.next != null && quickPointer.next.next != null) {
            quickPointer = quickPointer.next.next;
            slowPointer = slowPointer.next;
        }
        return slowPointer.next;
    }

}

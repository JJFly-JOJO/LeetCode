package LinkedList.NO143ReorderList;

import Util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/2 11:09
 * -------------------解法一------------
 * 由于链表不支持随机存取 每一次存取都需要遍历 因此我们可以先将链表放入线性集合中
 */
public class Solution1 {

    /**
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> listArray = new ArrayList<>();
        while (head != null) {
            listArray.add(head);
            head = head.next;
        }
        //双指针 向中间走
        int length = listArray.size();
        int left = 0;
        int right = length - 1;
        int button = 1;
        while (left < right) {
            if (button == 1) {
                listArray.get(left).next = listArray.get(right);
                left++;
            } else {
                listArray.get(right).next = listArray.get(left);
                right--;
            }
            button ^= 1;
        }
        listArray.get(left).next = null;
        return;
    }

}

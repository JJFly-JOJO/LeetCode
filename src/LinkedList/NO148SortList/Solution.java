package LinkedList.NO148SortList;

import Util.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/27 9:22
 * @description
 */
public class Solution {

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.sort(Comparator.comparingInt(o -> o.val));
        return dfs(list, 0);
    }

    private ListNode dfs(List<ListNode> list, int index) {
        if (index == list.size()) {
            return null;
        }
        list.get(index).next = dfs(list, index + 1);
        return list.get(index);
    }

}

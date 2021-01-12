package WeekContest.DoubleWeek.NO40;

import Util.ListNode;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/28 22:11
 * @description
 */
public class SolutionII {

    public static void main(String[] args) {
        ListNode list1 = Util.createLinkedList(new int[]{0, 1, 2, 3, 4, 5});
        ListNode list2=Util.createLinkedList(new int[]{1000000,1000001,1000002});
        new SolutionII().mergeInBetween(list1,3,4,list2);
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode head = list1;
        //find a
        int i = 1;
        ListNode deleteNode = list1;
        while (i < a) {
            list1 = list1.next;
            deleteNode = list1;
            i++;
        }
        ListNode addNode = list2;
        //find list2 last
        ListNode lastNode = list2;
        while (list2.next != null) {
            list2 = list2.next;
            lastNode = list2;
        }
        //find b
        int b1 = b + 1;
        list1 = list1.next;
        ListNode nextNode = list1;
        while (i < b1) {
            list1 = list1.next;
            nextNode = list1;
            i++;
        }
        //
        deleteNode.next = addNode;
        lastNode.next = nextNode;
        return head;
    }

}

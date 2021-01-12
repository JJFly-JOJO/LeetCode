package Random.NO382LinkedListRandomNode;


import java.util.Random;

public class Solution {

    ListNode head;

    public Solution(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        Random random = new Random();
        int count = 1;
        int value = head.val;
        ListNode temp = head.next;
        while (temp != null) {
            if (random.nextInt(++count) == 0) {
                value = temp.val;
            }
            temp = temp.next;
        }
        return value;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}

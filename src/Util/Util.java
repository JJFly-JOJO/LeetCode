package Util;

import java.util.ArrayDeque;

public class Util {

    public static TreeNode createTree(Integer[] arr) {
        // 使用队列来存储每一层的非空节点，下一层的数目要比上一层高
        ArrayDeque<TreeNode> pre = new ArrayDeque<>();
        TreeNode root = new TreeNode(arr[0]);
        pre.addLast(root);
        // 表示要遍历的下一个节点
        int index = 0;
        while (!pre.isEmpty()) {

            ArrayDeque<TreeNode> cur = new ArrayDeque<>();
            while (!pre.isEmpty()) {
                TreeNode node = pre.removeFirst();
                TreeNode left = null;
                TreeNode right = null;
                // 如果对应索引上的数组不为空的话就创建一个节点,进行判断的时候，
                // 要先索引看是否已经超过数组的长度，如果索引已经超过了数组的长度，那么剩下节点的左右子节点就都是空了
                // 这里index每次都会增加，实际上是不必要的，但是这样写比较简单
                if (++index < arr.length && arr[index] != null) {
                    left = new TreeNode(arr[index]);
                    cur.addLast(left);
                }
                if (++index < arr.length && arr[index] != null) {
                    right = new TreeNode(arr[index]);
                    cur.addLast(right);
                }
                node.left = left;
                node.right = right;
            }
            pre = cur;
        }


        return root;
    }

    public static Node createTreeForNode(Integer[] arr) {
        // 使用队列来存储每一层的非空节点，下一层的数目要比上一层高
        ArrayDeque<Node> pre = new ArrayDeque<>();
        Node root = new Node(arr[0]);
        pre.addLast(root);
        // 表示要遍历的下一个节点
        int index = 0;
        while (!pre.isEmpty()) {

            ArrayDeque<Node> cur = new ArrayDeque<>();
            while (!pre.isEmpty()) {
                Node node = pre.removeFirst();
                Node left = null;
                Node right = null;
                // 如果对应索引上的数组不为空的话就创建一个节点,进行判断的时候，
                // 要先索引看是否已经超过数组的长度，如果索引已经超过了数组的长度，那么剩下节点的左右子节点就都是空了
                // 这里index每次都会增加，实际上是不必要的，但是这样写比较简单
                if (++index < arr.length && arr[index] != null) {
                    left = new Node(arr[index]);
                    cur.addLast(left);
                }
                if (++index < arr.length && arr[index] != null) {
                    right = new Node(arr[index]);
                    cur.addLast(right);
                }
                node.left = left;
                node.right = right;
            }
            pre = cur;
        }


        return root;
    }

    public static void main(String[] args) {
        //Integer[] arr = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
        /*Integer[] arr = {5, 4};
        TreeNode tree = createTree(arr);
        prePrinTree(tree);*/

        int[] x = { 1, 2, 3, 4, 5, 6 };
        ListNode list = createLinkedList(x);
        printLinkedList(list);

    }

    private static void prePrinTree(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        prePrinTree(node.left);
        prePrinTree(node.right);
    }

    /**
     * 创建单向链表
     *
     * @param arr
     * @return
     */
    public static ListNode createLinkedList(int[] arr) {// 将输入的数组输入到链表中
        if (arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {// 过程
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static void printLinkedList(ListNode head) {// 将链表结果打印
        ListNode current = head;
        while (current != null) {
            System.out.printf("%d -> ", current.val);
            current = current.next;
        }
        System.out.println("NULL");
    }


}


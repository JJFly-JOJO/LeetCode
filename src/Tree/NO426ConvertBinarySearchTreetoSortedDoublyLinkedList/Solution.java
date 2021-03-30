package Tree.NO426ConvertBinarySearchTreetoSortedDoublyLinkedList;

import Util.Node;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/29 23:59
 * @description
 */
public class Solution {

    Node pre;

    Node head;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        dfs(root);
        pre.right = head;
        head.left = pre;
        return head;
    }

    /**
     * 中序遍历
     */
    private void dfs(Node node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (head == null) {
            head = node;
        }
        if (pre != null) {
            pre.right = node;
            node.left = pre;
        }
        pre = node;
        dfs(node.right);
    }

}

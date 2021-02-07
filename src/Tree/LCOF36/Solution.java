package Tree.LCOF36;

import Util.Node;
import Util.Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/29 22:49
 * @description
 */
public class Solution {

    Node maxNode;
    Node minNode;

    public static void main(String[] args) {
        Node root = Util.createTreeForNode(new Integer[]{4, 2, 5, 1, 3});
        System.out.println(new Solution().treeToDoublyList(root));
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        Node cur = root;
        while (cur != null) {
            minNode = cur;
            cur = cur.left;
        }
        cur = root;
        while (cur != null) {
            maxNode = cur;
            cur = cur.right;
        }
        dividingL(root.left, root, null);
        dividingR(root.right, root, null);
        minNode.left = maxNode;
        maxNode.right = minNode;
        return minNode;
    }

    private void dividingL(Node root, Node last, Node lastLast) {
        if (root == null) {
            return;
        }
        if (root.left == null) {
            if (lastLast != null) {
                root.left = lastLast;
                lastLast.right = root;
            }
        } else {
            dividingL(root.left, root, lastLast);
        }
        if (root.right == null) {
            if (last != null) {
                root.right = last;
            }
        } else {
            dividingR(root.right, root, last);
        }
    }

    private void dividingR(Node root, Node last, Node lastLast) {
        if (root == null) {
            return;
        }
        if (root.right == null) {
            if (lastLast != null) {
                root.right = lastLast;
                lastLast.left = root;
            }
        } else {
            dividingR(root.right, root, lastLast);
        }
        if (root.left == null) {
            if (last != null) {
                root.left = last;
            }
        } else {
            dividingL(root.left, root, last);
        }
    }

}

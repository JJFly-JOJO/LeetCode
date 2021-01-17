package Tree.NO297SerializeandDeserializeBinaryTree;

import Util.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/12 11:25
 * @description
 */
public class Codec {

    private static final String SPLIT = "\n";
    private static final String NULL_VALUE = "n";

    public static void main(String[] args) {
        String s = "aa\nbb\n";
        //String s = "aa";
        String[] arr = s.split("\\n");
        System.out.println("size=" + arr.length);
        System.out.println(Arrays.toString(arr));
        System.out.println("\\n");
    }

    /**
     * Encodes a tree to a single string.
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuilder path = new StringBuilder();
        if (root == null) {
            path.append(NULL_VALUE);
            return path.toString();
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        path.append(root.val).append(SPLIT);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (n.left != null) {
                    path.append(n.left.val).append(SPLIT);
                    q.offer(n.left);
                } else {
                    path.append(NULL_VALUE).append(SPLIT);
                }
                if (n.right != null) {
                    path.append(n.right.val).append(SPLIT);
                    q.offer(n.right);
                } else {
                    path.append(NULL_VALUE).append(SPLIT);
                }
            }
        }
        return path.toString();
    }

    /**
     * Decodes your encoded data to tree.
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        String[] d = data.split(SPLIT);
        if (d[0].equals(NULL_VALUE)) {
            return null;
        }
        int index = 0;
        TreeNode head = new TreeNode(Integer.parseInt(d[index++]));
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(head);
        while (!q.isEmpty() && index < d.length) {
            int size = q.size();
            for (int i = 0; i < size && index < d.length; i++, index++) {
                TreeNode n = q.poll();
                if (!d[index].equals(NULL_VALUE)) {
                    n.left = new TreeNode(Integer.parseInt(d[index]));
                    q.offer(n.left);
                }
                index++;
                if (index < d.length && !d[index].equals(NULL_VALUE)) {
                    n.right = new TreeNode(Integer.parseInt(d[index]));
                    q.offer(n.right);
                }
            }
        }
        return head;
    }

}

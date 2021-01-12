package Tree.NO102BinaryTreeLevelOrderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        System.out.println("before add:list is empty="+list.isEmpty());
        list.add(null);
        //list.add(1);
        list.add(null);
        System.out.println(list);
        System.out.println(list.isEmpty());
        System.out.println(list.size());//linkedlist是可以增加null元素的 并且占size数量
    }

    //BFS 二叉树遍历 记录层数level
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.offer(root);
        while (!treeNodeQueue.isEmpty()) {
            int size = treeNodeQueue.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = treeNodeQueue.poll();
                if (cur == null)
                    continue;
                level.add(cur.val);
                treeNodeQueue.offer(cur.left);
                treeNodeQueue.offer(cur.right);
            }
            if (!level.isEmpty()) {
                result.add(level);
            }
        }
        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

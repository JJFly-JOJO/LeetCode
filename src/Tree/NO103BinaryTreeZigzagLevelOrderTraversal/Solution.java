package Tree.NO103BinaryTreeZigzagLevelOrderTraversal;

import Util.TreeNode;
import Util.Util;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/25 19:39
 * ---------------笔记---------------
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * [3],
 * [20,9],
 * [15,7]
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = Util.createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        for (List temp : new Solution().zigzagLevelOrder(root)) {
            System.out.println(temp);
        }
    }

    /**
     * ------------可以直接层序遍历 奇数层调用Collections.reverse()--------
     * 这里我们采用双端队列 来减少reverse带来的O(n)的复杂度
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queueForBFSL1 = new LinkedList<>();
        queueForBFSL1.offer(root);
        Deque<TreeNode> queueForBFSL2 = new LinkedList<>();
        int button = 1;
        while (!queueForBFSL1.isEmpty() || !queueForBFSL2.isEmpty()) {
            List<Integer> tempRes = new ArrayList<>();
            TreeNode tempNode = null;
            int size = 0;

            if (button == 1) {
                size = queueForBFSL1.size();
                for (int i = 0; i < size; i++) {
                    tempNode = queueForBFSL1.pop();
                    tempRes.add(tempNode.val);
                    if (tempNode.left != null) {
                        queueForBFSL2.addFirst(tempNode.left);
                    }
                    if (tempNode.right != null) {
                        queueForBFSL2.addFirst(tempNode.right);
                    }
                }
            } else {
                size = queueForBFSL2.size();
                for (int i = 0; i < size; i++) {
                    tempNode = queueForBFSL2.pop();
                    tempRes.add(tempNode.val);
                    if (tempNode.right != null) {
                        queueForBFSL1.addFirst(tempNode.right);
                    }
                    if (tempNode.left != null) {
                        queueForBFSL1.addFirst(tempNode.left);
                    }
                }
            }

            res.add(tempRes);
            button ^= 1;
        }
        return res;
    }

}

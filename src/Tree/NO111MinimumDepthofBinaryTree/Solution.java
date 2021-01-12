package Tree.NO111MinimumDepthofBinaryTree;

import Util.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> tempPair = queue.poll();
            TreeNode curNode = tempPair.getKey();
            if (curNode.left == null && curNode.right == null) {
                return tempPair.getValue();
            }
            if (curNode.left != null) {
                queue.add(new Pair<>(curNode.left, tempPair.getValue() + 1));
            }
            if (curNode.right != null) {
                queue.add(new Pair<>(curNode.right, tempPair.getValue() + 1));
            }
        }
        return 0;
    }

}

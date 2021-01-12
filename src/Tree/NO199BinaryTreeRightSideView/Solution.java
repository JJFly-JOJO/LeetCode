package Tree.NO199BinaryTreeRightSideView;

import Util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/25 21:23
 * --------------------更好的思路：DFS 先右子节点遍历 这样我们可以发现第一次dfs到最底处
 * 就是将最右侧节点依次遍历到了一次 然后递归到最底层 再返回向左侧递归时 我们已经不再关心左侧的任何值了
 */
public class Solution {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queueForBFS = new LinkedList<>();
        queueForBFS.add(root);
        while (!queueForBFS.isEmpty()) {
            res.add(queueForBFS.peekLast().val);
            int size = queueForBFS.size();
            while (size > 0) {
                TreeNode tempNode = queueForBFS.pop();
                if (tempNode.left != null) {
                    queueForBFS.addLast(tempNode.left);
                }
                if (tempNode.right != null) {
                    queueForBFS.addLast(tempNode.right);
                }
                size--;
            }
        }
        return res;
    }
}

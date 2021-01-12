package Tree.NO107BinaryTreeLevelOrderTraversalII;

import Util.TreeNode;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/25 19:26
 * 示例：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * [15,7],
 * [9,20],
 * [3]
 */
public class Solution {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queueForBFS = new LinkedList<>();
        queueForBFS.add(root);
        while (!queueForBFS.isEmpty()) {
            List<Integer> tempRes = new ArrayList<>();
            int size = queueForBFS.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queueForBFS.poll();
                tempRes.add(curNode.val);
                if (curNode.left != null) {
                    queueForBFS.add(curNode.left);
                }
                if (curNode.right != null) {
                    queueForBFS.add(curNode.right);
                }
            }
            res.add(tempRes);
        }
        Collections.reverse(res);
        return res;
    }

}

package Tree.NO366FindLeavesofBinaryTree;

import Util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/11 15:05
 * @description -------------一次dfs 同时记录（通过递归返回）层level数 根据level数加入对应List集合中-------------
 */
public class SolutionII {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        height(root, res);
        return res;
    }

    private int height(TreeNode node, List<List<Integer>> res) {
        if (node == null) {
            return -1;
        }
        //注意层数是从叶子节点0开始向上累加的
        int level = Math.max(height(node.left, res), height(node.right, res)) + 1;
        if (res.size() < level + 1) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        return level;
    }

}

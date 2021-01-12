package Tree.NO199BinaryTreeRightSideView;

import Util.TreeNode;
import Util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 2.0
 * @date 2020/9/27 17:10
 * ------------DFS方法----------
 * 我们用depth记录层数 每一层最右边元素才加入res结果集中 也就是说我们要区分dfs到的当前层是第一次到的还是递归返回时第二次到的
 * 这里我们巧妙的利用了res结果集中元素的数量与当前depth做对比 如果当前depth小于res.size()就说明当前层已经遍历过
 * 第一次遍历到的元素已经加入到了结果集res中
 */
public class Solution2 {

    public static void main(String[] args) {
        TreeNode root = Util.createTree(new Integer[]{1, 2, 3});
        System.out.println(new Solution2().rightSideView(root));
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        DFS(root, res, 0);
        return res;
    }

    private void DFS(TreeNode node, List<Integer> res, int depth) {
        if (node == null) {
            return;
        }
        if (depth == res.size()) {
            res.add(node.val);
        }
        //只加右侧不行 存在此种情况 [1,2,3,4]
        //res.add(node.val);
        //右边先dfs 因为要右视图
        depth++;
        if (node.right != null) {
            DFS(node.right, res, depth);
        }
        if (node.left != null) {
            //注意用++depth 用depth+1 depth由于值拷贝不会发生变化
            DFS(node.left, res, depth);
        }
    }
}

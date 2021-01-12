package Tree.NO337HouseRobberIII;

import Util.TreeNode;

/**
 * @author zzj
 * @version 2.0
 * @date 2020/9/24 15:24
 * <p>
 * -----------对比version 1.0的暴力DFS-------
 * 我们其实可以“左右同时dfs” 也就是一层一层的走 当前节点为父节点 如果父节点rob
 * 那么下一层的儿子节点就都不能rob 而再下一层的孙子节点可以rob
 * 那么情况一-----父节点加孙子层的两个节点得到的结果
 * 如果当前父节点不rob 那么儿子层就都可以rob 而孙子层节点就都不可以rob
 * 那么情况二-----儿子层rob得到的结果
 * 思考：是否还有其他情况？
 * 如果父节点不rob 儿子节点也不rob 孙子节点再rob？显然是比父节点与孙子节点都rob得到的少的
 * 同理 父节点rob 孙子节点不rob  这就是我们version 1.0超时的原因（节点不rob 两个子节点都不rob 显然这样往下dfs的结果不是最大值）
 */
public class Solution2 {

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int money1 = 0;
        if (root.left != null) {
            money1 = rob(root.left.left) + rob(root.left.right);
        }
        int money2 = 0;
        if (root.right != null) {
            money2 = rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(root.val + money1 + money2, rob(root.left) + rob(root.right));
    }

}

package Tree.NO337HouseRobberIII;

import Util.TreeNode;

/**
 * @author zzj
 * @version 4.0
 * @date 2020/9/24 15:55
 * --------动态规划+后序遍历-------
 * 树状图的后序遍历思想：我们知道动态规划是子问题迭代到最终问题 因此 对于树
 * 我们清楚树的遍历我们只能从根节点开始递归 但是获取值的时机是可以改变的（也就是前序 中序 后序）
 * 由于动态规划是要从最小子问题向上迭代 自然我们树的遍历选择后序遍历
 * -------后序遍历时左子树递归完 右子树递归完 再在当前节点汇总
 * -------对应到该树的动态规划就是 左子树的最大值得到 再得到右子树的最大值 再更新（状态转移）当前节点
 * -------注意到该问题存在当前节点选了领接节点不能选的情况 因此需要增加一个选与不选的维度来消除后效性
 */
public class Solution4 {

    public int rob(TreeNode root) {
        //res[0] 表示当前节点rob res[1] 表示当前节点不rob
        //dp时 每个子问题节点都要记录下rob与不rob的最大值
        //类比solution1 其实version 1.0我们也是考虑的当前节点rob与不rob的情况 但是仅仅是DFS 没有记忆存值 因此存在超时
        int[] res = DFS(root);
        return Math.max(res[0], res[1]);
    }

    private int[] DFS(TreeNode node) {
        int[] res = new int[2];
        if (node == null) {
            return res;
        }
        int[] leftRes = DFS(node.left);
        int[] rightRes = DFS(node.right);
        //找到当前节点rob时的最大值 左右子节点必须不rob
        res[0] = node.val + leftRes[1] + rightRes[1];
        //找到当前节点不rob时最大值 左右子节点可以选择rob或者不rob
        res[1] = Math.max(Math.max(leftRes[0] + rightRes[0], leftRes[1] + rightRes[1]),
                Math.max(leftRes[0] + rightRes[1], leftRes[1] + rightRes[0]));
        return res;
    }

}

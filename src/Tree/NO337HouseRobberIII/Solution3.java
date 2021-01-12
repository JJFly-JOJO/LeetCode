package Tree.NO337HouseRobberIII;

import Util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 3.0
 * @date 2020/9/24 15:38
 * <p>
 * --------version 2.0任然存在优化空间-------
 * 注意到我们在爷爷层+孙子层 vs 儿子层 当我们在儿子层递归时
 * 儿子层+儿子的孙子层 vs 爷爷的孙子层（儿子的儿子层） 可以看见孙子层我们重复计算了
 * 因此我们可以采用一个hashMap来存储记忆的值
 */
public class Solution3 {

    private Map<TreeNode, Integer> nodeToVal = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Integer temp = null;
        if ((temp = nodeToVal.get(root)) != null) {
            return temp;
        }
        int money1 = 0;
        if (root.left != null) {
            money1 = rob(root.left.left) + rob(root.left.right);
        }
        int money2 = 0;
        if (root.right != null) {
            money2 = rob(root.right.left) + rob(root.right.right);
        }
        int max = Math.max(root.val + money1 + money2, rob(root.left) + rob(root.right));
        nodeToVal.put(root, max);
        return max;
    }

}

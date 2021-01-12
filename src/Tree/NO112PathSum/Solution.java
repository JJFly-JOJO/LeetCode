package Tree.NO112PathSum;


import Util.TreeNode;

public class Solution {

    public boolean hasPathSum(TreeNode root, int sum) {
        int curSum = 0;
        return preorder(root, sum, curSum);
    }

    /**
     * 此DFS是判断是否有从根节点的路径存在要求 而非一定要到叶节点的路径
     *
     * @param root
     * @param sum
     * @param curSum
     * @return
     */
    private boolean DFS(TreeNode root, int sum, int curSum) {
        if (root == null) {
            return false;
        }
        curSum += root.val;
        if (curSum == sum) {
            return true;
        }
        if (DFS(root.left, sum, curSum)) {
            return true;
        }
        if (DFS(root.right, sum, curSum)) {
            return true;
        }
        //curSum并不需要减回去 因为这是原生数据类型 传入的不是引用 而是在栈空间中新开辟的一块内存存取的拷贝内容
        //函数返回后 当前curSum就被销毁了
        return false;
    }

    private boolean preorder(TreeNode root, int sum, int curSum) {
        if (root == null) {
            //第一层可能出现根节点为null
            return false;
        }
        curSum += root.val;
        if (root.left != null) {
            if (preorder(root.left, sum, curSum)) {
                return true;
            }
        }
        if (root.right != null) {
            if (preorder(root.right, sum, curSum)) {
                return true;
            }
        }
        if (root.left == null && root.right == null) {
            if (curSum == sum) {
                return true;
            }
        }
        return false;
    }

}

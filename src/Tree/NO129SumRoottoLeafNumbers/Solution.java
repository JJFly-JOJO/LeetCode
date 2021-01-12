package Tree.NO129SumRoottoLeafNumbers;

import Util.TreeNode;
import Util.Util;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        TreeNode root = Util.createTree(new Integer[]{1, 2, 3});
        System.out.println(new Solution().sumNumbers(root));
    }

    public int sumNumbers(TreeNode root) {
        int sum = 0;
        List<Integer> path = new ArrayList<>();
        return DFS(root, sum, path);
    }

    private int DFS(TreeNode root, int sum, List<Integer> path) {
        if (root == null) {
            return sum;
        }
        path.add(root.val);
        if (root.left != null) {
            sum = DFS(root.left, sum, path);
        }
        if (root.right != null) {
            sum = DFS(root.right, sum, path);
        }
        if (root.right == null && root.left == null) {
            int factor = 1;
            int tempNum = 0;
            for (int i = path.size() - 1; i >= 0; i--) {
                tempNum += path.get(i) * factor;
                factor *= 10;
            }
            //自动拆箱装箱得到的Integer对象又是一个新new的对象了 不是原来的那个Integer对象！！！！！！！！！！！<-------易错
            //sum+=tempNum;
            sum += tempNum;
        }
        path.remove(path.size() - 1);
        return sum;
    }

}

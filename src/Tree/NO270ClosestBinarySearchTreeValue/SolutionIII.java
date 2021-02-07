package Tree.NO270ClosestBinarySearchTreeValue;

import Util.TreeNode;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/18 15:50
 * @description ---------二分法（分治） 复杂度O（H）H为树高度-------
 */
public class SolutionIII {

    public int closestValue(TreeNode root, double target) {
        int closestVal = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - closestVal)) {
                closestVal = root.val;
            }
            if (target > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return closestVal;
    }

}

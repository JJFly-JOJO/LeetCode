package Offer.NO04;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/6 0:02
 * @description
 */
public class Solution {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for (int[] r : matrix) {
            if (r[0] > target) {
                return false;
            }
            if (binarySearch(r, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

}

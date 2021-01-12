package Array.NO152MaximumProductSubarray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/18 11:13
 * @description -------------动态规划 联系NO53-----------
 *
 */
public class SolutionII {

    public int maxProduct(int[] nums) {
        int len = nums.length;
        //dp:以第i个元素结尾的子串的最大乘积以及最小乘积
        int[] maxDp = new int[len + 1];
        int[] minDp = new int[len + 1];
        //initialize
        maxDp[0] = 1;
        minDp[0] = 1;
        for (int i = 1; i <= len; i++) {
            maxDp[i] = Math.max(nums[i - 1], Math.max(nums[i - 1] * maxDp[i - 1], nums[i - 1] * minDp[i - 1]));
            minDp[i] = Math.min(nums[i - 1], Math.min(nums[i - 1] * maxDp[i - 1], nums[i - 1] * minDp[i - 1]));
        }
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            res = Math.max(res, maxDp[i]);
        }
        return res;
    }

    /**
     * ------------状态压缩-----------
     *
     * @param nums
     * @return
     */
    public int maxProductII(int[] nums) {
        int len = nums.length;
        //dp:以第i个元素结尾的子串的最大乘积以及最小乘积
        int maxDp = 1;
        int minDp = 1;
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            int tMax = maxDp;
            int tMin = minDp;
            maxDp = Math.max(nums[i - 1], Math.max(nums[i - 1] * tMax, nums[i - 1] * tMin));
            minDp = Math.min(nums[i - 1], Math.min(nums[i - 1] * tMax, nums[i - 1] * tMin));
            res = Math.max(res, maxDp);
        }
        return res;
    }


}

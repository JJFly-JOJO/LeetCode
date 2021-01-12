package Array.NO300LongestIncreasingSubsequence;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/1 10:17
 * @description -----------贪心算法+二分查找-----------
 */
public class SolutionII {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int res = 1;
        int[] dp = new int[len + 1];
        dp[1] = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > dp[res]) {
                dp[++res] = nums[i];
            } else if (nums[i] < dp[res]) {
                int index = binaryFind(dp, res, nums[i]);
                dp[index] = nums[i];
            }
        }
        return res;
    }

    private int binaryFind(int[] nums, int len, int target) {
        int l = 1;
        int r = len;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

}

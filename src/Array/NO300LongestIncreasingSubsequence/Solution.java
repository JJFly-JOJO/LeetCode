package Array.NO300LongestIncreasingSubsequence;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/30 15:21
 * @description -------------------暴力动态规划 枚举i之前所有点的构成的子序长度 找最大长度----------------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int[] res = new int[len];
        Arrays.fill(res, 1);
        int max = 1;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    res[i] = Math.max(res[i], res[j] + 1);
                    max = Math.max(max, res[i]);
                }
            }
        }
        return max;
    }

}

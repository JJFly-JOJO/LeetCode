package Array.NO239SlidingWindowMaximum;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/21 15:49
 * @description ----------------动态规划解法------------##类比NO53##----------
 */
public class Solution3 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        //特判
        int l = nums.length;
        if (l * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int[] leftDp = new int[l];
        //initialize
        for (int i = 0; i < l; i++) {
            if (i % k == 0) {
                leftDp[i] = nums[i];
                continue;
            }
            leftDp[i] = Math.max(leftDp[i - 1], nums[i]);
        }
        int[] rightDp = new int[l];
        //右侧需要初始化
        rightDp[l - 1] = nums[l - 1];
        for (int i = l - 2; i >= 0; i--) {
            if (i % k == 0) {
                rightDp[i] = nums[i];
                continue;
            }
            rightDp[i] = Math.max(rightDp[i + 1], nums[i]);
        }
        //dp
        int last = l - k;
        int[] res = new int[last + 1];
        for (int i = 0; i <= last; i++) {
            res[i] = Math.max(rightDp[i], leftDp[i + k - 1]);
        }
        return res;
    }
}

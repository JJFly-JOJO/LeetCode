package Array.NO239SlidingWindowMaximum;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/20 16:41
 * @description 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 */
public class Solution2 {

    /**
     * ------------------优化后的滑动窗口暴力解法(思路错误 后面新增的解可能超过当前最值！！！)----------------
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        //特判
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        int[] res = new int[n - k + 1];
        int r = 0;
        int lastI = n - k;
        for (int i = 0; i <= lastI; i++) {
            int maxVal = Integer.MIN_VALUE;
            int lastJ = i + k - 1;
            int tempI = i;
            for (int j = i; j <= lastJ; j++) {
                if (nums[j] >= maxVal) {
                    maxVal = nums[j];
                    i = j;
                }
            }
            //res赋值
            for (; tempI <= i && r < res.length; tempI++) {
                res[r++] = maxVal;
            }
        }
        return res;
    }
}

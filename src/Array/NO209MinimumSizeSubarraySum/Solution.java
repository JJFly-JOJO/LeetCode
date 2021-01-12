package Array.NO209MinimumSizeSubarraySum;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/22 11:28
 * @description --------------------------方法一 滑动窗口（双指针）方法--------------------------
 */
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minL = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        int sum = 0;
        int len = nums.length;

        while (r < len) {
            sum += nums[r++];
            if (sum >= s) {
                //移动left
                while (sum >= s) {
                    sum -= nums[l++];
                }
                minL = Math.min(minL, r - l + 1);
            }
        }
        return minL < Integer.MAX_VALUE ? minL : 0;
    }
}

package Array.NO164MaximumGap;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/7 11:08
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * <p>
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * <p>
 * --------------------解法一 快排 O(nlogn)的时间复杂度---------------------
 */
public class Solution {


    public int maximumGap(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 0;
        for (int i = 1; i < length; i++) {
            int val = nums[i] - nums[i - 1];
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

}

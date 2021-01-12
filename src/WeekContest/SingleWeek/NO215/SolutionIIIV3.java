package WeekContest.SingleWeek.NO215;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/17 10:38
 * @description ------------另一种前缀思路 我们是整个数组减去左右两端和为target 也就是说剩下的target' 值也是恒定的，
 * 我们要找左右两端前缀和个数最小，那么也就是找中间连续和target' 的个数最大！！！！
 */
public class SolutionIIIV3 {

    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        int sum = Arrays.stream(nums).sum();
        int target = sum - x;
        if (target < 0) {
            return -1;
        }
        //我们是要找最大数
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> subToNum = new HashMap<>();
        subToNum.put(0, 0);
        //针对 nums的和等于x的情况
        subToNum.put(sum, len);
        int subSum = 0;
        for (int i = 0; i < len; i++) {
            subSum += nums[i];
            int v = subSum - target;
            if (v >= 0 && subToNum.containsKey(v)) {
                res = Math.max(res, i + 1 - subToNum.get(v));
            }
            subToNum.put(subSum, i + 1);
        }
        return res == Integer.MIN_VALUE ? -1 : len - res;
    }

}

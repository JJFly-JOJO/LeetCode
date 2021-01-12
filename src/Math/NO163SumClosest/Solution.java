package Math.NO163SumClosest;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/2 9:06
 * @description
 */
public class Solution {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 2 * 10000;
        int last = nums.length - 3;
        for (int cursor = 0; cursor <= last; cursor++) {
            int subTarget = target - nums[cursor];
            int l = cursor + 1;
            int r = nums.length - 1;
            int v;
            while (l < r) {
                v = nums[l] + nums[r];
                int t = nums[cursor] + v;
                if (Math.abs(target - res) > Math.abs(target - t)) {
                    res = t;
                }
                if (v > subTarget) {
                    r--;
                } else if (v < subTarget) {
                    l++;
                } else {
                    return target;
                }
            }

        }
        return res;
    }

}

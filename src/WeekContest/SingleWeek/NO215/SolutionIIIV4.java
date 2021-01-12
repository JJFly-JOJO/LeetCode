package WeekContest.SingleWeek.NO215;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/17 11:39
 * @description -------------针对V3的方法 我们找中间连续子串和等于target的最大数量 我们还可以利用滑动窗口解决---------
 * 注意：我们target是一个确切值 不是找一个范围内的所有值，因此不需要用TreeSet（利用二分查找来找到区间内元素）
 */
public class SolutionIIIV4 {

    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        int l = 0;
        int r = 0;
        int sum = Arrays.stream(nums).sum();
        int target = sum - x;
        //特判 如果sum==x 那么会出现l越过r的情况 造成数组越界
        if (target == 0) {
            return len;
        }
        if (target < 0) {
            return -1;
        }
        int res = -1;
        int winSum = 0;
        for (; r < len; r++) {
            winSum += nums[r];
            while (winSum >= target) {
                if (winSum == target) {
                    res = Math.max(res, r - l + 1);
                }
                winSum -= nums[l++];
            }
        }
        return res == -1 ? -1 : len - res;
    }

}

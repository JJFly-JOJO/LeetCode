package Math.NO163SumClosest;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/2 9:52
 * @description --------------在方法一的基础上 可以改进 先进行当前游标下能走到的大最小值的计算 进行一个预处理-------------
 */
public class SolutionII {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 2 * 10000;
        int last = nums.length - 3;
        for (int cursor = 0; cursor <= last; cursor++) {
            int l = cursor + 1;
            int r = nums.length - 1;
            //计算当前游标下的最大值 如果最大值都小于target 那么本次游标下的双指针循环只有该max最接近target
            int max = nums[cursor] + nums[r] + nums[r - 1];
            if (max < target) {
                if (Math.abs(target - res) > target - max) {
                    res = max;
                }
                continue;
            }
            //计算当前游标下的最小值 如果最小值都大于target 那么本次游标下的双指针循环只有该min最接近target
            int min = nums[cursor] + nums[l] + nums[l + 1];
            if (min > target) {
                if (Math.abs(target - res) > min - target) {
                    res = min;
                }
                continue;
            }
            //特判之后 再进行双指针的循环 双指针每移动一次都需要判断
            int subTarget = target - nums[cursor];
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

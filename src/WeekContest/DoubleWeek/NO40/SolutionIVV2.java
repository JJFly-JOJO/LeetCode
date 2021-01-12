package WeekContest.DoubleWeek.NO40;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/30 10:51
 * @description ------------------暴力搜索 枚举当前i节点之前所有的最长子串结果 找到最大值 作为当前i节点的最长子串---------
 */
public class SolutionIVV2 {

    public int minimumMountainRemovals(int[] nums) {
        int len = nums.length;
        int last = len - 1;
        int[] dpLeft = new int[len];
        for (int i = 1; i < last; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dpLeft[i] = Math.max(dpLeft[i], dpLeft[j] + 1);
                }
            }
        }
        int[] dpRight = new int[len];
        for (int i = last - 1; i >= 1; i--) {
            for (int j = i + 1; j <= last; j++) {
                if (nums[j] < nums[i]) {
                    dpRight[i] = Math.max(dpRight[i], dpRight[j] + 1);
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < last; i++) {
            if (dpLeft[i] != 0 && dpRight[i] != 0) {
                res = Math.max(res, dpLeft[i] + dpRight[i] + 1);
            }
        }
        return len - res;
    }
}

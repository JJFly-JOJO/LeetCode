package Array.NO56MergeIntervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/14 15:40
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class Solution {

    public int[][] merge(int[][] intervals) {
        int length = intervals.length;
        if (length == 0) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[][] res = new int[length][];
        int r = 0;
        res[r++] = intervals[0];
        for (int i = 1; i < length; i++) {
            if (intervals[i][0] <= res[r - 1][1]) {
                //需要合并区间
                r--;
                int[] merge = new int[2];
                merge[0] = Math.min(res[r][0], intervals[i][0]);
                merge[1] = Math.max(res[r][1], intervals[i][1]);
                res[r++] = merge;
            } else {
                res[r++] = intervals[i];
            }
        }
        int[][] rRes = new int[r][];
        System.arraycopy(res, 0, rRes, 0, r);
        return rRes;
    }

}

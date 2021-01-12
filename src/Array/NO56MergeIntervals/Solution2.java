package Array.NO56MergeIntervals;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/16 20:03
 * @description [[2, 3], [4, 5], [6, 7], [8, 9], [1, 10]]
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution2().merge(new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}})));
    }

    /**
     * 向前合并
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int length = intervals.length;
        if (length <= 1) {
            return intervals;
        }
        //记录合并后的长度
        int count = length;
        for (int i = 0; i < length; i++) {
            if (intervals[i] == null) {
                continue;
            }
            boolean cover = false;
            for (int j = i + 1; j < length; j++) {
                if (intervals[j] == null) {
                    continue;
                }
                if (isCover(intervals[i], intervals[j])) {
                    intervals[j] = null;
                    count--;
                    cover = true;
                }
            }
            if (cover) {
                i--;
            }
        }
        int[][] res = new int[count][];
        int i = 0;
        for (int[] temp : intervals) {
            if (temp != null) {
                res[i++] = temp;
            }
        }
        return res;
    }

    /**
     * 向后合并---->相当于拿外层i去合并内层，合并成功一次内层就退出，外层继续遍历
     *
     * @param intervals
     * @return
     */
    public int[][] merge2(int[][] intervals) {
        int length = intervals.length;
        if (length <= 1) {
            return intervals;
        }
        //记录合并后的长度
        int count = length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (intervals[j] == null) {
                    continue;
                }
                if (isCover(intervals[j], intervals[i])) {
                    intervals[i] = null;
                    count--;
                    break;
                }
            }
        }
        int[][] res = new int[count][];
        int i = 0;
        for (int[] temp : intervals) {
            if (temp != null) {
                res[i++] = temp;
            }
        }
        return res;
    }

    /**
     * 区间2 向区间1合并
     *
     * @param interval1
     * @param interval2
     * @return
     */
    private boolean isCover(int[] interval1, int[] interval2) {
        if (interval1[1] < interval2[0] || interval2[1] < interval1[0]) {
            return false;
        }
        //合并区间
        interval1[0] = Math.min(interval1[0], interval2[0]);
        interval1[1] = Math.max(interval1[1], interval2[1]);
        return true;
    }
}

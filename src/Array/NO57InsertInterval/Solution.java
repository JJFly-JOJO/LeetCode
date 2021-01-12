package Array.NO57InsertInterval;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/14 14:35
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class Solution {

    public int[][] insert(int[][] intervals,
                          int[] newInterval) {
        boolean lInner = false;
        boolean rInner = false;
        int leftIndex = 0;
        int rightIndex = 0;
        int length = intervals.length;
        for (; leftIndex < length; leftIndex++) {
            if (newInterval[0] >= intervals[leftIndex][0]
                    && newInterval[0] <= intervals[leftIndex][1]) {
                lInner = true;
                break;
            }
            if (newInterval[0] < intervals[leftIndex][0]) {
                break;
            }
        }
        rightIndex = leftIndex;
        for (; rightIndex < length; rightIndex++) {
            if (newInterval[1] >= intervals[rightIndex][0]
                    && newInterval[1] <= intervals[rightIndex][1]) {
                rInner = true;
                break;
            }
            if (newInterval[1] < intervals[rightIndex][0]) {
                break;
            }
        }
        //找到新加入的区间 可能是合并区间
        int[] mergeInterval;
        if (!lInner && !rInner) {
            mergeInterval = newInterval;
            return getRes(intervals, leftIndex - 1, rightIndex, mergeInterval);
        } else if (lInner && !rInner) {
            mergeInterval = new int[2];
            mergeInterval[0] = intervals[leftIndex][0];
            mergeInterval[1] = newInterval[1];
            return getRes(intervals, leftIndex - 1, rightIndex, mergeInterval);
        } else if (!lInner) {
            mergeInterval = new int[2];
            mergeInterval[0] = newInterval[0];
            mergeInterval[1] = intervals[rightIndex][1];
            return getRes(intervals, leftIndex - 1, rightIndex + 1, mergeInterval);
        } else {
            mergeInterval = new int[2];
            mergeInterval[0] = intervals[leftIndex][0];
            mergeInterval[1] = intervals[rightIndex][1];
            return getRes(intervals, leftIndex - 1, rightIndex + 1, mergeInterval);
        }
    }

    private int[][] getRes(int[][] intervals,
                           int lIntercept, int rIntercept,
                           int[] mergeInterval) {
        int[][] res = new int[lIntercept + 1 + intervals.length - rIntercept + 1][];
        int k = 0;
        for (int i = 0; i <= lIntercept; i++) {
            res[k++] = intervals[i];
        }
        res[k++] = mergeInterval;
        for (int i = rIntercept; i < intervals.length; i++) {
            res[k++] = intervals[i];
        }
        return res;
    }

}

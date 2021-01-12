package WeekContest.DoubleWeek.NO40;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/28 23:18
 * @description [23, 47, 63, 72, 81, 99, 88, 55, 21, 33, 32] 1    erro 2
 */
public class SolutionIV {

    public static void main(String[] args) {
        System.out.println(new SolutionIV().minimumMountainRemovals(new int[]{23, 47, 63, 72, 81, 99, 88, 55, 21, 33, 32}));
    }

    public int minimumMountainRemovals(int[] nums) {
        int len = nums.length;
        int last = len - 1;
        int[] countLeft = new int[len];
        for (int i = 1; i < last; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    countLeft[i] = countLeft[j] + 1;
                    break;
                }
            }
        }
        int[] countRight = new int[len];
        for (int i = last - 1; i > 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > nums[j]) {
                    countRight[i] = countRight[j] + 1;
                    break;
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < last; i++) {
            int v = 0;
            if (countLeft[i] == 0) {
                continue;
            }
            v += countLeft[i];
            if (countRight[i] == 0) {
                continue;
            }
            v += countRight[i];
            res = Math.max(res, v);
        }
        return res == Integer.MIN_VALUE ? 0 : len - res;
    }

    public int minimumMountainRemovalsErro(int[] nums) {
        int len = nums.length;
        int last = len - 1;
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < last; i++) {
            int v = nums[i];
            int lRes = 0;
            int j = i - 1;
            while (j >= 0) {
                if (nums[j] < v) {
                    v = nums[j];
                } else {
                    lRes++;
                }
                j--;
            }
            if (lRes == i) {
                continue;
            }
            j = i + 1;
            v = nums[i];
            int rRes = 0;
            while (j < len) {
                if (v > nums[j]) {
                    v = nums[j];
                } else {
                    rRes++;
                }
                j++;
            }
            if (rRes == last - i) {
                continue;
            }
            res = Math.min(res, lRes + rRes);
        }
        return res;
    }

}

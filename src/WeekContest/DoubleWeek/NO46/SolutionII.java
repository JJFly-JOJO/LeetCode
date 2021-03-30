package WeekContest.DoubleWeek.NO46;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/20 22:08
 * @description
 */
public class SolutionII {

    private int sum;

    public boolean canChoose(int[][] groups, int[] nums) {
        sum = 0;
        for (int[] group : groups) {
            sum += group.length;
        }
        return backtracking(0, 0, sum, groups, nums);
    }

    private boolean backtracking(int indexG, int indexN, int subSum, int[][] groups, int[] nums) {
        if (indexG == groups.length) {
            return true;
        }
        if (subSum > nums.length - indexN) {
            return false;
        }
        for (int i = indexN; i <= nums.length - groups[indexG].length; i++) {
            int j = 0;
            for (; j < groups[indexG].length; j++) {
                if (nums[j + i] != groups[indexG][j]) {
                    break;
                }
            }
            if (j == groups[indexG].length &&
                    backtracking(indexG + 1, i + groups[indexG].length, subSum - groups[indexG].length, groups, nums)) {
                return true;
            }
        }
        return false;
    }

}

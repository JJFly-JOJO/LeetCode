package WeekContest.SingleWeek.NO227;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/7 11:05
 * @description
 */
public class SolutionII {

    public int maximumScore(int a, int b, int c) {
        int[] nums = new int[]{a, b, c};
        Arrays.sort(nums);
        int res = 0;
        while (nums[1] > 0) {
            res++;
            nums[2]--;
            nums[1]--;
            Arrays.sort(nums);
        }
        return res;
    }

}

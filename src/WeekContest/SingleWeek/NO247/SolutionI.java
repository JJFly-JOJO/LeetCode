package WeekContest.SingleWeek.NO247;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/27 10:33
 * @description
 */
public class SolutionI {

    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2] - nums[1] * nums[0];
    }

}

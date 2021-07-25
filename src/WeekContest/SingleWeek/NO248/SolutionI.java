package WeekContest.SingleWeek.NO248;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/4 10:36
 * @description
 */
public class SolutionI {

    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }

}

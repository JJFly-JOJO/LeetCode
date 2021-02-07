package WeekContest.SingleWeek.NO227;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/7 10:59
 * @description
 */
public class SolutionI {

    public boolean check(int[] nums) {
        int i = 1;
        for (; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                break;
            }
        }
        if (i >= nums.length) {
            return true;
        }
        i++;
        for (; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return nums[0] >= nums[nums.length - 1];
    }

}

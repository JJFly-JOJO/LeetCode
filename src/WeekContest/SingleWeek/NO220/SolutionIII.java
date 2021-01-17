package WeekContest.SingleWeek.NO220;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/20 11:04
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }

    public int maxResult(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        int res = nums[0] + nums[nums.length - 1];
        int cur = 0;
        int last = nums.length - 1;
        int border = cur + k;
        while (border < last) {
            int t = Integer.MIN_VALUE;
            for (int i = cur + 1; i <= border; i++) {
                if (nums[i] > 0) {
                    t = nums[i];
                    cur = i;
                    break;
                }
                if (nums[i] > t) {
                    t = nums[i];
                    cur = i;
                }
            }
            res += t;
            border = cur + k;
        }
        for (int i = cur + 1; i < last; i++) {
            if (nums[i] > 0) {
                res += nums[i];
            }
        }
        return res;
    }

}

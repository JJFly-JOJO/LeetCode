package WeekContest.SingleWeek.NO240;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/9 11:12
 * @description [1, 1, 3, 2, 2, 2, 1, 5, 1, 5]-25
 */
public class SolutionIII {

    public int maxSumMinProduct(int[] nums) {
        long mod = 1000000007;
        long res = 0L;
        long[] subSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            subSum[i + 1] = subSum[i] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            //针对特殊数据集超时情况
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i;
            int r = i;
            while (l >= 0 && nums[l] >= nums[i]) {
                l--;
            }
            while (r < nums.length && nums[r] >= nums[i]) {
                r++;
            }
            res = Math.max(res, nums[i] * (subSum[r] - subSum[l + 1]));
        }
        return (int) (res % mod);
    }
}

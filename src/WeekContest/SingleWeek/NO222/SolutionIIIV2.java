package WeekContest.SingleWeek.NO222;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/4 14:49
 * @description
 */
public class SolutionIIIV2 {
    public int waysToSplitI(int[] nums) {
        int mod = (int) 1e9 + 7;
        int len = nums.length;
        int[] subSum = new int[len];
        long count = 0L;
        subSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            subSum[i] = subSum[i - 1] + nums[i];
        }
        for (int i = 0; i < len; i++) {
            int target1 = subSum[i] << 1;
            int l = i + 1;
            while (l < len && subSum[l] < target1) {
                l++;
            }
            //int target2 = (subSum[i] + subSum[len - 1]) >>> 1;
            int r = len - 1;
            while (r > i && subSum[r] * 2 > subSum[i] + subSum[len - 1]) {
                r--;
            }
            if (r < l) {
                break;
            }
            count += r - l + 1;
            count %= mod;
        }
        return (int) count;
    }

    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] psum = new int[n];
        psum[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            psum[i] = psum[i - 1] + nums[i];
        }
        int res = 0;
        int a = 1, b = 1;
        for (int i = 0; i < n - 2; ++i) {
            a = Math.max(i + 1, a);
            while (a < n - 1 && psum[a] - psum[i] < psum[i]) {
                a++;
            }
            if (a == n - 1) {
                break;
            }
            b = Math.max(b, a);
            while (b < n - 1 && psum[n - 1] - psum[b] >= psum[b] - psum[i]) {
                b++;
            }
            res = (res + (b - a)) % 1000000007;
        }
        return res;
    }

}

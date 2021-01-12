package WeekContest.SingleWeek.NO217;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/29 11:24
 * @description ----------------超时--------------
 */
public class SolutionIIV2 {
    public int[] mostCompetitive(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[k];
        int lastInex = -1;
        for (int i = 0; i < k; i++) {
            int last = len - (k - i);
            int t = Integer.MAX_VALUE;
            //find min
            for (int j = lastInex + 1; j <= last; j++) {
                if (t > nums[j]) {
                    lastInex = j;
                    t = nums[j];
                    res[i] = nums[j];
                }
            }
        }
        return res;

    }
}

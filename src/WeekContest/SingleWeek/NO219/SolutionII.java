package WeekContest.SingleWeek.NO219;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/20 10:46
 * @description
 * [187,470,25,436,538,809,441,167,477,110,275,133,666,345,411,459,490,266,987,965,429,166,809,340,467,318,125,165,809,610,31,585,970,306,42,189,169
 * ,743,78,810,70,382,367,490,787,670,476,278,775,673,299,19,893,817,971,458,409,886,434]
 * 16911
 */
public class SolutionII {

    public int maximumUniqueSubarray(int[] nums) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        while (r < nums.length) {
            if (set.contains(nums[r])) {
                ans = Math.max(sum, ans);
                for (; l < r; l++) {
                    sum -= nums[l];
                    set.remove(nums[l]);
                    if (nums[l] == nums[r]) {
                        l++;
                        break;
                    }
                }
                continue;
            }
            sum += nums[r];
            set.add(nums[r]);
            r++;
        }
        ans = Math.max(ans, sum);
        return ans;
    }

}

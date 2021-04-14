package LeetCodeCup;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/5 15:02
 * @description
 */
public class SolutionI {

    public int purchasePlans(int[] nums, int target) {
        int mod = 1000000007;
        long res = 0L;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int idx = binarySearch(nums, i + 1, target - nums[i]);
            if (idx < 0) {
                break;
            }
            res += (idx - i);
        }
        return (int) (res % mod);
    }

    private int binarySearch(int[] nums, int idx, int target) {
        if (target < 0 || idx >= nums.length) {
            return -1;
        }
        int l = idx;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

}

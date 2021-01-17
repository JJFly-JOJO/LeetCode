package WeekContest.SingleWeek.NO224;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/17 10:35
 * @description -------------暴力双指针做法O(n3)----------
 */
public class SolutionII {

    private int[][] mem;

    public int tupleSameProduct(int[] nums) {
        Arrays.sort(nums);
        mem = new int[nums.length][nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int multi = nums[i] * nums[j];
                int ll = i + 1;
                int rr = j - 1;
                while (ll < rr) {
                    int m = nums[ll] * nums[rr];
                    if (m > multi) {
                        rr--;
                    } else if (m < multi) {
                        ll++;
                    } else {
                        res += 8;
                        ll++;
                        rr--;
                    }
                }
            }
        }
        return res;
    }

}

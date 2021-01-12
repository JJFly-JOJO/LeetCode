package Greedy.NO376WiggleSubsequence;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/14 10:01
 * @description
 */
public class Solution {

    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int res = 1;
        Integer tag = null;
        for (int i = 1; i < nums.length; i++) {
            int v = nums[i] - nums[i - 1];
            if (v == 0) {
                continue;
            }
            if (tag != null) {
                if (tag > 0 && v < 0) {
                    res++;
                }
                if (tag < 0 && v > 0) {
                    res++;
                }
            } else {
                res = 2;
            }
            tag = v;
        }
        return res;
    }

}

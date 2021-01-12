package Array.NO325MaximumSizeSubarraySumEqualsk;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/24 23:18
 * @description ---------------------暴力解法-------------------
 */
public class Solution {

    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        //记录左端开始的子串和
        int[] subSum = new int[len + 1];
        int max = 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            subSum[i + 1] = sum;
            if (sum == k) {
                max = i + 1;
            }
        }
        //暴力遍历
        int add = max + 1;
        while (add <= len) {
            int lastI = len - add;
            for (int i = 0; i <= lastI; i++) {
                if (subSum[i + add] - subSum[i] == k) {
                    max = add;
                    break;
                }
            }
            add++;
        }
        return max;
    }

}

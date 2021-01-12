package Array.NO152MaximumProductSubarray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/18 10:05
 * @description -----------记录下所有前缀区间 0作为分隔符 暴力枚举所有子串情况---------
 */
public class Solution {

    public int maxProduct(int[] nums) {
        int len = nums.length;
        int i = 0;
        int res = Integer.MIN_VALUE;
        int[] sub = new int[len];
        while (i < len) {
            int product = 1;
            int start = i;
            while (i < len && nums[i] != 0) {
                product *= nums[i];
                sub[i] = product;
                res = Math.max(res, product);
                for (int j = start; j < i; j++) {
                    res = Math.max(res, sub[i] / sub[j]);
                }
                i++;
            }
            while (i < len && nums[i] == 0) {
                res = Math.max(0, res);
                i++;
            }
        }
        return res;
    }

}

package Array.NO152MaximumProductSubarray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/20 9:21
 * @description ---------------贪心算法 类比NO53的贪心算法------------
 * 注意到我们的数都是整数 没有小数，那么除了0以外，连续相乘数值的绝对值只会越来越大，但是考虑的负数的情况，
 * 可能存在-1 2 3 4 5...的情况 会造成一次左到右的循环后全都为负数，此时我们可以再来一次从右到左的循环！！！
 */
public class SolutionIII {

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int len = nums.length;
        int s = 1;
        for (int num : nums) {
            s *= num;
            if (s > max) {
                max = s;
            }
            if (s == 0) {
                s = 1;
            }
        }
        s = 1;
        for (int i = len - 1; i >= 0; i--) {
            s *= nums[i];
            if (s > max) {
                max = s;
            }
            if (s == 0) {
                s = 1;
            }
        }
        return max;
    }

}

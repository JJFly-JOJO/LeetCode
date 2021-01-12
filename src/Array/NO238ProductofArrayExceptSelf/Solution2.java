package Array.NO238ProductofArrayExceptSelf;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/22 14:19
 * @description -----------------------改进 O(1)空间复杂度--------------
 */
public class Solution2 {

    /**
     * 使用O(1)空间复杂度的方法，题目所说结果res[N]是不计入空间复杂度中的，那么我们可以利用这个res来临时存储left或者right的结果
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        //先用res来存取连续左left值
        int val = 1;
        for (int i = 0; i < len; i++) {
            val *= nums[i];
            res[i] = val;
        }
        //动态获取连续右right值 以及更新res
        res[len - 1] = res[len - 2];
        val = 1;
        for (int i = len - 2; i >= 1; i--) {
            val *= nums[i + 1];
            res[i] = res[i - 1] * val;
        }
        res[0] = val * nums[1];
        return res;
    }

}

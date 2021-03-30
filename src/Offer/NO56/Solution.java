package Offer.NO56;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/13 21:52
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().singleNumbers(new int[]{1, 2, 5, 2})));
    }

    /**
     * 异或操作
     */
    public int[] singleNumbers(int[] nums) {
        int[] res = new int[2];
        int val = 0;
        for (int n : nums) {
            val ^= n;
        }
        int div = 1;
        int count = 0;
        while ((div & val) == 0) {
            div = div << 1;
            count++;
        }
        for (int n : nums) {
            if ((div & n) >>> count == 1) {
                res[0] ^= n;
            } else {
                res[1] ^= n;
            }
        }
        return res;
    }

}

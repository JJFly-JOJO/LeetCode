package BitManipulation.NO260SingleNumberIII;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/11 11:33
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().singleNumber(new int[]{1, 1, 0, -2147483648})));
    }

    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        int digit = 1;
        while ((xor & digit) == 0) {
            digit = digit << 1;
        }
        int a = 0;
        int b = 0;
        for (int n : nums) {
            if ((n & digit) > 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

}

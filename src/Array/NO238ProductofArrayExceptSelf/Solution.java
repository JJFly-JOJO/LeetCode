package Array.NO238ProductofArrayExceptSelf;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/22 12:23
 * @description -----------------类比##NO239 NO53##---------------
 */
public class Solution {

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int[] left = new int[len];
        int[] right = new int[len];
        //第一次左循环 更新left
        int val = 1;
        for (int i = 0; i < len; i++) {
            val *= nums[i];
            left[i] = val;
        }
        //第二次右循环 更新right
        val = 1;
        for (int i = len - 1; i >= 0; i--) {
            val *= nums[i];
            right[i] = val;
        }
        //find answer
        //特殊情况
        res[0] = right[1];
        res[len - 1] = left[len - 2];
        int last = len - 1;
        for (int i = 1; i < last; i++) {
            res[i] = left[i - 1] * right[i + 1];
        }
        return res;
    }

}

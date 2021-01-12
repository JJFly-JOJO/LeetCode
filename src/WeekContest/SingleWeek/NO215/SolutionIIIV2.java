package WeekContest.SingleWeek.NO215;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/17 9:56
 * @description -----------我们不需要关注是先拿左端还是先拿右端 因为最终的结果是要得到拿的最少次数 因此我们只用关心结果 不关注过程-----
 * 思路：当想到回溯方法上时，就会陷入枚举先拿左端还是先拿右端的陷阱，最终会产生很多重复枚举，造成超时，
 * 实际上，我们最终结果就是一个连续的左端加上一个连续的右端为target中，找到一个最少的操作数，也就是个数最少，这种连续区间和我们可以想到前缀和
 * 这里我们就可以计算左前缀和以及右前缀和 然后利用twoSum的思路
 */
public class SolutionIIIV2 {

    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        //左端前缀
        int[] lSub = new int[len + 1];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            lSub[i + 1] = sum;
        }
        //特判 也能解决左右前缀和边界相交的问题
        if (sum < x) {
            return -1;
        }
        //右端前缀 存入Map中
        Map<Integer, Integer> sumToNum = new HashMap<>();
        //右端一个元素没有
        sumToNum.put(0, 0);
        sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum += nums[i];
            sumToNum.put(sum, len - i);
        }
        //twoSum
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= len; i++) {
            int rest = x - lSub[i];
            if (rest < 0) {
                break;
            }
            if (sumToNum.containsKey(rest)) {
                res = Math.min(res, i + sumToNum.get(rest));
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}

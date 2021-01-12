package Array.NO325MaximumSizeSubarraySumEqualsk;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/28 10:27
 * @description -------------前缀数组（subSum） 配合 hashMap---------
 */
public class SolutionV2 {

    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        int res = 0;
        Map<Integer, Integer> sumToI = new HashMap<>();
        sumToI.put(0, -1);
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            int target = sum - k;
            Integer index = sumToI.get(target);
            if (index != null) {
                res = Math.max(i - index, res);
            }
            if (!sumToI.containsKey(sum)) {
                sumToI.put(sum, i);
            }
        }
        return res;
    }

}

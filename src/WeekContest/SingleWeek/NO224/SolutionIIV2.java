package WeekContest.SingleWeek.NO224;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/17 23:26
 * @description -----------------穷举方法之间也有复杂度之分---------
 * 思路：题干抓住每个元素值都不同，计算出两数相乘的所有组合，乘积值为key，数量为count，利用排列组合计算Cn2
 */
public class SolutionIIV2 {

    public int tupleSameProduct(int[] nums) {
        int res = 0;
        Map<Integer, Integer> vToC = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int multi = nums[i] * nums[j];
                vToC.put(multi, vToC.getOrDefault(multi, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> e : vToC.entrySet()) {
            int count = e.getValue();
            if (count >= 2) {
                //排列组合 Cn2
                res += count * (count - 1) * 4;
            }
        }
        return res;
    }

}

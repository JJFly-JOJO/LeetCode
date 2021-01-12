package WeekContest.SingleWeek.NO212;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/25 10:46
 * @description -------
 * <p>
 * 输入：nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
 * 输出：[true,false,true]
 * 解释：
 * 第 0 个查询，对应子数组 [4,6,5] 。可以重新排列为等差数列 [6,5,4] 。
 * 第 1 个查询，对应子数组 [4,6,5,9] 。无法重新排列形成等差数列。
 * 第 2 个查询，对应子数组 [5,9,3,7] 。可以重新排列为等差数列 [3,5,7,9] 。
 */
public class Solution2 {

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int len = l.length;
        List<Boolean> res = new ArrayList<>(len);
        Label:
        for (int i = 0; i < len; i++) {
            int[] sub = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            Arrays.sort(sub);
            int tempL = sub.length;
            int diff = sub[1] - sub[0];
            for (int k = 2; k < tempL; k++) {
                if (sub[k] - sub[k - 1] != diff) {
                    res.add(false);
                    continue Label;
                }
            }
            res.add(true);
        }
        return res;
    }

}

package WeekContest.SingleWeek.NO211;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/18 10:32
 * @description 输入：s = "cabbac"
 * 输出：4
 * 解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
 */
public class Solution1 {

    public int maxLengthBetweenEqualCharacters(String s) {
        int[] cToIndex = new int[123];
        Arrays.fill(cToIndex, -1);
        char[] chars = s.toCharArray();
        int length = s.length();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (cToIndex[chars[i]] == -1) {
                cToIndex[chars[i]] = i;
            } else {
                res.add(i - cToIndex[chars[i]] - 1);
            }
        }
        Collections.sort(res);
        return res.size() == 0 ? -1 : res.get(res.size() - 1);
    }

}

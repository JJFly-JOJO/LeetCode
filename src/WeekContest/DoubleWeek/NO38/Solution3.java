package WeekContest.DoubleWeek.NO38;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/4 10:19
 * @description
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(new Solution3().countSubstrings("ab", "bb"));
    }

    public int countSubstrings(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int res = 0;
        Map<String, Integer> subToCount = new HashMap<>();
        for (int i = 0; i < sLen; i++) {
            for (int j = i + 1; j <= sLen; j++) {
                String sub = s.substring(i, j);
                Integer num;
                if ((num = subToCount.get(sub)) != null) {
                    res += num;
                    continue;
                }
                char[] chars = sub.toCharArray();
                int charLen = chars.length;
                int last = tLen - charLen;
                int tempRes = 0;
                for (int k = 0; k <= last; k++) {
                    int count = 0;
                    for (int c = 0; c < charLen && count <= 2; c++) {
                        if (chars[c] != t.charAt(k + c)) {
                            count++;
                        }
                    }
                    if (count == 1) {
                        tempRes++;
                    }
                }
                res += tempRes;
                subToCount.put(sub, tempRes);
            }
        }
        return res;
    }
}

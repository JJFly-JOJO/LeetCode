package String.NO340LongestSubstringwithAtMostKDistinctCharacters;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/7 20:22
 * @description
 */
public class Solution {

    /**
     * ------优化方法 拿数组替代hashMap来计数-------
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> cToNum = new HashMap<>();
        int count = 0;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int l = 0;
        int r = 0;
        int res = 0;
        while (r < len) {
            int val = cToNum.getOrDefault(chars[r], 0) + 1;
            cToNum.put(chars[r], val);
            if (val == 1) {
                count++;
            }
            if (count > k) {
                res = Math.max(r - l, res);
                while (count > k) {
                    int t = cToNum.get(chars[l]) - 1;
                    cToNum.put(chars[l], t);
                    if (t == 0) {
                        count--;
                    }
                    l++;
                }
            }
            r++;
        }
        //r到达last 单独判断
        res = Math.max(res, r - l);
        return res;
    }
}

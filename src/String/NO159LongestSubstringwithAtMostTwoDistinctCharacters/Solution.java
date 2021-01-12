package String.NO159LongestSubstringwithAtMostTwoDistinctCharacters;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/23 10:34
 * @description
 */
public class Solution {

    public int lengthOfLongestSubstringTwoDistinctI(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> cToN = new HashMap<>();
        int count = 0;
        int res = 0;
        int len = chars.length;
        int r = 0;
        int l = 0;
        while (r < len) {
            int v = cToN.getOrDefault(chars[r], 0);
            if (v == 0) {
                count++;
            }
            cToN.put(chars[r], ++v);
            if (count > 2) {
                res = Math.max(res, r - l);
                while (count > 2) {
                    int t = cToN.get(chars[l]) - 1;
                    cToN.put(chars[l], t);
                    l++;
                    if (t == 0) {
                        count--;
                    }
                }
            }
            r++;
        }
        return Math.max(res, r - l);
    }

    /**
     * ------------------优化 利用数组代替map--------------
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] chars = s.toCharArray();
        int[] cToN = new int[123];
        int count = 0;
        int res = 0;
        int len = chars.length;
        int r = 0;
        int l = 0;
        while (r < len) {
            if (cToN[chars[r]] == 0) {
                count++;
            }
            cToN[chars[r]]++;
            if (count > 2) {
                res = Math.max(res, r - l);
                while (count > 2) {
                    if (--cToN[chars[l]] == 0) {
                        count--;
                    }
                    l++;
                }
            }
            r++;
        }
        return Math.max(res, r - l);
    }

}

package Backtracking.NO291WordPatternII;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/10 15:11
 * @description -------------------暴力回溯--------------
 * "itwasthebestoftimes"
 * "ittwaastthhebesttoofttimesss"
 */
public class Solution {

    private Map<Character, String> pToStr = new HashMap<>();
    private Set<String> strSet = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(new Solution().wordPatternMatch("itwasthebestoftimes"
                , "ittwaastthhebesttoofttimesss"));
    }

    public boolean wordPatternMatch(String pattern, String s) {
        char[] chars = pattern.toCharArray();
        if (chars.length == 0 && s.length() != 0) {
            return false;
        }
        return dfs(0, chars, s, 0);
    }

    private boolean dfs(int start, char[] pattern, String s, int count) {
        if (count == pattern.length) {
            return start == s.length();
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (pToStr.containsKey(pattern[count])) {
                if (!sub.equals(pToStr.get(pattern[count]))) {
                    continue;
                }
                if (dfs(i, pattern, s, count + 1)) {
                    return true;
                }
            } else {
                if (strSet.contains(sub)) {
                    continue;
                }
                pToStr.put(pattern[count], sub);
                strSet.add(sub);
                if (dfs(i, pattern, s, count + 1)) {
                    return true;
                }
                strSet.remove(sub);
                pToStr.remove(pattern[count]);
            }
        }
        return false;
    }
}

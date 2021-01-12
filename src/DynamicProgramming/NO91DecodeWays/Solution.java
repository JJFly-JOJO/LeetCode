package DynamicProgramming.NO91DecodeWays;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/11 11:42
 * @description ------------------带有记忆的回溯---------------
 */
public class Solution {

    private int res = 0;

    private Map<String, Integer> memo = new HashMap<>();

    public int numDecodings(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 1; i <= 2; i++) {
            if (i <= sb.length()) {
                String sub = sb.substring(0, i);
                int v = Integer.parseInt(sub);
                //排除前导0的情况
                if (sub.charAt(0) != '0' && v >= 1 && v <= 26) {
                    backtracking(i, sb);
                }
            }
        }
        return res;
    }

    private int backtracking(int i, StringBuilder sb) {
        if (i == sb.length()) {
            res++;
            return 1;
        }
        String sub = sb.substring(i, sb.length());
        if (memo.containsKey(sub)) {
            int v = memo.get(sub);
            res += v;
            return v;
        }
        int sum = 0;
        for (int j = 1; j <= 2; j++) {
            if (i + j <= sb.length()) {
                String ss = sb.substring(i, i + j);
                int v = Integer.parseInt(ss);
                if (ss.charAt(0) != '0' && v >= 1 && v <= 26) {
                    sum += backtracking(i + j, sb);
                }
            }
        }
        memo.put(sub, sum);
        return sum;
    }

}

package Greedy.NO455AssignCookies;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/25 10:59
 * @description
 */
public class Solution {

    public int findContentChildren(int[] g, int[] s) {
        int res = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gI = 0;
        int sI = 0;
        while (sI < s.length && gI < g.length) {
            while (sI < s.length && s[sI] < g[gI]) {
                sI++;
            }
            if (sI < s.length) {
                res++;
                gI++;
                sI++;
            }
        }
        return res;
    }

}

package String.NO131PalindromePartitioning;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/20 10:18
 * @description ----------带有记忆的回溯----------
 */
public class Solution {

    private int[][] mem;

    public List<List<String>> partition(String s) {
        char[] chars = s.toCharArray();
        mem = new int[chars.length][chars.length];
        List<List<String>> res = new ArrayList<>();
        backtracking(0, chars, new ArrayList<>(), res);
        return res;
    }

    private void backtracking(int start, char[] chars, List<String> path, List<List<String>> res) {
        if (start >= chars.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < chars.length; i++) {
            if (mem[start][i] == 0 && isPalindrome(start, i, chars) || mem[start][i] == 1) {
                mem[start][i] = 1;
                path.add(new String(chars, start, i - start + 1));
                backtracking(i + 1, chars, path, res);
                path.remove(path.size() - 1);
            } else {
                mem[start][i] = -1;
            }
        }
    }

    private boolean isPalindrome(int start, int end, char[] chars) {
        while (start < end) {
            if (chars[start++] != chars[end--]) {
                return false;
            }
        }
        return true;
    }

}

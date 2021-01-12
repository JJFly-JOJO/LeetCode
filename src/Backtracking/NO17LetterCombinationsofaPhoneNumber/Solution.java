package Backtracking.NO17LetterCombinationsofaPhoneNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/12 17:14
 * @description
 */
public class Solution {

    private char[][] nToC = new char[][]{{'a'}, {'a'}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    private int len;

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        len = digits.length();
        if (len == 0) {
            return res;
        }
        char[] chars = digits.toCharArray();
        int num = chars[0] - '0';
        for (int i = 0; i < nToC[num].length; i++) {
            StringBuilder path = new StringBuilder();
            path.append(nToC[num][i]);
            dfs(1, path, chars, res);
        }
        return res;
    }

    private void dfs(int l, StringBuilder path, char[] chars, List<String> res) {
        if (l == len) {
            res.add(path.toString());
            return;
        }
        int num = chars[l] - '0';
        for (int i = 0; i < nToC[num].length; i++) {
            path.append(nToC[num][i]);
            dfs(l + 1, path, chars, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

}

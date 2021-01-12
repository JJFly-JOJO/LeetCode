package String.NO22GenerateParentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/4 14:37
 * @description -------------------经典01选择回溯算法---------------
 * 思路：当前子问题往下递归时 有两种情况 一种是append "(" 另一种是append ")" 当然 能够append这两个括号也有对应条件
 */
public class Solution {

    public List<String> generateParenthesis(int n) {
        StringBuilder path = new StringBuilder("(");
        List<String> res = new ArrayList<>();
        if (n == 0) {
            res.add("");
            return res;
        }
        backtracing(n - 1, n, path, res);
        return res;
    }

    private void backtracing(int l, int r, StringBuilder path, List<String> res) {
        if (l == 0 && r == 0) {
            res.add(path.toString());
            return;
        }
        if (l > 0) {
            path.append('(');
            backtracing(l - 1, r, path, res);
            path.deleteCharAt(path.length() - 1);
        }
        if (l < r) {
            path.append(')');
            backtracing(l, r - 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

}

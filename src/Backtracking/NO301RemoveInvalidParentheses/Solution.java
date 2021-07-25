package Backtracking.NO301RemoveInvalidParentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/18 16:14
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().removeInvalidParentheses(")()(").toString());
    }

    public List<String> removeInvalidParentheses(String s) {
        char[] chars = s.toCharArray();
        //左右括号能删除的最少数量
        int leftCount = 0;
        int rightCount = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                leftCount++;
            }
            if (aChar == ')') {
                if (leftCount != 0) {
                    leftCount--;
                } else {
                    rightCount++;
                }
            }
        }
        StringBuilder path = new StringBuilder();
        Set<String> res = new HashSet<>();
        backtracking(0, 0, 0, leftCount, rightCount, path, chars, res);
        return new ArrayList<>(res);
    }

    private void backtracking(int idx, int l, int r, int leftCount, int rightCount,
                              StringBuilder path, char[] chars, Set<String> res) {
        if (idx >= chars.length) {
            if (leftCount == 0 && rightCount == 0) {
                res.add(path.toString());
            }
            return;
        }
        if (chars[idx] != '(' && chars[idx] != ')') {
            path.append(chars[idx]);
            backtracking(idx + 1, l, r, leftCount, rightCount, path, chars, res);
            path.deleteCharAt(path.length() - 1);
            return;
        }
        if (chars[idx] == '(') {
            path.append(chars[idx]);
            backtracking(idx + 1, l + 1, r, leftCount, rightCount, path, chars, res);
            path.deleteCharAt(path.length() - 1);
            if (leftCount > 0) {
                backtracking(idx + 1, l, r, leftCount - 1, rightCount, path, chars, res);
            }
        }
        if (chars[idx] == ')') {
            //“)”能添加的时机
            if (l > r) {
                path.append(chars[idx]);
                backtracking(idx + 1, l, r + 1, leftCount, rightCount, path, chars, res);
                path.deleteCharAt(path.length() - 1);
            }
            //不添加“)”的情况
            if (rightCount > 0) {
                backtracking(idx + 1, l, r, leftCount, rightCount - 1, path, chars, res);
            }
        }
    }

}

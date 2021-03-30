package String.NO241DifferentWaystoAddParentheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/28 11:12
 * @description -----------分治方法：String的substring是分治的体现-----------
 */
public class Solution {

    private Map<String, List<Integer>> mem = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        if (mem.containsKey(input)) {
            return mem.get(input);
        }
        List<Integer> res = new ArrayList<>();
        int length = input.length();
        if (length == 0) {
            return res;
        }
        //单独判断是否是num（不包含操作符）
        int num = 0;
        int index = 0;
        for (; index < length; index++) {
            char c = input.charAt(index);
            if (isOperator(c)) {
                break;
            }
            num = num * 10 + c - '0';
        }
        if (index == length) {
            res.add(num);
            return res;
        }
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (isOperator(c)) {
                //分治的体现 subString 实现了左子问题与右子问题的切分
                List<Integer> leftRes = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightRes = diffWaysToCompute(input.substring(i + 1, length));
                for (int l : leftRes) {
                    for (int r : rightRes) {
                        res.add(calculate(l, r, c));
                    }
                }
            }
        }
        mem.put(input, res);
        return res;
    }

    private int calculate(int l, int r, char c) {
        if (c == '+') {
            return l + r;
        }
        if (c == '-') {
            return l - r;
        }
        return l * r;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*';
    }

}

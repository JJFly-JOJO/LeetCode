package Backtracking.NO320GeneralizedAbbreviation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/14 12:13
 * @description -----------更加清晰的回溯思路 以及复杂度的分析----------
 * 思路：当前字母只有缩写与不缩写两种选择 如果单词长度为n 那么一共可能的组合即为2的n次方
 */
public class SolutionII {

    private int len;

    public List<String> generateAbbreviations(String word) {
        len = word.length();
        char[] chars = word.toCharArray();
        StringBuilder path = new StringBuilder();
        List<String> res = new ArrayList<>();
        backTracing(0, 0, path, chars, res);
        return res;
    }

    private void backTracing(int i, int num, StringBuilder path, char[] chars, List<String> res) {
        //标记当前层长度 用于递归ret时的path重置
        int l = path.length();
        if (i == len) {
            if (num > 0) {
                path.append(num);
            }
            res.add(path.toString());
        } else {
            //当前i下标选择缩写
            backTracing(i + 1, num + 1, path, chars, res);
            //当前i下标选择不缩写
            if (num > 0) {
                //如果当前i不选择缩写 那么要判断前面子串是否缩写了 缩写了就要加入num 并且重置为0
                path.append(num);
            }
            path.append(chars[i]);
            backTracing(i + 1, 0, path, chars, res);
        }
        //技巧！！--------------------------不需要使用delete进行繁琐的计算 需要归零到的下标处 利用setLength方法进行重置
        path.setLength(l);
    }

}

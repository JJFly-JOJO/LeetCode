package Backtracking.NO320GeneralizedAbbreviation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/13 22:02
 * @description
 */
public class SolutionI {

    private int len;

    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        len = word.length();
        //特判
        res.add(word);
        if (len == 0) {
            return res;
        }
        res.add(String.valueOf(len));
        StringBuilder sb = new StringBuilder();
        //最外层为要缩写的总个数
        for (int i = 1; i < len; i++) {
            //当前层缩写个数
            for (int j = 1; j <= i; j++) {
                int last = len - j;
                //从第k位缩写
                for (int k = 0; k <= last; k++) {
                    sb.append(word, 0, k).append(j);
                    dfs(k + j, i - j, sb, word, res);
                    //易错点！！！-----------------------------当append(j)的j为两位数时 这里会出现delete错误
                    //会多出一个1  10n------>1i10
                    sb.delete(sb.length() - k - String.valueOf(j).length(), sb.length());
                }
            }
        }
        return res;
    }

    private void dfs(int start, int l, StringBuilder sb, String word, List<String> res) {
        if (l == 0) {
            sb.append(word, start, len);
            res.add(sb.toString());
            sb.delete(sb.length() - (len - start), sb.length());
            return;
        }
        //当前层缩写个数
        for (int i = 1; i <= l; i++) {
            int last = len - i;
            //从第k位开始缩写
            for (int k = start + 1; k <= last; k++) {
                sb.append(word, start, k).append(i);
                dfs(k + i, l - i, sb, word, res);
                sb.delete(sb.length() - (k - start + String.valueOf(i).length()), sb.length());
            }

        }
    }

}

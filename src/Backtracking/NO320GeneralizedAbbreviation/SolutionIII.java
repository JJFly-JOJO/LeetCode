package Backtracking.NO320GeneralizedAbbreviation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/14 12:32
 * @description ---------------位操作--------------
 * 思路：抓住每个字母只有缩写与不缩写两种情况 利用位操作来枚举所有的可能情况（2） 0表示不缩写 1表示缩写
 */
public class SolutionIII {

    public static void main(String[] args) {
        int bit = 1;
        System.out.println((bit++) == 2);
        System.out.println((bit += 1) == 3);
    }

    public List<String> generateAbbreviations(String word) {
        char[] chars = word.toCharArray();
        int bitMax = 1 << word.length();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < bitMax; i++) {
            bitToStr(i, chars, res);
        }
        return res;
    }

    private void bitToStr(int bit, char[] chars, List<String> res) {
        int count = 0;
        StringBuilder s = new StringBuilder();
        for (char aChar : chars) {
            if ((bit & 1) == 0) {
                if (count > 0) {
                    s.append(count);
                    count = 0;
                }
                s.append(aChar);
            } else {
                count++;
            }
            bit >>>= 1;
        }
        if (count > 0) {
            s.append(count);
        }
        res.add(s.toString());
    }
}

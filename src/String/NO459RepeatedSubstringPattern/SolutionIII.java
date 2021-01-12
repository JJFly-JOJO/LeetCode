package String.NO459RepeatedSubstringPattern;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/26 11:19
 * @description --------------暴力枚举 以及优化策略--------
 */
public class SolutionIII {

    public boolean repeatedSubstringPattern(String s) {
        char[] chars = s.toCharArray();
        //优化1：保证子串组合成s 那么子串长度不能超过length/2
        int n = chars.length / 2;
        //枚举所有子串长度可能
        for (int i = 1; i <= n; i++) {
            //首先要保证整除性
            if (s.length() % i == 0) {
                boolean tag = true;
                //-------------------------技巧：这里我们巧妙的运用了相隔子串长度的位比较
                // -------------------------而不是采用子串拼接的思路，细化了比较粒度，提高效率
                //遍历 要保证能子串能够组成s 那么每每相隔i长度的字符都必须相等
                int last = s.length() - i;
                for (int j = 0; j < last; j++) {
                    if (chars[j] != chars[j + i]) {
                        tag = false;
                        break;
                    }
                }
                if (tag) {
                    return true;
                }
            }
        }
        return false;
    }

}

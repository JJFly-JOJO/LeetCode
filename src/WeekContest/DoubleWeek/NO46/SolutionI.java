package WeekContest.DoubleWeek.NO46;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/20 22:08
 * @description
 */
public class SolutionI {

    public static void main(String[] args) {
        System.out.println(new SolutionI().longestNiceSubstring("Bb"));
    }

    public String longestNiceSubstring(String s) {
        char[] chars = s.toCharArray();
        int dif = 'a' - 'A';
        String res = "";
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                Set<Character> set = new HashSet<>();
                for (int k = i; k <= j; k++) {
                    set.add(chars[k]);
                }
                int k = i;
                for (; k <= j; k++) {
                    if (chars[k] >= 'a' && chars[k] <= 'z' && !set.contains((char) (chars[k] - dif)) || (chars[k] >= 'A' && chars[k] <= 'Z' && !set.contains((char) (chars[k] + dif)))) {
                        break;
                    }
                }
                if (k > j) {
                    String t = s.substring(i, j + 1);
                    if (t.length() > res.length()) {
                        res = t;
                    }
                }
            }
        }
        return res;
    }

}

package WeekContest.SingleWeek.NO221;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/29 11:16
 * @description
 */
public class SolutionI {

    public boolean halvesAreAlike(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        char[] chars = s.toCharArray();
        int sub1 = 0;
        int sub2 = 0;
        int l = chars.length / 2;
        for (int i = 0; i < l; i++) {
            if (set.contains(chars[i])) {
                sub1++;
            }
        }
        for (int i = l; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                sub2++;
            }
        }
        return sub1 == sub2;
    }

}

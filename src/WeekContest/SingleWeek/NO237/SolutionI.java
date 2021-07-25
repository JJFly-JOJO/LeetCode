package WeekContest.SingleWeek.NO237;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/18 10:31
 * @description
 */
public class SolutionI {

    public boolean checkIfPangram(String sentence) {
        char[] chars = sentence.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            set.add(c);
        }
        return set.size() == 26;
    }

}

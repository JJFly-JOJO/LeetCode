package WeekContest.DoubleWeek.NO40;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/28 22:11
 * @description
 */
public class SolutionI {

    public int maxRepeating(String sequence, String word) {
        int res = 0;
        StringBuilder sb = new StringBuilder(word);
        while (sequence.contains(sb)) {
            res++;
            sb.append(word);
        }
        return res;
    }

}

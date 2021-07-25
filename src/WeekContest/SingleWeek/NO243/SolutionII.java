package WeekContest.SingleWeek.NO243;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/30 10:42
 * @description
 */
public class SolutionII {

    public String maxValue(String n, int x) {
        char[] chars = n.toCharArray();
        int idx = 0;
        if (chars[idx] == '-') {
            idx++;
            while (idx < chars.length && (chars[idx] - '0' - x) <= 0) {
                idx++;
            }
        } else {
            while (idx < chars.length && (chars[idx] - '0' - x) >= 0) {
                idx++;
            }
        }
        StringBuilder sb = new StringBuilder(n);
        sb.insert(idx - 1, x);
        return sb.toString();
    }

}

package WeekContest.SingleWeek.NO246;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/20 10:33
 * @description
 */
public class SolutionI {

    public String largestOddNumber(String num) {
        char[] chars = num.toCharArray();
        int idx = chars.length - 1;
        for (; idx >= 0; idx--) {
            if (((chars[idx] - '0') & 1) == 1) {
                break;
            }
        }
        if (idx < 0) {
            return "";
        }
        return new String(chars, 0, idx + 1);
    }

}

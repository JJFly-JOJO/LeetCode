package WeekContest.SingleWeek.NO228;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/14 10:33
 * @description
 */
public class SolutionI {

    public int minOperations(String s) {
        char[] chars = s.toCharArray();
        int r1 = 0;
        for (int i = 0; i < chars.length; i++) {
            if ((i & 1) == 0) {
                if (chars[i] == '1') {
                    r1++;
                }
            } else {
                if (chars[i] == '0') {
                    r1++;
                }
            }
        }
        return Math.min(r1, chars.length - r1);
    }

}

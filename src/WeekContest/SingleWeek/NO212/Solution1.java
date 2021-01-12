package WeekContest.SingleWeek.NO212;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/25 10:36
 * @description
 */
public class Solution1 {

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char[] chars = keysPressed.toCharArray();
        int len = chars.length;
        int max = releaseTimes[0];
        char c = chars[0];
        for (int i = 1; i < len; i++) {
            int v = releaseTimes[i] - releaseTimes[i - 1];
            if (v > max) {
                max = v;
                c = chars[i];
            } else if (v == max) {
                if (chars[i] - c > 0) {
                    c = chars[i];
                }
            }
        }
        return c;
    }

}

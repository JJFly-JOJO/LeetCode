package WeekContest.SingleWeek.NO225;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/24 10:37
 * @description
 */
public class SolutionI {

    public String maximumTime(String time) {
        char[] chars = time.toCharArray();
        if (chars[0] == '?') {
            if (chars[1] != '?' && chars[1] >= '4') {
                chars[0] = '1';
            } else {
                chars[0] = '2';
            }
        }
        if (chars[1] == '?') {
            if (chars[0] == '0' || chars[0] == '1') {
                chars[1] = '9';
            }
            if (chars[0] == '2') {
                chars[1] = '3';
            }
        }
        if (chars[3] == '?') {
            chars[3] = '5';
        }
        if (chars[4] == '?') {
            chars[4] = '9';
        }
        return new String(chars);
    }

}

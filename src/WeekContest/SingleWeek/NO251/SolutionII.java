package WeekContest.SingleWeek.NO251;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/25 10:28
 * @description
 */
public class SolutionII {

    public String maximumNumber(String num, int[] change) {
        char[] numChar = num.toCharArray();
        int firstIdx = 0;
        while (firstIdx < numChar.length) {
            int n = numChar[firstIdx] - '0';
            if (n < change[n]) {
                break;
            }
            firstIdx++;
        }
        while (firstIdx < numChar.length) {
            int n = numChar[firstIdx] - '0';
            if (n > change[n]) {
                break;
            }
            numChar[firstIdx] = (char) (change[n] + '0');
            firstIdx++;
        }
        return new String(numChar);
    }

}

package WeekContest.SingleWeek.NO216;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/22 10:17
 * @description
 */
public class SolutionII {

    public String getSmallestString(int n, int k) {
        int countZ = 0;
        while ((n + 25) < k) {
            countZ++;
            n--;
            k -= 26;
        }
        StringBuilder sb = new StringBuilder();
        int last = n - 1;
        for (int i = 0; i < last; i++) {
            sb.append('a');
            k--;
        }
        sb.append((char) (k + 'a'));
        for (int i = 0; i < countZ; i++) {
            sb.append('z');
        }
        return sb.toString();
    }
}

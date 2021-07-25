package WeekContest.SingleWeek.NO240;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/9 10:41
 * @description 2005
 */
public class SolutionI {

    public int maximumPopulation(int[][] logs) {
        int max = 0;
        int year = 0;
        for (int[] l : logs) {
            int count = 0;
            int y = Integer.MAX_VALUE;
            for (int[] log : logs) {
                if (log[1] > l[0] && log[0] <= l[0]) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                year = l[0];
            } else if (count == max) {
                if (l[0] < year) {
                    year = l[0];
                }
            }
        }
        return year;
    }

}

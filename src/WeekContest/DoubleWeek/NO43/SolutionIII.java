package WeekContest.DoubleWeek.NO43;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/9 23:29
 * @description
 */
public class SolutionIII {

    public int[] constructDistancedSequence(int n) {
        int[] res = new int[n * 2 - 1];
        for (int i = n; i > 1; i--) {
            int index = 0;
            while (index < res.length) {
                if (res[index] == 0 && res[index + i] == 0) {
                    res[index] = 5;
                    res[index + i] = 5;
                    break;
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] == 0) {
                res[i] = 1;
            }
        }
        return res;
    }

}

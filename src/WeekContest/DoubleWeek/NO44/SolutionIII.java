package WeekContest.DoubleWeek.NO44;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/23 23:24
 * @description
 */
public class SolutionIII {

    public int[] decode(int[] encoded) {
        //count A^B^C^D...^N
        int a = 0;
        int n = encoded.length + 1;
        for (int i = 1; i <= n; i++) {
            a ^= i;
        }
        //count B^C^D...^N
        for (int i = 1; i < encoded.length; i += 2) {
            a ^= encoded[i];
        }
        //res
        int[] res = new int[n];
        res[0] = a;
        for (int i = 1; i < res.length; i++) {
            res[i] = encoded[i - 1] ^ res[i - 1];
        }
        return res;
    }

}

package WeekContest.DoubleWeek.NO43;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/9 21:39
 * @description
 */
public class Solution {

    public int totalMoney(int n) {
        int res = 0;
        int count = n / 7;
        int mod = n % 7;
        int monday = 1;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 7; j++) {
                res += monday + j;
            }
            monday++;
        }
        for (int i = 0; i < mod; i++) {
            res += monday + i;
        }
        return res;
    }

}

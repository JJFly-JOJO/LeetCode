package WeekContest.SingleWeek.NO226;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/31 10:31
 * @description
 */
public class SolutionI {

    public int countBalls(int lowLimit, int highLimit) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int v = calculate(i);
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            res = Math.max(e.getValue(), res);
        }
        return res;
    }

    private int calculate(int i) {
        int num = 0;
        while (i != 0) {
            num += i % 10;
            i /= 10;
        }
        return num;
    }

}

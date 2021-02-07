package WeekContest.DoubleWeek.NO45;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/6 22:30
 * @description
 */
public class SolutionI {

    public int sumOfUnique(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) {
                res += e.getKey();
            }
        }
        return res;
    }

}

package WeekContest.SingleWeek.NO222;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/3 10:44
 * @description ----------- 尝试用哈希表记录中间满足2的幂的结果 方法存在超时------------
 */
public class SolutionII {

    private TreeMap<Integer, Integer> map;

    public int countPairs(int[] deliciousness) {
        long count = 0L;
        long max = (long) 1e9 + 7L;
        map = new TreeMap<>();
        map.put(2, 2);
        map.put(1, 1);
        Arrays.sort(deliciousness);
        for (int i = 0; i < deliciousness.length; i++) {
            for (int j = i + 1; j < deliciousness.length; j++) {
                int sum = deliciousness[i] + deliciousness[j];
                if (sum == 1) {
                    count++;
                    continue;
                }
                if ((sum & 1) == 1 || sum == 0) {
                    continue;
                }
                int maxMod = map.floorKey(sum);
                if (sum % maxMod != 0) {
                    continue;
                }
                int s = sum / maxMod;
                if (mod(sum)) {
                    count++;
                    if (count > max) {
                        count -= max;
                    }
                    map.put(sum, sum);
                    map.put(s, s);
                }
            }
        }
        return (int) (count % max);
    }

    private boolean mod(int sum) {
        if (map.containsKey(sum)) {
            return true;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        return mod(sum >>> 1);
    }

}

package WeekContest.SingleWeek.NO213;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/1 10:29
 * @description
 */
public class Solution1 {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> vToI = new HashMap<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            vToI.put(arr[i], i);
        }
        int count = 0;
        for (int[] piece : pieces) {
            int last = -1;
            for (int i : piece) {
                if (!vToI.containsKey(i)) {
                    return false;
                }
                int curIndex = vToI.get(i);
                if (last != -1 && curIndex - last != 1) {
                    return false;
                }
                last = curIndex;
                count++;
            }
        }
        return count == len;
    }

}

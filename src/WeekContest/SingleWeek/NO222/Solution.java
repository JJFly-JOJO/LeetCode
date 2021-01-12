package WeekContest.SingleWeek.NO222;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/3 10:36
 * @description
 */
public class Solution {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
        int count = 0;
        for (int[] boxType : boxTypes) {
            if (truckSize >= boxType[0]) {
                count += boxType[0] * boxType[1];
                truckSize -= boxType[0];
            } else {
                count += truckSize * boxType[1];
                return count;
            }
        }
        return count;
    }

}

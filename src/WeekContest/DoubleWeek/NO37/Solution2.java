package WeekContest.DoubleWeek.NO37;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/17 22:21
 * @description
 */
public class Solution2 {

    public int[] bestCoordinate(int[][] towers, int radius) {
        int length = towers.length;
        Map<Integer, List<int[]>> qToIndex = new HashMap<>();
        int[] q = new int[length];
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (j == i) {
                    q[i] += towers[i][2];
                } else {
                    double distance = getDistance(towers[i], towers[j]);
                    if (distance <= radius) {
                        int qIToJ = (int) Math.floor(towers[i][2] / (1 + distance));
                        int qJToI = (int) Math.floor(towers[j][2] / (1 + distance));
                        q[i] += qJToI;
                        q[j] += qIToJ;
                    }
                }
            }
        }
        for (int i = 0; i < length; i++) {
            List<int[]> tempList = qToIndex.getOrDefault(q[i], new ArrayList<>());
            tempList.add(towers[i]);
            qToIndex.put(q[i], tempList);
        }
        Arrays.sort(q);
        List<int[]> list = qToIndex.get(q[length - 1]);
        list.sort((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        return new int[]{list.get(0)[0], list.get(0)[1]};
    }

    private double getDistance(int[] tower1, int[] tower2) {
        int x = tower1[0] - tower2[0];
        int y = tower1[1] - tower2[1];
        return Math.sqrt(x * x + y * y);
    }

}

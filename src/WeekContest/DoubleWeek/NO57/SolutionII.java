package WeekContest.DoubleWeek.NO57;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/24 21:58
 * @description 2
 */
public class SolutionII {

    public static void main(String[] args) {
        int[][] nums=new int[][]{{33889, 98676},
                {80071, 89737}, {44118, 52565}, {52992, 84310},
                {78492, 88209}, {21695, 67063}, {84622, 95452},
                {98048, 98856}, {98411, 99433}, {55333, 56548},
                {65375, 88566}, {55011, 62821}, {48548, 48656},
                {87396, 94825}, {55273, 81868}, {75629, 91467}};
        Arrays.sort(nums,(o1,o2)->o1[0]-o2[0]);
        System.out.println(new SolutionII().smallestChair(new int[][]{{33889, 98676},
                {80071, 89737}, {44118, 52565}, {52992, 84310},
                {78492, 88209}, {21695, 67063}, {84622, 95452},
                {98048, 98856}, {98411, 99433}, {55333, 56548},
                {65375, 88566}, {55011, 62821}, {48548, 48656},
                {87396, 94825}, {55273, 81868}, {75629, 91467}}, 6));
    }

    public int smallestChair(int[][] times, int targetFriend) {
        Integer[] idxs = new Integer[times.length];
        for (int i = 0; i < idxs.length; i++) {
            idxs[i] = i;
        }
        Arrays.sort(idxs, (o1, o2) -> times[o1][0] - times[o2][0]);
        int[] chairs = new int[idxs.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int minChair = 0;
        for (int i = 0; i < idxs.length; i++) {
            while (!pq.isEmpty() && times[idxs[i]][0] >= pq.peek()[1]) {
                //chair chair下标 到达时间
                int[] chair = pq.poll();
//                if (chair[0] < minChair) {
//                    minChair = chair[0];
//                }
                chairs[chair[0]] = 0;
            }
            //minChair = getNextChair(minChair, chairs);
            minChair = getMinChair(chairs);
            if (targetFriend == idxs[i]) {
                return minChair;
            }
            chairs[minChair] = 1;
            times[idxs[i]][0] = minChair;
            pq.offer(times[idxs[i]]);
        }
        return -1;
    }

    private int getMinChair(int[] chairs) {
        for (int i = 0; i < chairs.length; i++) {
            if (chairs[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int getNextChair(int minChair, int[] chairs) {
        for (int i = minChair + 1; i < chairs.length; i++) {
            if (chairs[i] == 0) {
                return i;
            }
        }
        return 0;
    }

}

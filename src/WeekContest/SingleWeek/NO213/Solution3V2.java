package WeekContest.SingleWeek.NO213;

import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/1 12:56
 * @description
 */
public class Solution3V2 {

    public static void main(String[] args) {
    }

    public static void test() {
        System.out.println("Test");
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> maxHead = new PriorityQueue<>(1000, (o1, o2) -> o2 - o1);
        int len = heights.length;
        int index = 1;
        while ((bricks >= 0 || ladders >= 0) && index < len) {
            int val = heights[index] - heights[index - 1];
            if (val > 0) {
                if (bricks >= val) {
                    bricks -= val;
                    maxHead.add(val);
                } else if (ladders > 0) {
                    ladders--;
                    //贪心算法的体现！----------------如果当前梯子去替换堆的最大节点值
                    // 得到的bricks数量任然不能达到下一级 那么我们就不必再拿梯子去替换第二大的节点值了
                    // 因为这样是亏损的！
                    if (!maxHead.isEmpty() && bricks + maxHead.peek() >= val) {
                        bricks += maxHead.poll();
                        continue;
                    }
                } else {
                    break;
                }
            }
            index++;
        }
        return index - 1;
    }
}

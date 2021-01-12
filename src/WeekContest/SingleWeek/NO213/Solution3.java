package WeekContest.SingleWeek.NO213;

import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/1 10:50
 * @description
 */
public class Solution3 {

    public static void main(String[] args) {
        /*PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, (i1, i2) -> i2 - i1);
        maxHeap.add(5);
        maxHeap.add(6);
        maxHeap.add(5);
        maxHeap.add(4);*/
        int[] height = new int[]{1,5,1,2,3,4};
        System.out.println(new Solution3().furthestBuilding(height, 4, 1));

    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {

        //预处理
        int len = heights.length;
        int[] differ = new int[len];
        for (int i = 1; i < len; i++) {
            differ[i] = heights[i] - heights[i - 1];
        }
        //最大堆
        //大顶堆，容量11
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10000, (i1, i2) -> i2 - i1);
        //
        int index = 1;
        lable:
        while ((bricks >= 0 || ladders >= 0) && index < len) {
            if (differ[index] > 0) {
                if (bricks >= differ[index]) {
                    bricks -= differ[index];
                    maxHeap.add(differ[index]);
                } else {
                    if (ladders > 0) {
                        if (!maxHeap.isEmpty()) {
                            if (maxHeap.peek() + bricks >= differ[index]) {
                                ladders--;
                                bricks += maxHeap.poll();
                                continue lable;
                            } else {
                                ladders--;
                            }
                        } else {
                            ladders--;
                        }
                    } else {
                        break;
                    }
                }
            }
            index++;
        }
        return index - 1;
    }

}

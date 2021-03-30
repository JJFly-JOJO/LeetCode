package WeekContest.SingleWeek.NO228;

import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/14 10:57
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().minimumSize(new int[]{2, 4, 8, 2}, 4));
    }

    public int minimumSize(int[] nums, int maxOperations) {
        if (nums.length == 1) {
            int v = nums[0] / (maxOperations + 1);
            int mod = nums[0] % v;
            return mod == 0 ? v : v + 1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : nums) {
            pq.add(i);
        }
        while (maxOperations > 0) {
            int divideNum = pq.poll();
            int top = pq.peek();
            int i = 1;
            for (; i <= maxOperations; i++) {
                int val = divideNum / (i + 1);
                int mod = divideNum % val;
                val = mod == 0 ? val : val + 1;
                if (val <= top) {
                    for (int j = 0; j < mod; j++) {
                        pq.add(val);
                    }
                    for (int j = 0; j < i + 1 - mod; j++) {
                        pq.add(val - 1);
                    }
                    break;
                }
            }
            maxOperations -= i;
        }
        return pq.peek();
    }

}

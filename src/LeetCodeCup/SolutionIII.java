package LeetCodeCup;

import java.util.PriorityQueue;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/5 15:57
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().magicTower(new int[]{100,100,100,-250,-60,-140,-50,-50,100,150}));
        System.out.println(Integer.MAX_VALUE);
    }

    public int magicTower(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0;
        long sum = 1L;
        long negativeSum = 0;
        for (int i = 0; i < nums.length; i++) {
            while (sum + nums[i] <= 0 && !pq.isEmpty() && nums[i] > pq.peek()) {
                int val = pq.poll();
                negativeSum += -val;
                res++;
                sum += -val;
            }
            if (sum + nums[i] <= 0) {
                negativeSum += -nums[i];
                res++;
                continue;
            }
            if (nums[i] < 0) {
                pq.add(nums[i]);
            }
            sum += nums[i];
        }
        return sum > negativeSum ? res : -1;
    }

}

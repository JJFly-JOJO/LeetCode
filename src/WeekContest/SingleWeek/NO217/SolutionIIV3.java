package WeekContest.SingleWeek.NO217;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/9 12:39
 * @description ---------------单调栈--------------
 * --------------------子序列（不连续） 子集（连续）-------------
 */
public class SolutionIIV3 {

    public static void main(String[] args) {
        for (int i : new SolutionIIV3().mostCompetitive(new int[]{84, 10, 71, 23, 66, 61, 62, 64, 34, 41, 80, 25, 91, 43, 4, 75, 65, 13, 37, 41, 46, 90, 55, 8, 85, 61, 95, 71}, 24)) {
            System.out.print(" " + i);
        }
    }

    public int[] mostCompetitive(int[] nums, int k) {
        int count = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : nums) {
            int sum = count + stack.size();
            while (sum > k && !stack.isEmpty() && num < stack.peek()) {
                stack.pop();
                sum--;
            }
            if (stack.size() < k) {
                stack.push(num);
            }
            count--;
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = stack.pollLast();
        }
        return res;
    }

}

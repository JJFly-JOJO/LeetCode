package Array.NO739DailyTemperatures;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/8 13:56
 * @description -----------单调栈解法----------
 */
public class SolutionII {

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        //存放下标 ArrayDeque效率更高
        Deque<Integer> stack = new ArrayDeque<>();
        //Deque<Integer> stack = new LinkedList<>();
        //Stack<Integer> stack = new Stack<>();
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            int val = stack.isEmpty() ? 0 : stack.peek() - i;
            res[i] = val;
            stack.push(i);
        }
        return res;
    }

}

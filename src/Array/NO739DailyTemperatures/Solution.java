package Array.NO739DailyTemperatures;

import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/8 13:53
 * @description
 */
public class Solution {

    public int[] dailyTemperatures(int[] T) {
        int[] tToI = new int[101];
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];
        for (int i = T.length - 1; i >= 0; i--) {
            //注意题目要求严格单调 因此相等时也需要弹出
            while (!stack.isEmpty() && T[i] >= stack.peek()) {
                stack.pop();
            }
            int val = stack.isEmpty() ? 0 : tToI[stack.peek()] - i;
            res[i] = val;
            stack.push(T[i]);
            tToI[T[i]] = i;
        }
        return res;
    }

}

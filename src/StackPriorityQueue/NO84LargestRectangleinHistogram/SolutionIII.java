package StackPriorityQueue.NO84LargestRectangleinHistogram;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/18 15:31
 * @description -----单调栈 一次遍历-------
 */
public class SolutionIII {

    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        Arrays.fill(right, heights.length);
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int v = stack.pop();
                right[v] = i;
            }
            left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        for (int i = 0; i < heights.length; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i]));
        }
        return res;
    }

}

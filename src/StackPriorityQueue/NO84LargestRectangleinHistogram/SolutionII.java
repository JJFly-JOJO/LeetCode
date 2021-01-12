package StackPriorityQueue.NO84LargestRectangleinHistogram;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/17 17:15
 * @description -----------双单调栈-----------
 * 思路：遍历到当前高度的柱状图，能够平移（矩形的高度必须一致）到的左边界和右边界
 */
public class SolutionII {

    public static void main(String[] args) {
        System.out.println(new SolutionII().largestRectangleArea(new int[]{4, 2, 0, 3, 2, 5}));
    }

    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        stack.clear();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? heights.length - 1 : stack.peek() - 1;
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            res = Math.max(res, (right[i] - left[i] + 1) * heights[i]);
        }
        return res;
    }
}

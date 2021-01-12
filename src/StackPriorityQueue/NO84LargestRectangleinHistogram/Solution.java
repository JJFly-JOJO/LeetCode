package StackPriorityQueue.NO84LargestRectangleinHistogram;

import java.util.ArrayDeque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/17 15:42
 * @description -------------------单调栈  体会从数组左边单调与从数组右边单调的区别（保留的是哪一部分结果）---------------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().largestRectangleArea(new int[]{4, 2, 0, 3, 2, 5}));
    }

    public int largestRectangleArea(int[] heights) {
        int res = 0;
        //List<Integer> subSum = new ArrayList<>();
        //记录heights=>index
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            if (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int lastIndex = 0;
                while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                    lastIndex = stack.pop();
                    //subSum.add(heights[lastIndex] * (i - lastIndex));
                    res = Math.max(res, heights[lastIndex] * (i - lastIndex));
                }
                heights[lastIndex] = heights[i];
                stack.push(lastIndex);
            } else {
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int t = stack.pop();
            //subSum.add(heights[t] * (heights.length - t));
            res = Math.max(res, heights[t] * (heights.length - t));
        }
        /*for (int v : subSum) {
            if (v > res) {
                res = v;
            }
        }*/
        return res;
    }
}

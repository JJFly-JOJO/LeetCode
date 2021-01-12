package Array.NO581ShortestUnsortedContinuousSubarray;

import java.util.ArrayDeque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/21 10:51
 * @description
 */
public class Solution {

    public int findUnsortedSubarray(int[] nums) {
        ArrayDeque<Integer> stackL = new ArrayDeque<>();
        ArrayDeque<Integer> stackR = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stackL.isEmpty() && nums[i] < nums[stackL.peek()]) {
                stackL.pop();
            }
            stackL.push(i);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stackR.isEmpty() && nums[i] > nums[stackR.peek()]) {
                stackR.pop();
            }
            stackR.push(i);
        }
        int l = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i != stackL.peekLast()) {
                break;
            }
            l = i;
            stackL.pollLast();
        }
        int r = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i != stackR.peekLast()) {
                break;
            }
            r = i;
            stackR.pollLast();
        }
        if (l == -1 && r == -1) {
            return nums.length;
        }
        if (l != -1 && r != -1) {
            int res = r - l - 1;
            return res < 0 ? 0 : res;
        }
        if (l != -1) {
            return nums.length - l - 1;
        }
        return r;
    }

}

package Array.NO496NextGreaterElementI;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/8 12:53
 * @description --------------单调栈解法-----------
 */
public class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                stack.pop();
            }
            int val = stack.isEmpty() ? -1 : stack.peek();
            map.put(nums2[i], val);
            stack.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.get(nums1[i]);
        }
        return nums1;
    }

}

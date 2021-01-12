package Array.NO503NextGreaterElementII;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/9 11:28
 * @description ------------------单调栈方法----------------
 * 该数组是一个循环数组，我们可以利用s->s+s拼接的思路，将当前数组元素尾部拼接上前面的一段数组，
 * 然后再从尾部开始动态更新单调栈，利用单调栈只会看到离自己最近的那个较大（较小）元素的特点，
 * 我们并不需要担心当前元素的下一个元素，是否会因为前面较大的数，将循环数组段的元素全部弹出
 */
public class Soluition {

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        //init
        for (int i = nums.length - 2; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                stack.pop();
            }
            stack.push(i);
        }
        //find res
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && (nums[i] >= nums[stack.peek()] || i == stack.peek())) {
                stack.pop();
            }
            int val = stack.isEmpty() ? -1 : nums[stack.peek()];
            res[i] = val;
            stack.push(i);
        }
        return res;
    }

}

package String.NO402RemoveKDigits;

import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/15 21:55
 * <p>
 * 该题类比NO316 移除k位 我们可以看成留下n-k位数
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().removeKdigits("10", 1));
    }

    /**
     * @param name
     * @param k
     * @return
     */
    public String removeKdigits(String name, int k) {
        char[] chars = name.toCharArray();
        int lenghth = chars.length;
        if (lenghth == 0 || lenghth == k) {
            return "0";
        }
        //记录删除的个数
        int count = 0;
        //存放待删除的值
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        int i = 1;
        for (; i < lenghth && count < k; i++) {
            while (!stack.isEmpty() && chars[i] < stack.peek() && count < k) {
                stack.pop();
                count++;
            }
            stack.push(chars[i]);
        }
        for (int j = count; j < k; j++) {
            stack.pop();
        }
        StringBuilder res = new StringBuilder();
        boolean click = false;
        for (char temp : stack) {
            if (temp == '0' && !click) {
            } else {
                click = true;
                res.append(temp);
            }
        }
        for (; i < lenghth; i++) {
            if (chars[i] == '0' && !click) {
            } else {
                click = true;
                res.append(chars[i]);
            }
        }
        return res.length() == 0 ? "0" : res.toString();
    }

}

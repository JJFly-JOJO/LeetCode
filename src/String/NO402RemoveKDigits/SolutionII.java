package String.NO402RemoveKDigits;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/9 13:57
 * @description ------------------单调栈----------------
 * 注意这里：10200我们保存0200也是合法的，最后只需要对前导0进行处理
 */
public class SolutionII {

    public static void main(String[] args) {
        System.out.println(new SolutionII().removeKdigits("10200", 1));
    }

    public String removeKdigits(String name, int k) {
        char[] chars = name.toCharArray();
        //移除后字符串长度
        int len = chars.length - k;
        Deque<Character> stack = new ArrayDeque<>();
        int count = chars.length;
        for (char aChar : chars) {
            int sum = stack.size() + count;
            while (sum > len && !stack.isEmpty() && aChar < stack.peek()) {
                stack.pop();
                sum--;
            }
            if (stack.size() < len) {
                stack.push(aChar);
            }
            count--;
        }
        StringBuilder res = new StringBuilder();
        boolean tag = true;
        while (!stack.isEmpty()) {
            if (tag) {
                if (stack.peekLast() != '0') {
                    tag = false;
                    res.append(stack.pollLast());
                } else {
                    stack.pollLast();
                }
            } else {
                res.append(stack.pollLast());
            }
        }
        return res.length() == 0 ? "0" : res.toString();
    }

}

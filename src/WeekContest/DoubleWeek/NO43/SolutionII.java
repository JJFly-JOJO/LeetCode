package WeekContest.DoubleWeek.NO43;

import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/9 22:44
 * @description ----------------贪心算法---------------
 * 技巧1：预处理 避免x y大小的讨论 减少冗余代码
 * 技巧2：使用栈 满足“ab”或者“bc”组合时就弹出栈中元素
 */
public class SolutionII {

    public static void main(String[] args) {
        String s = "aabbaaxybbaabb";
        String ab = "ab";
        //System.out.println(s.replaceAll(ab, ""));
        System.out.println(s.indexOf("abgg"));
        //  System.out.println(new SolutionII().maximumGain("cdbcbbaaabab", 4, 5));
    }

    public int maximumGain(String s, int x, int y) {
        StringBuilder sb = new StringBuilder(s);
        int res = 0;
        //预处理 统一比较
        if (x < y) {
            int t = x;
            x = y;
            y = t;
            sb.reverse();
        }
        Stack<Character> stackIn = new Stack<>();
        char[] chars = sb.toString().toCharArray();
        //先处理ab
        for (char c : chars) {
            if (c == 'b') {
                if (!stackIn.isEmpty() && stackIn.peek() == 'a') {
                    stackIn.pop();
                    res += x;
                } else {
                    stackIn.push(c);
                }
            } else {
                stackIn.push(c);
            }
        }
        //再处理ba 同样需要栈来辅助记录已经遍历到的值 注意此时是倒序 因此当遇到字符b 再回看栈中元素
        Stack<Character> stackRe = new Stack<>();
        while (!stackIn.isEmpty()) {
            char c = stackIn.pop();
            if (c == 'b') {
                if (!stackRe.isEmpty() && stackRe.peek() == 'a') {
                    stackRe.pop();
                    res += y;
                } else {
                    stackRe.push(c);
                }
            } else {
                stackRe.push(c);
            }
        }
        return res;
    }

}

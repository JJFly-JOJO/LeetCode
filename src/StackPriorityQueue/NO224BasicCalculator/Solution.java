package StackPriorityQueue.NO224BasicCalculator;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    public int calculate(String s) {
        //注意 trim仅仅是去掉头部尾部的空字符串
        //String ss = s.trim();
        String ss = s.replace(" ", "");
        char[] charsOfS = ss.toCharArray();
        int length = ss.length();
        StringBuilder num = new StringBuilder();
        Stack<Character> stackOfS = new Stack<>();
        Stack<Integer> stackOfNum = new Stack<>();
        int meetBracket = 0;
        for (int i = 0; i < length; i++) {
            if (charsOfS[i] == '+' || charsOfS[i] == '-') {
                if (stackOfNum.size() > meetBracket) {
                    int tempRes = Integer.parseInt(num.toString()) * sign(stackOfS.pop()) + stackOfNum.pop();
                    stackOfNum.push(tempRes);
                    num.delete(0, num.length());
                } else {
                    stackOfNum.push(Integer.parseInt(num.toString()));
                    num.delete(0, num.length());
                }
                stackOfS.push(charsOfS[i]);
            } else if (charsOfS[i] == '(') {
                meetBracket++;
                if (stackOfNum.size() < meetBracket) {
                    //用0来填充(((------>(0+(0+(的情况
                    stackOfNum.push(0);
                    stackOfS.push('+');
                }
            } else if (charsOfS[i] == ')') {
                //出栈到上一个 (
                int tempRes = stackOfNum.pop() + sign(stackOfS.pop()) * Integer.parseInt(num.toString());
                num = new StringBuilder(String.valueOf(tempRes));
                meetBracket--;
            } else {
                num.append(charsOfS[i]);
            }
        }
        if (!stackOfNum.isEmpty()) {
            return stackOfNum.pop() + Integer.parseInt(num.toString()) * sign(stackOfS.pop());
        } else {
            return Integer.parseInt(num.toString());
        }
    }

    /**
     * 判断加号减号
     *
     * @param s
     * @return
     */
    int sign(Character s) {
        if (s.toString().equals("+")) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 基本方法 双栈方法：类似逆波兰表达式 对于操作符 就弹出栈中两个操作数计算再压入栈中
     *
     * @param s
     * @return
     */
    public int calculateForDoubleStack(String s) {
        int length = s.length();
        //可以不用先对string做去除空格处理 将其转换为char时 在遍历时遇见' '空格字符时不做处理即可
        char[] chars = s.toCharArray();
        //stack
        Stack<Integer> stackOfNum = new Stack<>();
        //解决遇见第一个操作时栈中只有一个元素的情况
        stackOfNum.push(0);
        Stack<Character> stackOfOp = new Stack<>();
        //num
        int num = 0;

        for (int i = 0; i < length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num = num * 10 + chars[i] - '0';
            } else if (chars[i] == '(') {
                //遇见左括号 用0填充入栈
                stackOfNum.push(0);
            } else {
                //遇见) + -三个操作符 stackNum中弹出一个数与当前num值相加入栈
                if (chars[i] == '+') {
                    stackOfNum.add(stackOfNum.pop() + num);
                    num = 0;
                } else if (chars[i] == '-') {
                    stackOfNum.add(stackOfNum.pop() - num);
                    num = 0;
                } else {
                    num = stackOfNum.pop() + num;
                }
            }
        }
        return stackOfNum.pop() + num;
    }

}

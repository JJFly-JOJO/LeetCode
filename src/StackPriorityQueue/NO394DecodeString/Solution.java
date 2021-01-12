package StackPriorityQueue.NO394DecodeString;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
    }

    /**
     * 存在问题
     *
     * @param s
     * @return
     */
    public String decodeStringErro(String s) {
        StringBuilder res = new StringBuilder();
        int length = s.length();
        if (length == 0) {
            return s;
        }
        Stack<StringBuilder> stackOfStr = new Stack<>();
        Stack<StringBuilder> stackOfNum = new Stack<>();
        char[] chars = s.toCharArray();
        //遇见num就要出栈
        //boolean meetNum=false;
        //boolean meetStr=false;
        StringBuilder tempStr = new StringBuilder();
        StringBuilder tempNum = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                if (tempStr.length() != 0) {
                    //str入栈
                    stackOfStr.push(new StringBuilder(tempStr));
                    tempStr.delete(0, tempStr.length());
                }
                tempNum.append(chars[i]);
            }
            //技巧：把该段放在else分支中 就不再需要复杂的字符判断
            /*else if (chars[i] >= 'a' && chars[i] <= 'z' || chars[i] >= 'A' && chars[i] <= 'Z') {
                tempStr.append(chars[i]);
            } */
            else if (chars[i] == '[') {
                stackOfNum.push(new StringBuilder(tempNum));
                //StringBuilder清空的三种方法比较！！！！！！！！！！！！！！！！！
                tempNum.delete(0, tempNum.length());
            } else if (chars[i] == ']') {
                //出栈
                if (tempStr.length() == 0) {
                    tempStr = stackOfStr.pop();
                } else {
                    if (!stackOfStr.isEmpty()) {
                        tempStr = stackOfStr.pop().append(tempStr);
                    }
                }
                StringBuilder number = stackOfNum.pop();
                StringBuilder tempRes = new StringBuilder();
                int count = Integer.parseInt(number.toString());
                for (int j = 0; j < count; j++) {
                    tempRes.append(tempStr);
                }
                if (stackOfStr.isEmpty()) {
                    res.append(tempRes);
                } else {
                    stackOfStr.push(stackOfStr.pop().append(tempRes));
                }
                //tempStr归0
                tempStr.delete(0, tempStr.length());
            } else {
                tempStr.append(chars[i]);
            }
        }
        //处理特殊情况 "2[abc]3[cd]ef" 最后的ef没有[]的情况
        if (tempStr.length() == 0) {
            return res.toString();
        } else {
            if (stackOfStr.isEmpty()) {
                return tempStr.toString();
            } else {
                return res.append(tempStr).toString();
            }
        }
    }

    public String decodeString(String s) {
        int length = s.length();
        if (length == 0) {
            return s;
        }
        char[] charsOfS = s.toCharArray();
        Stack<StringBuilder> stackOfStr = new Stack<>();
        Stack<StringBuilder> stackOfNum = new Stack<>();
        StringBuilder str = new StringBuilder();
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (charsOfS[i] >= '0' && charsOfS[i] <= '9') {
                num.append(charsOfS[i]);
            } else if (charsOfS[i] == '[') {
                //str与num全部入栈
                stackOfNum.push(new StringBuilder(num));
                num.delete(0, num.length());
                //空字符串也要！！！
                /*if (str.length() != 0) {
                    stackOfStr.push(new StringBuilder(str));
                    str.delete(0, str.length());
                }*/
                stackOfStr.push(new StringBuilder(str));
                str.delete(0, str.length());
            } else if (charsOfS[i] == ']') {
                //str必定非空 因为]前面必定有字母字符
                StringBuilder tempNum = stackOfNum.pop();
                StringBuilder temp = new StringBuilder();
                int count = Integer.parseInt(tempNum.toString());
                while (count-- > 0) {
                    temp.append(str);
                }
                //!!!!!!!!!!!!!!!!!!<-------------------
               /* if (!stackOfStr.isEmpty()) {
                    str = stackOfStr.pop().append(temp);
                } else {
                    str = temp;
                }*/
                str = stackOfStr.pop().append(temp);
            } else {
                str.append(charsOfS[i]);
            }
        }
        return str.toString();
    }

}

package StackPriorityQueue.NO71SimplifyPath;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        //分割字符串测试
        String str = "a//b/c";
        String[] strs = str.split("/");
        System.out.println(strs.length);
        for (String temp : strs) {
            System.out.println(temp);
        }

        String path = "/home/";
        System.out.println(new Solution().simplifyPath(path));
    }

    public String simplifyPath(String path) {
        //用栈存储结果
        Stack<String> res = new Stack<>();
        //先利用/分割字符串 注意如果字符串是"//" 会分割出一个空字符串（string存在但是length为0）
        String[] strs = path.split("/");
        for (String temp : strs) {
            if (temp.length() == 0 || temp.equals(".")) {
                continue;
            } else if (temp.equals("..")) {
                if (!res.isEmpty()) {
                    res.pop();
                }
            } else {
                res.push(temp);
            }
        }
        StringBuilder resStr = new StringBuilder();
        if (res.isEmpty()) {
            resStr.append("/");
            return resStr.toString();
        }
        for (int i = 0; i < res.size(); i++) {
            resStr.append("/");
            resStr.append(res.get(i));
        }
        return resStr.toString();
    }
}

package StackPriorityQueue.NO388LongestAbsoluteFilePath;

public class Solution {

    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("\\t");
    }

    /**
     * 技巧：
     * 1.能用数组代替栈尽量代替stack 数据不仅操作快 而且可以快速获取到指定下标
     * 2.数组模拟出栈和入栈 就是对数组值进行覆盖
     * 注意：
     * "\n" "\t"算一个字符 因为/\是对后面的字符进行转义
     *
     * @param input
     * @return
     */
    public int lengthLongestPath(String input) {
        int length = input.length();
        //记录"/n"之间的字符串长度
        int res = 0;
        //记录最长字符串长度
        int max = 0;
        //记录是否遇到最终层（"."文件） 目的是为了之后遇到t时候刷新之前层的时候 先判断到最终层的字符串长度 更新max的值
        int mark = 0;
        //记录当前层下标 第一层从下标1开始 对应技巧1！！！！
        int index = 1;
        //status的下标表示层数 每一层记录从开始层到此层的字符串长度
        //以 "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" 举例
        // dir处于status[0]层 此时为3 一个t表示第一层 当遇到底层也就是"."的文件时 此时记录长度 更新max
        //之后遇到t 重新更新对应层数值
        int[] status = new int[length];
        //对应技巧（1） 因为字符串长度还要考虑'/'字符的长度 也就是说更新每一层的时候 当前层还要加上1 但是第一层是没有'/'字符的
        //这是后就能用哨兵（第一层的前一层）来把这个加的1给减去
        status[0] = -1;
        char[] chars = input.toCharArray();
        for (int i = 0; i < length; i++) {
            if (chars[i] == '\n') {
                //遇到n 则要记录下当前层的长度 这里对第t层的值从新覆盖就相当于出栈！<--------------理解
                //技巧：当前层的长度是res加上上一层的长度 但是 注意到当我们是第一层的时候 下标为0 那么上一层下标是-1 会造成数组越界
                //那么我们可以把第一层放到下标为1处  那么上一层下标为0 就不会存在数组越界的问题<----------------------技巧（1）
                status[index] = status[index - 1] + (res + 1);
                //判断当前层是否是底层 是更新max
                if (mark > 0) {
                    max = Math.max(status[index], max);
                }
                //归0 字符串中的t数量已经指定好了在第几层 因此我们的index也要归1
                mark = 0;
                res = 0;
                index = 1;
            } else if (chars[i] == '\t') {
                //遇到一个t 层数下标加1
                index++;
            } else {
                if (chars[i] == '.') {
                    mark = 1;
                }
                res++;
            }
        }
        //还要进行一次mark判断 例如此种情况：dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext 最后一层的值还没有加上
        if (mark > 0) {
            max = Math.max(status[index - 1] + res + 1, max);
        }
        return max;
    }
}

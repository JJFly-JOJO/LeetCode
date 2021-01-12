package String.NO316RemoveDuplicateLetters;

import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/15 15:44
 * <p>
 * 字典序：两个字符串我们从左到右顺序比较 遇到第一个不同的字符时 当前字符谁大该字符串字典序就大
 */
public class Solution {

    private int[] isSure = new int[123];

    public static void main(String[] args) {
        /*System.out.println("1" + "" + "2");
        String s = "";
        char[] chars = s.toCharArray();
        System.out.println(chars[0]);*/
        System.out.println(new Solution().removeDuplicateLettersGreedyAndRecursive("cbacdcbc"));
    }

    /**
     * 方法一：贪心加递归
     * 思路：我们首先要清楚  我们如果标记了每个字符的出现次数 只有一个的字符位置是一定不会改变的
     * 而如果当前遍历到的字符出现次数没有减为0 那么就说明后面还能位置于该字符的当前位置进行交换
     * 贪心思想：（子问题）最左侧的字符满足其他字符至少出现一次情况下的的最小字符
     *
     * @param s
     * @return
     */
    public String removeDuplicateLettersGreedyAndRecursive(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        if (length == 0) {
            return "";
        }
        int[] isVisited = new int[123];
        //initialize
        for (int i = 0; i < length; i++) {
            isVisited[chars[i]]++;
        }
        //initialize minIndex初始值不能是isSure中的值
        int minIndex = 0;
        for (; minIndex < length; minIndex++) {
            if (isSure[chars[minIndex]] == 0) {
                break;
            }
        }
        if (minIndex == length) {
            return "";
        }
        for (int i = minIndex; i < length; i++) {
            if (isSure[chars[i]] == 1) {
                continue;
            }
            if (chars[minIndex] > chars[i]) {
                minIndex = i;
            }
            if (--isVisited[chars[i]] == 0) {
                //###注意！！！！！：如果当前字符遍历次数变为0 那么说明该字符的位置已经确定放置于此 而minIndex也同时确定
                // 与该字符的相对位置，下次递归时 我们就从minIndex+1开始的子串继续寻找最小的字符（不是从字符遍历次数为0处开始找）
                // 因为这之间还存在字符没有遍历
                break;
            }
            //###理解：此刻的break时的minIndex满足 左边的字符字典序都比该index下的大 而右边包含了当前子问题的所有字符
            // （如果不包含 那么说明前面存在字符 在index之后都不会出现了 但是我们把index之前的字符全部删除了 显然得到的答案不正确）
        }
        //minIndex已经确定好位置 那么就要在之后的子串中删除该字符 这里我们用一个map来标记
        isSure[chars[minIndex]] = 1;
        //s.substring(pos + 1).replaceAll("" + s.charAt(pos), "") 当然也可以在递归传入的子串中用""替代chars[minIndex]
        return chars[minIndex] + removeDuplicateLettersGreedyAndRecursive(s.substring(minIndex + 1, chars.length));
    }

    /**
     * 方法二：贪心加栈的思想
     * 思路：首先记录下每个字符的出现次数，然后顺序遍历，当i对应字符大于i+1对应字符 并且i出现次数还未变为0 那么i弹出
     * i+1入栈
     *
     * @param s
     * @return
     */
    public String removeDuplicateLettersStack(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        if (length == 0) {
            return "";
        }
        int[] isVisited = new int[123];
        int[] issSure = new int[123];
        //stack存放字符
        Stack<Character> stack = new Stack<>();
        //initialize isVisited
        for (int i = 0; i < length; i++) {
            isVisited[chars[i]]++;
        }
        stack.push(chars[0]);
        isVisited[chars[0]]--;
        issSure[chars[0]] = 1;
        for (int i = 1; i < length; i++) {
            isVisited[chars[i]]--;
            if (issSure[chars[i]] == 1) {
                continue;
            }
            //栈一直弹出  此情况对应："bbcaac" 当走到a时 c弹出 继续向后看 b此时visited次数用尽 位置固定 不能弹出
            while (!stack.isEmpty() && chars[i] < stack.peek() && isVisited[stack.peek()] > 0) {
                issSure[stack.pop()] = 0;
            }
            stack.push(chars[i]);
            issSure[chars[i]] = 1;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            str.append(stack.get(i));
        }
        return str.toString();
    }

}

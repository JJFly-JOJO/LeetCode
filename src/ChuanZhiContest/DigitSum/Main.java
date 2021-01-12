package ChuanZhiContest.DigitSum;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/8 22:08
 * @description
 */
public class Main {

    private static int sum = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getDigitSum(n));
    }

    private static int getDigitSum(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int len = s.length();
        //计算小于len以下的位数结果
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= 9; j++) {
                backtrcing(9 - j, 1, i);
            }
        }
        //计算len的情况 s要进行限制
        int last = chars[0] - '0';
        boolean tag = false;
        for (int i = 1; i <= last; i++) {
            if (i == last) {
                tag = true;
            }
            backtrcingR(9 - i, tag, 1, chars);
        }
        return sum;
    }

    private static void backtrcingR(int rest, boolean tag, int level, char[] chars) {
        if (rest == 0) {
            sum++;
            return;
        }
        if (level == chars.length) {
            return;
        }
        int last = rest;
        if (tag) {
            last = chars[level] - '0';
        }
        for (int i = 0; i <= last; i++) {
            backtrcingR(rest - i, tag, level + 1, chars);
        }

    }

    private static void backtrcing(int rest, int curLevel, int level) {
        if (rest == 0) {
            sum++;
            return;
        }
        if (curLevel == level) {
            return;
        }
        for (int i = 0; i <= rest; i++) {
            backtrcing(rest - i, curLevel + 1, level);
        }
    }

}

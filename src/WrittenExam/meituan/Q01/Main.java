package WrittenExam.meituan.Q01;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/20 15:59
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        int res = getResult(s1, s2);
        System.out.println(res);
    }

    private static int getResult(String srcStr, String oppStr) {
        char[] srcC = srcStr.toCharArray();
        char[] oppC = oppStr.toCharArray();
        int index = 0;
        int idx = 0;
        int count = 0;
        while (idx < oppC.length) {
            for (; index < srcC.length; ) {
                if (srcC[index] == oppC[idx]) {
                    idx++;
                    index++;
                    if (index >= srcC.length) {
                        index -= srcC.length;
                    }
                    break;
                }
                count++;
                index++;
                if (index >= srcC.length) {
                    index -= srcC.length;
                }
            }
        }
        return count;
    }
}

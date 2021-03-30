package WrittenExam.meituan.Q04;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/20 17:00
 * @description 第一行两个整数 n, m，表示班上有 n 个同学和小美想知道的天数。
 * <p>
 * 接下来 n 行，每行 n 个整数，表示值日表 ai,j (0 ≤ ai,j ≤ n)。保证有且仅有对角线上的数字是 0。
 * <p>
 * 1 ≤ n ≤ 500, 1 ≤ m ≤ 1018。
 * <p>
 * 3 7
 * 0 3 2
 * 3 0 3
 * 2 1 0
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long m = scanner.nextLong();
        scanner.nextLine();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextInt();
            }
            scanner.nextLine();
        }
        int res = getResult(a, m);
        System.out.println(res);
    }

    private static int getResult(int[][] a, long m) {
        int pre = 1;
        int cur = 2;
        if (m == 1) {
            return pre;
        }
        if (m == 2) {
            return cur;
        }
        int next = 0;
        for (long i = 3L; i <= m; i++) {
            next = a[cur - 1][pre - 1];
            pre = cur;
            cur = next;
        }
        return next;
    }

}

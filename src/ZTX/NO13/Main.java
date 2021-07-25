package ZTX.NO13;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/8 14:51
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] t = new int[n];
        int[] h = new int[n];

        int[] max = new int[n];
        int[] min = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = scanner.nextInt();
            h[i] = scanner.nextInt();
            int d = scanner.nextInt();
            max[i] = t[i] + d;
            min[i] = t[i] - d;
        }
        int[] result = getResult(n, max, h, min, t);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private static int[] getResult(int n, int[] max, int[] h, int[] min, int[] t) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int maxH = 0;
//            for (int j = i - 1; j >= 0; j--) {
//                if (h[j] > maxH) {
//                    if (t[j] >= borderL && t[j] <= borderR) {
//                        r++;
//                    }
//                    maxH = h[j];
//                }
//            }
//            maxH = 0;
            for (int j = i + 1; j < n; j++) {
                if (h[i] > maxH) {
                    if (t[i] >= min[j] && t[i] <= max[j]) {
                        res[j]++;
                    }
                }
                if (h[j] > maxH) {
                    if (t[j] >= min[i] && t[j] <= max[i]) {
                        res[i]++;
                    }
                    maxH = h[j];
                }
            }
        }
        return res;
    }

}

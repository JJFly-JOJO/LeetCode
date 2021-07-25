package ZTX.NO05;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/24 15:59
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        int t = scanner.nextInt();
        int result = getResult(s, t);
        System.out.println(result);
    }

    private static int getResult(int s, int t) {
        if (s == t) {
            return 0;
        }
        int count = 1;
        s++;
        if (s == t) {
            return count;
        }
        int gap = t - s;
        if (gap == 1) {
            return count + 2;
        }
        if (gap > 0) {
            int step = 0;
            int digit = 1;
            while (digit <= gap) {
                step++;
                digit = digit << 1;
            }
            digit = digit >>> 1;
            step--;
            return count + (gap - digit) * 2 + step * 2;
        } else {
            int step = 0;
            int digit = 1;
            int tempGap = -gap;
            while (digit < gap) {
                step++;
                digit = digit << 1;
            }
            return count + (digit - tempGap) * 2 + step * 2;
        }
    }


}

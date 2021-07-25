package ZTX.NO11;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/8 14:09
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int result = getResult(k, a, b);
        System.out.println(result);
    }

    private static int getResult(int k, int a, int b) {
        //预处理
        if (k == 0) {
            return 1;
        }
        int differ = a - b;
        if (k > 0 && differ <= 0 && k > a) {
            return -1;
        }
        if (k < 0 && differ >= 0) {
            return -1;
        }
        int res = 1;
        int pos = 0;
        while (true) {
            pos += a;
            if (Math.abs(pos) - Math.abs(k) >= 0) {
                break;
            }
            pos -= b;
            if (Math.abs(pos) - Math.abs(k) >= 0) {
                break;
            }
            res++;
        }
        return res;
    }

}

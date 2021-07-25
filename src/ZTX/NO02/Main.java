package ZTX.NO02;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/24 15:09
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < T; i++) {
            long n = scanner.nextLong();
            long result = getResult(n);
            System.out.println(result);
            scanner.nextLine();
        }

    }

    private static long getResult(long n) {
        long digit = 1L;
        long count = 0L;
        while (digit < n) {
            digit = digit << 1;
            count++;
        }
        return count + 1;
    }

}

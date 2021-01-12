package IPCContest.Trading;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/13 14:17
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = new int[3];
        for (int i = 0; i < 3; i++) {
            input[i] = scanner.nextInt();
        }
        System.out.println(String.format("%.2f", input[1] * 100.0 / input[2]));
    }

}

package ZTX.NO01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/24 14:53
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int h = scanner.nextInt();
        int u = scanner.nextInt();
        scanner.nextLine();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int result = getResult(h, u, arr);
        System.out.println(result);
    }

    private static int getResult(int h, int u, Integer[] arr) {
        int sum = h;
        int count = 0;
        Arrays.sort(arr, (o1, o2) -> o2 - o1);
        for (int n : arr) {
            if (sum >= u) {
                break;
            }
            sum += n;
            count++;
        }
        return count;
    }

}

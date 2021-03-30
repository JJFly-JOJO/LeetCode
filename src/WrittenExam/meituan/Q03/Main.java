package WrittenExam.meituan.Q03;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/20 16:33
 * @description 第一行三个整数 n, m, C，意义如题目描述。
 * <p>
 * 第二行 n 个整数，第 i 个整数为 ai。
 * <p>
 * 1 ≤ n, m ≤ 105, 1 ≤ C ≤ 50, 1 ≤ ai ≤ 104
 * <p>
 * 5 3 4
 * 5 8 3 10 7
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int C = scanner.nextInt();
        scanner.nextLine();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int res = getResult(arr, m, C);
        System.out.println(res);
    }

    private static int getResult(int[] arr, int m, int c) {
        int max = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int n : arr) {
            max = Math.max(n, max);
            minHeap.add(n);
        }
        while (minHeap.size() > m) {
            int v1 = minHeap.poll();
            int v2 = minHeap.poll();
            int v = v1 + v2;
            max = Math.max(v, max);
            minHeap.add(v);
        }
        double res = max / (double) c;
        return (int) Math.ceil(res);
    }

}

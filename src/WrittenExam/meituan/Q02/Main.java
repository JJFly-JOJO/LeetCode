package WrittenExam.meituan.Q02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/20 16:14
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int mod = scanner.nextInt();
        scanner.nextLine();
        int[] setA = new int[n];
        int[] setB = new int[n];
        for (int i = 0; i < n; i++) {
            setA[i] = scanner.nextInt();
        }
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            setB[i] = scanner.nextInt();
        }
        int res = getResult(setA, setB, mod);
        System.out.println(res);
    }

    private static int getResult(int[] setA, int[] setB, int mod) {
        HashMap<Integer, Integer> mapB = new HashMap<>();
        for (int n : setB) {
            if (!mapB.containsKey(n)) {
                mapB.put(n, 1);
            } else {
                mapB.put(n, mapB.get(n) + 1);
            }
        }
        int x = 0;
        for (; ; x++) {
            HashMap<Integer, Integer> tB = new HashMap<>(mapB);
            int[] t = Arrays.copyOf(setA, setA.length);
            HashMap<Integer, Integer> mem = new HashMap<>();
            for (int i = 0; i < t.length; i++) {
                if (mem.containsKey(t[i])) {
                    t[i] = mem.get(t[i]);
                } else {
                    int v = (t[i] + x) % mod;
                    mem.put(t[i], v);
                    t[i] = v;
                }
            }
            int count = setB.length;
            for (int n : t) {
                Integer c = tB.get(n);
                if (c == null || c == 0) {
                    break;
                } else {
                    tB.put(n, c - 1);
                    count--;
                }
            }
            if (count == 0) {
                return x;
            }
        }
    }

}

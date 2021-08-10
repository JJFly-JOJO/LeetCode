package Alibaba.NO01;

import java.util.*;

/*
7
2 3 4 2
1 3 4
2 3 4 2
2 3 4 5
1 3 1
2 6 4 5
2 6 12 6


* */
public class Main {

    private static Map<Integer, Integer> union = new HashMap<>();

    private static int find(int x) {
        if (union.get(x) != x) {
            union.put(x, find(union.get(x)));
        }
        return union.get(x);
    }

    private static void unionF(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }
        union.put(y, x);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            int t = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (t == 1) {
                if (!union.containsKey(a)) {
                    union.put(a, a);
                }
                if (!union.containsKey(b)) {
                    union.put(b, b);
                }
                unionF(a, b);
            } else {
                int c = scanner.nextInt();
                int modA = a % c;
                int modB = b % c;
                if (modA == modB) {
                    System.out.println("YES");
                } else {
                    if (union.containsKey(modA) && union.containsKey(modB) && find(modA) == find(modB)) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                }
            }
            scanner.nextLine();
        }
    }

}

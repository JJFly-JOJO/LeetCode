package ZTX.NO12;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/8 14:31
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String s = scanner.next();
        int result = getResult(n, s);
        System.out.println(result);
    }

    private static int getResult(int n, String s) {
        int count = 62;
        if (n > count) {
            return -1;
        }
        HashMap<Integer, Integer> cToIdx = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            cToIdx.put('a' + i, i);
        }
        for (int i = 0; i < 26; i++) {
            cToIdx.put('A' + i, i + 26);
        }
        for (int i = 0; i < 10; i++) {
            cToIdx.put('0' + i, i + 52);
        }
        boolean[] isVisited = new boolean[count];
        char[] chars = s.toCharArray();
        int res = 0;
        for (char c : chars) {
            int idx = cToIdx.get((int) c);
            while (isVisited[idx]) {
                res++;
                idx++;
                if (idx >= count) {
                    idx -= count;
                }
            }
            isVisited[idx] = true;
        }
        return res;
    }

}

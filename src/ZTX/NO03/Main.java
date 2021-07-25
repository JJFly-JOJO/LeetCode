package ZTX.NO03;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/24 15:22
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        ArrayDeque<Integer> stk1 = new ArrayDeque<>();
        ArrayDeque<Integer> stk2 = new ArrayDeque<>();
        ArrayDeque<Integer> stk3 = new ArrayDeque<>();
        ArrayDeque<Integer> stk4 = new ArrayDeque<>();
        ArrayDeque<Integer> stk5 = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            int val = scanner.nextInt();
            if (val == 1) {
                stk1.push(i);
            } else if (val == 2) {
                stk2.push(i);
            } else if (val == 3) {
                stk3.push(i);
            } else if (val == 4) {
                stk4.push(i);
            } else {
                stk5.push(i);
            }

            if (stk1.isEmpty() || stk2.isEmpty() || stk3.isEmpty() || stk4.isEmpty() || stk5.isEmpty()) {
                System.out.println(-1);
            } else {
                System.out.println(stk1.pop() + " " + stk2.pop() + " " + stk3.pop() + " " + stk4.pop() + " " + stk5.pop());
            }
        }
    }

}

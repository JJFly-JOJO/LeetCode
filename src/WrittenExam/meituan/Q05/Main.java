package WrittenExam.meituan.Q05;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/20 17:39
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m0 = scanner.nextInt();
        double w0 = scanner.nextDouble();
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            String[] strs = str.split(" ");
            if (strs[0].equals("A")) {
                int m = Integer.parseInt(strs[1]);
                int w = Integer.parseInt(strs[2]);
                stack.push(m);
                stack.push(w);
                double value = (double) m0 * w0 / 100.0 + (double) m * w / 100.0;
                m0 = m0 + m;
                w0 = value * 100 / m0;
            } else {
                if (!stack.isEmpty()) {
                    int w = stack.pop();
                    int m = stack.pop();
                    double value = (double) m0 * w0 / 100.0 - (double) m * w / 100.0;
                    m0 = m0 - m;
                    w0 = value * 100 / m0;
                }
            }
            System.out.println(m0 + " " + String.format("%.5f", w0));
        }

    }
}

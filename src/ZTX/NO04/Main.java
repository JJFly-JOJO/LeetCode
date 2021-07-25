package ZTX.NO04;

import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/24 15:32
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();
        boolean[] visited1 = new boolean[n1];
        boolean[] visited2 = new boolean[n2];
        LinkedHashSet<Integer> list1 = new LinkedHashSet<>();
        LinkedHashSet<Integer> list2 = new LinkedHashSet<>();
        int border = n1;
        for (int i = 0; i < q; i++) {
            int cur = scanner.nextInt();
            if (cur <= border && !visited1[cur - 1]) {
                visited1[cur - 1] = true;
                list2.add(cur);
            } else if (list1.contains(cur)) {
                list1.remove(cur);
                list2.add(cur);
            } else if (cur > border && !visited2[cur - border - 1]) {
                visited2[cur - border - 1] = true;
                list1.add(cur);
            } else if (list2.contains(cur)) {
                list2.remove(cur);
                list1.add(cur);
            }
        }
        for (int i = 0; i < n1; i++) {
            if (!visited1[i]) {
                System.out.print(i + 1 + " ");
            }
        }
        for (int i : list1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < n2; i++) {
            if (!visited2[i]) {
                System.out.print(i + border + 1 + " ");
            }
        }
        for (int i : list2) {
            System.out.print(i + " ");
        }
    }

}

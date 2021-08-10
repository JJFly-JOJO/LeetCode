package Alibaba.NO02;
/*
30 12 7
18 1 2021
* */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int d = scanner.nextInt();
        int m = scanner.nextInt();
        int w = scanner.nextInt();
        scanner.nextLine();
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        int k = scanner.nextInt();

        int oneYearDays = m * d;
        int days = (k - 1) * oneYearDays + (j - 1) * d + i;
        int mod = days % w;
        mod = (mod - 1 + w) % w;
        System.out.println((char) ('a' + mod));

    }

}

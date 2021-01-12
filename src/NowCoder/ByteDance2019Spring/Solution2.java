package NowCoder.ByteDance2019Spring;

import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/12 11:40
 * @description -----------
 * 4 3
 * 1 2 3 4
 * 可选方案 (1, 2, 3), (1, 2, 4), (1, 3, 4), (2, 3, 4)
 * 输出：
 * 一个数字，表示不同埋伏方案的数量。结果可能溢出，请对 99997867 取模
 */
public class Solution2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        scanner.nextLine();
        String[] strs = scanner.nextLine().split(" ");
        int[] cities = new int[n];
        for (int i = 0; i < n; i++) {
            cities[i] = Integer.parseInt(strs[i]);
        }
        System.out.println(getMethods(n, d, cities));
    }

    private static int getMethods(int n, int d, int[] cities) {
        long max = 99997867;
        long res = 0L;
        for (int i = 0; i < n; i++) {
            int index = binaryFind(cities[i] + d, cities);
            int t = index - i - 1;
            if (t < 1) {
                continue;
            }
            long tRes = (1L + t) * t / 2;
            res += tRes;
            //暴力解法
            /*for (int j = i + 2; j < n; j++) {
                if (cities[j] - cities[i] > d) {
                    break;
                }
                res += j - i - 1;
            }
            res = res % max;*/
        }
        return (int) (res % max);
    }

    private static int binaryFind(int target, int[] cities) {
        int l = 0;
        int r = cities.length - 1;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (cities[m] > target) {
                r = m;
            } else if (cities[m] < target) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return cities[l] <= target ? l : l - 1;
    }


}

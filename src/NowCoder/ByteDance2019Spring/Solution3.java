package NowCoder.ByteDance2019Spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/12 15:06
 * @description ------还有优化空间------
 * 1 1 1 2 2 2 6 6 6 7 7 7 9 9 可以组成1,2,6,7的4个刻子和9的雀头，可以和牌
 * 1 1 1 1 2 2 3 3 5 6 7 7 8 9 用1做雀头，组123,123,567,789的四个顺子，可以和牌
 * 1 1 1 2 2 2 3 3 3 5 6 7 7 9 无论用1 2 3 7哪个做雀头，都无法组成和牌的条件。
 */
public class Solution3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> l = new ArrayList<>();
        //hasNext会发生阻塞 可以用重载的方法利用pattern终止读取
        /*while (scanner.hasNext()) {
            l.add(scanner.nextInt());
        }*/

        //String[] s=scanner.nextLine().split(" ");
        //int[] p = new int[13];
        int[] count = new int[10];
        for (int i = 0; i < 13; i++) {
            //p[i] = scanner.nextInt();
            count[scanner.nextInt()]++;
        }
        List<Integer> res = getRes(count);
        if (res.size() == 0) {
            System.out.println("0");
        }
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    private static List<Integer> getRes(int[] count) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (count[i] == 4) {
                continue;
            }
            count[i]++;
            if (canWin(count)) {
                res.add(i);
            }
            count[i]--;
        }
        return res;
    }

    private static boolean canWin(int[] count) {
        for (int i = 1; i <= 9; i++) {
            //找到雀头
            if (count[i] >= 2) {
                count[i] -= 2;
                if (dfs(count)) {
                    count[i] += 2;
                    return true;
                }
                count[i] += 2;
            }
        }
        return false;
    }

    private static boolean dfs(int[] count) {
        int c = 0;
        for (int i = 1; i <= 9; i++) {
            if (count[i] == 0) {
                c++;
                continue;
            }
            if (count[i] >= 3) {
                count[i] -= 3;
                if (dfs(count)) {
                    count[i] += 3;
                    return true;
                }
                count[i] += 3;
            }
            if (i <= 7 && count[i + 1] > 0 && count[i + 2] > 0) {
                count[i]--;
                count[i + 1]--;
                count[i + 2]--;
                if (dfs(count)) {
                    count[i]++;
                    count[i + 1]++;
                    count[i + 2]++;
                    return true;
                }
                count[i]++;
                count[i + 1]++;
                count[i + 2]++;
            }
        }
        return c == 9;
    }

}

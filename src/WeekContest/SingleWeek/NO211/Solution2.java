package WeekContest.SingleWeek.NO211;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/18 18:06
 * @description ----------------------贝祖定理（最大公约数 ax+by=cz(x y的最大公约数是z)）---------
 * ----------------------暴力枚举-----------------
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().findLexSmallestString("31", 4, 1));
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    public String findLexSmallestString(String s, int a, int b) {
        String res = s;
        //!!!技巧------------------省去了我们拼接的操作
        StringBuilder ss = new StringBuilder(s + s);
        //gcd
        int sLen = s.length();
        int g = gcd(sLen, b);
        //外层循环 枚举移位的所有可能----------贝祖定理ax-by=cz(z为最大公约数)
        for (int i = 0; i < sLen; i += g) {
            //判断公约数是奇数还是偶数 奇数则每一个位置都可以增加a 偶数只能是奇数位置增加a
            boolean isOdd = (g & 1) == 1;
            //外层加a操作一共进行10次（0~9 9x10=90 又回到了原来的位数上）
            for (int n = 0; n <= 9; n++) {
                //注意每一次都要重置subC----------------这里我们直接new
                char[] subC = ss.substring(i, i + sLen).toCharArray();
                int add = n * a;
                //奇数位遍历
                for (int j = 1; j < sLen; j += 2) {
                    subC[j] = (char) ((subC[j] - '0' + add) % 10 + '0');
                }
                //偶数位遍历
                if (isOdd) {
                    for (int k = 0; k <= 9; k++) {
                        int addk = k * a;
                        char[] subCC = Arrays.copyOf(subC, subC.length);
                        for (int j = 0; j < sLen; j += 2) {
                            subCC[j] = (char) ((subCC[j] - '0' + addk) % 10 + '0');
                        }
                        String temp = new String(subCC);
                        if (res.compareTo(temp) > 0) {
                            res = temp;
                        }
                    }
                } else {
                    String temp = new String(subC);
                    if (res.compareTo(temp) > 0) {
                        res = temp;
                    }
                }
            }
        }
        return res;
    }

    public String findLexSmallestStringPromotion(String s, int a, int b) {
        String res = s;
        StringBuilder ss = new StringBuilder(s + s);
        int sLen = s.length();
        int g = gcd(sLen, b);
        for (int i = 0; i < sLen; i += g) {
            boolean isOdd = (g & 1) == 1;
            char[] subC = ss.substring(i, i + sLen).toCharArray();
            for (int n = 0; n <= 9; n++) {
                for (int j = 1; j < sLen; j += 2) {
                    subC[j] = (char) ((subC[j] - '0' + a) % 10 + '0');
                }
                //偶数位遍历
                if (isOdd) {
                    for (int k = 0; k <= 9; k++) {
                        for (int j = 0; j < sLen; j += 2) {
                            subC[j] = (char) ((subC[j] - '0' + a) % 10 + '0');
                        }
                        if (compare(res, subC) > 0) {
                            res = new String(subC);
                        }
                    }
                } else {
                    if (compare(res, subC) > 0) {
                        res = new String(subC);
                    }
                }
            }
        }
        return res;
    }

    private int compare(String s, char[] c) {
        int len1 = s.length();
        int len2 = c.length;
        char[] sC = s.toCharArray();
        int len = Math.min(len1, len2);
        int k = 0;
        while (k < len) {
            if (sC[k] != c[k]) {
                return sC[k] - c[k];
            }
            k++;
        }
        return len1 - len2;
    }

}

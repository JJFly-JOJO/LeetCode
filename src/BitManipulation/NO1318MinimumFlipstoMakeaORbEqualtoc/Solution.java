package BitManipulation.NO1318MinimumFlipstoMakeaORbEqualtoc;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/20 15:48
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minFlips(2, 6, 5));
    }

    public int minFlips(int a, int b, int c) {
        int orValue = a | b;
        int digit = 1 << 31;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            int v1 = digit & orValue;
            int v2 = digit & c;
            if (v1 != v2) {
                if (v1 == digit) {
                    int c1 = (a & digit) == digit ? 1 : 0;
                    int c2 = (b & digit) == digit ? 1 : 0;
                    count += c1 + c2;
                } else {
                    count++;
                }
            }
            digit = digit >>> 1;
        }
        return count;
    }

}

package Greedy.NO738MonotoneIncreasingDigits;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/15 19:47
 * @description
 */
public class Solution {

    public static void main(String[] args) {

    }

    public int monotoneIncreasingDigits(int N) {
        String n = String.valueOf(N);
        char[] chars = n.toCharArray();
        int l = chars.length;
        for (int i = 1; i < l; i++) {
            if (chars[i] < chars[i - 1]) {
                i--;
                while (i > 0 && --chars[i] < chars[i - 1]) {
                    i--;
                }
                if (i != 0) {
                    chars[i]++;
                }
                int sub = Integer.parseInt(new String(chars, 0, i + 1));
                return sub * (int) Math.pow(10, l - i - 1.0) - 1;
            }
        }
        return N;
    }

}

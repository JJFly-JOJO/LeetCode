package String.NO248StrobogrammaticNumberIII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/5 14:40
 * @description ----------模拟（暴力解法）不进行复杂的讨论 ---------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().strobogrammaticInRange("50","100"));
    }

    private int res = 0;

    private char[][] map = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public int strobogrammaticInRange(String low, String high) {
        int lo = low.length();
        int hi = high.length();
        for (int i = lo; i <= hi; i++) {
            char[] chars = new char[i];
            recursive(0, i - 1, chars, low, high);
        }
        return res;
    }

    private void recursive(int l, int r, char[] chars, String low, String high) {
        if (l > r) {
            String str = String.valueOf(chars);
            if (compare(str, low) && compare(high, str)) {
                res++;
            }
            return;
        }
        for (int i = 0; i < 5; i++) {
            chars[l] = map[i][0];
            chars[r] = map[i][1];
            if (l == 0 && i == 0 && l != r) {
                continue;
            }
            if (l == r && (i == 2 || i == 4)) {
                continue;
            }
            recursive(l + 1, r - 1, chars, low, high);
        }
    }

    private boolean compare(String str, String low) {
        if (str.length() > low.length()) {
            return true;
        } else if (str.length() < low.length()) {
            return false;
        } else {
            return str.compareTo(low) >= 0;
        }
    }

}

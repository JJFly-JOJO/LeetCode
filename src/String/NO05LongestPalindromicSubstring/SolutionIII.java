package String.NO05LongestPalindromicSubstring;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/24 9:15
 * @description -------------中心扩散方法 O(n2)复杂度（暴力枚举O(n3)）--------------
 */
public class SolutionIII {

    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        if (len <= 1) {
            return s;
        }
        String res = s.substring(0, 1);
        for (int i = 1; i < len; i++) {
            String s1 = centerSpread(i, i, chars);
            if (s1.length() > res.length()) {
                res = s1;
            }
            String s2 = centerSpread(i - 1, i, chars);
            if (s2.length() > res.length()) {
                res = s2;
            }
        }
        return res;
    }

    private String centerSpread(int l, int r, char[] chars) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            l--;
            r++;
        }
        return new String(chars, l + 1, r - l - 1);
    }

}

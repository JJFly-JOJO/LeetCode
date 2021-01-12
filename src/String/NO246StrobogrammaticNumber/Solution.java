package String.NO246StrobogrammaticNumber;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/29 14:44
 * @description
 */
public class Solution {

    public boolean isStrobogrammatic(String num) {
        char[] chars = num.toCharArray();
        int l = 0;
        int r = chars.length - 1;
        while (l < r) {
            if (!isSame(chars[l], chars[r])) {
                return false;
            }
            l++;
            r--;
        }
        return l != r || isSame(chars[l], chars[r]);
    }

    private boolean isSame(char c1, char c2) {
        if (c1 == c2 && (c1 == '0' || c1 == '1' || c1 == '8')) {
            return true;
        }
        return c1 == '6' && c2 == '9' || c1 == '9' && c2 == '6';
    }

}

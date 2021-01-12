package String.NO161OneEditDistance;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/28 15:23
 * @description
 */
public class Solution {

    public boolean isOneEditDistance(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (lenS == 0 && lenT == 0) {
            return false;
        }
        if (lenS == lenT) {
            return sameLength(s, t);
        } else {
            if (lenS > lenT) {
                String temp = s;
                s = t;
                t = temp;
            }
            return differLength(s, t);
        }
    }

    /**
     * s 长度小的字符串 t为长度大的字符串
     */
    private boolean differLength(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (lenT - lenS > 1) {
            return false;
        }
        char[] cS = s.toCharArray();
        char[] tS = t.toCharArray();
        //先找到第一个不同字符下标
        int i = 0;
        while (i < lenS && cS[i] == tS[i]) {
            i++;
        }
        //如果出现不同字符 则return false
        while (i < lenS && cS[i] == tS[i + 1]) {
            i++;
        }
        return i == lenS;
    }

    private boolean sameLength(String s, String t) {
        int len = s.length();
        char[] cS = s.toCharArray();
        char[] tS = t.toCharArray();
        int count = 0;
        for (int i = 0; i < len && count <= 2; i++) {
            if (cS[i] != tS[i]) {
                count++;
            }
        }
        //注意 两个字符串编辑距离必须为1 对于 ccc ccc这种不需要编辑的也是不可以的
        return count == 1;
    }

}

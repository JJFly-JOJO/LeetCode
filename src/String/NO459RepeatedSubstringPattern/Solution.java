package String.NO459RepeatedSubstringPattern;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/26 9:29
 * @description -------------KMP算法解决  在s+s串中如果匹配到s串就能够保证存在s=s's's's'...
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().repeatedSubstringPatternErro("abaababaab"));
    }

    public boolean repeatedSubstringPattern(String s) {
        return KMP(s + s, s) != s.length() * 2;
    }

    private int KMP(String s, String pattern) {
        int[] nextIndex = computeNext(pattern);
        char[] sC = s.toCharArray();
        char[] pC = pattern.toCharArray();
        //这里我们要修改匹配的位置 我们要从下标0开始匹配（保证错位）
        int index = 1;
        int ptr = 0;
        while (index < sC.length) {
            if (sC[index] == pC[ptr]) {
                index++;
                ptr++;
                if (ptr == pC.length) {
                    return index;
                }
            } else {
                ptr = nextIndex[ptr];
                if (ptr == -1) {
                    index++;
                    ptr = 0;
                }
            }
        }
        return index;
    }

    private int[] computeNext(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] nextIndex = new int[len + 1];
        //initial
        nextIndex[0] = -1;
        nextIndex[1] = 0;
        int i = 2;
        int ptr = 0;
        while (i < nextIndex.length) {
            if (chars[i - 1] == chars[ptr]) {
                nextIndex[i++] = ++ptr;
            } else if (ptr == 0) {
                nextIndex[i++] = ptr;
            } else {
                ptr = nextIndex[ptr];
            }
        }
        return nextIndex;
    }

    public boolean repeatedSubstringPatternErro(String s) {
        int[] nextIndex = computeNext(s);
        int i = s.length();
        int interval = i - nextIndex[i];
        if (interval == s.length()) {
            return false;
        }
        i = nextIndex[i];
        while (i > 0) {
            int t = i - nextIndex[i];
            if (t != interval) {
                return false;
            }
            i = nextIndex[i];
        }
        return true;
    }

}

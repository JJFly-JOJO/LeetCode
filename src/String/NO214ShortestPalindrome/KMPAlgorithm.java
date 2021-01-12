package String.NO214ShortestPalindrome;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/25 10:00
 * @description --------------关键理解nextIndex数组(模式串)记录的内容---------------
 * nextIndex记录的是当前下标对应的pattern字符不匹配时 前半部分次最长的公共子串的下一个字符下标（也就是我们要跳转比较的下一个字符下标）
 * 举例：s: aabaac  pattern此时匹配到了aabaaz 此时前半部最长的公共子串为aabaa 次公共子串aa 那么我们就将
 * pattern移动到次子串的下一个字符下标处：aab 与aabaac重新对比
 *
 * pattern：模式串
 * s：查询串
 */
public class KMPAlgorithm {

    public int KMP(String s, String pattern) {
        char[] sChar = s.toCharArray();
        int len = sChar.length;
        char[] pChar = pattern.toCharArray();
        int[] nextIndex = computeNextI(pattern);
        int i = 0;
        int j = 0;
        while (i < len) {
            if (sChar[i] != pChar[j]) {
                j = nextIndex[j];
                if (j == -1) {
                    i++;
                    j = 0;
                }
            } else {
                i++;
                j++;
                if (j == pChar.length) {
                    return i - pChar.length;
                }
            }
        }
        return -1;
    }

    private int[] computeNextI(String pattern) {
        int[] nextIndex = new int[pattern.length() + 1];
        //表示0下标字符不匹配时 前面已经不存在公共前缀
        nextIndex[0] = -1;
        nextIndex[1] = 0;
        for (int i = 2; i < nextIndex.length; i++) {
            for (int j = 1; j < i; j++) {
                if (pattern.substring(0, j).equals(pattern.substring(i - j, i))) {
                    nextIndex[i] = j;
                }
            }
        }
        return nextIndex;
    }

    private int[] computeNextII(String pattern) {
        char[] pChars = pattern.toCharArray();
        int[] nextIndex = new int[pChars.length];
        //initialize
        nextIndex[0] = -1;
        nextIndex[1] = 0;
        int i = 2;
        //不仅仅是pattern（移动窗口）的指针 同时也记录着前缀匹配的个数
        int ptr = 0;
        while (i < pChars.length) {
            if (pChars[i - 1] == pChars[ptr]) {
                nextIndex[i] = ptr + 1;
                i++;
                ptr++;
            } else if (ptr == 0) {
                nextIndex[i++] = 0;
            } else {
                //---------------------------难点------------------------
                ptr = nextIndex[ptr];
            }
        }
        return nextIndex;
    }

}

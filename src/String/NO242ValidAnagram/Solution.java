package String.NO242ValidAnagram;

import java.util.Arrays;

public class Solution {

    public boolean isAnagram(String s, String t) {
        int lengthS = s.length();
        int lengthT = t.length();
        if (lengthS != lengthT) {
            return false;
        }
        int[] mapOfT = new int[26];
        for (int i = 0; i < lengthT; i++) {
            int index = t.charAt(i) - 'a';
            mapOfT[index]++;
        }
        //滑动窗口判断
        for (int i = 0; i < lengthS; i++) {
            int index = s.charAt(i) - 'a';
            if (mapOfT[index] == 0) {
                return false;
            }
            mapOfT[index]--;
        }
        return true;
    }

    /**
     * 既然两个单词仅仅是字符位置不一样 而其字符以及个数都相同 那么他们的map[26]也一定一样 因此我们直接将两个
     * 字符串放入两个数组map中 对比数组是否相同即可
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagramForToWindow(String s, String t) {
        int lengthS = s.length();
        int lengthT = t.length();
        if (lengthS != lengthT) {
            return false;
        }
        int[] mapS = new int[26];
        int[] mapT = new int[26];
        for (int i = 0; i < lengthS; i++) {
            mapS[s.charAt(i) - 'a']++;
            mapT[t.charAt(i) - 'a']++;
        }
        /*for (int i = 0; i < 26; i++) {
            if (mapS[i] != mapT[i]) {
                return false;
            }
        }*/
        //直接用jdk的数组比较API
        if (Arrays.equals(mapS, mapT)) {
            return true;
        }
        return false;
    }
}

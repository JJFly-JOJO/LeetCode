package String.NO383RansomNote;

public class Solution {

    /**
     * 滑动窗口 但是题意是不需要顺序 只要找到有字母满足就可以
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int lengthR = ransomNote.length();
        int lengthM = magazine.length();
        if (lengthR > lengthM) {
            return false;
        }
        int slowPointer = 0;

        for (int i = 0; i < lengthM && slowPointer < lengthR; i++) {
            if (ransomNote.charAt(slowPointer) == magazine.charAt(i)) {
                slowPointer++;
            }
        }
        if (slowPointer < lengthR) {
            return false;
        }
        return true;
    }

    /**
     * hash  计数排序
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstructForHash(String ransomNote, String magazine) {
        int[] countSort = new int[26];
        int lengthM = magazine.length();
        for (int i = 0; i < lengthM; i++) {
            countSort[magazine.charAt(i) - 'a']++;
        }
        int lengthR = ransomNote.length();
        for (int i = 0; i < lengthR; i++) {
            if (--countSort[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计数排序和滑动窗口
     * 任然以ransomNote作为滑动窗口 但是为了加快查找 我们利用计数排序和索引的方法
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstructForHashAndWindow(String ransomNote, String magazine) {
        int[] countSort = new int[26];
        int lengthR = ransomNote.length();
        if (lengthR == 0) {
            return true;
        }
        for (int i = 0; i < lengthR; i++) {
            countSort[ransomNote.charAt(i) - 'a']++;
        }
        int lengthM = magazine.length();
        for (int i = 0; i < lengthM; i++) {
            int curIndex = magazine.charAt(i) - 'a';
            if (countSort[curIndex] > 0) {
                countSort[curIndex]--;
                if (--lengthR == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}

package WeekContest.SingleWeek.NO238;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/25 10:56
 * @description
 */
public class SolutionIII {

    public int longestBeautifulSubstring(String word) {
        int res = 0;
        char[] chars = word.toCharArray();
        int r = 0;
        while (r < chars.length) {
            if (chars[r] != 'a') {
                r++;
                continue;
            }
            int nextIdx = 0;
            int count = 0;
            int idx;
            while (r < chars.length && ((idx = charToIdx(chars[r])) == nextIdx || idx == nextIdx - 1)) {
                count++;
                r++;
                if (idx == nextIdx) {
                    nextIdx++;
                }
            }
            if (nextIdx == 5) {
                res = Math.max(res, count);
            }
        }
        return res;
    }

    private int charToIdx(char c) {
        if (c == 'a') {
            return 0;
        }
        if (c == 'e') {
            return 1;
        }
        if (c == 'i') {
            return 2;
        }
        if (c == 'o') {
            return 3;
        }
        return 4;
    }

}

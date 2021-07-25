package WeekContest.DoubleWeek.NO57;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/24 21:58
 * @description
 */
public class SolutionI {

    public boolean areOccurrencesEqual(String s) {
        int[] cnt = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            cnt[c - 'a']++;
        }
        int curCnt = -1;
        for (int c : cnt) {
            if (c != 0) {
                if (curCnt == -1) {
                    curCnt = c;
                }
                if (curCnt != c) {
                    return false;
                }
            }
        }
        return true;
    }

}

package WeekContest.SingleWeek.NO247;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/27 11:01
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().wonderfulSubstrings("aba"));
    }

    public long wonderfulSubstrings(String word) {
        char[] wd = word.toCharArray();
        int len = wd.length;
        int[][] subNum = new int[len + 1][10];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 10; j++) {
                if (wd[i] == j + 'a') {
                    subNum[i + 1][j] = subNum[i][j] + 1;
                } else {
                    subNum[i + 1][j] = subNum[i][j];
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (judge(i, j, subNum)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean judge(int start, int end, int[][] subNum) {
        int oddCnt = 0;
        for (int i = 0; i < 10; i++) {
            int cnt = subNum[end + 1][i] - subNum[start][i];
            if ((cnt & 1) == 1) {
                oddCnt++;
            }
            if (oddCnt > 1) {
                return false;
            }
        }
        return true;
    }

}

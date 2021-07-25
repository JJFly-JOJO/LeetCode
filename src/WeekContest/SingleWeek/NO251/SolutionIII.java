package WeekContest.SingleWeek.NO251;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/25 10:48
 * @description
 */
public class SolutionIII {

    private int cpatSum = 0;

    private int e = 0;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        e = students.length;
        int[][] sToMMatrix = new int[e][e];
        for (int i = 0; i < e; i++) {
            for (int j = 0; j < e; j++) {
                sToMMatrix[i][j] = cntMatch(students[i], mentors[j]);
            }
        }
        boolean[] isVisited = new boolean[e];
        for (int i = 0; i < e; i++) {
            int sum = sToMMatrix[0][i];
            isVisited[i] = true;
            backtracking(sum, sToMMatrix, isVisited, 1);
            isVisited[i] = false;
        }
        return cpatSum;
    }

    private void backtracking(int sum, int[][] sToMMatrix, boolean[] isVisited, int level) {
        if (level == e) {
            if (sum > cpatSum) {
                cpatSum = sum;
            }
            return;
        }
        for (int i = 0; i < e; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                backtracking(sum + sToMMatrix[level][i], sToMMatrix, isVisited, level + 1);
                isVisited[i] = false;
            }
        }
    }

    private int cntMatch(int[] student, int[] mentor) {
        int cnt = 0;
        for (int i = 0; i < student.length; i++) {
            if (student[i] == mentor[i]) {
                cnt++;
            }
        }
        return cnt;
    }

}

package WeekContest.DoubleWeek.NO37;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/17 23:27
 * @description 输入：n = 4, k = 2
 * 输出：5
 * 解释：
 * 如图所示，两个线段分别用红色和蓝色标出。
 * 上图展示了 5 种不同的方案 {(0,2),(2,3)}，{(0,1),(1,3)}，{(0,1),(2,3)}，{(1,2),(2,3)}，{(0,1),(1,2)} 。
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(new Solution3().numberOfSets(48, 12));
    }

    public int numberOfSets(int n, int k) {
        long[][] memory = new long[n][k + 1];
        return (int) (recursive(0, n - 1, k, memory) % 1000000007);
    }

    private long recursive(int left, int right, int k, long[][] memory) {
        if (right - left < k) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        long sum = 0;
        for (int j = left + 1; j <= right; j++) {
            int lineCount = k - 1;
            if (memory[j][lineCount] <= 0) {
                memory[j][lineCount] = recursive(j, right, lineCount, memory);
                if (memory[j][lineCount] == 0) {
                    break;
                }
            }
            long val = (j - left) * memory[j][lineCount];
            sum += val >= 1000000007 ? val % 1000000007 : val;
        }
        return sum;
    }
}

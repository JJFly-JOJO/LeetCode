package WeekContest.DoubleWeek.NO43;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/9 23:29
 * @description ---------------回溯算法-----------
 * 思路：注意这里的回溯 如果从最大数n到1依次放入到数组中，如果得到一个可行解那么就返回 那么得到的结果可能不是字典序最大的结果
 * 例如：n=5 错误答案=[5,2,4,2,3,5,4,3,1] 正确答案=[5,3,1,4,3,5,2,4,2]
 * 错误原因：如果我们的回溯思路是 当前元素造成后面元素无法全部放入，那么当前元素向后挪动到下一个空位，但是注意到我们要求字典序
 * 最大，那么我们是要关注开头位的数越大越好
 *
 * 因此正确的回溯思路是从高位到低位回溯，高位数从最大数n开始到最小数1枚举
 */
public class SolutionIII {

    private boolean[] isVisited;
    private int N;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SolutionIII().constructDistancedSequence(5)));
    }

    public int[] constructDistancedSequence(int n) {
        int[] res = new int[2 * n - 1];
        isVisited = new boolean[n + 1];
        N = n;
        backTracking(0, res);
        return res;
    }

    private boolean backTracking(int index, int[] res) {
        if (index >= res.length) {
            return true;
        }
        if (res[index] != 0) {
            return backTracking(index + 1, res);
        }
        for (int i = N; i > 1; i--) {
            if (!isVisited[i] && index + i < res.length && res[index + i] == 0) {
                isVisited[i] = true;
                res[index] = i;
                res[index + i] = i;
                if (backTracking(index + 1, res)) {
                    return true;
                }
                res[index] = 0;
                res[index + i] = 0;
                isVisited[i] = false;
            }
        }
        if (!isVisited[1]) {
            res[index] = 1;
            isVisited[1] = true;
            if (backTracking(index + 1, res)) {
                return true;
            }
            isVisited[1] = false;
            res[index] = 0;
        }
        return false;
    }

}

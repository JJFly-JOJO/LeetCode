package WeekContest.DoubleWeek.NO57;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/24 23:28
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().splitPainting(new int[][]{
                {1, 4, 5}, {4, 7, 7}, {1, 7, 9}}));
    }

    public List<List<Long>> splitPainting(int[][] segments) {
        int max = 0;
        for (int[] s : segments) {
            max = Math.max(max, s[1]);
        }
        long[] differArray = new long[max + 2];
        //增加一个边界记录
        Set<Integer> leftBoundSet = new HashSet<>();
        for (int[] s : segments) {
            differArray[s[0]] += s[2];
            differArray[s[1]] -= s[2];
            leftBoundSet.add(s[0]);
        }
        List<List<Long>> ans = new ArrayList<>();
        List<Long> paint = new ArrayList<>();
        long preSum = 0L;
        long sum = 0L;
        for (int i = 1; i < differArray.length; i++) {
            sum += differArray[i];
            if (sum != preSum || leftBoundSet.contains(i)) {
                paint.add((long) i);
                if (paint.size() == 2) {
                    paint.add(preSum);
                    ans.add(new ArrayList<>(paint));
                    paint.clear();
                    if (sum != 0) {
                        paint.add((long) i);
                    }
                }
                preSum = sum;
            }
        }
        return ans;
    }

}

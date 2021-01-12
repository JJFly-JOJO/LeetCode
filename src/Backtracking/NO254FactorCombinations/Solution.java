package Backtracking.NO254FactorCombinations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/30 13:58
 * @description
 */
public class Solution {

    /**
     * --------------暴力回溯 + 类似NO204中判断质数for循环加快的方法：sqrt 来进行剪枝----------
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        //剪枝
        int half = (int) Math.sqrt(n);
        List<Integer> path = new ArrayList<>();
        for (int i = 2; i <= half; i++) {
            if (n % i == 0) {
                path.add(i);
                dfs(i, n / i, path, res);
                path.remove(path.size() - 1);
            }
        }
        return res;
    }

    private void dfs(int cur, int curN, List<Integer> path, List<List<Integer>> res) {
        if (curN == 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        int half = (int) Math.sqrt(curN);
        for (int i = cur; i <= half; i++) {
            if (curN % i == 0) {
                path.add(i);
                dfs(i, curN / i, path, res);
                path.remove(path.size() - 1);
            }
        }
        //易错点！---------------当前值可以不需要因式分解！！！
        path.add(curN);
        res.add(new ArrayList<>(path));
        path.remove(path.size() - 1);
    }

}

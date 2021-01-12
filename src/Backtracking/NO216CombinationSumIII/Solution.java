package Backtracking.NO216CombinationSumIII;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        //当前层为1
        int curLevel = 1;
        int curSum = 0;
        List<Integer> path = new ArrayList<>();
        if (curLevel < k) {
            for (int i = 1; i <= 9; i++) {
                curSum = i;
                if (curSum >= n) {
                    continue;
                }
                path.add(i);
                BackTracking(i + 1, n, curLevel + 1, k, curSum, path, res);
                path.remove(path.size() - 1);
            }
        } else {
            if (n > 9) {
                return res;
            }
            res.add(new ArrayList<Integer>() {
                {
                    add(n);
                }
            });
            return res;
        }
        return res;
    }

    private void BackTracking(int begin, int n,
                              int curLevel, int k,
                              int curSum, List<Integer> path,
                              List<List<Integer>> res) {
        if (curLevel < k) {
            for (int i = begin; i <= 9; i++) {
                int tempNum = curSum;
                tempNum += i;
                if (tempNum >= n) {
                    return;
                }
                path.add(i);
                BackTracking(i + 1, n, curLevel + 1, k, tempNum, path, res);
                path.remove(path.size() - 1);
            }
        } else {
            int needInt = n - curSum;
            //<begin 排除(n=7) [1,2,4]和[1,4,2]重复的情况
            if (needInt > n || needInt < begin) {
                return;
            }
            path.add(needInt);
            res.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
        }
        return;
    }
}

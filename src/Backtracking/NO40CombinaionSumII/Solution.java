package Backtracking.NO40CombinaionSumII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //先升序排序
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            //当前层遍历 如果当前元素与前一个元素相同 那么直接跳过 因为前一个元素得到的所有结果肯定包含了当前元素得到的所有结果
            //是一个包含关系
            if (i != 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int curSum = candidates[i];
            if (curSum > target) {
                break;
            }
            path.add(candidates[i]);
            if (curSum == target) {
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                continue;
            }
            BackTracking(i + 1, candidates, target, curSum, path, res);
            path.remove(path.size() - 1);
        }
        return res;
    }

    private void BackTracking(int begin, int[] candidates,
                              int target, int curSum,
                              List<Integer> path, List<List<Integer>> res) {
        for (int i = begin; i < candidates.length; i++) {
            if (i != begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            int tempSum = curSum;
            tempSum += candidates[i];
            if (tempSum > target) {
                return;
            }
            path.add(candidates[i]);
            if (tempSum == target) {
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                continue;
            }
            BackTracking(i + 1, candidates, target, tempSum, path, res);
            path.remove(path.size() - 1);
        }
    }

}

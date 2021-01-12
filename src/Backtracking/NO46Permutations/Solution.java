package Backtracking.NO46Permutations;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        int level = 1;
        int[] isVisited = new int[nums.length];
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            isVisited[i] = 1;
            path.add(nums[i]);
            if (level == length) {
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                continue;
            } else {
                BackTracing(nums, isVisited, level + 1, path, length, res);
            }
            path.remove(path.size() - 1);
            isVisited[i] = 0;
        }
        return res;
    }

    private void BackTracing(int[] nums, int[] isVisited,
                             int curLevel, List<Integer> path,
                             int length, List<List<Integer>> res) {
        for (int i = 0; i < length; i++) {
            if (isVisited[i] == 1) {
                continue;
            }
            isVisited[i] = 1;
            path.add(nums[i]);
            if (curLevel == length) {
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                isVisited[i] = 0;
                return;
            } else {
                BackTracing(nums, isVisited, curLevel + 1, path, length, res);
            }
            path.remove(path.size() - 1);
            isVisited[i] = 0;
        }
    }

}

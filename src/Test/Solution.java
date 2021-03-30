package Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/15 15:52
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        HashSet<Integer> isVisited = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> path = new ArrayList<>();
            backtracking(i, path, nums, res, isVisited);
        }
        return res;
    }

    private void backtracking(int index, List<Integer> path, int[] nums, List<List<Integer>> res, HashSet<Integer> isVisited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (isVisited.contains(nums[index])) {
            return;
        }
        isVisited.add(nums[index]);
        path.add(nums[index]);
        for (int i = 0; i < nums.length; i++) {
            backtracking(i, path, nums, res, isVisited);
        }
        path.remove(path.size() - 1);
        isVisited.remove(nums[index]);
    }

}

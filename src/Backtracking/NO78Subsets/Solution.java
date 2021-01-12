package Backtracking.NO78Subsets;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    //回溯算法 向下执行 不满足条件就回退
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //result.add(new ArrayList<>());
        backTracking(0, nums, result, new ArrayList<Integer>());
        return result;
    }

    private void backTracking(int i, int[] nums, List<List<Integer>> result, ArrayList<Integer> temp) {
        //temp.add(nums[i]);
        result.add(new ArrayList<>(temp));//调用拷贝构造 把当前temp拷贝一份加入结果集
        for (int j = i; j < nums.length; j++) {
            temp.add(nums[j]);
            backTracking(j + 1, nums, result, temp);
            temp.remove(temp.size() - 1);
        }
        //temp.remove(temp.size() - 1);
    }
}

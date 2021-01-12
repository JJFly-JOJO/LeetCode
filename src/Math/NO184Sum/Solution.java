package Math.NO184Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/2 10:56
 * @description --------------暴力回溯 利用max<target min>target进行一定的剪枝----------------
 */
public class Solution {

    public static void main(String[] args) {
        List<List<Integer>> res = new Solution().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int last = nums.length - 4;
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i <= last; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //剪枝
            int max = nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3];
            if (max < target) {
                continue;
            }
            int min = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min > target) {
                break;
            }
            path.add(nums[i]);
            backtracing(i + 1, 1, nums[i], nums, target, path, res);
            path.remove(path.size() - 1);
        }
        return res;
    }

    private void backtracing(int index, int level, int sum, int[] nums, int target, List<Integer> path, List<List<Integer>> res) {
        if (level == 4) {
            if (sum == target) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        int last = nums.length - (4 - level);
        for (int i = index; i <= last; i++) {
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            //剪枝
            int v = 0;
            for (int j = 1; j <= 3 - level; j++) {
                v += nums[nums.length - j];
            }
            if (level != 3) {
                int max = sum + nums[i] + v;
                if (max < target) {
                    continue;
                }
            }
            v = 0;
            for (int j = 1; j <= 3 - level; j++) {
                v += nums[i + j];
            }
            if (level != 3) {
                int min = sum + nums[i] + v;
                if (min > target) {
                    break;
                }
            }
            path.add(nums[i]);
            backtracing(i + 1, level + 1, sum + nums[i], nums, target, path, res);
            path.remove(path.size() - 1);
        }
    }

}

package Math.NO184Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/3 10:12
 * @description --------------迭代方法 迭代两层 第三层用双指针
 */
public class SolutionII {

    public static void main(String[] args) {
        List<List<Integer>> res = new SolutionII().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        for (int i = 0; i < len - 3; i++) {
            //去重
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                //去重
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                //三数之和思想 剪枝
                int max = nums[i] + nums[j] + nums[len - 1] + nums[len - 2];
                if (max < target) {
                    continue;
                }
                int min = nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (min > target) {
                    break;
                }
                int t = target - nums[i] - nums[j];
                int l = j + 1;
                int r = len - 1;
                while (l < r) {
                    int v = nums[l] + nums[r];
                    if (v == t) {
                        /*List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        res.add(list);*/
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                        r--;
                        while (l < r && nums[l] == nums[l - 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r + 1]) {
                            r--;
                        }

                    } else if (v < t) {
                        l++;
                        //可以优化 跳过重复元素
                    } else {
                        r--;
                    }
                }
            }
        }
        return res;
    }

}

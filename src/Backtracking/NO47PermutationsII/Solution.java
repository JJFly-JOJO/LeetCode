package Backtracking.NO47PermutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/27 19:45
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        for (List<Integer> temp : new Solution().permuteUnique(nums)) {
            System.out.println(temp);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //先排序 以便跳过重复元素
        Arrays.sort(nums);
        //遍历集合 这里用下标作为遍历集合
        int length = nums.length;
        if (length == 1) {
            List<Integer> tempRes = new ArrayList<>();
            tempRes.add(nums[0]);
            res.add(tempRes);
            return res;
        }
        boolean[] isVisited = new boolean[length];
        List<Integer> tempRes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            //当前层重复元素是没有必要选择的
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            tempRes.add(nums[i]);
            isVisited[i] = true;
            dfs(nums, isVisited, tempRes, res);
            isVisited[i] = false;
            tempRes.remove(tempRes.size() - 1);
        }
        return res;
    }

    private void dfs(int[] nums, boolean[] isVisited, List<Integer> tempRes, List<List<Integer>> res) {
        int length = nums.length;
        if (tempRes.size() == length) {
            res.add(new ArrayList<>(tempRes));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (isVisited[i]) {
                continue;
            }
            //当前层重复元素没有必要选择 但是上一层选择到的相同元素 当前层是可以选择上一层往后一个 同样的元素的
            //该判断得到的最终形式如下：
            //  1  当前层之后的1全部跳过 下面同理
            //   \
            //    1
            //     \
            //      1
            //       \
            //        1
            if (i != 0 && !isVisited[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }
            tempRes.add(nums[i]);
            isVisited[i] = true;
            dfs(nums, isVisited, tempRes, res);
            isVisited[i] = false;
            tempRes.remove(tempRes.size() - 1);
        }
    }
}

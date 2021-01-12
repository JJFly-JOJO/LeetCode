package Math.NO153Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/16 16:23
 * @description -------------------------双指针思路-------------------
 * 思路：
 * 首先对数组进行排序处理
 * 外层遍历数组 确定为第一个元素 另外两个元素从两端向中间走 因为左端值是最小 右端值是最大
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().threeSum(new int[]{-2, 0, 0, 2, 2}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        if (length < 3) {
            return res;
        }
        int last = length - 2;
        for (int i = 0; i < last; i++) {
            //跳过当前值与前一个值相等的情况
            while (i > 0 && i < last && nums[i] == nums[i - 1]) {
                i++;
            }
            //特判
            if (nums[i] > 0 || i == last) {
                break;
            }
            //双指针遍历
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    //排除重复的情况 例如:[0,0,0,0,0]
                    while (left < right && nums[left] == nums[left - 1] && nums[right] == nums[right + 1]) {
                        left++;
                        right--;
                    }
                } else if (sum > 0) {
                    right--;
                } else {
                    //sum<0
                    left++;
                }
            }
        }
        return res;
    }

}

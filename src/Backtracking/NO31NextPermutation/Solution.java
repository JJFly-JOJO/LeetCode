package Backtracking.NO31NextPermutation;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/28 10:13
 * 描述: 字典序下一个最大
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class Solution {

    /**
     * 单增区间与单减区间
     *---------------------优化 由于我们已经严格满足了当找到maxIndex时 右侧严格单调递减
     * 因此交换两个元素之后 我们可以直接反转后面的数组即可（因为我们是从maxIndex开始知道单间区间结束 也就是数组最后一个元素）
     * 不过实际上 针对降序数组排序成升序数组调用sort（见源码） 与直接反转 复杂度相同
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return;
        }
        int indexOfMax = 0;
        for (int i = 1; i < length; i++) {
            if (nums[i] > nums[i - 1]) {
                indexOfMax = i;
            }
        }
        //特判
        if (indexOfMax == 0) {
            //这里已经保证了单调递减 因此我们不需要排序sort 直接reverse即可
            //注意数组的反转reverse函数需要自己实现
            Arrays.sort(nums);
            return;
        }
        int indexBeforeMax = indexOfMax - 1;
        int changeIndex = 0;
        for (int i = indexOfMax + 1; i < length; i++) {
            if (nums[i] <= nums[indexBeforeMax]) {
                break;
            }
            changeIndex = i;
        }
        if (changeIndex == 0) {
            int temp = nums[indexOfMax];
            nums[indexOfMax] = nums[indexBeforeMax];
            nums[indexBeforeMax] = temp;
        } else {
            int temp = nums[changeIndex];
            nums[changeIndex] = nums[indexBeforeMax];
            nums[indexBeforeMax] = temp;
        }
        Arrays.sort(nums, indexOfMax, length);
        return;
    }

}

package Array.NO287FindtheDuplicateNumber;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/9 15:48
 * 给定一个包含 n + 1 个整数的数组 nums 数字都在 1 到 n 之间（包括 1 和 n）
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * <p>
 * ---------------------回顾原地hash的方法 利用好数值范围是在数组下标范围内 数组值指向下标 下标对应的值又指向另一个下标-----------------
 * ---------------------二份查找-----------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findDuplicate(new int[]{1, 4, 4, 2, 4}));
    }

    public int findDuplicate(int[] nums) {
        int length = nums.length;
        //注意这里的二分边界是对值进行二分 不是对数组下标
        int left = 1;
        int right = length - 1;
        while (left < right) {
            //---------------------------!!!无符号右移 不会出现溢出情况
            int mid = (left + right) >>> 1;
            int count = 0;
            for (int temp : nums) {
                if (temp <= mid && temp >= left) {
                    count++;
                }
            }
            if (count > (mid - left + 1)) {
                //重复值出现在左侧
                right = mid;
            } else {
                //重复值出现在右侧
                left = mid + 1;
            }
        }
        return left;
    }

}

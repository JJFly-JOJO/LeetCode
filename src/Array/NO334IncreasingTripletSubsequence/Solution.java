package Array.NO334IncreasingTripletSubsequence;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/6 11:47
 * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: true
 * <p>
 * 输入: [5,1,4,3,3,5,2,1]------------>注意子序列不要求顺序
 * 输出: true
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().increasingTriplet(new int[]{5, 1, 5, 5, 2, 5, 4}));
    }

    public boolean increasingTripletErro(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return false;
        }
        int slow = 0;
        int fast = 1;
        while (fast < length) {
            if (nums[fast] <= nums[fast - 1]) {
                if (fast - slow >= 3) {
                    return true;
                }
                slow = fast;
                fast = slow;
            }
            fast++;
        }
        return length - slow >= 3;
    }

    /**
     * 状态机思想！！！！！！！！！！
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return false;
        }
        //当前元素可能的状态有三种
        // 第一种：比递增子序的第一个元素值还小
        //第二种：比递增子序的第一个元素值大 比递增子序的第二个元素值小
        //第三种：比递增子序的第二个元素值大 比递增子序的第三个元素值小
        int[] status = new int[3];
        status[0] = Integer.MAX_VALUE;
        status[1] = Integer.MAX_VALUE;
        status[2] = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            //根据满足的情况 更新状态
            if (nums[i] <= status[0]) {
                status[0] = nums[i];
            } else if (nums[i] <= status[1]) {
                status[1] = nums[i];
            } else if (nums[i] <= status[2]) {
                return true;
            }
        }
        return false;
    }

}

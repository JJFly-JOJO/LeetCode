package Array.NO53MaximumSubarray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/10 21:24
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArrayForDividing(new int[]{-2, 1, -3}));
    }

    /**
     * ------------------方法一 动态规划------------
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int maxSum = Integer.MIN_VALUE;
        //分别记录起始下标 以及当前子串长度
        int[] dp = new int[length];
        //initialize
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
            if (nums[i] > maxSum) {
                maxSum = nums[i];
            }
        }
        for (int i = 2; i <= length; i++) {
            int lastIndex = length - i;
            //j表示下标
            for (int j = 0; j <= lastIndex; j++) {
                //注意观察这里只有i下标在变化 因此可以状态压缩
                //dp[j][i] = dp[j][i - 1] + nums[j + i - 1];
                dp[j] = dp[j] + nums[j + i - 1];
                if (dp[j] > maxSum) {
                    maxSum = dp[j];
                }
            }
        }
        return maxSum;
    }

    /**
     * -------------方法二 贪心算法 一次遍历 ----------
     *
     * @param nums
     * @return
     */
    public int maxSubArrayForGreedy(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        //int length = nums.length;
        for (int num : nums) {
            sum += num;
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    /**
     * --------------方法三 分治方法--- 线段树的区间合并思想---------
     *1.区别动态规划 回溯算法 分治方法 分治方法：相当于把一个问题二分下去 一直拆分成最小子问题 在递归返回时来合并子问题到上层问题
     * 带有记忆的回溯：下层的子问题可能会在上上层被用到 回溯算法应该与DFS联系起来
     * 动态规划：同样是记忆子问题 但是一个问题的组合可以是由不同规模的子问题组合而成的 而分治 一个问题就是固定为二分后的子问题组成
     *
     * 2.该题体会子问题合并时存在哪些情况 对于存在的情况 我们需要用一个类对象来维护这些值
     *
     * 3.分治算法中当前层的子问题供上一层使用结束后 是一定不会再被用到的 因此不需要储存下来 只需要返回给上一层后自行在栈区
     * 被销毁即可（GC）
     *
     * 4.体会这里的问题由子问题组成的情况：当前问题的解可能等于子问题的解 也可能等于子问题组合后的解
     * @param nums
     * @return
     */
    public int maxSubArrayForDividing(int[] nums) {
        return divideAndGetStatus(nums, 0, nums.length - 1).mSum;
    }

    private Status divideAndGetStatus(int[] nums, int left, int right) {
        if (left == right) {
            //如果分治到最小子问题 也就是一个元素 那么该子区间的4个值都是该元素值本身
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }
        //mid=(left+right)>>1(有符号右移可以解决溢出问题) 会向下取整 因此右区间用mid+1 而不是左区间用mid-1 会出现mid-1=left-1的情况
        Status leftS = divideAndGetStatus(nums, left, (left + right) >> 1);
        //-----------易错点！！！！！！！((left + right) >> 1) + 1注意加减乘除的运算优先级是高于位移运算符的
        Status rightS = divideAndGetStatus(nums, ((left + right) >> 1) + 1, right);
        //当前区间Status赋值
        int i = leftS.iSum + rightS.iSum;
        int l = Math.max(leftS.iSum + rightS.lSum, leftS.lSum);
        int r = Math.max(leftS.rSum + rightS.iSum, rightS.rSum);
        int m = Math.max(Math.max(leftS.mSum, rightS.mSum), leftS.rSum + rightS.lSum);
        return new Status(i, l, r, m);
    }


    /**
     *
     */
    public class Status {
        /**
         * 用一个类来维护区间的信息
         * iSum 当前区间元素和
         * lSum 当前区间左端开始的最大和
         * rSum 当前区间右端开始的最大和
         * mSum 当前区间的最大连续子区间和
         */
        public int iSum;
        public int lSum;
        public int rSum;
        public int mSum;

        public Status() {
        }

        public Status(int i, int l, int r, int m) {
            iSum = i;
            lSum = l;
            rSum = r;
            mSum = m;
        }
    }

}

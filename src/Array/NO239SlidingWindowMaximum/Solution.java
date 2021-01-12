package Array.NO239SlidingWindowMaximum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/20 16:06
 * @description 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 */
public class Solution {

    /**
     * -------------------该方法存在超时的情况 思考：我们需要完整的构建这颗完全二叉树（堆）吗？---------
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowErro(int[] nums, int k) {
        //priority queue
        Queue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        int length = nums.length;
        int[] res = new int[length - k + 1];
        int i = 0;
        //initialize
        int j = 0;
        for (; j < k; j++) {
            queue.add(nums[j]);
        }
        res[i++] = queue.peek();
        //sliding window
        int quitIndex = 0;
        for (; j < length; j++) {
            queue.remove(nums[quitIndex++]);
            queue.add(nums[j]);
            res[i++] = queue.peek();
        }
        return res;
    }

    /**
     * --------------改进：我们其实只需要维护当前窗口的最大值以及对应下标（下标是用来判断当前最大值是否还在窗口中）
     * 我们完全不需要维护其他比这个还小的数在这个堆中的构建----------或者说我们并不需要使用二叉树来维护这个堆 线性堆即可
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        //特判
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        int[] res = new int[n - k + 1];
        int r = 0;
        //存放下标 用于判断当前元素是否越界
        Deque<Integer> deque = new ArrayDeque<>();
        //initialize
        int i = 0;
        for (; i < k; i++) {
            cleanDeque(deque, k, i, nums);
            deque.add(i);
        }
        res[r++] = nums[deque.peekFirst()];
        //sliding window
        int rmoveIndex = 0;
        for (; i < n; i++) {
            cleanDeque(deque, rmoveIndex++, i, nums);
            deque.add(i);
            res[r++] = nums[deque.peekFirst()];
        }
        return res;
    }

    /**
     * 弹出窗口左边界元素 加入的元素 删除元素之前比该元素小的所有元素 因为后面的窗口肯定是先走过当前元素之前的前面的元素
     * 注意这里堆的体现 最左边的元素一定是最大的元素！！！！！！！！！！因为每次加入新元素如果都没有删去前面的元素 说明前面的元素是大于后面的元素的
     *
     * 理解这个线性堆：根据此窗口的特性 如果当前加入的元素是大于前面的某几个元素，我们完全不在需要维护前面几个元素了，直接从堆中去除，因为滑动
     * 窗口的特性------->类似队列，后进入的总是后弹出的
     *
     * @param deque
     * @param rI
     * @param aI
     * @param nums
     */
    private void cleanDeque(Deque<Integer> deque, int rI, int aI, int[] nums) {
        //判断头部元素是否是即将remove的元素
        if (!deque.isEmpty() && deque.peek() == rI) {
            deque.pollFirst();
        }
        //循环 remove比aI小的元素 注意这里的堆体现！！！我们从后向前比较 如果出现当前元素大于加入元素的情况 我们停止 因为堆保证了前面的元素更大
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[aI]) {
            deque.pollLast();
        }
    }
}

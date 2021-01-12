package StackPriorityQueue.NO373FindKPairswithSmallestSums;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/25 11:09
 * @description ----------最小堆解法--------
 * 思路：如果使用小顶堆，整体思路是循环k次，每次从小顶堆中弹出的元素就是当前第k'个元素
 * 我们如何保证每次弹出的元素一定就是第k'个元素呢？我们如果保证每次入堆的元素中一定包含或者堆中已经包含了下一个最小元素就可以了
 * 与大顶堆的区别：
 * 大顶堆思路：大顶堆只维护k的规模，遍历所有可能情况，如果当前遍历到的元素值在大顶堆内，那么就弹出大顶堆最大值，当前值加入大顶堆，
 * 大顶堆类似对所有元素进行排序，不过利用了堆的特性不再关心元素之间的大小关系，复杂度O(nlogk)
 * 小顶堆的优势：如果我们的元素规模很大，但是k很小呢？（仅仅只要前几个元素），那么使用大顶堆是不划算的，
 * 如果使用小顶堆，虽然每次都要不停地往堆中加若干可能是下一个最小值的元素，堆在不断扩大，插入操作耗时不断增加，但是如果k很小，我们
 * 只需要执行很少次数的O(loga、logb、logc...)操作，就找到最终结果。
 * 该题找潜在最小值的思路：当前堆弹出的是第i个最小值对(n1,n2)，那么他潜在的下一个最小值就是(n1,n2+1)和(n1+1,n2)【nums1 nums2都是已经排序好了的】
 * 但是注意到：(n1,n2+1)->(n1+1,n2+1) (n1+1,n2)->(n1+1,n2+1)会出现重复加入元素
 * 见LeetCode总结：图解
 * for (int i : nums1) {
 *             q.add(new int[]{i, nums2[0], 0});
 *         }
 */
public class SolutionII {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return res;
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> (o[0] + o[1])));
        /*for (int i : nums1) {
            q.add(new int[]{i, nums2[0], 0});
        }*/
        //更进一步的优化！！！！--------------------理解
        //我们只需要枚举到k规模即可，因为是绝对到不了k+1的
        for (int i = 0; i < nums1.length && i < k; i++) {
            q.add(new int[]{nums1[i], nums2[0], 0});
        }
        while (!q.isEmpty() && k > 0) {
            int[] t = q.poll();
            res.add(Arrays.asList(t[0], t[1]));
            if (t[2] + 1 < nums2.length) {
                q.add(new int[]{t[0], nums2[t[2] + 1], t[2] + 1});
            }
            k--;
        }
        return res;
    }

}

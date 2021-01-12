package Array.NO164MaximumGap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/9 21:32
 * --------------------基数排序 时间复杂度（O(kn)）k为最大位数---------------
 */
public class Solution3 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(3);
            }
        };
        list.clear();
        System.out.println(list.size());
        list.add(100);
        System.out.println(list);
        List<List<Integer>> listList = new ArrayList<>(10);
        listList.add(new ArrayList<>());
        listList.get(0).add(1);
        System.out.println(listList.get(0).get(0));
    }

    public int maximumGap(int[] nums) {
        int length = nums.length;
        //特判
        if (length < 2) {
            return 0;
        }
        //每一位的数只有0-9 一共十个数
        List<List<Integer>> numsAfterSort = new ArrayList<>(10);
        //initialize
        for (int i = 0; i < 10; i++) {
            numsAfterSort.add(new ArrayList<>());
        }
        //找到最大数 确定循环次数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        //记录除的10的位数
        int exp = 1;
        //nums[]数组用来保存更新后的数
        while (max > 0) {
            //清除当前基数排序所用的数组
            for (int i = 0; i < 10; i++) {
                numsAfterSort.get(i).clear();
            }
            for (int i = 0; i < length; i++) {
                numsAfterSort.get(nums[i] / exp % 10).add(nums[i]);
            }
            //当前迭代结束 当前位的顺序是正确的 更新nums
            int j = 0;
            for (int i = 0; i < numsAfterSort.size(); i++) {
                for (int temp : numsAfterSort.get(i)) {
                    nums[j++] = temp;
                }
            }
            exp *= 10;
            max /= 10;
        }

        //排序结束 找到最大间隙
        int maxGap = 0;
        for (int i = 1; i < length; i++) {
            int val = nums[i] - nums[i - 1];
            if (val > maxGap) {
                maxGap = val;
            }
        }
        return maxGap;
    }

    /**
     * -------------------基数排序的另一种写法 只需要一位数组即可 但是要经过两次变换
     * 第一次记录当前位出现的次数 在进行累加变化 记录当前位在数组的下标-------
     *
     * @param nums
     * @return
     */
    public int maximumGapForRadixSort(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }
        //一位数组 用来记录当前位出现的次数 以及下标
        //List<Integer> count=new ArrayList<>(length);
        int[] count = new int[10];
        int max = Integer.MIN_VALUE;
        for (int temp : nums) {
            if (temp > max) {
                max = temp;
            }
        }
        int exp = 1;
        //用来临时储存排序后的数组
        int[] temp = new int[length];
        while (max > 0) {
            //count每次都要重置0
            Arrays.fill(count, 0);
            //先排序 用count记录每个位出现的次数
            for (int num : nums) {
                count[num / exp % 10]++;
            }
            //count数组累加 记录下标（当前位的最大下标
            // 因此之后当前位的基数排序是要从下标大的位置开始（上一位的大数））
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            //注意要从大到小开始 因为count记录的是当前位的最大下标
            for (int i = length - 1; i >= 0; i--) {
                temp[--count[nums[i] / exp % 10]] = nums[i];
            }
            //临时temp赋值给nums
            System.arraycopy(temp, 0, nums, 0, length);
            max /= 10;
            exp *= 10;
        }

        //find maxGap
        int maxGap = 0;
        for (int i = 1; i < length; i++) {
            int val = nums[i] - nums[i - 1];
            if (val > maxGap) {
                maxGap = val;
            }
        }
        return maxGap;
    }
}

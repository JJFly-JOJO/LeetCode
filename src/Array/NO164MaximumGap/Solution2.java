package Array.NO164MaximumGap;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/7 20:52
 * -----------------桶排序-------------------
 */
public class Solution2 {

    public static void main(String[] args) {
        //[100,3,2,1]
        System.out.println(new Solution2().maximumGap(new int[]{3, 6, 9, 1}));
    }

    public int maximumGap(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }
        //找到max min
        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        //思考：确定间隙 如何保证我们找到的最大间隙是在桶之间找到 而不是在桶中的元素之间产生最大间隙？
        //-------鸽巢原理-------：我们只要保证至少存在一个空桶 那么这个最大间隙只有可能在桶之间产生 因为一个空桶相差了interval
        //而如果在桶中的元素 最大相差interval-1（interval是作为桶的容量）
        //注意 min max也是单独在一个桶中
        //我们排除了min和max元素 那么还有length-2个元素要放入桶中 我们要保证存在空桶 那么我们桶的数量至少要比
        //元素的数量（length-2）多1 而且考虑到桶的数量过多 带来的比较速率开销以及空间开销 我们桶的数量就选择为length-1
        //思考：如果这length-2个元素全部在一个桶中 是不是意味着最大gap存在于桶中而不是桶之间？
        //注意 不要忘记了min max是单独在一个桶中 min与max这两个桶至少有一个桶与length-2的桶相差至少一个空桶

        int gap = max - min;
        //注意此处 如果max与min大小相等 要单独讨论
        if (gap == 0) {
            return 0;
        }
        //gap之间的整数个数
        int count = gap + 1;
        //桶大小interval 向上取整 原因：由于元素为整数 实际的桶的范围划分为[min,min+interval) 右侧为开区间 因此向上取整是没有影响的
        int interval = (int) Math.ceil((double) count / (length - 2 + 1));
        //--------思考 这里能否用Math.floor？ floor可以让桶更多 是可以满足鸽巢原理的 但是要注意下面new bucket时要考虑桶数量是否超过了lengthD

        //桶的数量
        int lengthD1 = length - 1;
        //创建桶 初始化 桶中记录最大值与最小值 用于桶之间间隙确定
        int[] maxBucket = new int[lengthD1];
        int[] minBucket = new int[lengthD1];
        Arrays.fill(maxBucket, -1);
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        //将元素放入桶中
        for (int num : nums) {
            if (num == min || num == max) {
                continue;
            }
            //确定当前元素放入哪个桶中
            //思考！！！这里为什么是用当前值减去左边界值（我们n-2个数在[min,max]之间）
            //此区间存在的整数个数为count=max-min+1 那么分成n-2个间隙interval=count/(n-2)
            //当前元素减去左边界 如果大于等于一个间隙值 那么与interval取商得到的就是下一个区间
            //举例：[0,9] 分成2个区间（interval=5） 则[0,4] [5,9]
            int bucketIndex = (num - min) / interval;
            if (num > maxBucket[bucketIndex]) {
                maxBucket[bucketIndex] = num;
            }
            if (num < minBucket[bucketIndex]) {
                minBucket[bucketIndex] = num;
            }
        }
        //比较桶之间间隙 注意还有min桶与max桶 要单独讨论
        int maxGap = 0;
        int lastMax = min;
        for (int i = 0; i < lengthD1; i++) {
            if (minBucket[i] == Integer.MAX_VALUE) {
                continue;
            }
            int temp = minBucket[i] - lastMax;
            if (temp > maxGap) {
                maxGap = temp;
            }
            lastMax = maxBucket[i];
        }
        return Math.max(maxGap, max - lastMax);
    }

}

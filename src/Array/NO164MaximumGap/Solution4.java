package Array.NO164MaximumGap;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/10 14:28
 * -----------------桶排序改进 并不需要单独对max和min拿出桶来 只要保证鸽巢原理即可------------
 */
public class Solution4 {

    public static void main(String[] args) {
        System.out.println(new Solution4().maximumGap(new int[]{1, 10}));
    }

    public int maximumGap(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }
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
        int gap = max - min;
        if (gap == 0) {
            return 0;
        }
        int count = gap + 1;
        int interval = (int) Math.ceil((double) count / (length + 1));
        int lengthD1 = length + 1;
        int[] maxBucket = new int[lengthD1];
        int[] minBucket = new int[lengthD1];
        Arrays.fill(maxBucket, -1);
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        for (int num : nums) {
            int bucketIndex = (num - min) / interval;
            if (num > maxBucket[bucketIndex]) {
                maxBucket[bucketIndex] = num;
            }
            if (num < minBucket[bucketIndex]) {
                minBucket[bucketIndex] = num;
            }
        }
        int maxGap = 0;
        int lastMax = maxBucket[0];
        for (int i = 1; i < lengthD1; i++) {
            if (minBucket[i] == Integer.MAX_VALUE) {
                continue;
            }
            int temp = minBucket[i] - lastMax;
            if (temp > maxGap) {
                maxGap = temp;
            }
            lastMax = maxBucket[i];
        }
        return maxGap;
    }

}

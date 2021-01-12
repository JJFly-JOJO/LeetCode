package Array.NO04MedianofTwoSortedArrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/11 22:57
 * ---------------------------方法一 用长度小的数组往长度大的数组插入 看插入的下标与中位数下标比较----------------------
 * 该方法时间复杂度最坏的情况为：长度小的数据全部都在中位数下标的左侧 此时的时间复杂度为O(mlog(n))
 */
public class Solution {

    public static void main(String[] args) {
        long min = Integer.MIN_VALUE + 1L;
        //System.out.println(min / 2);
        //System.out.println((Integer.MIN_VALUE - 1) >> 1);
        System.out.println(new Solution().findMedianSortedArrays(new int[]{1}, new int[]{2, 3}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int midIndex = (length1 + length2 - 1) >>> 1;
        if (length1 < length2) {
            return findMidian(nums2, nums1, midIndex);
        } else {
            return findMidian(nums1, nums2, midIndex);
        }

    }

    /**
     * @param numsL    长度大的数组
     * @param numsS    长度小的数组 长度小的数组向长度大的数组插入元素
     * @param midIndex
     * @return
     */
    private double findMidian(int[] numsL, int[] numsS, int midIndex) {
        int lengthOfNumsL = numsL.length;
        int lengthOfNumsS = numsS.length;
        //判断要找的中位数个数
        int count = (lengthOfNumsL + lengthOfNumsS) % 2;
        for (int i = 0; i < lengthOfNumsS; i++) {
            int indexInNumsL = getIndex(numsS[i], numsL);
            //计算实际插入的下标值
            int realIndex = indexInNumsL + i + 1;
            //与要寻找的中位数下标比较
            if (midIndex == realIndex) {
                if (count == 1) {
                    return numsS[i];
                } else {
                    if (i + 1 >= lengthOfNumsS) {
                        return numsS[i] + (numsL[indexInNumsL + 1] - (double) numsS[i]) / 2;
                    } else if (indexInNumsL + 1 >= lengthOfNumsL) {
                        return numsS[i] + (numsS[i + 1] - (double) numsS[i]) / 2;
                    }
                    return numsS[i] + (Math.min(numsS[i + 1], numsL[indexInNumsL + 1]) - (double) numsS[i]) / 2;
                }
            } else if (midIndex < realIndex) {
                //相对中位数下标
                int relativeMidIndex = midIndex - i;
                if (count == 1) {
                    return numsL[relativeMidIndex];
                } else {
                    if (relativeMidIndex + 1 >= lengthOfNumsL) {
                        return numsL[relativeMidIndex] + (numsS[i] - (double) numsL[relativeMidIndex]) / 2;
                    }
                    return numsL[relativeMidIndex] + (Math.min(numsL[relativeMidIndex + 1], numsS[i]) - (double) numsL[relativeMidIndex]) / 2;
                }
            }
        }
        //特殊情况 numsS（长度小的数组）长度为0（举例：[] [1]------>返回1)
        //numsS里面的所有值下标都在midIndex之前 则我们的中位数下标在numsL中 则减去numsS长度找到相对中位数下标
        int midIndexInNumsL = midIndex - lengthOfNumsS;
        return count == 1 ? numsL[midIndexInNumsL] : numsL[midIndexInNumsL] + (numsL[midIndexInNumsL + 1] - (double) numsL[midIndexInNumsL]) / 2;
    }

    /**
     * 返回的是插入的num的前一个元素下标
     *
     * @param num
     * @param numsL
     * @return
     */
    private int getIndex(int num, int[] numsL) {
        int left = 0;
        int right = numsL.length - 1;
        while (left < right) {
            //两数（该无符号右移方法只针对正数溢出）相加 不论进制 最多进一位
            //如果是负数相加（然后要做除以2的处理）产生的溢出是无法解决的
            int midIndex = (left + right) >>> 1;
            if (numsL[midIndex] < num) {
                left = midIndex + 1;
            } else if (numsL[midIndex] > num) {
                right = midIndex;
            } else {
                return midIndex;
            }
        }
        return numsL[left] > num ? left - 1 : left;
    }

}

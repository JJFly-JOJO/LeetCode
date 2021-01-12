package Array.NO321CreateMaximumNumber;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/13 0:05
 * ------------------------------暴力 分 治（和）思想 （归并排序）----------------------
 * * nums1 = [3, 4, 6, 5]
 * * nums2 = [9, 1, 2, 5, 8, 3]
 * * k = 5
 * * 输出:
 * * [9, 8, 6, 5, 3]
 * * 输入:
 * * nums1 = [3, 9]
 * * nums2 = [8, 9]
 * * k = 3
 * * 输出:
 * * [9, 8, 9]
 * * 输入:
 * * nums1 = [6, 7]
 * * nums2 = [6, 0, 4]
 * * k = 5
 * * 输出:
 * * [6, 7, 6, 0, 4]
 * <p>
 * [2,5,6,4,4,0]
 * [7,3,8,0,6,5,7,6,2]
 * 15
 * [7,3,8,2,5,6,4,4,0,6,5,7,6,2,0]
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().maxNumber(new int[]{2, 5, 6, 4, 4, 0}, new int[]{7, 3, 8, 0, 6, 5, 7, 6, 2}, 15)));
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] maxSpellingNum = new int[k];
        for (int i = 0; i <= k; i++) {
            int[] sub1 = getMaxSubArr(nums1, i);
            int[] sub2 = getMaxSubArr(nums2, k - i);
            if (sub1 == null || sub2 == null) {
                continue;
            }
            int[] mergeNums = getMerge(sub1, sub2);
            for (int j = 0; j < k; j++) {
                if (mergeNums[j] < maxSpellingNum[j]) {
                    break;
                } else if (mergeNums[j] > maxSpellingNum[j]) {
                    maxSpellingNum = mergeNums;
                    break;
                }
            }
        }
        return maxSpellingNum;
    }

    private int[] getMerge(int[] sub1, int[] sub2) {
        int length1 = sub1.length;
        int length2 = sub2.length;
        int mergeLength = length1 + length2;
        int[] mergeNums = new int[mergeLength];
        int m = 0;
        int i = 0;
        int j = 0;
        lable:
        while (true) {
            if (i == length1) {
                while (j < length2) {
                    mergeNums[m++] = sub2[j++];
                }
                break;
            }
            if (j == length2) {
                while (i < length1) {
                    mergeNums[m++] = sub1[i++];
                }
                break;
            }
            if (sub1[i] > sub2[j]) {
                mergeNums[m++] = sub1[i++];
            } else if (sub1[i] < sub2[j]) {
                mergeNums[m++] = sub2[j++];
            } else {
                int tempI = i + 1;
                int tempJ = j + 1;
                while (tempI < length1 && tempJ < length2) {
                    if (sub1[tempI] > sub2[tempJ]) {
                        mergeNums[m++] = sub1[i++];
                        continue lable;
                    } else if (sub1[tempI] < sub2[tempJ]) {
                        mergeNums[m++] = sub2[j++];
                        continue lable;
                    }
                    tempI++;
                    tempJ++;
                }
                //如果存在sub是另一个sub的前一部分 那我们先取sub长度多的 让其公共部分先取完 剩下的不同的再让前面的判断来比较
                if (tempI == length1) {
                    mergeNums[m++] = sub2[j++];
                } else {
                    mergeNums[m++] = sub1[i++];
                }
            }
        }
        return mergeNums;
    }

    private int[] getMaxSub(int[] nums, int subLen) {
        int length = nums.length;
        if (subLen > length) {
            return null;
        }
        //特判
        /*if (subLen == 0) {
            return new int[0];
        }
        if (subLen == length) {
            return nums;
        }*/
        //贪心算法思想
        //我们如果一遍循环找到subLen的长度会出现找到最后剩下的元素组成的长度不够subLen
        //这样讨论会很复杂 我们可以换成删去removeLength 当然删不够也没有影响 我们只用取前subLen即可
        int removeLength = length - subLen;
        int i = 0;
        Stack<Integer> subNums = new Stack<>();
        while (i < length) {
            while (!subNums.empty() && nums[i] > subNums.peek() && removeLength > 0) {
                subNums.pop();
                removeLength--;
            }
            subNums.push(nums[i++]);
        }
        int[] sub = new int[subLen];
        for (int j = 0; j < subLen; j++) {
            sub[j] = subNums.get(j);
        }
        return sub;
    }

    private int[] getMaxSubArr(int[] nums, int subLen) {
        int length = nums.length;
        if (length < subLen) {
            return null;
        }
        int[] res = new int[subLen];
        int choose = 0;
        for (int i = 0; i < length; i++) {
            while (length - i + choose > subLen && choose > 0 && nums[i] > res[choose - 1]) {
                choose--;
            }
            if (choose < subLen) {
                res[choose++] = nums[i];
            }
        }
        return res;
    }

}

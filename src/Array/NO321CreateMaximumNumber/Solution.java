package Array.NO321CreateMaximumNumber;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/12 19:17
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * ----------------------方法一 暴力贪心算法------------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().maxNumberErro2(new int[]{6, 7}, new int[]{6, 0, 4}, 5)));
    }

    public int[] maxNumberErro(int[] nums1, int[] nums2, int k) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int lastIndex1 = nums1.length - 1;
        int lastIndex2 = nums2.length - 1;
        int[] mergeNum = new int[k];
        int m = 0;
        while (m < k) {
            if (lastIndex1 < 0) {
                while (m < k) {
                    mergeNum[m++] = nums2[lastIndex2--];
                }
                break;
            }
            if (lastIndex2 < 0) {
                while (m < k) {
                    mergeNum[m++] = nums1[lastIndex1--];
                }
                break;
            }
        }
        return null;
    }

    public int[] maxNumberErro2(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int i = 0;
        //List<Integer> res = new ArrayList<>();
        int length1 = nums1.length;
        int length2 = nums2.length;
        int num1Begin = 0;
        int num2Begin = 0;
        label:
        while (k > 0) {
            int resetBegin1 = num1Begin;
            int resetBegin2 = num2Begin;
            int run1 = num1Begin;
            int run2 = num2Begin;
            int count = length1 + length2 - run1 - run2;
            int resetCount = count;
            boolean choose = false;
            while (count >= k) {
                if (run2 == length2) {
                    int maxIndex = run1;
                    count = resetCount;
                    while (run1 < length1 && count >= k) {
                        if (nums1[run1] > nums1[maxIndex]) {
                            maxIndex = run1;
                        }
                        run1++;
                        count--;
                    }
                    res[i++] = nums1[maxIndex];
                    num1Begin = maxIndex + 1;
                    num2Begin = resetBegin2;
                    k--;
                    continue label;
                } else {
                    //num1先跑
                    while (run1 < length1 && count >= k) {
                        if (nums1[run1] > nums2[run2]) {
                            num1Begin = run1 + 1;
                            num2Begin = resetBegin2;
                            choose = true;
                            break;
                        }
                        run1++;
                        count--;
                    }
                }
                if (run1 == length1) {
                    int maxIndex = run2;
                    count = resetCount;
                    while (run2 < length2 && count >= k) {
                        if (nums2[run2] > nums2[maxIndex]) {
                            maxIndex = run2;
                        }
                        run2++;
                        count--;
                    }
                    //加入结果集
                    res[i++] = nums2[maxIndex];
                    num2Begin = maxIndex + 1;
                    num1Begin = resetBegin1;
                    k--;
                    continue label;
                } else {
                    //num2后跑
                    while (run2 < length2 && count >= k) {
                        if (nums2[run2] > nums1[run1]) {
                            num2Begin = run2 + 1;
                            num1Begin = resetBegin1;
                            choose = false;
                            break;
                        }
                        run2++;
                        count--;
                    }
                }
            }
            if (choose) {
                res[i++] = nums1[num1Begin - 1];
                k--;
            } else {
                res[i++] = nums2[num2Begin - 1];
                k--;
            }
        }
        return res;
    }

}

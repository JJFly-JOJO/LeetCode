package Array.NO88MergeSortedArray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/20 13:45
 * @description
 */
public class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums3 = new int[m + n];
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        while (p1 < m && p2 < n) {
            if (nums1[p1] < nums2[p2]) {
                nums3[p3++] = nums1[p1++];
            } else {
                nums3[p3++] = nums2[p2++];
            }
        }
        if (p1 < m) {
            System.arraycopy(nums1, p1, nums3, p3, m - p1);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums3, p3, n - p2);
        }
        System.arraycopy(nums3, 0, nums1, 0, nums1.length);
    }

}

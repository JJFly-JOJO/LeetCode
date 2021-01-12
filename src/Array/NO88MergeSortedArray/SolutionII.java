package Array.NO88MergeSortedArray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/20 13:51
 * @description -----------------不需要使用nums3 只需要在nums1上利用其后的n个空间没有利用 实现原地归并---------
 */
public class SolutionII {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int r1 = m - 1;
        int r2 = n - 1;
        int r = nums1.length - 1;
        while (r1 >= 0 && r2 >= 0) {
            /*if (nums1[r1] > nums2[r2]) {
                nums1[r--] = nums1[r1--];
            } else {
                nums1[r--] = nums2[r2--];
            }*/
            nums1[r--] = nums1[r1] > nums2[r2] ? nums1[r1--] : nums2[r2--];
        }
        //由于原地归并 我们只需要将nums2剩下的元素拷贝到nums1的前半部即可（即使nums2为空）
        System.arraycopy(nums2, 0, nums1, 0, r2 + 1);
    }

}

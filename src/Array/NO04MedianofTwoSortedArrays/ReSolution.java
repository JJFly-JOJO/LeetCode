package Array.NO04MedianofTwoSortedArrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/24 18:14
 * @description
 */
public class ReSolution {

    public static void main(String[] args) {
        System.out.println(new ReSolution().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int pos2 = (nums1.length + nums2.length) / 2;
        int pos1 = pos2 - 1;
        int pos = 0;
        int len = nums1.length + nums2.length;
        List<Integer> mem = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        while (pos < len && pos <= pos2) {
            int curNum = 0;
            if (idx1 < nums1.length && idx2 < nums2.length) {
                if (nums1[idx1] < nums2[idx2]) {
                    curNum = nums1[idx1++];
                } else {
                    curNum = nums2[idx2++];
                }
            } else if (idx1 >= nums1.length) {
                curNum = nums2[idx2++];
            } else if (idx2 >= nums2.length) {
                curNum = nums1[idx1++];
            }
            if (pos == pos1) {
                mem.add(curNum);
            }
            if (pos == pos2) {
                mem.add(curNum);
            }
            pos++;
        }
        if (mem.size() == 1) {
            return mem.get(0);
        }
        return (len & 1) == 1 ? mem.get(1) : (mem.get(0) + mem.get(1)) / 2.0;
    }

}

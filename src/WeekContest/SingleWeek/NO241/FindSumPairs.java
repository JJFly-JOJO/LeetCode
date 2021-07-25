package WeekContest.SingleWeek.NO241;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/18 10:38
 * @description
 */
public class FindSumPairs {

    private int[] nums1;

    private int[] nums2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        Arrays.sort(this.nums1);
        this.nums2 = nums2;
    }

    public void add(int index, int val) {
        nums2[index] += val;
    }

    public int count(int tot) {
        int res = 0;
        for (int n : nums2) {
            int v = tot - n;
            if (v <= 0) {
                break;
            }
            res += find(v);
        }
        return res;
    }

    private int find(int v) {
        int l = 0;
        int r = nums1.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums1[mid] >= v) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        int count = 0;
        r++;
        while (r < nums1.length && nums1[r] == v) {
            count++;
            r++;
        }
        return count;
    }

}

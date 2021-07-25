package WeekContest.SingleWeek.NO240;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/9 11:04
 * @description
 */
public class SolutionII {

    public int maxDistance(int[] nums1, int[] nums2) {
        int res = 0;
        for (int i = 0; i < nums1.length; i++) {
            int idx = find(nums2, nums1[i]);
            if (idx >= i) {
                res = Math.max(res, idx - i);
            }
        }
        return res;
    }

    private int find(int[] nums2, int tar) {
        int l = 0;
        int r = nums2.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums2[mid] >= tar) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

}

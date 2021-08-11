package Lilith.NO02;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/11 7:32 下午
 * @description
 */
public class Solution {

    public long ans(int[] array, int k) {
        long ans = 0L;
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            int idx = binarySearch(array, k - array[i]);
            if (idx <= i) {
                break;
            }
            ans = ans + idx - i;
        }
        return ans;
    }

    private int binarySearch(int[] array, int target) {
        int l = 0;
        int r = array.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (array[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

}

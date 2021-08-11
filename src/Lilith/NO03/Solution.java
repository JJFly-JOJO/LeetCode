package Lilith.NO03;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/8/11 7:41 下午
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minimum(new int[]{1, 2, 3, 4, 5}));
    }

    public long minimum(int[] a) {
        int[] num = new int[a.length * 2];
        long sum = 0L;
        for (int i = 0; i < a.length; i++) {
            num[i] = a[i];
            sum += a[i];
        }
        for (int i = a.length; i < num.length; i++) {
            num[i] = a[i - a.length];
        }
        long ans = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        int maxLen = a.length;
        long preDiffer = sum;
        long windowSum = 0L;
        while (r < num.length) {
            if (r - l + 1 >= maxLen) {
                ans = Math.min(ans, preDiffer);
                preDiffer = Math.abs(sum - 2 * windowSum);
                windowSum -= num[l++];
                continue;
            }
            windowSum += num[r];
            long tmp = Math.abs(sum - 2 * windowSum);
            if (tmp <= preDiffer) {
                preDiffer = tmp;
                r++;
                continue;
            }
            ans = Math.min(ans, preDiffer);
            preDiffer = tmp;
            windowSum -= num[l++];
            tmp = Math.abs(sum - 2 * windowSum);
            while (l < r && tmp <= preDiffer) {
                windowSum -= num[l++];
                preDiffer = tmp;
                tmp = Math.abs(sum - 2 * windowSum);
            }
            ans = Math.min(ans, preDiffer);
            preDiffer=tmp;
            r++;
        }
        return ans;
    }

}

package Array.NO1561MaximumNumberofCoinsYouCanGet;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/25 0:29
 * @description
 */
public class Solution {
    public int maxCoins(int[] piles) {
        int len = piles.length;
        Arrays.sort(piles);
        int sum = 0;
        for (int i = len / 3; i < len; i += 2) {
            sum += piles[i];
        }
        return sum;
    }
}

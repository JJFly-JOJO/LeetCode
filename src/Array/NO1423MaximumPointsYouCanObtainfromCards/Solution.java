package Array.NO1423MaximumPointsYouCanObtainfromCards;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/17 15:55
 * @description
 */
public class Solution {

    public int maxScore(int[] cardPoints, int k) {
        //sliding window
        int sum = Arrays.stream(cardPoints).sum();
        int len = cardPoints.length;
        int l = 0;
        int r = len - k;
        int subSum = 0;
        for (int i = l; i < r; i++) {
            subSum += cardPoints[i];
        }
        int res = sum - subSum;
        for (; r < len; r++) {
            subSum += cardPoints[r];
            subSum -= cardPoints[l++];
            res = Math.max(res, sum - subSum);
        }
        return res;
    }

}

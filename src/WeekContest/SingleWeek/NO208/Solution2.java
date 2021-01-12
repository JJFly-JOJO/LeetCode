package WeekContest.SingleWeek.NO208;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/27 11:37
 */
public class Solution2 {

    public int minOperationsMaxProfit(int[] customers,
                                      int boardingCost,
                                      int runningCost) {
        int max4Val = boardingCost * 4 - runningCost;
        int max3Val = max4Val - boardingCost;
        int max2Val = max3Val - boardingCost;
        int max1Val = max2Val - boardingCost;
        int max0Val = -boardingCost;
        if (max4Val <= 0) {
            return -1;
        }
        int res = 0;
        int sum = 0;
        int tempRes = 0;
        int index = 0;
        for (int i = 0; sum >= 0; i++) {
            if (i < customers.length) {
                sum += customers[i];

            }
            if (sum >= 4) {
                tempRes += max4Val;
                sum -= 4;
            } else if (sum == 3) {
                tempRes += max3Val;
                sum -= 3;
            } else if (sum == 2) {
                tempRes += max2Val;
                sum -= 2;
            } else if (sum == 1) {
                tempRes += max1Val;
                sum -= 1;
            } else if (sum == 0) {
                if (i >= customers.length) {
                    break;
                } else {
                    tempRes += max0Val;
                }
            }
            if (tempRes > res) {
                res = tempRes;
                index = i;
            }
        }
        return index + 1;
    }

}

package WeekContest.DoubleWeek.NO37;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/17 22:21
 * @description [6, 0, 7, 0, 7, 5, 7, 8, 3, 4, 0, 7, 8, 1, 6, 8, 1, 1, 2, 4, 8, 1, 9, 5, 4, 3, 8, 5, 10, 8, 6, 6, 1, 0, 6, 10, 8, 2, 3, 4]
 */
public class Solution1 {

    public static void main(String[] args) {
        System.out.println();
    }

    public double trimMean(int[] arr) {
        int length = arr.length;
        int deleteNum = (int) (length * 0.05);
        Arrays.sort(arr);
        int last = length - deleteNum;
        long sum = 0;
        for (int i = deleteNum; i < last; i++) {
            sum += arr[i];
        }
        return (double) sum / (last - deleteNum);
    }
}

package WeekContest.SingleWeek.NO242;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/23 10:47
 * @description
 */
public class SolutionII {

    public static void main(String[] args) {
        String s;
        HashMap m;
        System.out.println(new SolutionII().minSpeedOnTime(new int[]{1, 3, 2}, 6));
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        //预处理
        //最后一段耗时一定为正数（即使很小）
        if (hour <= dist.length - 1) {
            return -1;
        }
        hour = hour * 100;
        int left = 1;
        int right = 10000000;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            double hours = 0;
            for (int i = 0; i < dist.length - 1; i++) {
                hours += Math.ceil(((double) dist[i]) / mid);
            }
            //hours+dist[n-1]/mid   hour
            //hour最多两位小数 因此乘100
            //将小数计算转换为正数（除法转乘法） 避免小数误差
            int differ = (int) ((hours * mid + dist[dist.length - 1]) * 100 - hour * mid);
            if (differ > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}

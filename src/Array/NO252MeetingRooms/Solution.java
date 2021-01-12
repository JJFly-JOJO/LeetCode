package Array.NO252MeetingRooms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/23 17:14
 * @description
 */
public class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int len = intervals.length;
        for (int i = 1; i < len; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}

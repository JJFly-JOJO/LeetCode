package WeekContest.SingleWeek.NO246;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/6/20 10:41
 * @description
 */
public class SolutionII {

    public int numberOfRounds(String startTime, String finishTime) {
        String[] startStr = startTime.split(":");
        int startHour = Integer.parseInt(startStr[0]);
        int startMin = Integer.parseInt(startStr[1]);
        String[] finishStr = finishTime.split(":");
        int finishHour = Integer.parseInt(finishStr[0]);
        int finishMin = Integer.parseInt(finishStr[1]);
        int ans = 0;
        if (startHour == finishHour && startMin <= finishMin) {
            return getEndMin(finishMin) - 4 + getStartMin(startMin);
        }
        ans += getStartMin(startMin);
        ans += getEndMin(finishMin);
        int hour = 0;
        if (startHour < finishHour) {
            hour = finishHour - startHour - 1;
        } else {
            hour = 24 - startHour - 1 + finishHour;
        }
        ans += hour * 4;
        return ans;
    }

    private int getEndMin(int min) {
        if (min >= 45) {
            return 3;
        }
        if (min >= 30) {
            return 2;
        }
        if (min >= 15) {
            return 1;
        }
        return 0;
    }

    private int getStartMin(int min) {
        if (min == 0) {
            return 4;
        }
        if (min <= 15) {
            return 3;
        }
        if (min <= 30) {
            return 2;
        }
        if (min <= 45) {
            return 1;
        }
        return 0;
    }

}

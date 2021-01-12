package WeekContest.SingleWeek.NO208;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/27 11:18
 */
public class Solution {

    public int minOperations(String[] logs) {
        String s = "./";
        String b = "../";
        int count = 0;
        for (int i = 0; i < logs.length; i++) {
            if (logs[i].equals(s)) {
                continue;
            } else if (logs[i].equals(b)) {
                if (count == 0) {
                    continue;
                } else {
                    count--;
                }
            } else {
                count++;
            }
        }
        return count;
    }

}

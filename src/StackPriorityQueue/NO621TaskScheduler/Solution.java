package StackPriorityQueue.NO621TaskScheduler;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/21 23:42
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }

    public int leastInterval(char[] tasks, int n) {
        int[] taskCnt = new int[26];
        int[] nextTime = new int[26];
        for (char t : tasks) {
            taskCnt[t - 'A']++;
        }
        int cnt = 0;
        int curTime = 0;
        while (cnt < tasks.length) {
            int curIdx = -1;
            int maxCnt = 0;
            int minTim = Integer.MAX_VALUE;
            for (int i = 0; i < 26; i++) {
                if (taskCnt[i] > maxCnt && nextTime[i] <= curTime) {
                    maxCnt = taskCnt[i];
                    curIdx = i;
                }
            }
            if (curIdx == -1) {
                for (int i = 0; i < 26; i++) {
                    if (nextTime[i] < minTim && taskCnt[i] > 0) {
                        curIdx = i;
                        minTim = nextTime[i];
                        maxCnt = taskCnt[i];
                    } else if (nextTime[i] == minTim && taskCnt[i] > 0) {
                        if (taskCnt[i] > maxCnt) {
                            curIdx = i;
                            maxCnt = taskCnt[i];
                        }
                    }
                }
            }
            taskCnt[curIdx]--;
            cnt++;
            curTime = nextTime[curIdx] < curTime ? curTime + 1 : nextTime[curIdx] + 1;
            nextTime[curIdx] = nextTime[curIdx] + n + 1;
        }
        return curTime;
    }

}

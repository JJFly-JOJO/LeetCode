package WeekContest.SingleWeek.NO240;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/10 16:38
 * @description
 */
public class SolutionIV {

    public int largestPathValue(String colors, int[][] edges) {
        char[] clr = colors.toCharArray();
        int[] inCount = new int[clr.length];
        LinkedList<Integer>[] adjTab = new LinkedList[clr.length];
        for (int i = 0; i < adjTab.length; i++) {
            adjTab[i] = new LinkedList<>();
        }
        for (int[] e : edges) {
            inCount[e[1]]++;
            adjTab[e[0]].add(e[1]);
        }
        //dp initialize
        int[][] dp = new int[clr.length][26];
        //BFS
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < inCount.length; i++) {
            if (inCount[i] == 0) {
                q.offer(i);
                dp[i][clr[i] - 'a']++;
            }
        }
        //记录遍历的节点个数 判断是否存在环 如果有环 节点数小于总节点数
        int found = 0;
        while (!q.isEmpty()) {
            found++;
            int node = q.poll();
            for (int n : adjTab[node]) {
                for (int c = 0; c < 26; c++) {
                    dp[n][c] = Math.max(dp[n][c], dp[node][c]);
                }
                inCount[n]--;
                if (inCount[n] == 0) {
                    //入度为0 再加入队列中
                    q.offer(n);
                    //判断当前节点颜色 更新对应的dp
                    dp[n][clr[n] - 'a']++;
                }
            }
        }
        if (found != clr.length) {
            //存在环
            return -1;
        }
        int res = 0;
        for (int[] d : dp) {
            for (int i : d) {
                res = Math.max(i, res);
            }
        }
        return res;
    }

}

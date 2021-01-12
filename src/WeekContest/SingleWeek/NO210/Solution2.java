package WeekContest.SingleWeek.NO210;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/11 10:34
 * 输入：n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
 * 输出：5
 * 解释：2 和 5 的网络秩为 5，注意并非所有的城市都需要连接起来。
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().maximalNetworkRank(5,
                new int[][]{{2, 3}, {0, 3}, {0, 4}, {4, 1}}));
    }

    /**
     * --------------暴力解法--------------
     *
     * @param n
     * @param roads
     * @return
     */
    public int maximalNetworkRank(int n, int[][] roads) {
        int res = 0;
        if (roads == null || roads.length == 0) {
            return res;
        }
        boolean[] cityExisting = new boolean[n];
        Map<Integer, Set<Integer>> cityToCity = new HashMap<>();
        for (int[] temp : roads) {
            cityExisting[temp[0]] = true;
            cityExisting[temp[1]] = true;
            Set<Integer> adjCities = cityToCity.getOrDefault(temp[0], new HashSet<>());
            adjCities.add(temp[1]);
            cityToCity.put(temp[0], adjCities);
            adjCities = cityToCity.getOrDefault(temp[1], new HashSet<>());
            adjCities.add(temp[0]);
            cityToCity.put(temp[1], adjCities);
        }
        for (int i = 0; i < n; i++) {
            if (!cityExisting[i]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (!cityExisting[j]) {
                    continue;
                }
                int temp = cityToCity.get(i).size() + cityToCity.get(j).size();
                if (cityToCity.get(i).contains(j)) {
                    temp--;
                }
                if (temp > res) {
                    res = temp;
                }
            }
        }
        return res;
    }

}

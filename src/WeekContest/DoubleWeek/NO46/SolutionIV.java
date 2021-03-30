package WeekContest.DoubleWeek.NO46;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/20 23:35
 * @description
 */
public class SolutionIV {

    private Map<Integer, List<Integer>> graph = new HashMap<>();

    public int[] getCoprimes(int[] nums, int[][] edges) {
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], key -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], key -> new ArrayList<>()).add(e[0]);
        }
        boolean[] isVisited = new boolean[nums.length];
        int[] res = new int[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs(0, res, isVisited, path, nums);
        return res;
    }

    private void dfs(int node, int[] res, boolean[] isVisited, List<Integer> path, int[] nums) {
        if (isVisited[node]) {
            return;
        }
        isVisited[node] = true;
        int i = path.size() - 1;
        for (; i >= 0; i--) {
            if (nums[path.get(i)] == nums[node]) {
                res[node] = res[path.get(i)];
                break;
            }
            if (gcd(nums[path.get(i)], nums[node]) == 1) {
                res[node] = path.get(i);
                break;
            }
        }
        if (i < 0) {
            res[node] = -1;
        }
        path.add(node);
        for (int n : graph.get(node)) {
            dfs(n, res, isVisited, path, nums);
        }
        path.remove(path.size() - 1);
        isVisited[node] = false;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

}

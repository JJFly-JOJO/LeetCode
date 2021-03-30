package UnionFind.NO1319NumberofOperationstoMakeNetworkConnected;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/23 21:57
 * @description ----------------DFS解法--------------
 */
public class SolutionII {

    //注意这里使用领接矩阵会超出内存限制 使用邻接表替换
    //private boolean[][] graph;
    private List<Integer>[] graph;

    private boolean[] isVisited;

    public int makeConnected(int n, int[][] connections) {
        //特判 只有边数大于等于节点数-1 才能成图
        if (n - 1 > connections.length) {
            return -1;
        }
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : connections) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        isVisited = new boolean[n];
        //记录连通集个数
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                dfs(i);
                ans++;
            }
        }
        return ans - 1;
    }

    private void dfs(int i) {
        isVisited[i] = true;
        for (int to : graph[i]) {
            if (!isVisited[to]) {
                dfs(to);
            }
        }
    }

}

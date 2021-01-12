package StackPriorityQueue.NO332ReconstructItenerary;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/2 17:39
 * @description -----------------欧拉图与半欧拉图---------------
 * 思路：该题是一个有向图，有题干一定能保证欧拉图或半图，那么对于有向图是欧拉图或半图的条件：
 * 对于有向图 GG，GG 是半欧拉图当且仅当 GG 的所有顶点属于同一个强连通分量且
 * <p>
 * 恰有一个顶点的出度与入度差为 1；
 * <p>
 * 恰有一个顶点的入度与出度差为 1；
 * <p>
 * 所有其他顶点的入度和出度相同。
 */
public class Solution {

    List<String> res;
    private Map<String, PriorityQueue<String>> fTo;

    public List<String> findItinerary(List<List<String>> tickets) {
        fTo = new HashMap<>();
        for (List<String> l : tickets) {
            PriorityQueue<String> p = fTo.getOrDefault(l.get(0), new PriorityQueue<>());
            p.add(l.get(1));
            fTo.put(l.get(0), p);
        }
        res = new ArrayList<>();
        dfs("JFK", res);
        Collections.reverse(res);
        return res;
    }

    private void dfs(String from, List<String> res) {
        if (!fTo.containsKey(from)) {
            res.add(from);
            return;
        }
        PriorityQueue<String> to = fTo.get(from);
        while (!to.isEmpty()) {
            dfs(to.poll(), res);
        }
        res.add(from);
    }

}

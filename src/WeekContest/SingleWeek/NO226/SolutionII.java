package WeekContest.SingleWeek.NO226;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/31 10:40
 * @description
 */
public class SolutionII {

    public static void main(String[] args) {
        System.out.println(new SolutionII().restoreArray(new int[][]{{2, 1}, {3, 4}, {3, 2}
    }));
}

    public int[] restoreArray(int[][] adjacentPairs) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] p : adjacentPairs) {
            map.computeIfAbsent(p[0], key -> new ArrayList<>()).add(p[1]);
            map.computeIfAbsent(p[1], key -> new ArrayList<>()).add(p[0]);
        }
        int start = 0;
        for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
            if (e.getValue().size() == 1) {
                start = e.getKey();
                break;
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        int last = 100001;
        res.add(start);
        for (int i = 0; i < adjacentPairs.length; i++) {
            List<Integer> list = map.get(res.get(i));
            if (list.size() == 1) {
                res.add(list.get(0));
            } else {
                if (list.get(0) == last) {
                    res.add(list.get(1));
                } else {
                    res.add(list.get(0));
                }
            }
            last = res.get(i);
        }
        int[] r = new int[adjacentPairs.length + 1];
        for (int i = 0; i < res.size(); i++) {
            r[i] = res.get(i);
        }
        return r;
    }

}

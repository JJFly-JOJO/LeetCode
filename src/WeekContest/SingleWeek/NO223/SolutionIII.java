package WeekContest.SingleWeek.NO223;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/10 10:46
 * @description
 */
public class SolutionIII {

    private int[] unionSize;

    private int[] union;

    private int size;

    public static void main(String[] args) {
        System.out.println(new SolutionIII().minimumHammingDistance(new int[]{5, 1, 2, 4, 3},
                new int[]{1, 5, 4, 2, 3}, new int[][]{{0, 4}, {4, 2}, {1, 3}, {1, 4}}));
        String a = "abc";
        String b = "def";
        StringBuilder sb = new StringBuilder();
        sb.append(a).append(b);
        String ab = a + b;
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        union = new int[source.length];
        unionSize = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            union[i] = i;
        }
        Arrays.fill(unionSize, 1);
        size = source.length;
        for (int[] edge : allowedSwaps) {
            unionF(edge[0], edge[1]);
        }
        int res = 0;
        Map<Integer, IncreaseHashMap<Integer>> mapOfS = new HashMap<>();
        Map<Integer, IncreaseHashMap<Integer>> mapOfT = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
            int root = uFind(i);
            /*IncreaseHashMap<Integer> vToC = mapOfS.getOrDefault(uFind(i), new IncreaseHashMap());
            vToC.put(source[i]);
            mapOfS.put(root, vToC);*/
            //java 8 对hashmap中上面三行代码的整合
            mapOfS.computeIfAbsent(root, k -> new IncreaseHashMap<>()).put(source[i]);
            mapOfT.computeIfAbsent(root, k -> new IncreaseHashMap<>()).put(target[i]);
        }
        for (Map.Entry<Integer, IncreaseHashMap<Integer>> e : mapOfS.entrySet()) {
            //遍历每个并查集中的元素
            IncreaseHashMap<Integer> tUnionMap = mapOfT.get(e.getKey());
            for (Map.Entry<Integer, Integer> v : e.getValue().entrySet()) {
                //易错-------------Math.abs(v.getValue() - tUnionMap.get(v.getKey()))
                //会造成res重复的情况 因为如果source中的某元素a个数少于target元素a的个数m个
                // 那么就可能会存在source中某元素b的个数多于target元素b的个数m个 这样会造成结果集乘2
                /*if (tUnionMap.containsKey(v.getKey())) {
                    res += Math.abs(v.getValue() - tUnionMap.get(v.getKey()));
                } else {
                    res += v.getValue();
                }*/
                //由上面易错分析 我们只记录source当前并查集合中比target当前并查集合中元素多出来的个数
                // 当然包括target中不存在的元素
                int count = v.getValue() - tUnionMap.getOrDefault(v.getKey(), 0);
                if (count > 0) {
                    res += count;
                }
            }
        }
        return res;
    }

    private boolean connect(int x, int y) {
        return uFind(x) == uFind(y);
    }

    private void unionF(int x, int y) {
        x = uFind(x);
        y = uFind(y);
        if (x == y) {
            return;
        }
        //代码优雅
        if (unionSize[x] < unionSize[y]) {
            int t = x;
            x = y;
            y = t;
        }
        //合并 永远是秩小的连接秩大的
        union[y] = x;
        unionSize[x] += unionSize[y];
        size--;
    }

    private int uFind(int x) {
        return union[x] == x ? x : uFind(union[x]);
    }

    public class IncreaseHashMap<T> extends HashMap<T, Integer> {
        public void put(T i) {
            super.put(i, getOrDefault(i, 0) + 1);
        }
    }

}

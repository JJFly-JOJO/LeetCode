package UnionFind.NO1202SmallestStringWithSwaps;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/14 17:15
 * @description
 */
public class Solution {

    private int[] union;

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] chars = s.toCharArray();
        union = new int[s.length()];
        for (int i = 0; i < union.length; i++) {
            union[i] = i;
        }
        for (List<Integer> p : pairs) {
            unionF(p.get(0), p.get(1));
        }
        //-------------------注意这里也可以使用PriorityQueue 而不需要调用排序sort方法
        Map<Integer, LinkedList<Character>> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.computeIfAbsent(find(i), k -> new LinkedList<>()).add(chars[i]);
        }
        for (Map.Entry<Integer, LinkedList<Character>> e : map.entrySet()) {
            Collections.sort(e.getValue(), (o1, o2) -> o1 - o2);
        }
        for (int i = 0; i < chars.length; i++) {
            chars[i] = map.get(find(i)).poll();
        }
        return new String(chars);
    }

    private void unionF(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a != b) {
            //b->a
            union[b] = a;
        }
    }

    private int find(int x) {
        if (union[x] != x) {
            union[x] = find(union[x]);
        }
        return union[x];
    }

}

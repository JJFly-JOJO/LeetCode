package UnionFind.NO947MostStonesRemovedwithSameRoworColumn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/18 0:14
 * @description --------并查集 技巧：细化元素粒度--------
 * 思路：虽然我们可以以一个position作为一个元素，但是这样就需要我们自己去判断两个position是否连通，
 * 这样只有i=0；j=i+1来枚举所有的组合判断是否连通。
 * 如果我们细化position元素的粒度，我们以x，y坐标值为一个元素，当前pos表示一个连通集，那么我们union x y
 * 坐标，就能避免枚举所有pos组合（题干x或y轴坐标值相同就可以认为是属于一个连通分量），
 * 这里要注意保证x y坐标值不重合（x y坐标值分别表示两个不同元素），可以采用取反（取负数不行，0->0），或者x坐标+10000...
 */
public class SolutionII {

    /**
     * 使用哈希表来作为union 可以压缩空间
     */
    private Map<Integer, Integer> union = new HashMap<>();

    private int size;

    public int removeStones(int[][] stones) {
        //初始化
        for (int[] s : stones) {
            union.put(~s[0], ~s[0]);
            union.put(s[1], s[1]);
        }
        size = union.size();
        for (int[] s : stones) {
            //细化粒度的同时 要保证元素的唯一性
            unionF(~s[0], s[1]);
        }
        return stones.length - size;
    }

    private void unionF(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a != b) {
            union.put(b, a);
            size--;
        }
    }

    private int find(int x) {
        if (union.get(x) != x) {
            union.put(x, find(union.get(x)));
        }
        return union.get(x);
    }

}

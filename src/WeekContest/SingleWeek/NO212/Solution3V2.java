package WeekContest.SingleWeek.NO212;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/27 17:09
 * @description ----------------并查集方法 计算出所有边 边按长度从小到大排序 然后边小的点进行合并 最终合并到起点所在集合与终点所在集合连通-------
 * ---------------并查集的两种优化方式！！！（1）按秩合并（2）路径优化
 */
public class Solution3V2 {

    /**
     * union 记录每个节点所属的上一级父节点
     * unionSize 记录当前节点的高度
     * size 用来记录当前集合数量
     */
    public int[] union;
    public int[] unionSize;
    public int size;

    public int minimumEffortPathForUnion(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int product = row * col;
        //初始化边
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //注意特殊情况的排除
                //注意二维坐标的一维转化
                if (j > 0) {
                    edges.add(new Edge(i * col + j, i * col + j - 1, Math.abs(heights[i][j] - heights[i][j - 1])));
                }
                if (i > 0) {
                    edges.add(new Edge((i - 1) * col + j, i * col + j, Math.abs(heights[i][j] - heights[i - 1][j])));
                }
            }
        }
        //初始化并查集
        size = product;
        union = new int[product];
        for (int i = 0; i < product; i++) {
            union[i] = i;
        }
        unionSize = new int[product];
        Arrays.fill(unionSize, 1);
        //边集合排序
        Collections.sort(edges, (o1, o2) -> o1.len - o2.len);
        //遍历边 合并并查集
        for (Edge e : edges) {
            unionF(e.x, e.y);
            if (connect(0, product - 1)) {
                return e.len;
            }
        }
        return 0;
    }

    /**
     * 判断两个节点是否属于一个集合
     *
     * @param x
     * @param y
     * @return
     */
    private boolean connect(int x, int y) {
        return uFind(x) == uFind(y);
    }

    private void unionF(int x, int y) {
        x = uFind(x);
        y = uFind(y);
        if (x == y) {
            return;
        }
        //并查集合并时 我们为了减小树的高度 就必须将规模小的树接到规模大的树上
        //注意！！！-----------------------------------这里并查集使用了按秩优化  小规模集合接到大规模集合上

        /*if (unionSize[x] < unionSize[y]) {
            union[x] = y;
            unionSize[y] += unionSize[x];
        } else {
            union[y] = x;
            unionSize[x] += unionSize[y];
        }
        size--;*/
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

    /**
     * 并查集中 找到当前节点从属的父节点
     *
     * @param x
     * @return
     */
    private int uFind(int x) {
        return union[x] == x ? x : uFind(union[x]);
    }

    class Edge {
        public int x;
        public int y;
        public int len;

        public Edge(int X, int Y, int L) {
            x = X;
            y = Y;
            len = L;
        }

    }

}

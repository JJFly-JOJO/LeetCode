package DFSBFS.NO200NumberofIslands;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};
        System.out.println(new Solution().numIslandsForBFS(grid));
    }

    /**
     * 方法一：
     * DFS
     * 不需要重新使用标记空间 直接对于遍历到的点 置零 DFS几次就表示有几个岛屿
     *
     * @param grid
     * @return
     */
    public int numIslandsForDFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int countOfIsland = 0;
        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                DFS(grid, i, j);
                countOfIsland++;
            }
        }
        return countOfIsland;
    }

    /**
     * 深度搜索 遍历到时将1置0
     * 终止条件 数组下标越界 或者数组值为0
     *
     * @param grid
     * @param i
     * @param j
     */
    private void DFS(char[][] grid, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;

        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        //dfs
        DFS(grid, i - 1, j);
        DFS(grid, i + 1, j);
        DFS(grid, i, j - 1);
        DFS(grid, i, j + 1);

    }

    /**
     * 方法二：
     * BFS
     *
     * @param grid
     * @return
     */
    public int numIslandsForBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int countOfIsland = 0;
        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    countOfIsland++;
                    //遍历过 一定要置零 为bfs提供终止条件
                    grid[i][j] = '0';
                    Queue<Integer> tempQueueForIsland = new LinkedList<>();
                    //技巧 将二维数组下标转换为一维数组下标存入<--------------------------!!!
                    tempQueueForIsland.add(i * col + j);
                    while (!tempQueueForIsland.isEmpty()) {
                        //队列头部弹出
                        int rowAndCol = tempQueueForIsland.poll();
                        //转换为行 列
                        int tempRow = rowAndCol / col;
                        int tempCol = rowAndCol % col;
                        //向上下左右进行广度搜索
                        if (tempRow - 1 >= 0 && grid[tempRow - 1][tempCol] == '1') {
                            grid[tempRow - 1][tempCol] = '0';
                            tempQueueForIsland.add((tempRow - 1) * col + tempCol);
                        }
                        if (tempRow + 1 < row && grid[tempRow + 1][tempCol] == '1') {
                            grid[tempRow + 1][tempCol] = '0';
                            tempQueueForIsland.add((tempRow + 1) * col + tempCol);
                        }
                        if (tempCol - 1 >= 0 && grid[tempRow][tempCol - 1] == '1') {
                            grid[tempRow][tempCol - 1] = '0';
                            tempQueueForIsland.add(tempRow * col + tempCol - 1);
                        }
                        if (tempCol + 1 < col && grid[tempRow][tempCol + 1] == '1') {
                            grid[tempRow][tempCol + 1] = '0';
                            tempQueueForIsland.add(tempRow * col + tempCol + 1);
                        }
                    }

                }

            }
        }
        return countOfIsland;
    }

    /**
     * 并查集方法
     *注意这里grid[][]==1遍历到之后 不能置0 将当前元素合并到集合的动作相当于置0遍历过的意思
     * @param grid
     * @return
     */
    public int numIslandsForUnion(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        //当前(r,c)与(r-1,c)结合 结合用一维数组的下标
                        uf.union(r * nc + c, (r - 1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r + 1][c] == '1') {
                        //注意这里grid[][]==1遍历到之后 不能置0 将当前元素合并到集合的动作相当于置0遍历过的意思
                        uf.union(r * nc + c, (r + 1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c + 1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }
        return uf.count;
    }

    /**
     * 方法三 并查集 一个点向外扩散 作为一个集合
     * 区别DFS和BFS 此方法是多个点向外扩散 能够union的合并在一起
     * 而DFS和BFS是单个点向外扩散 直到无法扩散后 再寻找下一个点
     */
    class UnionFind {
        int count;
        int[] parent;
        //标记当前节点属于的集合代数 代数越大 则代数小的集合归并到代数大的集合
        int[] rank;

        //初始化
        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            //将二维数组转化为一维数组
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                    rank[i * n + j] = 0;
                }
            }

        }

        /**
         * 递归找到扩散中心点（root）
         *
         * @param i
         * @return
         */
        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        /**
         * 目的是结合两个点作为一个集合
         *
         * @param x grid[i][j]对应的一维数组下标
         * @param y grid[m][n]对应的一维数组下标
         */
        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {

                /*
                *按秩合并 
                * parent[rooty] = rootx;
                *可以直接归并 不用记录代数
                * 设置代数的目的 减少递归层数
                * 让新的代数指向旧的代数 防止旧代数链过长
                * 相当于平衡树枝
                * */
                //合并
                if (rank[rootx] > rank[rooty]) {
                    //如果rootx所在集合先产生 则将rooty所在集合归并到rootx中
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    //数组实现的指针 相当于将数组下标对应值指向另一个数组下标
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx]++;
                }
                //count记录1的个数
                //合并一次 1集合数少1
                //最后遍历完后 count数就是无法union到一起的集合数
                count--;
            }

        }

        public int getCount() {
            return count;
        }
    }

}

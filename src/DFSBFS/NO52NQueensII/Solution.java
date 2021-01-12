package DFSBFS.NO52NQueensII;

public class Solution {

    /**
     * 矩阵特点 主对角元素i-j=常数
     * 副对角元素i+j=常数
     * 尽量不要使用hashMap 我们可以确定要开辟的数组大小 用数组代替hashMap
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        //设置vistited
        //行遍历
        int[] rowsVisited = new int[n];
        //主对角线遍历 注意：主对角元素相减有负数 因此要将0中点向右平移到n中点 保证数组不越界
        int[] mainDiaVisited = new int[2 * n];
        //副对角线遍历
        int[] subDiaVisited = new int[2 * n];
        return DFS(0, 0, 0, n, rowsVisited, mainDiaVisited, subDiaVisited);
    }

    private int DFS(int row, int col, int count, int n,
                    int[] rowsVisited,
                    int[] mainDiaVisited,
                    int[] subDiaVisited) {
        for (int i = 0; i < n; i++) {
            if (isVisted(row, i, n, rowsVisited, mainDiaVisited, subDiaVisited)) {
                continue;
            }
            if (row == n - 1) {
                count++;
            } else {
                rowsVisited[i] = 1;
                mainDiaVisited[row - i + n] = 1;
                subDiaVisited[row + i] = 1;
                count = DFS(row + 1, 0, count, n, rowsVisited, mainDiaVisited, subDiaVisited);
                subDiaVisited[row + i] = 0;
                mainDiaVisited[row - i + n] = 0;
                rowsVisited[i] = 0;
            }
        }
        return count;
    }

    private boolean isVisted(int row, int i, int n, int[] rowsVisited, int[] mainDiaVisited, int[] subDiaVisited) {
        if (rowsVisited[i] == 1 || mainDiaVisited[row - i + n] == 1 || subDiaVisited[row + i] == 1) {
            return true;
        }
        return false;
    }

    /**
     * 上一种方法我们用的数组代替hashMap的遍历集合
     * 如果我们用位操作代替数组 那么速度将会更快
     *
     * @param n
     * @return
     */
    public int totalNQueensForBitmap(int n) {
        return backTrcacingForBitMap(0, 0, 0, 0, 0, n);
    }

    public int backTrcacingForBitMap(int row, int count,
                                     int rowVisited,
                                     int mainDiaVisited,
                                     int subDiaVisted,
                                     int n) {
        //当前行初始化
        //如果n为4 1左移4位10000 再减1 得到1111
        int initalRow = (1 << n) - 1;
        //如果row已近等于n 说明dfs到尽头 是一个解(注意 row是从0开始的)
        if (row == n) {
            return ++count;
        }
        //遍历集合对当前行进行筛选
        //注意 initialRow的1表示未占用 遍历集合的1表示已经占用
        int curRow = initalRow & ~(rowVisited | mainDiaVisited | subDiaVisted);
        while (curRow != 0) {
            //遍历当前行的所有可行列
            //!!!!!!!!!!!!技巧<------------找到从右向左的第一个1
            //方法：当前元素取相反数（不是~取反） 然后再与当前元素做与操作
            // 原因：取相反数 所有位取反再加一
            int tempRow = -curRow & curRow;
            //更新遍历集合（这里没有采用主对角 副对角元素坐标间的关系）因为
            //位操作我们是无法得到当前1是在第几位的
            //但是！！移位操作却很方便！！！
            //每往下走一行 对角元素都会往左边或者右边（主对角 副对角）移动一位

            //rowVisited|=tempRow;!!!!!不要在当前层改变遍历集合 在递归的传入
            //参数时再改变 因为传入非引用类型的数据时 是拷贝的一份数据 因此我们
            // 可以免去递归结束后归位遍历集（把1置0）的情况
            count = backTrcacingForBitMap(row + 1, count, rowVisited | tempRow, (mainDiaVisited | tempRow) >>> 1, (subDiaVisted | tempRow) << 1, n);
            //当前列遍历后 需要更新curRow
            //注意这里的技巧：
            //tempRow为0000100 取反后 1111011
            //再与curRow与操作 那么当前遍历的（0000100）位就会将对应的curRow上的位置0
            curRow &= ~tempRow;
            //注意这里也可以用异或^操作 curRow^=tempRow
        }
        return count;
    }

}

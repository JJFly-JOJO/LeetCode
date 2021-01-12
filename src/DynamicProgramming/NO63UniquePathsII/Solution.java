package DynamicProgramming.NO63UniquePathsII;

import java.util.Arrays;

public class Solution {

    public int uniquePathsWithObstaclesForMySolution(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)//如果起点为1则解为0
            return 0;
        int m = obstacleGrid.length;//row
        int n = obstacleGrid[0].length;//column
        /*if (obstacleGrid[m - 1][n - 1] == 1)//终点为1 解同样为0
            return 0;*/
        int[] cur = new int[n];
        //初始化cur 若obstacle第一行存在障碍物 则cur之后元素都为0
        Arrays.fill(cur, 1);
        /*for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                //Arrays.fill(cur,0,i,1);
                Arrays.fill(cur, i, n, 0);
                break;
            }
        }*/
        //Arrays.fill(cur, 1);//第一行结果 边界解数都是1
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 1) {
                    cur[j] = 0;//上下有障碍物 没有解的可能
                    if (j == 1)
                        cur[j - 1] = 0;//排除第一列（边界）存在障碍物的情况 第一列存在障碍物 则之后解数都为0
                    if (i == 1) {
                        Arrays.fill(cur, j, n, cur[j - 1]);
                        break;
                    }
                } else if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 0) {
                    //上有障碍物 则当前元素之后元素全部为当前值
                    //cur[j] = cur[j - 1];
                    //第一排存在障碍物情况
                    if (i == 1) {
                        Arrays.fill(cur, j, n, cur[j - 1]);
                        break;
                    }
                    cur[j] = cur[j - 1];
                } else if (obstacleGrid[i - 1][j] == 0 && obstacleGrid[i][j - 1] == 1) {
                    //左有障碍物 则仅仅是当前元素受到影响 下一个元素依然保证与上下元素关联
                    //如果左边界有障碍物 则之后的cur[0]都为0
                    if (j == 1) {
                        cur[j - 1] = 0;
                    }
                    //cur[j] = cur[j];
                } else {
                    cur[j] = cur[j] + cur[j - 1];
                }
                if (obstacleGrid[i][j] == 1)
                    cur[j] = 0;
            }
            //[[0,0],[1,1],[0,0]]情况 需要考虑到每一行的结束元素是否有障碍物
        }
        return cur[n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)//如果起点为1则解为0<--------------------不为0 起点需要初始化为1！！！！！！！！
            return 0;
        int m = obstacleGrid.length;//row
        int n = obstacleGrid[0].length;//column
        int[] cur = new int[n];
        cur[0] = 1;//<-------------------------------起点初始化！！！！！！！！！！
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    cur[j] = 0;
                    //continue;
                } else if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0)
                    cur[j] += cur[j - 1];//上面存在障碍 或者不存在障碍都属于这一种情况
            }
        return cur[n - 1];
    }

}

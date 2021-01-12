package WeekContest.SingleWeek.NO213;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/1 11:48
 * @description
 */
public class Solution4 {

    public static void main(String[] args) {
        int[] destination=new int[]{2,3};
        System.out.println(new Solution4().kthSmallestPath(destination,1));
    }

    private int row = 0;

    private int col = 0;

    private int count = 0;

    private int res;

    public String kthSmallestPath(int[] destination, int k) {
        row = destination[0] + 1;
        col = destination[1] + 1;
        boolean[][] isVisited = new boolean[row][col];
        dfs(0, 0, isVisited, k, 0);
        StringBuilder str = new StringBuilder();
        int len = row + col - 2;
        int mask = 1 << (len - 1);
        while (mask > 0) {
            if ((mask & res) == 0) {
                str.append("H");
            } else {
                str.append("V");
            }
            mask = mask >>> 1;
        }
        return str.toString();
    }

    private void dfs(int r, int c, boolean[][] isVisited, int k, int path) {
        if (k == count) {
            return;
        }
        if (isVisited[r][c]) {
            return;
        }
        if (r == row - 1 && c == col - 1) {
            if (++count == k) {
                res = path;
            }
            return;
        }

        isVisited[r][c] = true;
        if (c + 1 < col) {
            dfs(r, c + 1, isVisited, k, path << 1);
        }
        if (r + 1 < row) {
            dfs(r + 1, c, isVisited, k, (path << 1) + 1);
        }
        isVisited[r][c] = false;
    }

}

package WeekContest.SingleWeek.NO224;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/17 11:13
 * @description
 */
public class SolutionIV {

    private char[][] matrix;

    private int[] mousePos;

    private int[] catPos;

    private int[] foodPos;

    private int row;

    private int col;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        matrix = new char[grid.length][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = grid[i].toCharArray();
        }
        row = matrix.length;
        col = matrix[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == 'C') {
                    catPos = new int[]{r, c};
                }
                if (matrix[r][c] == 'M') {
                    mousePos = new int[]{r, c};
                }
                if (matrix[r][c] == 'F') {
                    foodPos = new int[]{r, c};
                }
            }
        }
        for (int i = 0; i < 1000; i++) {
            if (mouseMove(catJump, mouseJump)) {
                return true;
            }
            if (catMove(catJump, mouseJump)) {
                return true;
            }
        }
        return false;
    }

    private boolean mouseMove(int catJump, int mouseJump) {

        int up = mousePos[0] - mouseJump;
        while (up < 0 || matrix[up][mousePos[1]] == '#'
                || (up == catPos[0] && (mousePos[1] >= catPos[1] - catJump && mousePos[1] <= catPos[1] + catJump)
                || (mousePos[1] == catPos[1] && (up >= catPos[0] - catJump && up <= catPos[0] + catJump)))) {
            up++;
        }
        int down = mousePos[0] + mouseJump;
        while (down >= row || matrix[down][mousePos[1]] == '#'
                || (down == catPos[0] && (mousePos[1] >= catPos[1] - catJump && mousePos[1] <= catPos[1] + catJump)
                || (mousePos[1] == catPos[1] && (up >= catPos[0] - catJump && up <= catPos[0] + catJump)))) {
            down--;
        }
        int right = mousePos[1] + mouseJump;
        while (right >= col || matrix[right][mousePos[1]] == '#'
                || (down == catPos[0] && (mousePos[1] >= catPos[1] - catJump && mousePos[1] <= catPos[1] + catJump)
                || (mousePos[1] == catPos[1] && (up >= catPos[0] - catJump && up <= catPos[0] + catJump)))) {
            down--;
        }
        int left = 0;
        return false;
    }

    private boolean catMove(int catJump, int mouseJump) {
        return true;
    }

}

package LeetCodeCup;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/5 15:13
 * @description
 */
public class SolutionII {

    public static void main(String[] args) {
        SolutionII s2 = new SolutionII();
        System.out.println(s2.orchestraLayout(4, 3, 2));
        int num = 6;
        //int[][] nums = new int[num][num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(s2.orchestraLayout(num, i, j) + " ");
            }
            System.out.println();
        }
        /*for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }*/
    }

    public int orchestraLayout(int num, int xPos, int yPos) {
        long top = 0;
        long bottom = num - 1;
        long left = 0;
        long right = num - 1;
        long edges = Math.min(Math.min(xPos - top, bottom - xPos), Math.min(yPos - left, right - yPos));
        long count = (num * 4 - 4 + (num - (edges - 1) * 2) * 4 - 4) * edges / 2;
        top += edges;
        bottom -= edges;
        left += edges;
        right -= edges;
        if (xPos == top) {
            count += yPos - left + 1;
            return getPos(count);
        }
        if (yPos == right) {
            count += right - left + 1 + xPos - top;
            return getPos(count);
        }
        if (xPos == bottom) {
            count += (right - left + 1) * 2 - 1 + right - yPos;
            return getPos(count);
        }
        count += (right - left + 1) * 3 - 2 + bottom - xPos;
        return getPos(count);
    }

    private int getPos(long count) {
        long mod = count % 9;
        return (int) (mod == 0 ? 9 : mod);
    }

    public int orchestraLayoutI(int num, int xPos, int yPos) {
        long top = 0L;
        long bottom = num - 1L;
        long left = 0L;
        long right = num - 1L;
        long count = 0L;
        long edge = num;

        long val = Math.min(Math.min(xPos - top, bottom - xPos), Math.min(yPos - left, right - yPos));
        long edge1Sum = edge * 4 - 4;
        long edge2Sum = (edge - (val - 1) * 2) * 4 - 4;
        count = (edge1Sum + edge2Sum) * val / 2 % 9;
        top += val;
        bottom -= val;
        left += val;
        right -= val;

        /*while (xPos != top && xPos != bottom && yPos != left && yPos != right) {
            count += (edge * 4 - 4) % 9;
            top++;
            bottom--;
            left++;
            right--;
            edge -= 2;
        }*/
        if (xPos == top) {
            count += yPos - left + 1;
            int mod = (int) (count % 9);
            return mod == 0 ? 9 : mod;
        }
        if (yPos == right && xPos > top && xPos < bottom) {
            count += edge + xPos - top;
            int mod = (int) (count % 9);
            return mod == 0 ? 9 : mod;
        }
        if (xPos == bottom && bottom != top) {
            count += edge + edge - 2 + right - yPos + 1;
            int mod = (int) (count % 9);
            return mod == 0 ? 9 : mod;
        }
        if (yPos == left && xPos > top && xPos < bottom && left != right) {
            count += edge + edge - 2 + edge + bottom - xPos;
            int mod = (int) (count % 9);
            return mod == 0 ? 9 : mod;
        }
        int mod = (int) (count % 9);
        return mod == 0 ? 9 : mod;
    }

}

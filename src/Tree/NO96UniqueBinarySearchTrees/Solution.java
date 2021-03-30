package Tree.NO96UniqueBinarySearchTrees;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/1 23:17
 * @description ---------带有记忆的分治解法--------
 */
public class Solution {

    private int[][] mem;

    public int numTrees(int n) {
        mem = new int[n + 1][n + 1];
        return dividing(1, n);
    }

    private int dividing(int l, int r) {
        if (l >= r) {
            return 1;
        }
        if (mem[l][r] > 0) {
            return mem[l][r];
        }
        int sum = 0;
        for (int i = l; i <= r; i++) {
            int leftSum = dividing(l, i - 1);
            int rightSum = dividing(i + 1, r);
            sum += leftSum * rightSum;
        }
        mem[l][r] = sum;
        return sum;
    }

}

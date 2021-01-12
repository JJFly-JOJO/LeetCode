package Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/9 19:33
 * @description
 */
public class HomeWorkSoluton2V2 {

    private double res;

    public static void main(String[] args) {
        double[] pRed = new double[]{0.5, 0.4, 0.7};
        double[] p1 = new double[]{0.2, 0.4, 0.4};
        double[][] p2 = new double[][]{{0.5, 0.2, 0.3}, {0.3, 0.5, 0.2}, {0.2, 0.3, 0.5}};

        //连续取出红球概率
        double p = new HomeWorkSoluton2V2().getRedProbablity(pRed, p1, p2, 15);
        System.out.println("连续取出红球概率 p=" + p);
    }

    private double getRedProbablity(double[] pRed, double[] p1, double[][] p2, int n) {
        for (int i = 0; i < 3; i++) {
            dfs(i, p2, pRed, n - 1, p1[i] * pRed[i]);
        }
        return res;
    }

    private void dfs(int curBox, double[][] p2, double[] pRed, int n, double p) {
        if (n == 0) {
            res += p;
            return;
        }
        for (int i = 0; i < 3; i++) {
            dfs(i, p2, pRed, n - 1, p * pRed[i] * p2[curBox][i]);
        }
    }
}

package Util;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/29 11:07
 * @description --------------暴力DFS-------------
 */
public class HomeWorkSolution2 {

    private double probability = 0;
    /**
     * 记录当前取的次数
     */
    private int count = 0;

    public static void main(String[] args) {
        int[][] box = new int[][]{{5, 5}, {4, 6}, {7, 3}};
        //int[][] box = new int[][]{{0, 0}, {1, 0}, {0, 0}};

        double[] p1 = new double[]{0.2, 0.4, 0.4};
        double[][] p2 = new double[][]{{0.5, 0.2, 0.3}, {0.3, 0.5, 0.2}, {0.2, 0.3, 0.5}};
        //double[][] p2 = new double[][]{{0.5, 0.3, 0.2}, {0.2, 0.5, 0.3}, {0.3, 0.2, 0.5}};

        //连续取出红球概率
        double p = new HomeWorkSolution2().getRedProbablity(box, p1, p2, 15);
        System.out.println("连续取出红球概率 p=" + p);
        //int[] temp = new int[]{5, 5};
        //System.out.println((double) temp[0]-- / (temp[0] + temp[1]));
    }

    private double getRedProbablity(int[][] box, double[] p1, double[][] p2, int c) {
        count = c;
        //第一次取
        for (int i = 0; i < p1.length; i++) {
            double p = chooseRedOfBox(box[i], p1[i]);
            count--;
            dfs(box, i, p2, p);
            box[i][0]++;
            count++;
        }
        return probability;
    }

    /**
     * @param boxs
     * @param curBox 当前选择的box下标
     * @param p2
     * @param p      当前概率
     */
    private void dfs(int[][] boxs, int curBox, double[][] p2, double p) {
        if (count == 0) {
            probability += p;
            return;
        }
        for (int i = 0; i < boxs.length; i++) {
            if (boxs[i][0] > 0) {
                double tp = chooseRedOfBox(boxs[i], p2[curBox][i]);
                count--;
                dfs(boxs, i, p2, tp * p);
                count++;
                boxs[i][0]++;
            }
        }
    }

    /**
     * @param box
     * @param p   选择当前box的概率
     * @return
     */
    private double chooseRedOfBox(int[] box, double p) {
        double sum = box[0] + (double) box[1];
        return p * box[0]-- / sum;
    }
}

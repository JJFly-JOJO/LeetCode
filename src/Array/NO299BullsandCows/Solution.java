package Array.NO299BullsandCows;

public class Solution {

    /**
     * ----------互相证明存在（self）-------
     *
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        //题目 保证二者 长度相等
        int length = secret.length();
        int bullsNum = 0;
        int cowsNum = 0;
        //----------secret遍历到的为1 guess遍历到的为-1 二者相遇为0 数组初始化数值为10
        int[] isVisited = new int[10];

        for (int i = 0; i < length; i++) {
            int sNum = secret.charAt(i) - '0';
            int gNum = guess.charAt(i) - '0';
            if (sNum == gNum) {
                bullsNum = bullsNum + 1;
            } else {
                //secret
                if (isVisited[sNum] < 0) {
                    cowsNum = cowsNum + 1;
                }
                isVisited[sNum] = isVisited[sNum] + 1;
                /*if (isVisited[sNum] == 0) {
                    isVisited[sNum] = 1;
                } else if (isVisited[sNum] == -1) {
                    cowsNum = cowsNum + 1;
                    //设置为相遇 否则会出现重复数字都被记入成奶牛的情况
                    isVisited[sNum] = 0;
                }*/
                //guess
                if (isVisited[gNum] > 0) {
                    cowsNum = cowsNum + 1;
                }
                isVisited[gNum] = isVisited[gNum] - 1;
                /*if (isVisited[gNum] == 0) {
                    isVisited[gNum] = -1;
                } else if (isVisited[gNum] == 1) {
                    cowsNum = cowsNum + 1;
                    isVisited[gNum] = 0;
                }*/
            }
        }
        return bullsNum + "A" + cowsNum + "B";
    }

}

package Offer.NO64;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/14 11:27
 * @description
 */
public class Solution {

    public int sumNums(int n) {
        //利用&& ||运算短路的思路
        boolean flag = (n > 0) && ((n += sumNums(n - 1)) > 0);
        return n;
    }

}

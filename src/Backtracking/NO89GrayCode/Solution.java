package Backtracking.NO89GrayCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/11 9:39
 * @description --------------格雷编码 镜像反射的方法 从规模n=0的格雷编码开始往n=i递推--------------
 */
public class Solution {

    public static void main(String[] args) {
        String n = "0010";
        System.out.println(Integer.parseInt(n));
    }

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        int head = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            head <<= 1;
        }
        return res;
    }

}

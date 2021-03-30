package Offer.NO62;

import java.util.ArrayList;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/3/11 21:31
 * @description
 */
public class Solution {

    public int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (list.size() > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

}

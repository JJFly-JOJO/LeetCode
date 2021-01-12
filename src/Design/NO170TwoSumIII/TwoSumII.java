package Design.NO170TwoSumIII;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/11 14:11
 * @description
 */
public class TwoSumII {

    List<Integer> list;
    private Map<Integer, Integer> nToC;
    private boolean sort = false;
    /**
     * Initialize your data structure here.
     */
    public TwoSumII() {
        nToC = new HashMap<>();
        list = new ArrayList<>();
    }

    public static void main(String[] args) {
        TwoSumII t = new TwoSumII();
        t.add(3);
        t.add(2);
        t.add(1);
        System.out.println(t.find(2));
    }

    /**
     * Add the number to an internal data structure..
     */
    public void add(int number) {
        sort = false;
        list.add(number);
        nToC.put(number, nToC.getOrDefault(number, 0) + 1);
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        if (!sort) {
            Collections.sort(list);
            sort = true;
        }
        int index = binarySearch(list, value / 2 + 1);
        for (int i = 0; i <= index && i < list.size(); i++) {
            int rest = value - list.get(i);
            Integer count = nToC.get(rest);
            if (count != null) {
                if (rest == list.get(i)) {
                    if (count > 1) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private int binarySearch(List<Integer> list, int target) {
        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (list.get(m) > target) {
                r = m;
            } else if (list.get(m) < target) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return l;
    }
}

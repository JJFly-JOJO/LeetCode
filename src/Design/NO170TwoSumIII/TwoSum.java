package Design.NO170TwoSumIII;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/11 10:49
 * @description
 */
public class TwoSum {

    private Map<Integer, Integer> numMap = new HashMap<>();

    private TreeSet<Integer> numTree = new TreeSet<>((o1, o2) -> o1.equals(o2) ? 1 : o1 - o2);

    /**
     * Initialize your data structure here.
     */
    public TwoSum() {

    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.add(0);
        twoSum.add(0);
        twoSum.add(0);
        System.out.println(twoSum.find(0));
    }

    /**
     * Add the number to an internal data structure..
     */
    public void add(int number) {
        numTree.add(number);
        numMap.put(number, numMap.getOrDefault(number, 0) + 1);
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        //Set<Integer> sub = numTree.subSet((int) -1e5, true, value / 2, true);
        for (int i : numTree.subSet((int) -1e5, true, value / 2 + 1, true)) {
            Integer count = numMap.get(value - i);
            if (count != null) {
                if (i == value - i) {
                    return count > 1;
                }
                return true;
            }
        }
        return false;
    }

}

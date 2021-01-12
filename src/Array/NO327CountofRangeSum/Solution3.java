package Array.NO327CountofRangeSum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/29 16:12
 * @description -------------前缀数组 + 利用平衡树（二分查找）-----------
 * 注意：这里我们不使用TreeSet 而是使用TreeMap 因为前缀和可能存在相同值（hash相同的情况）因此
 */
public class Solution3 {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add("1");
        System.out.println(list.get(0) instanceof Integer);
        // erro:: System.out.println(list.get(0) - 1);
        System.out.println((Integer) list.get(0) - 1);
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "a");
        treeMap.put(2, "b");
        treeMap.put(3, "c");
        treeMap.put(4, "d");
        treeMap.put(5, "e");
        treeMap.put(6, "f");
        Map<Integer, String> subMap = treeMap.subMap(2, true, 4, true);
        subMap.put(3, "change");
        System.out.println(treeMap.get(3));
    }

    public int countRangeSum(int[] a, int lower, int upper) {
        TreeMap<Long, Integer> subToCount = new TreeMap<>();
        int res = 0;
        //初始化 要将sum[j]-sum[i-1]中j-0的subSum情况考虑到
        subToCount.put(0L, 1);
        long sum = 0L;
        for (int t : a) {
            sum += t;
            //注意！！-------------------------------------获得的subMap不是copy的一个新的TreeMap 而是原来treeMap的一部分
            for (int c : subToCount.subMap(sum - upper, true, sum - lower, true).values()) {
                res += c;
            }
            subToCount.put(sum, subToCount.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

}

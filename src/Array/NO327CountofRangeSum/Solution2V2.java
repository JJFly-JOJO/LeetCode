package Array.NO327CountofRangeSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/21 17:19
 * @description ---------------------树状数组实现------------------
 * ------------------low<=sum[j]-sum[i]<=up 类似动态规划思想-------------
 * 注意：
 * 1.树状数组对应的原来元素下标都是从1开始
 */
public class Solution2V2 {

    private int N;
    private int[] treeArray;

    public int countRangeSum(int[] a, int lower, int upper) {
        int res = 0;
        List<Long> l = new ArrayList<>();
        long sum = 0L;
        l.add(sum);
        for (int t : a) {
            sum += t;
            long low = sum - lower;
            long up = sum - upper;
            l.add(sum);
            l.add(low);
            l.add(up);
        }
        //排序
        Collections.sort(l);
        //去重 离散化 离散化是将值转换为下标 下标之间顺序反应了值之间的大小关系
        List<Long> list = unique(l);
        //创建树状数组 注意树状数组下标都是从1开始
        N = list.size() + 1;
        treeArray = new int[N];
        //动态更新树状数组 以及 更新结果
        update(binaryS(list, 0) + 1, 1);
        sum = 0;
        for (int t : a) {
            sum += t;
            int lowIndex = binaryS(list, sum - lower) + 1;
            int upperIndex = binaryS(list, sum - upper) + 1;
            res += getSum(lowIndex) - getSum(upperIndex - 1);
            update(binaryS(list, sum) + 1, 1);
        }
        return res;
    }

    /**
     * 二分查找 用于找到值对应的下标
     *
     * @param list
     * @param target
     * @return
     */
    private int binaryS(List<Long> list, long target) {
        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (list.get(mid) > target) {
                r = mid;
            } else if (list.get(mid) < target) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        //此处不存在找不到的情况
        return l;
    }

    private List<Long> unique(List<Long> l) {
        List<Long> list = new ArrayList<>();
        int size = l.size();
        if (size == 0) {
            return list;
        }
        list.add(l.get(0));
        for (int i = 1; i < size; i++) {
            if (!l.get(i).equals(l.get(i - 1))) {
                list.add(l.get(i));
            }
        }
        return list;
    }

    private int lowBit(int i) {
        return i & (-i);
    }

    /**
     * i下标是与treeArray下标对应 从1开始
     *
     * @param i
     * @param k
     */
    private void update(int i, int k) {
        while (i < N) {
            treeArray[i] += k;
            i += lowBit(i);
        }
    }

    private int getSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += treeArray[i];
            i -= lowBit(i);
        }
        return sum;
    }

}

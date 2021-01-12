package Array.NO327CountofRangeSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/17 19:04
 * @description
 */
class Solution2 {

    int N;
    int[] tree;

    public static void main(String[] args) {
        System.out.println(new Solution2().countRangeSum(new int[]{-2, 5, -1}, -2, 2));
    }

    public int countRangeSum(int[] a, int lower, int upper) {
        // lower <= sum[j] - sum[i - 1] <= upper
        // sum[j] - upper <= sum[i - 1] <= sum[j] - lower
        List<Long> ys = new ArrayList();
        ys.add(0L); // sum[i - 1]有可能等于0
        long sum = 0, n = a.length;
        for (int value : a) {
            sum += value;
            ys.add(sum);
            ys.add(sum - upper);
            ys.add(sum - lower);
        }
        //排序，去重，二分查找 离散化的常规操作。
        Collections.sort(ys);
        ys = unique(ys);
        N = ys.size();
        tree = new int[N + 1];   //这里树状数组的下标从1开始
        add(binaryFind(ys, 0) + 1, 1);  //加入前缀和为0的情况
        sum = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            int left = binaryFind(ys, sum - upper) + 1;
            int right = binaryFind(ys, sum - lower) + 1;
            ans += query(right) - query(left - 1);
            add(binaryFind(ys, sum) + 1, 1);
        }
        return ans;
    }

    public int binaryFind(List<Long> list, long target) {
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (list.get(mid) >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    public void add(int x, int c) {
        for (int i = x; i <= N; i += lowBit(i)) tree[i] += c;
    }

    public int query(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowBit(i)) res += tree[i];
        return res;
    }

    public int lowBit(int x) {
        return x & -x;
    }


    public List<Long> unique(List<Long> list) {
        List<Long> res = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (res.isEmpty() || res.get(res.size() - 1) != list.get(i)) {
                res.add(list.get(i));
            }
        }
        return res;
    }


}


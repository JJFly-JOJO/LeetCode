package DFSBFS.NO364NestedListWeightSumII;

import DFSBFS.NO339NestedListWeightSum.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/31 15:57
 * @description ------------------错误思路当前层数对应的权值是由最深的一条dfs路径决定的 而非所有路径的叶子节点都是1---
 */
public class Solution {

    private int sum = 0;

    public int depthSumInverse(List<NestedInteger> nestedList) {
        dfs(nestedList);
        return sum;
    }

    private int dfs(List<NestedInteger> nestedList) {
        List<Integer> intList = new ArrayList<>();
        int depth = 1;
        for (NestedInteger t : nestedList) {
            if (t.isInteger()) {
                intList.add(t.getInteger());
            } else {
                depth = Math.max(depth, dfs(t.getList()));
            }
        }
        int val = 0;
        for (int t : intList) {
            val += t;
        }
        sum += val * depth;
        return depth + 1;
    }
}

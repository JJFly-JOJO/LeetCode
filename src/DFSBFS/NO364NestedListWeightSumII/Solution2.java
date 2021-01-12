package DFSBFS.NO364NestedListWeightSumII;

import DFSBFS.NO339NestedListWeightSum.NestedInteger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/31 16:01
 * @description
 */
public class Solution2 {

    /**
     * 1.BFS 严格层序遍历 2.用stack记录下每层叶子值的sum 最后再遍历stack计算总和
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        //初始化 注意这里输入的nestedList 类型是list类型而非NestedInteger类型
        Queue<NestedInteger> queue = new ArrayDeque<>(nestedList);
        Stack<Integer> stack = new Stack<>();
        //bfs
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger t = queue.poll();
                if (t.isInteger()) {
                    sum += t.getInteger();
                } else {
                    for (NestedInteger temp : t.getList()) {
                        queue.add(temp);
                    }
                }
            }
            stack.add(sum);
        }
        //计算sum
        int sum = 0;
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            sum += (i + 1) * stack.pop();
        }
        return sum;
    }

}

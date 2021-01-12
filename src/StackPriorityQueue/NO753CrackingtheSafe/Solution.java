package StackPriorityQueue.NO753CrackingtheSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/6 16:21
 * @description ---------------Hierholzer 算法遍历欧拉图 -----------------
 * 类比NO332 332中的有向图中可能存在且只存在一个入度与出度差1的节点 可能为欧拉图也可能为半欧拉图
 * 该题的有向图出度与入度相等 属于欧拉图
 *
 * 欧拉图特点：1.有回路那么dfs会再回到当前节点，走到死胡同一定是最后加入的节点
 * 2.所有边都能走到 也就是说 当前节点有n个边 只要边没有遍历完 走任意一条边都能走回到当前节点 然后走下一条边
 *
 * dfs:加入当前节点的条件是当前节点的所有出度都已经走完
 *
 */
public class Solution {

    Set<Integer> isVisited = new HashSet<>();
    StringBuilder path = new StringBuilder();
    int mod;
    int K;

    public String crackSafe(int n, int k) {
        mod = (int) Math.pow(10, n - 1);
        K = k;
        //思考：为什么不能正向dfs 也就是每dfs一个节点就先将当前节点加入
        dfs(0);
        for (int i = 1; i < n; i++) {
            path.append(0);
        }
        return path.toString();
    }

    private void dfs(int node) {
        for (int i = 0; i < K; i++) {
            int nodeToEdge = node * 10 + i;
            if (!isVisited.contains(nodeToEdge)) {
                isVisited.add(nodeToEdge);
                int curNode = nodeToEdge % mod;
                dfs(curNode);
                //思考：为什么不能正向dfs 也就是每dfs一个节点就先将当前节点加入
                //类比332 对于起始节点u 有u->a->b->...->u最终该欧拉回路要回到u
                // 如果我们正向dfs，也就是遇到一个节点先加入到结果集中 那么会出现先把回路回到的u加入到结果集中
                //也就是先该u是欧拉路径的最后一个节点(u->....->u...->u(最后一个节点))，而此节点之后不应该
                // 连接新的节点，但是由于正向dfs造成后面又连接了新的节点，造成答案错误
                //因此先dfs（相当于二叉树的后序遍历）当当前节点的next节点都已经遍历过了 我们再将当前节点加入结果集
                //因为此时加入结果集的此节点 一定是欧拉路径中的最后一个节点（遍历所有边后走到的最后一个顶点）
                //这样得到的结果是一个逆序结果而已
                path.append(i);
            }
        }
    }

}

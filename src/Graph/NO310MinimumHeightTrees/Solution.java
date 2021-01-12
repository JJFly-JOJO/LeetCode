package Graph.NO310MinimumHeightTrees;


import java.util.*;

/**
 * --------------------------------笔记-----------------------------
 * ----------可能的思路？（self）
 * DFS找到最长链 取中点
 */
public class Solution {

    /**
     * 分析（self）：
     * 图与树的区别点 对于度（无向图没有入度出度之分）为1的节点（也可以理解为入度为0）
     * 对于树的叶子节点 也可以理解为入度为1 当然根节点入度也为1
     * 对于此题要找最小高度的树 那我们可以以入度为1的根节点一层一层向内出发 直到到达最终各个点相遇处
     * 那么这个点就是作为根节点能满足的最小高度树
     * 类比:
     * 直线段从两端向中点走(hop) 一直跳到相与处 自然是中点
     * 证明：
     * 能成为根节点的点 要么就是入度为1的点 要么就是图中的点 如果是图中的点 我们可以确定 入度为1的点
     * 都是叶子节点 那么如果我们要保证树高度最小 我们要让这些叶节点平分节点（不平分必然会出现一个枝的节点
     * 数量小于平均值 而另一个枝的节点数量大于平均值 自然也就不是最小高度 极端情况 就选叶节点作为根节点的树）
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //记录入度
        int[] inDegree = new int[n];
        //result
        List<Integer> result = new ArrayList<>();
        //特殊情况 单节点
        if (n == 1) {
            result.add(0);
            return result;
        }
        //graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        //initialize
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            inDegree[node1]++;
            inDegree[node2]++;
            Set<Integer> tempSet = graph.getOrDefault(node1, new HashSet<>());
            tempSet.add(node2);
            graph.put(node1, tempSet);
            tempSet = graph.getOrDefault(node2, new HashSet<>());
            tempSet.add(node1);
            graph.put(node2, tempSet);
        }
        //记录当前层数 节点要与层数对应 最后找到同层的作为根节点的解
        int[] levelForNode = new int[n];
        //queue for BFS
        Queue<Integer> queueForBFS = new LinkedList<>();
        //初始化入队
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 1) {
                queueForBFS.add(i);
                levelForNode[i] = levelForNode[i] + 1;
            }
        }
        int lastNode = 0;
        int beforeNode = queueForBFS.peek();
        //BFS
        //遇见中点只有两种情况 奇数 单点 偶数 双点
        while (true) {
            lastNode = queueForBFS.poll();
            inDegree[lastNode] = inDegree[lastNode] - 1;
            Set<Integer> curSet = graph.get(lastNode);
            for (int tempNode : curSet) {
                if ((inDegree[tempNode] = inDegree[tempNode] - 1) == 1) {
                    queueForBFS.add(tempNode);
                    levelForNode[tempNode] = levelForNode[lastNode] + 1;
                }
            }
            if (queueForBFS.isEmpty()) {
                break;
            }
            beforeNode = lastNode;
        }
        result.add(lastNode);
        if (levelForNode[lastNode] == levelForNode[beforeNode]) {
            result.add(beforeNode);
        }
        return result;
    }

    /**
     * ----------------------时间复杂度最低解法----------------------
     * 思路：DFS找到最长链
     * <p>
     * 如何构建领接表？
     * 我们需要把每个节点之间的连接关系都表达出来吗？对于此图 能够成为一棵树 那么一定不存在环 也就是说
     * 每个节点与边相结合 那么我们就可以直接利用输入的edges[][]参数 每个节点只记录一条边信息 也就是说
     * 只能从当前节点获得前驱节点 只需要这个信息就能完成DFS
     * 如何找到最长链？
     * 对于此数据结构 每个节点与一条边配套 那么会有一个节点是独立出来的 我们以此节点为根节点 进行DFS 可以找到
     * 从此节点出发的一条最长链 但是这条最长链不一定是最终所要找的最长链 如果我们从这条最长链的叶子节点出发 进行DFS的话 那么
     * 找到的链一定为最长链
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTreesForDFS(int n, int[][] edges) {
        //result
        List<Integer> res = new ArrayList<>();
        //构建单向领接表
        int[][] graph = new int[n][];
        //能成为树的图 边数=节点数-1
        for (int i = 0; i < n - 1; i++) {
            int nodeA = edges[i][0];
            int nodeB = edges[i][1];
            if (graph[nodeA] == null) {
                graph[nodeA] = edges[i];
            } else {
                graph[nodeB] = edges[i];
            }
        }

        //找到初始根节点 也就是graph中存在有一个节点没有edge 也就是graph[root][]==null
        int root = getRoot(graph);
        //DFS 找到当前根节点下的最长链枝叶end 同时返回从当前root到end的链长度
        int[] rootAndLength = DFS(root, graph);
        //找到从当前root出发的最长链 以及end节点 将此链反向 将end单独出来 root设置前驱节点
        ReverseRootAndEnd(graph, root, rootAndLength[0]);
        //重新找到新的最长链
        rootAndLength = DFS(rootAndLength[0], graph);
        //找到根节点 也就是最长链的中间点 注意长度是从0开始的
        int indexOfRoot = rootAndLength[1] / 2;
        int resultRoot = rootAndLength[0];
        for (int i = 0; i < indexOfRoot; i++) {
            resultRoot = getNextNode(resultRoot, graph[resultRoot]);
        }
        res.add(resultRoot);
        //误区！！！！！！！！！！！！！！！！！！！！！！！！！！！奇数除以2 向下取整为奇数 偶数除以2 可能为奇数 也可能为偶数
        //判断长度是奇数还是偶数<------------------------技巧  注意这里length从0开始的 也就是说 奇数为偶下标 偶数为奇下标
        //奇数与1想与 为1 偶数与1相与 为0!!!!!!!!!!!注意 这里并没有考虑偶数0的情况 这也巧妙的表示了 偶数为0 自然只有一个节点
        //自然是不可以向下搜索
        //----------length从0开始 就可以直接理解成下标！！！
        if ((rootAndLength[1] & 1) == 1) {
            res.add(getNextNode(resultRoot, graph[resultRoot]));
        }
        return res;
    }

    private void ReverseRootAndEnd(int[][] graph, int root, int newRoot) {
        int[] preEdge = null;
        int curNode = newRoot;

        while (curNode != root) {
            int[] tempEdge = graph[curNode];
            graph[curNode] = preEdge;
            preEdge = tempEdge;
            curNode = getNextNode(curNode, preEdge);
        }
        //当前root的edge还为空 因此不能在while循环里面再往下递归到root处 getNextNode会出现空指针异常
        graph[root] = preEdge;
    }

    /**
     *
     * 考虑图只有一个节点的情况 边edges为0
     *
     * @param root
     * @param graph
     * @return
     */
    private int[] DFS(int root, int[][] graph) {
        int n = graph.length;
        //用来记录从当前节点到root所需路径 同时也可以作为节点是否遍历过的标记集合
        int[] lengthFromCurToRoot = new int[n];
        //用栈来记录当前遍历到的节点路径
        int[] stack = new int[n];
        int size = 0;
        //找最长length以及对应的node 初始点为root 以及初始长度为0
        int[] endAndLength = new int[2];
        endAndLength[0] = root;
        endAndLength[1] = 0;
        //int maxLength = 0;
        //int end = root;
        //注意！！！！！！！！！！！对于此单向领接表 所有的节点最终都会指向root<-------------------
        for (int i = 0; i < n; i++) {
            int curNode = i;
            while (curNode != root && lengthFromCurToRoot[curNode] == 0) {
                stack[size++] = curNode;
                //获取下一个指向的节点
                curNode = getNextNode(curNode, graph[curNode]);
            }
            while (size > 0) {
                //依次出栈
                int temp = stack[--size];
                lengthFromCurToRoot[temp] = lengthFromCurToRoot[curNode] + 1;
                curNode = temp;
                //记录当前最长的length 以及对应的当前node
                if (lengthFromCurToRoot[curNode] > endAndLength[1]) {
                    endAndLength[1] = lengthFromCurToRoot[curNode];
                    endAndLength[0] = curNode;
                }
            }
        }
        return endAndLength;
    }

    private int getRoot(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int getNextNode(int curNode, int[] edge) {
        return curNode == edge[0] ? edge[1] : edge[0];
    }


}
    /*public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //记录入度
        int[] inDegree = new int[n];
        //result
        List<Integer> result = new ArrayList<>();
        //graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        //initialize
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            List<Integer> tempList = graph.get(node1);
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(0);
            }
            tempList.set(0, tempList.get(0).intValue() + 1);
            tempList.add(node2);
            graph.put(node1, tempList);
            tempList = graph.get(node2);
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(0);
            }
            tempList.set(0, tempList.get(0).intValue() + 1);
            tempList.add(node1);
            graph.put(node2, tempList);
        }

        //queue for BFS
        Queue<Integer> queueForBFS=new LinkedList<>();
        //初始化入队
        for(Map.Entry<Integer,List<Integer>> temp:graph.entrySet()){
            if(temp.getValue().get(0)==1){
                queueForBFS.add(temp.getKey());
                //result.add(temp.getKey());
            }
        }
        //BFS
        //遇见中点只有两种情况 奇数 单点 偶数 双点
        while (!queueForBFS.isEmpty()){


        }
    }*/


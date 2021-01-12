package Graph.NO133CloneGraph;

import java.util.*;

/**
 * ------------尾递归-----------
 */
public class Solution {

    /**
     * 方法一：DFS
     * 注意我们要克隆一个图 不仅仅是要把节点全部克隆完毕 还需要把边也要克隆
     * 误区：会陷入寻找DFS终止条件的误区 其实遍历完所有的Node 也就终止DFS了（遍历过的节点 不再遍历）
     * 那么你可能会问 如果遍历结束后是否存在有些节点相连（边）的关系没有记录进来
     * <p>
     * 我们要弄清 对于无向连通图 从其中一个点DFS 其实是从中心扩散 也就是说 当前扩散 这个点的所有边是在当前层全部遍历到的
     * DFS到下一层 同样从这个点扩散 但是有一条边会连到上一个点 这条边自然被排除了
     * <p>
     * 难点-尾递归！！！：理解递归的两种获取值的情况
     * 一种是在递归进入到下一层就获取值 每进入一层 就获取一次值 一般这种是在DFS的形参中传入最终要获取的结果
     * 另一种是return DFS 也就是递归到底层 返回时 我们再获取值（返回值） 一般这种是在最终DFS返回的值作为要获取的结果
     *
     * @param node
     * @return
     */
    public Node cloneGraphForDFS(Node node) {
        Map<Node, Node> isVisited = new HashMap<>();
        return DFS(node, isVisited);
    }

    private Node DFS(Node node, Map<Node, Node> isVisited) {
        if (node == null) {
            return node;
        }
        Node containNode;
        if ((containNode = isVisited.get(node)) != null) {
            return containNode;
        }
        Node cloneNode = new Node(node.val);
        isVisited.put(node, cloneNode);
        for (Node temp : node.neighbors) {
            //类似后序遍历 当DFS到底层向上返回时 再赋值
            //细节:!!!!!!!!!!!!!!!
            //遇见遍历过的node 返回的是cloneNode 这时是出现了环的情况 也要把这个遍历过的node记录下来 返回领接到当前层的node
            cloneNode.neighbors.add(DFS(temp, isVisited));
        }
        return cloneNode;
    }

    /**
     * 方法二:BFS
     * <p>
     * 注意：BFS时 需要在入队的时候当前curNode就必须把下一层与其领接的元素全部领接
     * 而不是在出队的时候再找出队元素的上一个元素 与其领接
     *
     * @param node
     * @return
     */
    public Node cloneGraphForBFS(Node node) {
        if (node == null) {
            return node;
        }
        Queue<Node> queueForBFS = new LinkedList<>();
        Queue<Node> queueForClone = new LinkedList<>();
        //由题意 我们以节点的val值（与索引值相同）作为key
        //Set<Integer> isVisited = new HashSet<>();
        Map<Integer, Node> isVisited = new HashMap<>();
        //入队时clone（获取值）
        Node cloneNode = new Node(node.val);
        //注意这里完全可以用一个Map<Node,node>解决问题<-----------------------!!!
        //key为原来的node value为cloneNode(克隆后的node)
        queueForBFS.add(node);
        queueForClone.add(cloneNode);
        isVisited.put(node.val, cloneNode);
        while (!queueForBFS.isEmpty()) {
            Node curNode = queueForBFS.poll();
            Node curClone = queueForClone.poll();
            for (Node temp : curNode.neighbors) {
                Node containNode;
                if ((containNode = isVisited.get(temp.val)) != null) {
                    curClone.neighbors.add(containNode);
                    continue;
                }
                Node clone = new Node(temp.val);
                curClone.neighbors.add(clone);
                queueForBFS.add(temp);
                queueForClone.add(clone);
                isVisited.put(temp.val, clone);
            }
        }
        return cloneNode;
    }


    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}

package NC.NO93;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/14 19:40
 * @description
 */
public class Solution {

    private Node head;
    private Node tail;
    private Map<Integer, Node> map;
    private int K;

    public Solution() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
    }

    public void insert(Node n, Node pos) {
        n.pre = pos;
        n.next = pos.next;
        pos.next = n;
        n.next.pre = n;
    }

    public void delete(Node n) {
        n.pre.next = n.next;
        n.next.pre = n.pre;
    }

    public void set(int key, int val) {
        Node n = new Node(key, val);
        if (map.containsKey(key)) {
            insert(n, head);
            Node oldN = map.get(key);
            delete(oldN);
            map.put(key, n);
            return;
        }
        if (map.size() >= K) {
            map.remove(tail.pre.key);
            delete(tail.pre);
            insert(n, head);
            map.put(key, n);
            return;
        }
        insert(n, head);
        map.put(key, n);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node n = map.get(key);
        delete(n);
        insert(n, head);
        return n.val;
    }

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU(int[][] operators, int k) {
        // write code here
        this.K = k;
        List<Integer> res = new ArrayList<>();
        for (int[] op : operators) {
            if (op[0] == 1) {
                set(op[1], op[2]);
            } else {
                res.add(get(op[1]));
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    public class Node {
        public int val;
        public int key;
        public Node pre;
        public Node next;

        public Node() {
        }

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

}

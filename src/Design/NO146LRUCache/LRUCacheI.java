package Design.NO146LRUCache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/20 0:56
 * @description
 */
public class LRUCacheI {

    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCacheI(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public static void main(String[] args) {
        LRUCacheI lruCacheI = new LRUCacheI(2);
        lruCacheI.put(1, 1);
        lruCacheI.put(2, 2);
        lruCacheI.get(1);
        lruCacheI.put(3, 3);
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n == null) {
            return -1;
        }
        remove(n);
        addFirst(n);
        return n.v;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            n.v = value;
            remove(n);
            addFirst(n);
            return;
        }
        Node n = new Node(key, value);
        map.put(key, n);
        if (map.size() <= capacity) {
            addFirst(n);
            return;
        }
        Node last = removeLast();
        map.remove(last.k);
        addFirst(n);
        return;
    }

    public Node removeLast() {
        Node last = tail.pre;
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
        return last;
    }

    public void addFirst(Node n) {
        n.next = head.next;
        head.next.pre = n;
        n.pre = head;
        head.next = n;
    }

    public void remove(Node n) {
        n.pre.next = n.next;
        n.next.pre = n.pre;
    }

    class Node {
        public int k;
        public int v;
        public Node pre;
        public Node next;

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }

        public Node() {
        }
    }

}

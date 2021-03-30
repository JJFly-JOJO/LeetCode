package Design.NO146LRUCache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/13 23:38
 * @description
 */
public class LRUCache {

    private int capacity;

    private HashMap<Integer, Integer> map = new HashMap<>();

    private LinkedList<Integer> lru = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            lru.remove(Integer.valueOf(key));
            lru.addFirst(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.put(key, value);
            lru.remove(Integer.valueOf(key));
            lru.addFirst(key);
            return;
        }
        map.put(key, value);
        if (lru.size() < capacity) {
            lru.addFirst(key);
            return;
        }
        map.remove(lru.removeLast());
        lru.addFirst(key);
    }

}

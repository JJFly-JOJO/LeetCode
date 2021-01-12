package Random.NO381InsertDeleteGetRandomO1Duplicatesallowed;

import java.util.*;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/10 16:30
 */
public class RandomizedCollection {

    /**
     * dict: key为插入的数值 hashSet中key存放数值对应索引下标
     */
    Map<Integer, LinkedHashSet<Integer>> dict = new HashMap<>();
    List<Integer> numSet = new ArrayList<>();
    Random random = new Random();

    public RandomizedCollection() {


    }

    public static void main(String[] args) {
        RandomizedCollection test = new RandomizedCollection();
        test.insert(1);
        test.remove(1);
        //test.insert(1);
    }

    public boolean insert(int val) {
        boolean existing = false;
        LinkedHashSet<Integer> indexSet = dict.getOrDefault(val, new LinkedHashSet<>());
        if (indexSet.isEmpty()) {
            existing = true;
        }
        numSet.add(val);
        indexSet.add(numSet.size() - 1);
        dict.put(val, indexSet);
        return existing;
    }

    public boolean remove(int val) {
        LinkedHashSet<Integer> indexSet = dict.get(val);
        if (indexSet == null || indexSet.isEmpty()) {
            return false;
        }
        //通过迭代器 获得set集合的第一个元素
        Integer removingIndex = indexSet.iterator().next();
        indexSet.remove(removingIndex);
        //在list集合中删除对应下标元素 用lastIndex下的元素替代
        int lastIndex = numSet.size() - 1;
        int lastVal = numSet.get(lastIndex);
        numSet.set(removingIndex, lastVal);
        numSet.remove(lastIndex);

        //更新字典dict中被改变元素的下标值
        indexSet = dict.get(lastVal);
        indexSet.add(removingIndex);
        indexSet.remove(lastIndex);

        //可以不需要此段 利用set不能放置重复值的特点 如果removingIndex等于lastIndex 那么先调用add方法时
        // 会因为重复值 而使得set集合中只有一个index
        /*if (lastIndex != 0) {
            //当前集合中只有一个元素
            indexSet.add(removingIndex);
        }*/
        return true;
    }

    public int getRandom() {
        return numSet.get(random.nextInt(numSet.size()));
    }
}

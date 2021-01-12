package Random.NO380InsertDeleteGetRandomO1;

import java.util.*;

public class RandomizedSet {

    /**
     * 一个数组 以页数（数组下标）-value组合
     * 一个字典 以value-页数（数组下标）组合
     * <p>
     * 技巧一:key存放数值 value存放数值对应的在数组的下标 就像字典一样 我们查需要的元素 得到这个元素所在的页数
     * 技巧二:随机取一个数弹出 这个随机我们可以随机数组的长度 得到的随机值就是数组下标 弹出下标对应的值 此时我们不用把后面的数
     * 一一挪动过来 这是线性复杂度 没有必要 只需要将末尾的元素放到此移出元素的下标处 末尾size-1 同时更新字典中值value
     * 对应的页数（数组下标）
     */
    private Map<Integer, Integer> map = new HashMap<>();
    private List<Integer> array = new ArrayList<>();
    private Random random = new Random();

    public RandomizedSet() {
    }

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "123");
        System.out.println(map.remove(1));

    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, array.size());
        //array.add(val);
        array.add(array.size(), val);
        return true;
    }

    public boolean remove(int val) {
        Integer removeIndex;
//        if (!map.containsKey(val)) {
//            return false;
//        }
//        removeIndex=map.get(val);
        if ((removeIndex = map.remove(val)) == null) {
            return false;
        }
        //排除只剩下一个元素的情况
        int size = array.size();
        if (size == 1) {
            array.remove(0);
            return true;
        }

        //array 尾部元素交换到被移除元素处
        int lastIndex = size - 1;
        int lastValue = array.get(lastIndex);
        map.put(lastValue, removeIndex);
        array.set(removeIndex, lastValue);
        array.remove(lastIndex);
        //map.remove(val);
        return true;
    }

    public int getRandom() {
        return array.get(random.nextInt(array.size()));
    }

}

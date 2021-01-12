package Random.NO384ShuffleanArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 思路一：每一次摸取元素 都是当前所有元素个数n的1/n概率（保证每一次摸取元素在当前情况下概率均等）
 * <p>
 * 提问:我们只保证当前元素在当前集合中以均等概率抽中 那么对于整个集合呢（每抽取一个元素出来 元素集合个数-1）
 * 由我们提出的概率策略 那么每一次抽取都算作一轮抽取（因为每次一抽取 元素集合总数是不变的）
 * 要证明概率相等 那么我们就要证明某一个元素e在第k轮的概率等于集合总元素个数的1/n
 *
 * 时间复杂度：在ArrayList每一次的remove时 会造成O(N平方)的时间复杂度
 *
 */
public class Solution {
    /**
     * 用两个数组 其中一个保存打乱后的结果 一个保存初始结果
     */
    private int[] curArray;
    private int[] originalArray;
    private Random random;

    public Solution(int[] nums) {
        curArray = nums;
        originalArray = nums.clone();
        random = new Random();
    }

    public int[] reset() {
        curArray = originalArray;
        originalArray = originalArray.clone();
        return curArray;
    }

    private List<Integer> getCopyArray() {
        List<Integer> copyList = new ArrayList<>();
        //注意是获取当前数组 而不是原始数组 因为可能会对数组多次洗牌
        for (int temp : curArray) {
            copyList.add(temp);
        }
        return copyList;
    }

    public int[] shuffle() {
        List<Integer> arrayList = getCopyArray();
        for (int i = 0; i < curArray.length; i++) {
            //nextInt 得到的是0到n-1的伪随机数
            int removeIndex = random.nextInt(arrayList.size());
            curArray[i] = arrayList.get(removeIndex);
            //当前轮结束 取出抽出的元素
            arrayList.remove(removeIndex);
        }
        return curArray;
    }
}

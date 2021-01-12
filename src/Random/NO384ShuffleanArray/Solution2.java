package Random.NO384ShuffleanArray;

import java.util.Random;

/**
 * 首先 能否不用ArrayList copy一次数组
 * 其次 每次拿出一个元素 我们是否需要真的找到这个元素然后取出？
 * 我们完全可以把当前选中的元素放到数组队首 队首元素放回到当前元素的位置 我们下一次寻找时 只从队首元素（已经是选中的元素）
 * 的后一个元素开始随机寻找
 */
public class Solution2 {

    Random random = new Random();
    private int[] curArray;
    private int[] originalArray;

    public Solution2(int[] nums) {
        curArray = nums;
        originalArray = nums.clone();
        //random = new Random();
    }

    public int[] reset() {
        curArray = originalArray;
        originalArray = originalArray.clone();
        return curArray;
    }

    public int[] shuffle() {
        //最后一个元素不需要随机抽取
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!<----------------------不要在每次for循环的<语句中加入运算 例如 length-1
        for (int i = 0; i < curArray.length; i++) {
            int chooseIndex = random.nextInt(curArray.length - i) + i;
            //exchange
            int temp = curArray[i];
            curArray[i] = curArray[chooseIndex];
            curArray[chooseIndex] = temp;
        }
        return curArray;
    }

}

package Array.NO307RangeSumQueryMutable;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/22 19:00
 * @description
 */
public class NumArray {

    private int N;

    private int[] treeArray;

    private int[] array;

    public NumArray(int[] nums) {
        int nLen = nums.length;
        array = new int[nLen];
        N = nLen + 1;
        treeArray = new int[N];
        for (int i = 0; i < nLen; i++) {
            update(i, nums[i]);
        }
    }

    public void update(int i, int val) {
        int temp = val;
        val = val - array[i];
        array[i] = temp;
        i++;
        while (i < N) {
            treeArray[i] += val;
            i += lowBit(i);
        }
    }

    public int sumRange(int i, int j) {
        return getSum(++j) - getSum(i);
    }

    private int getSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += treeArray[i];
            i -= lowBit(i);
        }
        return sum;
    }

    private int lowBit(int i) {
        return i & (-i);
    }

}

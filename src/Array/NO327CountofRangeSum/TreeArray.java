package Array.NO327CountofRangeSum;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/20 10:04
 * @description
 */
public class TreeArray {

    private int length;
    private int[] treeArray;

    TreeArray(int s) {
        length = s;
        treeArray = new int[s + 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        TreeArray treeArray = new TreeArray(nums.length);
        //initialize
        //初始树状数组相当于每个元素都为0 然后我们遍历我们原始数组
        //每加入一个元素 就更新整个树状数组
        for (int i = 0; i < nums.length; i++) {
            treeArray.update(i + 1, nums[i]);
        }
        //获取和
        System.out.println(treeArray.getSum(nums.length));
    }

    /**
     * 获取数从最低位到最高位连续的0个数k 返回以2的k次方的值返回
     * i我们只讨论非负情况 否则没有意义
     */
    private int lowBit(int i) {
        return i & (-i);
    }

    /**
     * 在原来的数组i处（注意是下标从1开始）增加k 更新树状数组
     *
     * @param i
     * @param k
     */
    public void update(int i, int k) {
        while (i <= length) {
            treeArray[i] += k;
            i += lowBit(i);
        }
    }

    /**
     * 求原来数组1~i的和（数组下标从1开始）
     *
     * @param i
     * @return
     */
    public int getSum(int i) {
        int res = 0;
        while (i > 0) {
            res += treeArray[i];
            i -= lowBit(i);
        }
        return res;
    }

}
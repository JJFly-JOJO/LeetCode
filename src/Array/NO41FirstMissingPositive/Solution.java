package Array.NO41FirstMissingPositive;

public class Solution {
    /**
     * 要求 常数级别的空间 那么我们不能创建一个同样大小的数组 我们只能利用原来的数组
     * 首先我们明确解决的思路 对于1-n个数 我们如果对应的放到nums[n]的数组中，1对应下标0 2对应下标1...以此类推
     * 如果我们这nums中n个元素都放满了 那么说明缺失的第一个正数在n+1这个正数上 如果在nums中就存在第一个没有被
     * 赋值的元素 那么结果就是这
     * <p>
     * 如何就在原来的数组上进行标记呢（我们不可能把所有数排序后重新放回数组）？
     * 方法一：互相hash
     * 先假设没有负数，如果当前下标1对应的元素是3 那么我们就到下标2（3）的元素下把元素置为负数，
     * 表示2（3）下标已经遍历到（当然我们实际的遍历还是从左到右遍历数组），改为负数的好处，不会破坏
     * 此元素值，也就是说，当我们走到下标2（3）的时候，只用对此元素取绝对值，就能找到另外对应的下标，
     * 然后置为负数。
     * <p>
     * 如果从值为3的元素跳转到下标2时，对应的元素值是负数怎么办？我们只需要把负数改为n+1（正数（正数表示没有遍历到） 并且在n长度之外）
     * 这样就能保证下一次走到这个负数元素上时，取绝对值，是不会索引到对应的下标下的（超出数组范围）
     * <p>
     * 注意！！！！！！！！！！！！测试数据有重复元素 也就是[1111111111]
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        //首先要对负数进行处理 否则负数与遍历到的元素变为负数混在一起
        for (int i = 0; i < length; i++) {
            if (nums[i] <= 0) {
                nums[i] = length + 1;
            }
        }

        for (int i = 0; i < length; i++) {
            int temp = Math.abs(nums[i]);
            if (temp > 0 && temp <= length) {
                nums[temp - 1] = -Math.abs(nums[temp - 1]);
            }
        }

        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return length + 1;
    }

    /**
     * 方法二：交换元素
     * 我们把当前元素放到正确的下标下 注意测试数据有相同元素 也就是1111111的时候 需要停止交换
     *
     * @param nums
     * @return
     */
    public int firstMissingPositiveForExchange(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            //int curIndex=0;
            int curNum = nums[i];
            while (curNum > 0 && curNum <= length && curNum != i + 1) {
                int nextHopNum = nums[curNum - 1];
                //针对11111的情况
                if (nextHopNum == curNum) {
                    break;
                }
                nums[curNum - 1] = curNum;
                nums[i] = nextHopNum;
                curNum = nextHopNum;
            }
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return length + 1;
    }

}

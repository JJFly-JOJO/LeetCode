package WeekContest.DoubleWeek.NO35;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/21 14:23
 * <p>
 * -----------差分数组---------扫描线（相当于一条线对一段子区间进行相加）-----
 */
public class Solution3 {

    /**
     * 该方法存在超时情况----超时原因：遍历request时 进行索引+1 时间复杂度是request的O（NM）（M对应每个request的区间长度）
     *
     * @param nums
     * @param requests
     * @return
     */
    public int maxSumRangeQueryTimeOut(int[] nums, int[][] requests) {
        int length = nums.length;
        Arrays.sort(nums);
        //贪心 找到重复最多的index
        int[] indexToCountMap = new int[length];
        int row = requests.length;
        //int col = requests[0].length;
        for (int i = 0; i < row; i++) {
            int end = requests[i][1];
            for (int j = requests[i][0]; j <= end; j++) {
                indexToCountMap[j]++;
            }
        }
        long res = 0L;
        Arrays.sort(indexToCountMap);
        for (int i = indexToCountMap.length - 1; i >= 0 && indexToCountMap[i] > 0; i--) {
            res += indexToCountMap[i] * nums[i];
        }
        return (int) (res % (1000000000 + 7));
    }

    /**
     * ---------差分数组--------
     * 有上面的解法超时我们可以看到 我们每一次request[row]都在进行许多次+1操作 这是很费时的
     * 我们又看到 每一次的+1操作都是在对一个区间的每个数进行加1操作，我们能不能找到一个标记要进行+1区间的方法
     * 最后再对标记完成的各个区间进行+n操作？
     * <p>
     * ---------差分数组的介绍----------
     * 例如一个数组：
     * 序号： 0 1 2 3 4 5
     * 值：   0 2 5 4 9 3
     * <p>
     * 如果我们用一个differ[]数组来记录数组前后两个元素的差值 那么该数组可以写成
     * 序号： 0 1 2 3 4 5
     * 值：   0 2 5 4 9 3
     * 差值： · 2 3 -1 5 -6
     * <p>
     * 那么我们要得到这个differ数组某个index对应的真实值呢？
     * 我们从第一个元素开始 这里第一个元素值为0 那么我们要得到index=3的元素值 我们就可以从differ的index=1开始累加差值
     * 0+2+3-1=4
     * <p>
     * -----------差分数组的优势！！----------
     * 如果我们对区间进行增加减少同一个值（注意是同一个值！！）
     * 例如对区间1-4的元素进行加2操作 那么原来的index数组 我们是对1-4这个区间的每个数进行+2操作
     * 思考：如果这个区间很大呢？去过我们要对区间进行多次增减操作呢？------------->计算量会暴增！！！！
     * <p>
     * 如果是差分数组的话  该1-4区间内的元素同时增减 其实其差值是没有变的 变化的是1（区间端点处下标）和5（区间结尾后一个下标）
     * 序号： 0 1 2 3 4 5
     * 值：   0 2+2 5+2 4+2 9+2 3
     * 差值： ·2+2 3 -1 5 -6-2
     * 可以看到差分数组的优势------对于区间多次增减的情况 我们只需要记录两个端点的改变值即可 而最后我们要得到最终值
     * 只用进行一次O(n)复杂度的遍历即可！！！！！！
     * <p>
     * <p>
     * -----------------差分数组就能够实现对区间的多次修改（增减）操作-----------
     * 我们的记录索引次数的index[]数组不再记录索引次数 而是记录前后两个数组元素的差值！！
     * 初始状态每个对应索引次数都是0 也就是differ[]的初始状态都为0 每个索引次数都为0 前后差值为0
     * 然后我们遍历每个request[]  每个request[]给出了我们引用了的区间的两个端点值 那么我们就更新
     * 我们的differ 在begin端点处+1 在end+1处-1
     *
     * @param nums
     * @param requests
     * @return
     */
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int length = nums.length;
        Arrays.sort(nums);
        //贪心 找到重复最多的index
        //差分数组 长度加1 防止数组越界
        int[] differ = new int[length + 1];
        int row = requests.length;
        for (int i = 0; i < row; i++) {
            differ[requests[i][0]]++;
            differ[requests[i][1] + 1]--;
        }
        //将差分数组转化为实际数组对应索引个数
        for (int i = 1; i < length; i++) {
            differ[i] += differ[i - 1];
        }
        long res = 0L;
        Arrays.sort(differ);
        for (int i = length - 1; i >= 0 && differ[i + 1] > 0; i--) {
            res += differ[i + 1] * nums[i];
        }
        return (int) (res % (1000000000 + 7));
    }

}
